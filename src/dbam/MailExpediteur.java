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
import javax.mail.*;
import javax.mail.internet.*;


/**
 * Servlet implementation class MailExpediteur
 */
@WebServlet(description = "Permet d'envoyer des mails :) youpi", urlPatterns = { "/MailExpediteur" })
public class MailExpediteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	      int sessionId = Integer.parseInt(request.getParameter("sessionId"));
	      int utilisateurId = Integer.parseInt(request.getParameter("utilisateurId"));
	      Session ses = facade.getSession(sessionId);
	      Utilisateur utilisateur = facade.getUtilisateur(utilisateurId);
		
	      Properties props = new Properties();
	      props.put("mail.smtp.host", "localhost");
	      props.put("mail.from", "adresse@expediteur.com");
	      javax.mail.Session sess = javax.mail.Session.getInstance(props, null);
	      try 
	      {
	    	  MimeMessage msg = new MimeMessage(sess);
	          msg.setFrom();
	          msg.setRecipients(Message.RecipientType.TO, utilisateur.getMail());
	          msg.setSubject("Bonjour cher utilisateur, "
	        		+ "voici tes informations personnelles :"
	        		+ "pseudo : " + utilisateur.getPseudo() + "\n" 
	        		+ "password : " + utilisateur.getMdp());
	          msg.setSentDate(new Date());
	          msg.setText("Corps du message!\n");
	          Transport.send(msg);
	      } 
	      catch (MessagingException mex) 
	      {
	         System.out.println("send failed, exception: " + mex); 
	      }
	      ((ServletResponse) request).setContentType("text/html");
	      PrintWriter out = ((ServletResponse) request).getWriter();
	      out.println("Message envoyé");
	      out.close();
	}

}
