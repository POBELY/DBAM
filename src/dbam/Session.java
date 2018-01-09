package dbam;

import java.util.Collection;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;


@Entity
public class Session {

	//**********ATTRIBUTS*********
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;	// identifiant d'une session
	private int nbQuestionsReussi;	// nombre de question du checkpoint en cours réussi 
	private int nbQuestionsPerdu;	// nombre de question du checkpoint en cours raté
	
	@ManyToOne
	private Scenario scenario;		// scénario de la session
	@ManyToOne
	private Checkpoint checkpointCourant;	//checkpoint courant
	@ManyToOne
	private Question questionCourante; // la question en cours du checkpoint courant
	
	
	@ManyToOne
	private Utilisateur joueur;
	@ManyToMany
	private Collection<Question> questionsFaites; // liste des ID des questions déjà réalisées au cours du checkpoint

	//**********CONSTRUCTEURS**********
	
	public Session() {}
	
	public Session(int scenarioID, int checkpointCourantID, int questionCouranteID) {
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

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public Checkpoint getCheckpointCourant() {
		return checkpointCourant;
	}

	public void setCheckpointCourant(Checkpoint checkpointCourant) {
		this.checkpointCourant = checkpointCourant;
	}

	public Question getQuestionCourante() {
		return questionCourante;
	}

	public void setQuestionCourante(Question questionCourante) {
		this.questionCourante = questionCourante;
	}

	public Collection<Question> getQuestionsFaites() {
		return questionsFaites;
	}

	public void setQuestionsFaites(Collection<Question> questionsFaites) {
		this.questionsFaites = questionsFaites;
	}

	public Utilisateur getJoueur() {
		return joueur;
	}

	public void setJoueur(Utilisateur joueur) {
		this.joueur = joueur;
	}
}
