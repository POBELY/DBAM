package dbam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.plaf.synth.SynthSeparatorUI;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

@Singleton
public class Facade {

	@PersistenceContext
	private EntityManager em;

	
	//****************************************************************
	//*************** Ajouter un nouvel objet à la BDD ***************
	//****************************************************************
	 
	
	public int addUtilisateur(String pseudo, String mdp, String mail) {
		Utilisateur utilisateur = new Utilisateur(pseudo,mdp,mail);
		utilisateur.setScenariosTermines(new ArrayList<Scenario>());
		em.persist(utilisateur);	
		return  utilisateur.getId();
	}
	
	public int addScenario(String nom, String description, String texteVictoire, int auteurID, Scenario.Statut statut) {
		// Créer le scénario avec son auteur
		Utilisateur auteur = em.find(Utilisateur.class,auteurID);
		Scenario scenario = new Scenario(nom,description,texteVictoire,statut);
		scenario.setAuteur(auteur);	
		// ajouter ce scénario à la BDD
		em.persist(scenario);
		return scenario.getId();
	}
	
	// Ajout d'un checkpoint sans checkpoint suivant (premier checkpoint créé d'un scénario, ou ajout d'un checkpoint en dernière position
	public int addCheckpoint(int nbVictReq, int nbDefMax, String texteVictoire, String texteDefaite, int scenarioID) {
		// Récupèrer lescénario
		Scenario scenario = em.find(Scenario.class,scenarioID);
		// Créer notre nouveau checkpoint
		Checkpoint checkpoint = new Checkpoint(nbVictReq, nbDefMax, texteVictoire, texteDefaite);
		checkpoint.setScenario(scenario);
		// Récupérer les checkpoints de ce scénario
		TypedQuery<Checkpoint> reqCheckpoints = em.createQuery("from Checkpoint where scenario_ID=" + scenarioID,Checkpoint.class);
		Collection<Checkpoint>  checkpoints = reqCheckpoints.getResultList();
		// Si il s'agit du premier checkpoint créé du scénario, ne rien faire, 
		//sinon, chercher celui qui n'a pas de checkpoint suivant, et définir le checkpoint créé comme son suivant
		if (!checkpoints.isEmpty()) {
			TypedQuery<Checkpoint> req = em.createQuery("from Checkpoint where suivant_id is null and scenario_ID=" + scenarioID,Checkpoint.class);
			Checkpoint checkpointPrecedent = req.getSingleResult();
			checkpointPrecedent.setSuivant(checkpoint);
		}
		// Ajouter ce checkpoint à la BDD
		em.persist(checkpoint);
		return checkpoint.getId();
	}
	
	// Ajout d'un checkpoint avec checkpoint suivant
	// Précondition : ne doit pas être ajouté en première position
	public int addCheckpoint(int nbVictReq, int nbDefMax, String texteVictoire, String texteDefaite, int scenarioID, int checkpointSuivantID) {
		// Récupèrer le scénario
		Scenario scenario = em.find(Scenario.class,scenarioID);
		// Créer notre nouveau checkpoint
		Checkpoint checkpoint = new Checkpoint(nbVictReq, nbDefMax, texteVictoire, texteDefaite);
		checkpoint.setScenario(scenario);
		// Situer le checkpoint par rapport aux autres
		Checkpoint checkpointSuivant = em.find(Checkpoint.class, checkpointSuivantID);
		checkpoint.setSuivant(checkpointSuivant);		
		TypedQuery<Checkpoint> req = em.createQuery("from Checkpoint where suivant_id=" + checkpointSuivantID + "and scenario_id=" + scenarioID,Checkpoint.class);
		Checkpoint checkpointPrecedent = req.getSingleResult();
		checkpointPrecedent.setSuivant(checkpoint);	
		// Ajouter ce checkpoint à la BDD
		em.persist(checkpoint);
		return checkpoint.getId();
	}
	
	public int addQuestion(String texteQuestion, int checkpointID) {
		Checkpoint checkpoint = em.find(Checkpoint.class,checkpointID);
		Question question = new Question(texteQuestion);
		question.setCheckpoint(checkpoint);
		em.persist(question);
		return question.getId();
	}

