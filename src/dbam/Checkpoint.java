package dbam;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;


@Entity
public class Checkpoint {

	//**********ATTRIBUTS*********
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;	// identifiant du checkpoint
	private int nbVictRequis; 	// nombre de réponses correctes attendues pour passer au checkpoint suivant
	private int nbDefMax;		// nombre maximum de réponses fausses autorisées avant de recommencer ce checkpoint
	private String texteVictoire; // texte affiché une fois le checkpoint passé
	private String texteDefaite;  // texte affiché une fois le checkpoint perdu
	@OneToMany(mappedBy="checkpoint",fetch=FetchType.EAGER)
	private Collection<Question> questions;	// ensembles des questions définissant le checkpoint
	@ManyToOne
	private Scenario scenario; // scénario auquel appartient le checkpoint
	@OneToOne
	private Checkpoint suivant; // checkpoint suivant a réalisé si celui-ci est gagné et si un suivant existe

	//**********CONSTRUCTEURS**********

	public Checkpoint(){}
	
	public Checkpoint(int nbVictReq, int nbDefMax, String texteVictoire, String texteDefaite) {
		this.nbVictRequis = nbVictReq;
		this.nbDefMax = nbDefMax;
		this.texteVictoire = texteVictoire;
		this.texteDefaite = texteDefaite;
	}

	
	//**********GETTERS/SETTERS**********
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbVictRequis() {
		return nbVictRequis;
	}

	public void setNbVictRequis(int nbVictRequis) {
		this.nbVictRequis = nbVictRequis;
	}

	public int getNbDefMax() {
		return nbDefMax;
	}

	public void setNbDefMax(int nbDefMax) {
		this.nbDefMax = nbDefMax;
	}

	public String getTexteVictoire() {
		return texteVictoire;
	}

	public void setTexteVictoire(String texteVictoire) {
		this.texteVictoire = texteVictoire;
	}

	public String getTexteDefaite() {
		return texteDefaite;
	}

	public void setTexteDefaite(String texteDefaite) {
		this.texteDefaite = texteDefaite;
	}

	public Collection<Question> getQuestions() {
		Collection<Question> tmp = new ArrayList<Question>();
		for (Question q : questions) {
			if (!tmp.contains(q)) {
				tmp.add(q);
			}
		}
		questions = tmp;
		return questions;
	}

	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public Checkpoint getSuivant() {
		return suivant;
	}

	public void setSuivant(Checkpoint suivant) {
		this.suivant = suivant;
	}
}
