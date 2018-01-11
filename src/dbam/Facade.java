package dbam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.plaf.synth.SynthSeparatorUI;

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
		if (mdp.equals(mdp2) && mdp.length()>=4) {
			TypedQuery<Utilisateur> req = em.createQuery("from Utilisateur where pseudo = '" + pseudo + "'",Utilisateur.class);
			// pseudo valide si il n'appartient pas déjà à un utilisateur
			if (req.getResultList().isEmpty()) {
				Utilisateur utilisateur = new Utilisateur(pseudo,mdp,mail);
				em.persist(utilisateur);
				res = true;
			}		
		}
		return res;
	}
	
	public void addScenario(String nom, String description, String texteVictoire, int auteurID, Scenario.Statut statut) {
		// Créer le scénario avec son auteur
		Utilisateur auteur = em.find(Utilisateur.class,auteurID);
		Scenario scenario = new Scenario(nom,description,texteVictoire,statut);
		scenario.setAuteur(auteur);
		// ajouter ce scénario aux scénarios de l'utilisateur
		Collection<Scenario> scenarios = auteur.getMesScenarios();
		scenarios.add(scenario);		
		// ajouter ce scénario à la BDD
		em.persist(scenario);
	}
	
	public void addCheckpoint(int nbVictReq, int nbDefMax, String texteVictoire, String texteDefaite, int scenarioID) {
		Scenario scenario = em.find(Scenario.class,scenarioID);
		Checkpoint checkpoint = new Checkpoint(nbVictReq, nbDefMax, texteVictoire, texteDefaite);
		checkpoint.setScenario(scenario);
		Collection<Checkpoint>  checkpoints = scenario.getCheckpoints();
		// Gérer l'ordre des checkpoints ?
		checkpoints.add(checkpoint);
		em.persist(checkpoint);
	}
	
	public void addQuestion(String texteQuestion, int checkpointID) {
		Checkpoint checkpoint = em.find(Checkpoint.class,checkpointID);
		Question question = new Question(texteQuestion);
		question.setCheckpoint(checkpoint);
		Collection<Question> questions = checkpoint.getQuestions();
		questions.add(question);
		em.persist(question);
	}

	public void addReponse(String texteReponse, int questionID) {
		Question question = em.find(Question.class,questionID);
		Reponse reponse = new Reponse(texteReponse);
		reponse.setQuestion(question);
		Collection<Reponse> reponses = question.getChoix();
		reponses.add(reponse);
		em.persist(reponse);
	}
	
	public void addSession(int scenarioID, int joueurID) {
		// On récupère le scénario associé à la session dans la BDD
		Scenario scenario = em.find(Scenario.class,scenarioID);
		// On récupère le premier checkpoint de la liste de checkpoint du scénario
		
		TypedQuery<Checkpoint> reqCheckpoint = em.createQuery("from Checkpoint where scenario_id=" + scenarioID,Checkpoint.class);	
		Checkpoint checkpointDepart = (reqCheckpoint.getResultList()).get(0);
		
		//Checkpoint checkpointDepart = (((ArrayList<Checkpoint>) scenario.getCheckpoints()).get(0));
		// On choisi une question au hasard parmis les questions du premier checkpoint
		//ArrayList<Question> questions = (ArrayList<Question>) checkpointDepart.getQuestions();
		TypedQuery<Question> reqQuestion = em.createQuery("from Question where checkpoint_id=" + checkpointDepart.getId(),Question.class);
		List<Question> questions = reqQuestion.getResultList();
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
		Collection<Session> sessions = joueur.getSessions();
		sessions.add(session);
		// On ajoute cette session à notre BDD
		em.persist(session);
	}
	
	//*************************************************************
	//*************** Modifier des données des objets de la BDD ***
	//*************************************************************
	
	//*************** Utilisateur ************************************
	
	public void setMdpUtilisateur(int utilisateurID, String newMdp) {
		Utilisateur utilisateur = em.find(Utilisateur.class, utilisateurID);
		utilisateur.setMdp(newMdp);
	}
	
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
	
	//*************** Question ************************************
	
	public void setTexteQuestion(int questionID, String newTexteQuestion) {
		Question question = em.find(Question.class,questionID);
		question.setTexteQuestion(newTexteQuestion);
	}
	
	//*************** Reponse *************************************
	
	public void setTexteChoixReponse(int reponseID, String newTexteChoix) {
		Reponse reponse = em.find(Reponse.class,reponseID);
		reponse.setTexteChoix(newTexteChoix);
	}
	
	public void setNbChoisiReponse(int reponseID, int newNbChoisi) {
		Reponse reponse = em.find(Reponse.class,reponseID);
		reponse.setNbChoisi(newNbChoisi);
	}
	
	//*************************************************************
	//*************** Supprimer des objets de la BDD ***
	//*************************************************************
	
	public void removeUtilisateur(int utilisateurID) {
		Utilisateur utilisateur = em.find(Utilisateur.class, utilisateurID);
		em.remove(utilisateur);
	}
	
	//public void 
	
	//*************************************************************
	//****************AUTRES METHODES******************************
	//*************************************************************
	
	public boolean connexionPossible(String pseudo, String mdp) {
		boolean mdpJuste = false;
		TypedQuery<Utilisateur> req = em.createQuery("from Utilisateur where pseudo = '" + pseudo + "'",Utilisateur.class);
		for(Utilisateur u : req.getResultList()) {
			if (mdp.equals(u.getMdp())) {
				mdpJuste = true;
			}
		}
		return mdpJuste; 
	}
	
	public void upNbChoisiReponse(int reponseID) {
		Reponse reponse = em.find(Reponse.class,reponseID);
		reponse.setNbChoisi(reponse.getNbChoisi() + 1);
	}
	
	public boolean pseudoPris(String pseudo) {
		boolean res = false;
		TypedQuery<Utilisateur> req = em.createQuery("from Utilisateur where pseudo = '" + pseudo + "'",Utilisateur.class);
		if (req.getResultList().isEmpty()) {
			res = true;
		}
		return res;
	}
}