	public int addReponse(String texteReponse, int questionID) {
		Question question = em.find(Question.class,questionID);
		Reponse reponse = new Reponse(texteReponse);
		reponse.setQuestion(question);	
		em.persist(reponse);
		return reponse.getId();
	}
	
	public int addSession(int scenarioID, int joueurID) {
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
		ArrayList<Question> questionsRestantes = (ArrayList<Question>) checkpointDepart.getQuestions();
		// On créer une session initialisé a 0 réponse réussi et raté, puis on met à jour ces attributs initialisées
		Session session = new Session();
		session.setScenario(scenario);
		session.setCheckpointCourant(checkpointDepart);
		session.setQuestionCourante(questionDepart);
		session.setQuestionsRestantes(questionsRestantes);
		session.setNbQuestionsPerdu(0);
		session.setNbQuestionsReussi(0);
		//On récupère le joueur de la session, puis on le met à jour dans la session
		Utilisateur joueur = em.find(Utilisateur.class,joueurID);
		session.setJoueur(joueur);	
		// On ajoute cette session à notre BDD
		em.persist(session);
		return session.getId();
	}
	
	public void addScenarioTermine(int utilisateurID, int scenarioID) {
		Scenario scenario = em.find(Scenario.class,scenarioID);
		Utilisateur utilisateur = em.find(Utilisateur.class,utilisateurID);
		utilisateur.getScenariosTermines().add(scenario);
	}
	
