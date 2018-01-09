package dbam;

import java.util.Collection;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;


@Entity
public class Session {

	//**********ATTRIBUTS*********
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;	// identifiant d'une session
	private int nbQuestionsReussi;	// nombre de question du checkpoint en cours réussi 
	private int nbQuestionsPerdu;	// nombre de question du checkpoint en cours raté 
	private int scenarioID;		//ID du scénario de la session
	private int checkpointCourantID;	//ID du checkpoint courant
	
	private int questionCouranteID; // ID de la question en cours du checkpoint courant
	@ManyToOne
	private Utilisateur joueur;
	@ManyToMany(mappedBy="sessions")
	private Collection<Question> questionsFaites; // liste des ID des questions déjà réalisées au cours du checkpoint

	//**********CONSTRUCTEURS**********
	
	public Session() {}
	
	public Session(int scenarioID, int checkpointCourantID, int questionCouranteID) {
		this.scenarioID = scenarioID;
		this.checkpointCourantID = checkpointCourantID;
		this.questionCouranteID = questionCouranteID;
		this.nbQuestionsPerdu = 0;
		this.nbQuestionsReussi = 0;
	}
	
	//**********GETTERS/SETTERS**********
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbQuestionsReussi() {
		return nbQuestionsReussi;
	}

	public void setNbQuestionsReussi(int nbQuestionsReussi) {
		this.nbQuestionsReussi = nbQuestionsReussi;
	}

	public int getNbQuestionsPerdu() {
		return nbQuestionsPerdu;
	}

	public void setNbQuestionsPerdu(int nbQuestionsPerdu) {
		this.nbQuestionsPerdu = nbQuestionsPerdu;
	}

	public int getScenarioID() {
		return scenarioID;
	}

	public void setScenarioID(int scenarioID) {
		this.scenarioID = scenarioID;
	}

	public int getCheckpointCourantID() {
		return checkpointCourantID;
	}

	public void setCheckpointCourantID(int checkpointCourantID) {
		this.checkpointCourantID = checkpointCourantID;
	}

	public Collection<Question> getQuestionsFaites() {
		return questionsFaites;
	}

	public void setQuestionsFaites(Collection<Question> questionsFaites) {
		this.questionsFaites = questionsFaites;
	}

	public int getQuestionCouranteID() {
		return questionCouranteID;
	}

	public void setQuestionCouranteID(int questionCouranteID) {
		this.questionCouranteID = questionCouranteID;
	}

	public Utilisateur getJoueur() {
		return joueur;
	}

	public void setJoueur(Utilisateur joueur) {
		this.joueur = joueur;
	}
}
