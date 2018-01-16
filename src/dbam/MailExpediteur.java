package dbam;

import java.io.IOException;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import javax.ejb.EJB;
import javax.mail.*;
import javax.mail.internet.*;


/**
 * Servlet implementation class MailExpediteur
 */
@WebServlet(description = "Permet d'envoyer des mails :) youpi", urlPatterns = { "/MailExpediteur" })
public class MailExpediteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private Facade facade;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailExpediteur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String mail = request.getParameter("mail");
	      System.out.println(mail);
	      List<Utilisateur> users = facade.recupererUtilisateurs(mail);
	      Properties props = new Properties();
	      props.put("mail.smtp.host", "localhost");
	      props.put("mail.from", "dontBeAMouton@gmail.com");
	      javax.mail.Session sess = javax.mail.Session.getInstance(props, null);
	      try 
	      {	  for (Utilisateur u : users) {
		    	  MimeMessage msg = new MimeMessage(sess);
		          msg.setFrom();
		          msg.setRecipients(Message.RecipientType.TO, mail);
		          msg.setSubject("Récupération identifiants");
		          msg.setSentDate(new Date());
		          msg.setText("Bonjour cher utilisateur\n , "
		        		+ "voici tes identifiants :\n "
		        		+ "pseudo : " + u.getPseudo() + "\n" 
		        		+ "password : " + u.getMdp() + "\n"
		        				+ "tu seras toujours le bienvenue sur Don't Be A Mouton ;) ");
		          Transport.send(msg);
		          System.out.println("mail envoyé");
	      }
	      } 
	      catch (MessagingException mex) 
	      {
	         System.out.println("send failed, exception: " + mex); 
	      }
		  response.setContentType("/text.html");
		  RequestDispatcher disp;
			
	      disp = request.getRequestDispatcher("mdp_oublie_validation.jsp");
		  disp.forward(request, response);

	}

}
