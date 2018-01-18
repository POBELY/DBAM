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
		String destination = request.getParameter("destination");		
		String source = request.getParameter("source");
		String action = request.getParameter("action");
		String deconnexion = request.getParameter("deconnexion");

		/* Les actions sont des actions qu'effectue la Servlet si on lui demande, mais elles ne consistent pas en 
		 * un changement de page. */
		if(action != null) {
			switch(action) {
			// TODO !!!
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

					int id1 = facade.addScenario("s1", "s1", "yes", 1, Scenario.Statut.PUBLIC);
					int id2 = facade.addCheckpoint(1, 1, "bonjour", "dommage", id1);
					int id3 = facade.addQuestion("pourquoi?", id2);
					int id4 = facade.addReponse("Oui", id3);
					int id5 = facade.addReponse("Non", id3);
					int idd1 = facade.addQuestion("pourquoi2?", id2);
					int idd2 = facade.addReponse("Oui2", idd1);
					int idd3 = facade.addReponse("Non2", idd1);
					int id6 = facade.addCheckpoint(1, 1, "on continue", "raté", id1);
					int id7= facade.addQuestion("Tu préfères qui ?", id6);
					int id8 = facade.addReponse("Maman", id7);
					int id9 = facade.addReponse("Papa", id7);
							
					int id10 = facade.addSession(id1, 1);

					int id11 = facade.addScenario("s2", "s2", "yes", 1, Scenario.Statut.PUBLIC);
					int id12 = facade.addCheckpoint(1, 1, "bienvenue", "echec", id11);
					int id13 = facade.addQuestion("?", id12);
					int id14 = facade.addReponse("0", id13);
					int id15 = facade.addReponse("1", id13);
					
					facade.addScenarioTermine(1, 2);

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
			//session = request.getSession();
			//pseudo = (String) session.getAttribute(PSEUDO_SESSION);
			// PS : il faut pas tout à fait faire comme ça ^^'
			// là en fait ça crée une toute nouvelle session à partir de rien
			if (source.equals("scenarios") || source.equals("mes_scenarios")) {
				String scenarioID = request.getParameter("scenarioID");
				session  = (HttpSession) request.getSession();
				String pseudo = (String) session.getAttribute(PSEUDO_SESSION);
				int id = facade.getIDUtilisateur(pseudo);
				int sessionID = facade.addSession(Integer.parseInt(scenarioID), id);
				Session sessionJeu = facade.getSession(sessionID);
				request.setAttribute("Session", sessionJeu);
			}
			if (source.equals("checkpoint_fin")) {
				int sessionID = Integer.parseInt((String)request.getAttribute("sessionID"));
				Session sessionCourante = facade.getSession(sessionID);
				sessionCourante.setCheckpointCourant(sessionCourante.getCheckpointCourant().getSuivant());
				
			}
			disp = request.getRequestDispatcher("checkpoint.jsp");
			disp.forward(request, response);
			break;
		case "question" :
			if (source.equals("checkpoint")) {
				String sessionID = request.getParameter("sessionID");
				Session sessionJeu = facade.getSession(Integer.parseInt(sessionID));
				request.setAttribute("Session", sessionJeu);
			}
			disp = request.getRequestDispatcher("question.jsp");
			disp.forward(request, response);
			break;
		case "question_felicitation" :
			disp = request.getRequestDispatcher("question_validation.jsp");
			disp.forward(request, response);
			break;
		case "scenarios" :
			// Calcul nb question/checkpoint faux, etat Fait non correct, ajout attribut liste scenarios terminés par l'utilisateur pour etat fait OUI
			if (source.equals("accueil")) {
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
			if (source.equals("accueil")) {
				session  = (HttpSession) request.getSession();
				String pseudo = (String) session.getAttribute(PSEUDO_SESSION);
				int id = facade.getIDUtilisateur(pseudo);
				request.setAttribute("mesScenarios", facade.getMesScenario(id));
			}
			disp = request.getRequestDispatcher("mes_scenarios.jsp");
			disp.forward(request, response);
			break;
		case "debut_creer_scenario" :
			disp = request.getRequestDispatcher("debut_creer_scenario.jsp");
			disp.forward(request, response);
			break;
		case "milieu_creer_scenario" :
			if (source.equals("debut_creer_scenario")) {
				String nomScenario = request.getParameter("nom_scenario");
				String descriptionScenario = request.getParameter("description");
				String statut = request.getParameter("vivibilite");
				session  = (HttpSession) request.getSession();
				String pseudo = (String) session.getAttribute(PSEUDO_SESSION);
				int id = facade.getIDUtilisateur(pseudo);
				//Scenario.Statut a = Scenario.Statut.valueOf(statut);
				//System.out.println("*****************************************************************************************************************************************************************************************************************************");
				//System.out.println(a);
				int idScenario = facade.addScenario(nomScenario, descriptionScenario, "", id, Scenario.Statut.PRIVE);
				Scenario scenario = facade.getScenario(idScenario);
				request.setAttribute("scenario", scenario);
			}
			disp = request.getRequestDispatcher("milieu_creer_scenario.jsp");
			disp.forward(request, response);
			break;
		case "creer_checkpoint" :
			disp = request.getRequestDispatcher("creer_checkpoint.jsp");
			disp.forward(request, response);
			break;
		case "explication_scenario" :
			disp = request.getRequestDispatcher("explication_scenario.jsp");
			disp.forward(request, response);
			break;
		case "creer_question" :
			disp = request.getRequestDispatcher("creer_question.jsp");
			disp.forward(request, response);
			break;
		case "suite_question" :
			int sessionID = Integer.parseInt(request.getParameter("sessionID"));
			Session sessionCourante = facade.getSession(sessionID);
			int choixID = Integer.parseInt(request.getParameter("choixID"));
			disp = request.getRequestDispatcher(facade.jouer(choixID, sessionCourante));
			request.setAttribute("session", sessionCourante);
			disp.forward(request, response);
		default :
			System.out.println("Cette destination n'est pas connue !");
			disp = request.getRequestDispatcher("/erreur404.jsp");
			disp.forward(request, response);
			break;
		}
	}

	public static String getIdSession() {
		return ID_SESSION;
	}
}
