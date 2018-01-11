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
			HttpSession session;
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
				HttpSession session;
				// Si on a réussi
				if (mdp_confirm.equals(mdp) && mdp.length() >=4 && !facade.pseudoPris(pseudo)) {
					// On crée la session !
					int id = facade.addUtilisateur(pseudo, mdp, mail);
					session = request.getSession();
					session.setAttribute(PSEUDO_SESSION, pseudo);
					session.setAttribute(ID_SESSION, id);
				// Si on a échoué a créer la session
				} else {				
					// On re-redirige vers la page d'inscription
					// TODO : rajouter un paramètre pour que l'inscription affiche que ça n'a pas marché !
					disp = request.getRequestDispatcher("inscription.jsp");
					disp.forward(request, response);
				}
			// Si on vient de la page de connexion
			} else if(source.equals("connexion")) {
				String pseudo = request.getParameter("pseudo");
				String mdp = request.getParameter("mdp");
				String mdp_confirm = request.getParameter("mdp_confirm");
				String mail = request.getParameter("mail");
				HttpSession session;
				// Si on peut se connecter
				if (facade.connexionPossible(pseudo, mdp)) {
					// On se connecte !
					session = request.getSession();
					session.setAttribute(PSEUDO_SESSION, pseudo);
					int id = facade.getIDUtilisateur(pseudo);
					session.setAttribute(ID_SESSION, id);
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
			disp = request.getRequestDispatcher("checkpoint.jsp");
			disp.forward(request, response);
			break;
		case "question" :
			disp = request.getRequestDispatcher("question.jsp");
			disp.forward(request, response);
			break;
		case "question_felicitation" :
			disp = request.getRequestDispatcher("question_validation.jsp");
			disp.forward(request, response);
			break;
		case "scenarios" :
			disp = request.getRequestDispatcher("scenarios.jsp");
			disp.forward(request, response);
			break;
		case "mes_scenarios" :
			disp = request.getRequestDispatcher("mes_scenarios.jsp");
			disp.forward(request, response);
			break;
		case "debut_creer_scenario" :
			disp = request.getRequestDispatcher("debut_creer_scenario.jsp");
			disp.forward(request, response);
			break;
		case "milieu_creer_scenario" :
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
		default :
			System.out.println("Cette destination n'est pas connue !");
			disp = request.getRequestDispatcher("/erreur404.jsp");
			disp.forward(request, response);
			break;
		}
	}
}
