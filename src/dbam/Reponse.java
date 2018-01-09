package dbam;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Reponse {

	//**********ATTRIBUTS*********
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;	// identifiant du choix de réponse
	private String texteChoix;	// texte du choix proposé
	private int nbChoisi;	// nombre de fois que cette réponse a été choisi comme solution
	@ManyToOne
	private Question question; // question à laquelle appartient le choix de réponse


//**********CONSTRUCTEURS**********

	public Reponse() {}
	
	public Reponse(String texteChoix) {
		this.texteChoix = texteChoix;
		this.nbChoisi = 0;
	}

//**********GETTERS/SETTERS**********
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexteChoix() {
		return texteChoix;
	}

	public void setTexteChoix(String texteChoix) {
		this.texteChoix = texteChoix;
	}

	public int getNbChoisi() {
		return nbChoisi;
	}

	public void setNbChoisi(int nbChoisi) {
		this.nbChoisi = nbChoisi;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}