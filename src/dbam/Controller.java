package dbam;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;



/**
 * Servlet implementation class DBAM
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private Facade facade;
	
	private static final String PSEUDO_SESSION = "pseudoS";
	private static final String ID_SESSION ="idS";
	
    public Controller() {
        super();        
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("/text.html");
		RequestDispatcher disp;

		HttpSession session;
		int sessionID;
		String destination = request.getParameter("destination");		
		String source = request.getParameter("source");
		String action = request.getParameter("action");
		String deconnexion = request.getParameter("deconnexion");

		/* Les actions sont des actions qu'effectue la Servlet si on lui demande, mais elles ne consistent pas en 
		 * un changement de page. */
		if(action != null) {
			switch(action) {
			case "ajouter" :
				String type = request.getParameter("type");
				if(type != null) {
					switch (type) {
					case "groupe":
						break;
					default:
						break;
					}
				}
				break;
			case "delete" :
				break;
			default:
				break;
			}
			return;
		}
		
		/* Si on veut se déconnecter, ça ne doit pas influencer ce que l'on veut faire */
		if(deconnexion != null && deconnexion.equals("oui")) {
			// On crée une session vide pour le joueur
			session = request.getSession();
			session.setAttribute(PSEUDO_SESSION, null);
			
			// On l'amène à l'accueil
			disp = request.getRequestDispatcher("accueil.jsp");
			disp.forward(request, response);
			return;
		}
		
		/* Tout le code doit se trouver dans ce switch ! <3 */
		switch(destination) {
		case "accueil" :
			// Si on vient de la page d'inscription
			if(source != null && source.equals("inscription")) {
				String pseudo = request.getParameter("pseudo");
				String mdp = request.getParameter("mdp");
				String mdp_confirm = request.getParameter("mdp_confirm");
				String mail = request.getParameter("mail");
				// Si on a échoué a créer la session
				if (mdp.length() < 4) {
					// On re-redirige vers la page d'inscription
					request.setAttribute("erreur",  "Le mot de passe est trop court ! (au moins 4 characters)");
					disp = request.getRequestDispatcher("inscription.jsp");
					disp.forward(request, response);
				} else if(!mdp_confirm.equals(mdp)){
					// On re-redirige vers la page d'inscription
					request.setAttribute("erreur",  "Les mots de passe doivent être égaux !");
					disp = request.getRequestDispatcher("inscription.jsp");
					disp.forward(request, response);
				} else if(facade.pseudoPris(pseudo)){
					// On re-redirige vers la page d'inscription
					request.setAttribute("erreur",  "Ce pseudo est déjà pris !");
					disp = request.getRequestDispatcher("inscription.jsp");
					disp.forward(request, response);
				} else {
				// Si on a réussi à créer la session
					// On crée la session !
					int id = facade.addUtilisateur(pseudo, mdp, mail);
					session = request.getSession();
					session.setAttribute(PSEUDO_SESSION, pseudo);
					session.setAttribute(getIdSession(), id);
				}
			// Si on vient de la page de connexion
			} else if(source.equals("connexion")) {
				String pseudo = request.getParameter("pseudo");
				String mdp = request.getParameter("mdp");
				String mdp_confirm = request.getParameter("mdp_confirm");
				String mail = request.getParameter("mail");
				// Si on peut se connecter
				if (facade.connexionPossible(pseudo, mdp)) {
					// On se connecte !
					session = request.getSession();
					session.setAttribute(PSEUDO_SESSION, pseudo);
					int id = facade.getIDUtilisateur(pseudo);
					session.setAttribute(getIdSession(), id);
				// Si on peut pas se connecter
				}else{
					// On re-redirige vers la page de connexion
					// TODO : rajouter un paramètre pour que l'inscription affiche que ça n'a pas marché !
					disp = request.getRequestDispatcher("connexion.jsp");
					disp.forward(request, response);
				}
			}
			disp = request.getRequestDispatcher("accueil.jsp");
			disp.forward(request, response);
			break;
		case "inscription" :
			disp = request.getRequestDispatcher("inscription.jsp");
			disp.forward(request, response);
			break;
		case "connexion" :
			disp = request.getRequestDispatcher("connexion.jsp");
			disp.forward(request, response);
			break;
		case "mdp_oublie" :
			disp = request.getRequestDispatcher("mdp_oublie.jsp");
			disp.forward(request, response);
			break;
		case "mdp_oublie_validation" :
			disp = request.getRequestDispatcher("mdp_oublie_validation.jsp");
			disp.forward(request, response);
			break;
		case "checkpoint" :
			// TODO !
			if (source.equals("scenarios") || source.equals("mes_scenarios")) {
				int scenarioID = Integer.parseInt(request.getParameter("scenarioID"));
				session  = (HttpSession) request.getSession();
				String pseudo = (String) session.getAttribute(PSEUDO_SESSION);
				int id = facade.getIDUtilisateur(pseudo);
				sessionID = facade.addSession(scenarioID, id);
				Session sessionJeu = facade.getSession(sessionID);
				request.setAttribute("Session", sessionJeu);
				Scenario s = facade.getScenario(scenarioID);
				session.setAttribute("id_scenario", scenarioID);
			}
			if (source.equals("checkpoint_fin")) {
				sessionID = Integer.parseInt(request.getParameter("sessionID"));
				Session sessionJeu = facade.getSession(sessionID);
				request.setAttribute("Session", sessionJeu);
				sessionJeu.setCheckpointCourant(sessionJeu.getCheckpointCourant().getSuivant());
				
			}
			if (source.equals("accueil")) {
				if (facade.pasDeScenario()) {
					PremierScenario prems = new PremierScenario(facade);
					int scenarioID = prems.ajoutScenarioTest();
					session  = (HttpSession) request.getSession();
					String pseudo = (String) session.getAttribute(PSEUDO_SESSION);
					if (pseudo == "anonymous") {
						facade.addUtilisateur(pseudo, "0000", "0@0");
					}
					int id = facade.getIDUtilisateur(pseudo);
					sessionID = facade.addSession(scenarioID, id);
					Session sessionJeu = facade.getSession(sessionID);
					request.setAttribute("Session", sessionJeu);

				}
			}
			disp = request.getRequestDispatcher("checkpoint.jsp");
			disp.forward(request, response);
			break;
		case "question" :
			sessionID = Integer.parseInt(request.getParameter("sessionID"));
			Session sessionJeu = facade.getSession(sessionID);
			
			request.setAttribute("Session", sessionJeu);
			System.out.println("question dans le controller");
			disp = request.getRequestDispatcher("question.jsp");
			disp.forward(request, response);
			break;
		case "question_felicitation" :
			disp = request.getRequestDispatcher("question_validation.jsp");
			disp.forward(request, response);
			break;
		case "scenarios" :

			// Calcul nb question/checkpoint faux, etat Fait non correct, ajout attribut liste scenarios terminés par l'utilisateur pour etat fait OUI
			{
			request.setAttribute("scenariosPublic", facade.getScenariosPublic());
			session = request.getSession();
			String pseudo = (String) session.getAttribute(PSEUDO_SESSION);
			if (pseudo != null) {
				int id = facade.getIDUtilisateur(pseudo);
				request.setAttribute("scenariosSessions", facade.getScenariosEnCours(id));
				//request.setAttribute("scenariosTermines", facade.getScenariosTermines(id));
			}
			}
			disp = request.getRequestDispatcher("scenarios.jsp");
			disp.forward(request, response);
			break;
		case "mes_scenarios" :
			{
			if(source.equals("milieu_creer_scenario")) {
				String visibilite = request.getParameter("visibilite");
				int id_scenario = Integer.parseInt(request.getParameter("id_scenario"));
				Scenario s = facade.getScenario(id_scenario);
				String text_v = request.getParameter("text_victoire");
				facade.setTextVictoireScenario(id_scenario, text_v);
				if(visibilite.equals("privee")) {
					facade.setStatutScenario(id_scenario, Scenario.Statut.PRIVE);
				} else {
					facade.setStatutScenario(id_scenario, Scenario.Statut.PUBLIC);
				}
			}
			session  = (HttpSession) request.getSession();
			String pseudo = (String) session.getAttribute(PSEUDO_SESSION);
			int id = facade.getIDUtilisateur(pseudo);
			request.setAttribute("mesScenarios", facade.getMesScenario(id));
			disp = request.getRequestDispatcher("mes_scenarios.jsp");
			disp.forward(request, response);
			}
			break;
		case "debut_creer_scenario" :
			disp = request.getRequestDispatcher("debut_creer_scenario.jsp");
			disp.forward(request, response);
			break;
		case "milieu_creer_scenario" :
			if (source.equals("debut_creer_scenario")) {
				String nomScenario = request.getParameter("nom_scenario");
				String descriptionScenario = request.getParameter("description");
				session  = (HttpSession) request.getSession();
				String pseudo = (String) session.getAttribute(PSEUDO_SESSION);
				int id = facade.getIDUtilisateur(pseudo);
				// vérifier que le scénario n'existe pas déjà TODO
				int idScenario = facade.addScenario(nomScenario, descriptionScenario, "", id, Scenario.Statut.PRIVE);
				Scenario scenario = facade.getScenario(idScenario);
				request.setAttribute("scenario", scenario);
				request.setAttribute("idScenario", idScenario);
			}
			if (source.equals("creer_checkpoint")) {
				String idScenario = request.getParameter("idScenario");
				String nbVictReq = request.getParameter("nb_victoires");
				String nbDefMax = request.getParameter("nb_defaites");
				String texteVictoire = request.getParameter("text_reussite");
				String texteDefaite = request.getParameter("text_defaite");
				facade.addCheckpoint(Integer.parseInt(nbVictReq), Integer.parseInt(nbDefMax), texteVictoire, texteDefaite, Integer.parseInt(idScenario));
				// Ici idScenario est null, gérer dans les boutons générés, le champ hiden
				request.setAttribute("idScenario", Integer.parseInt(idScenario));
				Scenario scenario = facade.getScenario(Integer.parseInt(idScenario));
				request.setAttribute("scenario", scenario);

			}
			disp = request.getRequestDispatcher("milieu_creer_scenario.jsp");
			disp.forward(request, response);
			break;
		case "creer_checkpoint" :
			if (source.equals("milieu_creer_scenario")) {
				String idScenario = request.getParameter("idScenario");
				request.setAttribute("idScenario", Integer.valueOf(idScenario));
			}
			disp = request.getRequestDispatcher("creer_checkpoint.jsp");
			disp.forward(request, response);
			break;
		case "explication_scenario" :
			disp = request.getRequestDispatcher("explication_scenario.jsp");
			disp.forward(request, response);
			break;
		case "creer_question" :
			if (source.equals("milieu_creer_scenario")) {
				String idScenario = request.getParameter("idScenario");
				System.out.println("ID Scenario : " + idScenario);
				request.setAttribute("idScenario", Integer.valueOf(idScenario));
			}
			disp = request.getRequestDispatcher("creer_question.jsp");
			disp.forward(request, response);
			break;
		case "suite_question" :
			System.out.println("suite_question dans le controller");
			sessionID = Integer.parseInt(request.getParameter("sessionID"));
			Session sessionCourante = facade.getSession(sessionID);
			int choixID = Integer.parseInt(request.getParameter("choixID"));
			System.out.println("-----------------------------------------------------------------------------------------choixID dans le controleur "+ choixID);
			String desti = facade.jouer(choixID, sessionCourante);
			System.out.println("DESTINATIONNNNNNN -> " + desti);
			request.setAttribute("Session", sessionCourante);
			disp = request.getRequestDispatcher(desti);
			disp.forward(request, response);
			break;
		default :
			System.out.println("Cette destination n'est pas connue !");
			disp = request.getRequestDispatcher("erreur404.jsp");
			disp.forward(request, response);
			break;
		}
	}

	public static String getIdSession() {
		return ID_SESSION;
	}
}
