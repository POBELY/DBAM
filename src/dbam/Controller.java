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

		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
			case "delete" :
				break;
			default:
				break;
			}
			return;
		}
		
		String pseudo;
		String mdp;
		String mail;
		String source = request.getParameter("source");
		HttpSession  session;
		switch(source) {
		case "inscription" :
			pseudo = request.getParameter("pseudo");
			mdp = request.getParameter("mdp");
			String mdp_confirm = request.getParameter("mdp_confirm");
			mail = request.getParameter("mail");
			if (mdp_confirm.equals(mdp)&& mdp.length() >=4 && !facade.pseudoPris(pseudo)) {
				int id = facade.addUtilisateur(pseudo, mdp, mail);
				session = request.getSession();
				session.setAttribute(PSEUDO_SESSION, pseudo);
				session.setAttribute(ID_SESSION, id);
			} else {				
				request.setAttribute("destination","inscription"); // je suis pas sure
			}
	
			break;
		case "connexion" :
			pseudo = request.getParameter("pseudo");
			mdp = request.getParameter("mdp");
			if (facade.connexionPossible(pseudo, mdp)) {
				session = request.getSession();
				session.setAttribute(PSEUDO_SESSION, pseudo);// ajouter l'id 
			}else{
				request.setAttribute("destination","connexion"); // je suis pas sure
			}
			break;
		case "deconnexion" :
			session = request.getSession();
			session.setAttribute(PSEUDO_SESSION, null);
			break;
		default :
			break;
		}
		String destination = request.getParameter("destination");
		RequestDispatcher disp;
		
		
		switch(destination) {
		case "accueil" :
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
			session = request.getSession();
			pseudo = (String) session.getAttribute(PSEUDO_SESSION);
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
			// Calcul nb question/checkpoint faux, etat Fait non correct, ajout attribut liste scenarios terminés par l'utilisateur pour etat fait OUI
			if (source.equals("accueil")) {
				request.setAttribute("scenariosPublic", facade.getScenariosPublic());
				session = request.getSession();
				pseudo = (String) session.getAttribute(PSEUDO_SESSION);
				if (pseudo != null) {
					int id = facade.getIDUtilisateur(pseudo);
					request.setAttribute("scenariosSessions", facade.getScenariosEnCours(id));
				}
					
			}
			disp = request.getRequestDispatcher("scenarios.jsp");
			disp.forward(request, response);
			break;
		case "mes_scenarios" :
			if (source.equals("accueil")) {
				session = request.getSession();
				pseudo = (String) session.getAttribute(PSEUDO_SESSION);
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

	public static String getAttSessionUser() {
		return PSEUDO_SESSION;
	}

	  

}