	public void addQuestionRestantes(int sessionID, int questionID) {
		Session session = em.find(Session.class,sessionID);
		Question question = em.find(Question.class,questionID);
		session.getQuestionsRestantes().add(question);
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
	
	public void setNomScenario(int scenarioID, String newNom) {
		Scenario scenario = em.find(Scenario.class,scenarioID);
		scenario.setNom(newNom);
	}
	
	public void setDescriptionScenario(int scenarioID, String newDescription) {
		Scenario scenario = em.find(Scenario.class,scenarioID);
		scenario.setDescription(newDescription);
	}
	
	public void setStatutScenario(int scenarioID, Scenario.Statut newStatut) {
		Scenario scenario = em.find(Scenario.class, scenarioID);
		scenario.setStatut(newStatut);
	}
	
	public void setTextVictoireScenario(int scenarioID, String text) {
		Scenario s = em.find(Scenario.class, scenarioID);
		s.setTexteVictoire(text);
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
		checkpoint.setTexteVictoire(newTexteVictoire);
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
	//*************** Récupérer des objets de la BDD ***
	//*************************************************************
	
	
	public Utilisateur getUtilisateur(int id) {
		return em.find(Utilisateur.class, id);
	}
	
	public Scenario getScenario(int id) {
		return em.find(Scenario.class, id);
	}
	
	public Checkpoint getCheckpoint(int id) {
		return em.find(Checkpoint.class, id);
	}
	
	public Question getQuestion(int id) {
		return em.find(Question.class, id);
	}
	
	public Reponse getReponse(int id) {
		return em.find(Reponse.class, id);
	}
	
	public Session getSession(int id) {
		return em.find(Session.class, id);
	}
	
	public Collection<Scenario> getScenariosTermines(int utilisateurID) {	
		TypedQuery<Scenario> req = em.createQuery("select Scenario.id,scenario.description,scenario.nom,scenario.statut,scenario.texteVictoire,scenario.auteur_id from Scenario, Utilisateur_Scenario where Scenario.id = scenariosTermines_id and utilisateur_id=" + utilisateurID,Scenario.class);
		return req.getResultList();
	}
	
	public Collection<Scenario> getMesScenario(int utilisateurID) {
		TypedQuery<Scenario> req = em.createQuery("from Scenario where auteur_id=" + utilisateurID,Scenario.class);	
		return req.getResultList();
	}
	
	public Collection<Session> getMesSessions(int utilisateurID) {
		TypedQuery<Session> req = em.createQuery("from Session where joueur_id=" + utilisateurID,Session.class);	
		return req.getResultList();
	}
	
	public Collection<Checkpoint> getCheckpoints(int scenarioID) {
		TypedQuery<Checkpoint> req = em.createQuery("from Checkpoint where scenario_id=" + scenarioID,Checkpoint.class);	
		return req.getResultList();
	}
	
	public Collection<Question> getQuestions(int checkpointID) {
		TypedQuery<Question> req = em.createQuery("from Question where checkpoint_id=" + checkpointID,Question.class);	
		return req.getResultList();
	}
	
	public Collection<Reponse> getReponses(int questionID) {
		TypedQuery<Reponse> req = em.createQuery("from Reponse where question_id=" + questionID,Reponse.class);	
		return req.getResultList();
	}
	
	public Checkpoint getPrecedentCheckpoint(int suivantCheckpointID) {
		TypedQuery<Checkpoint> req = em.createQuery("from Checkpoint where suivant_id=" + suivantCheckpointID,Checkpoint.class);	
		return req.getSingleResult();
	}
	
	public int getIDUtilisateur(String pseudo) {
		TypedQuery<Utilisateur> req = em.createQuery("from Utilisateur where pseudo='" + pseudo + "'", Utilisateur.class);	
		return req.getSingleResult().getId();
	}
	
	public Collection<Scenario> getScenariosPublic() {
		TypedQuery<Scenario> req = em.createQuery("from Scenario where statut=1",Scenario.class);	
		return req.getResultList();
	}
	
	public Collection<Scenario> getScenariosEnCours(int utilisateurID) {
		Collection<Scenario> scenarios = new ArrayList<Scenario>();
		Collection<Session> sessions = this.getMesSessions(utilisateurID);
		for (Session session : sessions) {
			scenarios.add(session.getScenario());
		}
		return scenarios;
	}
	
	
	//*************************************************************
	//*************** Supprimer des objets de la BDD ***
	//*************************************************************
	
	
	public void removeUtilisateur(int utilisateurID) {
		Utilisateur utilisateur = em.find(Utilisateur.class, utilisateurID);
		em.remove(utilisateur);
	}
	
	public void removeScenario(int scenarioID) {
		Scenario scenario = em.find(Scenario.class, scenarioID);
		em.remove(scenario);
	}
	
	public void removeCheckpoint(int checkpointID) {
		Checkpoint checkpoint = em.find(Checkpoint.class, checkpointID);
		em.remove(checkpoint);
	}
	
	public void removeQuestion(int questionID) {
		Question question = em.find(Question.class, questionID);
		em.remove(question);
	}
	
	public void removeReponse(int reponseID) {
		Reponse reponse = em.find(Reponse.class, reponseID);
		em.remove(reponse);
	}
	
	public void removeSession(int sessionID) {
		Session session = em.find(Session.class, sessionID);
		em.remove(session);
	}
	
	public void removeQuestionsRestantes(int sessionID) {
		Session session = em.find(Session.class,sessionID);
		session.setQuestionsRestantes(new ArrayList<Question>());
	}
	
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
		boolean res = true;
		TypedQuery<Utilisateur> req = em.createQuery("from Utilisateur where pseudo = '" + pseudo + "'",Utilisateur.class);
		if (req.getResultList().isEmpty()) {
			res = false;
		}
		return res;
	}
	
	public List<Utilisateur> recupererUtilisateurs(String mail) {
		System.out.println("je suis dans la facade, mail : "+mail);
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		TypedQuery<Utilisateur> req = em.createQuery("from Utilisateur where mail = '" + mail + "'",Utilisateur.class);
		for (Utilisateur u :  req.getResultList()){
			System.out.println("on est dans la boucle, user :"+u);
			users.add(u);
		}
		return users;
	}
	
	public boolean pasDeScenario() {
		TypedQuery<Scenario> req = (TypedQuery<Scenario>) em.createQuery("from Scenario", Scenario.class);
		return req.getResultList().isEmpty();
			
	}
	


	//*************** Méthodes pour jouer *************************************

	public List<Reponse> bonnesReponses(Question question) {
		int nbChoisisMax;
		boolean bonneRep = true;
		Reponse reponse;
		TypedQuery<Reponse> req = em.createQuery("from Reponse where question_id = '" + question.getId() + "' order by nbChoisi", Reponse.class );
		List<Reponse> listeReponses = req.getResultList();
		Iterator<Reponse> it = listeReponses.iterator();
		List<Reponse> res = new ArrayList<Reponse>();
		nbChoisisMax = listeReponses.get(0).getNbChoisi();

		while( bonneRep && it.hasNext()) {
			reponse = (Reponse) it.next();
			
			if (nbChoisisMax != reponse.getNbChoisi())  {

				bonneRep = false;
			}else{
				res.add(reponse);
			}
		}
		return res; 
		
	}
	public void initCheckpoint(Session sessionCourante) {
		sessionCourante.setQuestionsRestantes(sessionCourante.getCheckpointCourant().getQuestions());
		sessionCourante.setNbQuestionsReussi(0);
		sessionCourante.setNbQuestionsPerdu(0);
	}
	
	public Question getProchaineQuestion(Session sessionCourante) {

		Collections.shuffle((List<Question>) sessionCourante.getQuestionsRestantes());
		List<Question> listQ = (List<Question>) sessionCourante.getQuestionsRestantes();
		listQ.remove(sessionCourante.getQuestionCourante());
		return listQ.get(0);

	}
	
	public String jouer (int choixID, int sessionID) {
		String destination = null;
		boolean bonneRep = false;
		Session sessionCourante = getSession(sessionID);
		int nbperdues = sessionCourante.getNbQuestionsPerdu();
		int nbreussies = sessionCourante.getNbQuestionsReussi();
		Question prochaineQuestion = getProchaineQuestion(sessionCourante);
		// On récupère la "bonne réponse"
		for (Reponse r : bonnesReponses(sessionCourante.getQuestionCourante())) {
			if (choixID == r.getId()) {
				bonneRep = true;
			}
		}
		

		// On verifie la réponse
		if (bonneRep) {
			// Le joueur a cliqué sur la bonne réponse
			sessionCourante.setNbQuestionsReussi(nbreussies + 1);
			
			if (sessionCourante.getNbQuestionsReussi() >= sessionCourante.getCheckpointCourant().getNbVictRequis()) {
				// Le joueur a suffisament de bonnes réponses pour finir le checkpoint
				if ( sessionCourante.getCheckpointCourant() == null) {
					// Ce checkpoint était le dernier, on remet la session a zero
					sessionCourante.setCheckpointCourant(sessionCourante.getScenario().getCheckpoints().get(0));
					initCheckpoint(sessionCourante);
					destination = "question_felicitation.jsp"; 
				}else {
					// Il y a d'autres checkpoints après
					sessionCourante.setCheckpointCourant(sessionCourante.getCheckpointCourant().getSuivant());
					initCheckpoint(sessionCourante);
					getProchaineQuestion(sessionCourante);
					destination = "checkpoint_fin.jsp"; 
				}
			}else{
				//Le joueur n'a pas suffisament de bonnes réponses pour finir le checkpoint
					sessionCourante.getQuestionsRestantes().remove(sessionCourante.getQuestionCourante());
					getProchaineQuestion(sessionCourante);
					destination = "question.jsp";

			}
		}else{
			// Le joueur a cliqué sur la mauvaise réponse
			
			sessionCourante.setNbQuestionsPerdu( nbperdues + 1);
			
			if (sessionCourante.getNbQuestionsPerdu() >= sessionCourante.getCheckpointCourant().getNbDefMax()) {
				// Le joueur a atteint le nombre max de défaites pour ce checkpoint
				initCheckpoint(sessionCourante);
				getProchaineQuestion(sessionCourante);
				destination = "checkpoint.jsp";

			}else{
				//Le joueur n'a pas atteint le nombre max de défaites pour ce checkpoint
				sessionCourante.getQuestionsRestantes().remove(sessionCourante.getQuestionCourante());
				getProchaineQuestion(sessionCourante);
				destination = "question.jsp";
			}
			
		}
		sessionCourante.getQuestionsRestantes().remove(sessionCourante.getQuestionCourante());
		sessionCourante.setQuestionCourante(prochaineQuestion);
		System.out.println("perdues : "+ sessionCourante.getNbQuestionsPerdu());
		System.out.println("reussies : "+ sessionCourante.getNbQuestionsReussi());

		
		return destination;
	}
	
	

	   
}

