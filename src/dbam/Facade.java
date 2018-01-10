package dbam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Singleton
public class Facade {

	@PersistenceContext
	private EntityManager em;
	
	//****************************************************************
	//*************** Ajouter un nouvel objet à la BDD ***************
	//****************************************************************
	 
	
	public boolean addUtilisateur(String pseudo, String mdp, String mdp2, String mail) {
		boolean res = false;
		// Mot de passe de confirmation (mdp2) identique au mot de passe donné (mdp), comportant plus de 4 caractères
		if (mdp.equals(mdp2) && mdp.length()<4) {
				TypedQuery<Utilisateur> req = em.createQuery("select * from Personne where pseudo = '" + pseudo + "'",Utilisateur.class);
				// pseudo valide si il n'appartient pas déjà à un utilisateur
				if (req == null) {
					Utilisateur utilisateur = new Utilisateur(pseudo,mdp,mail);
					em.persist(utilisateur);
					res = true;
				}		
		}
		return res;
	}
	
	public void addScenario(String nom, String description, String texteVictoire, int auteurID, Scenario.Statut statut) {
		Utilisateur auteur = em.find(Utilisateur.class,auteurID);
		Scenario scenario = new Scenario(nom,description,texteVictoire,statut);
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
		// On récupère le scénario associé à la session dans la BDD
		Scenario scenario = em.find(Scenario.class,scenarioID);
		// On récupère le premier checkpoint de la liste de checkpoint du scénario
		Checkpoint checkpointDepart = (((ArrayList<Checkpoint>) scenario.getCheckpoints()).get(0));
		// On choisi une question au hasard parmis les questions du premier checkpoint
		ArrayList<Question> questions = (ArrayList<Question>) checkpointDepart.getQuestions();
		Random rand = new Random(); 
		int i = rand.nextInt(questions.size());
		Question questionDepart = questions.get(i);
		// On définis cette question comme question faite
		ArrayList<Question> questionsFaites = new ArrayList<Question>();
		questionsFaites.add(questionDepart);
		// On créer une session initialisé a 0 réponse réussi et raté, puis on met à jour ces attributs initialisées
		Session session = new Session();
		session.setScenario(scenario);
		session.setCheckpointCourant(checkpointDepart);
		session.setQuestionCourante(questionDepart);
		session.setQuestionsFaites(questionsFaites);
		//On récupère le joueur de la session, puis on le met à jour dans la session
		Utilisateur joueur = em.find(Utilisateur.class,joueurID);
		session.setJoueur(joueur);
		// On ajoute cette session à notre BDD
		em.persist(session);
	}
	
	//*************************************************************
	//*************** Modifier des objets de la BDD ***************
	//*************************************************************
	
	//*************** Scenario ************************************
	
	public void setNomScenario(Scenario scenarioID, String newNom) {
		Scenario scenario = em.find(Scenario.class,scenarioID);
		scenario.setNom(newNom);
	}
	
	public void setDescriptionScenario(Scenario scenarioID, String newDescription) {
		Scenario scenario = em.find(Scenario.class,scenarioID);
		scenario.setDescription(newDescription);
	}
	
	public void setStatutScenario(Scenario scenarioID, Scenario.Statut newStatut) {
		Scenario scenario = em.find(Scenario.class,scenarioID);
		scenario.setStatut(newStatut);
	}
	
	
	
	//*************** Checkpoint ************************************
	
	public void setNbVictReqCheckpoint(int checkpointID, int newNbVictReq) {
		Checkpoint checkpoint = em.find(Checkpoint.class, checkpointID);
		checkpoint.setNbVictRequis(newNbVictReq);
	}
	
	public void setNbDefMaxCheckpoint(int checkpointID, int newNbDefMax) {
		Checkpoint checkpoint = em.find(Checkpoint.class, checkpointID);
		checkpoint.setNbDefMax(newNbDefMax);
	}
	
	public void setTexteVictoireCheckpoint(int checkpointID, String newTexteVictoire) {
		Checkpoint checkpoint = em.find(Checkpoint.class, checkpointID);
		checkpoint.setTexteVictoire(newTexteVictoire);;
	}
	
	public void setTexteDefaiteCheckpoint(int checkpointID, String newTexteDefaite) {
		Checkpoint checkpoint = em.find(Checkpoint.class, checkpointID);
		checkpoint.setTexteDefaite(newTexteDefaite);
	}
}
