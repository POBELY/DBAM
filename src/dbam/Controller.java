package dbam;

import java.io.IOException;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class DBAM
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private Facade facade;
	
	private static final String ATT_SESSION_USER = "SessionUtilisateur";
	
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
		String pseudo;
		String mdp;
		String mail;
		String source = request.getParameter("source");
		System.out.println("Ceci est l'opération effectuée" + source);
		HttpSession  session;
		switch(source) {
		case "inscription" :
			pseudo = request.getParameter("pseudo");
			mdp = request.getParameter("mdp");
			String mdp_confirm = request.getParameter("mdp_confirm");
			mail = request.getParameter("mail");
			if (mdp_confirm.equals(mdp)) {
				facade.addUtilisateur(pseudo, mdp, mdp_confirm, mail);
				session = request.getSession();
				session.setAttribute(ATT_SESSION_USER, pseudo);
			} else {				
				request.setAttribute("destination","inscription"); // je suis pas sure
			}
	
			break;
		case "connexion" :
			pseudo = request.getParameter("pseudo");
			mdp = request.getParameter("mdp");
			if (facade.connexionPossible(pseudo, mdp)) {
				session = request.getSession();
				session.setAttribute(ATT_SESSION_USER, pseudo);
			}else{
				request.setAttribute("destination","connexion"); // je suis pas sure
			}
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
		case "scenario" :
			disp = request.getRequestDispatcher("scenario.jsp");
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
			System.out.println("Cette destination n'est pas connu !");
			disp = request.getRequestDispatcher("/erreur404.jsp");
			disp.forward(request, response);
			break;
		}
		
		

	}

	public static String getAttSessionUser() {
		return ATT_SESSION_USER;
	}

}
