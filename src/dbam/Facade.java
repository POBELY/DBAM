package dbam;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Singleton
public class Facade {

	@PersistenceContext
	private EntityManager em;
	
	public boolean addUtilisateur(String pseudo, String mdp, String mdp2, String mail) {
		boolean res = false;
		// Mot de passe de confirmation (mdp2) identique au mot de passe donné (mdp), comportant plus de 4 caractères
		if (mdp.equals(mdp2) && mdp.length()<4) {
			// Mail valide si il comporte le caractère "@"
			if (mail.contains("@")) {
				TypedQuery<Utilisateur> req = em.createQuery("select p from Personne p where p.pseudo = " + pseudo,Utilisateur.class);
				// pseudo valide si il n'appartient pas déjà à un utilisateur
				if (req == null) {
					Utilisateur utilisateur = new Utilisateur(pseudo,mdp,mail);
					em.persist(utilisateur);
					res = true;
				}
			}
		}
		return res;
	}
	
	public void addScenario(String nom, String description, String texteVictoire, int auteurID) {
		Utilisateur auteur = em.find(Utilisateur.class,auteurID);
		Scenario scenario = new Scenario(nom,description,texteVictoire);
		scenario.setAuteur(auteur);
		em.persist(scenario);
	}
	
	public void addCheckpoint(int nbVictReq, int nbDefMax, String texteVictoire, String texteDefaite, int scenarioID) {
		Scenario scenario = em.find(Scenario.class,scenarioID);
		Checkpoint checkpoint = new Checkpoint(nbVictReq, nbDefMax, texteVictoire, texteDefaite);
		checkpoint.setScenario(scenario);
		em.persist(checkpoint);
	}
	
	public void addQuestion(String texteQuestion, int checkpointID) {
		Checkpoint checkpoint = em.find(Checkpoint.class,checkpointID);
		Question question = new Question(texteQuestion);
		question.setCheckpoint(checkpoint);
		em.persist(question);
	}

	public void addReponse(String texteReponse, int questionID) {
		Question question = em.find(Question.class,questionID);
		Reponse reponse = new Reponse(texteReponse);
		reponse.setQuestion(question);
		em.persist(reponse);
	}
	
	public void addSession(int scenarioID, int joueurID) {
		Scenario scenario = em.find(Scenario.class,scenarioID);
		
	/*	Checkpoint checkpoint = em.find(, arg1)
		
		Session session = new Session(scenarioID,);
		Utilisateur joueur = em.find(Utilisateur.class,joueurID);
		session.setJoueur(joueur);
		em.persist(session);*/
		
		
		
		
		/*Scenario scenario = em.find(Scenario.class,scenarioID);
		Session session = new Session(scenario);
		Utilisateur joueur = em.find(Utilisateur.class,joueurID);
		session.setJoueur(joueur);
		em.persist(session);*/
		
		//this.checkpointCourant = (((ArrayList<Checkpoint>) scenario.getCheckpoints()).get(0));
		//this.questionCourante = (((ArrayList<Question>) this.checkpointCourant.getQuestions()).get(0));
		//this.questionsFaites= new ArrayList<Question>();
		
	}
	
}
