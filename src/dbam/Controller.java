package dbam;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBAM
 */
@WebServlet("/DBAM")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("/text.html");
		//response.getWriter().println("<html><body> La toute première page de DBAM snif. <html/></body>");

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
		case "question_validation" :
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
			disp = request.getRequestDispatcher("erreur404.jsp");
			disp.forward(request, response);
			break;
		}

	}

}
