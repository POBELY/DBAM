package dbam;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Question {

	//**********ATTRIBUTS*********
	
	@Id
	@Generatedvalue(strategy=GenerationType.AUTO)
	private int id;	// identifiant de la question
	private String texteQuestion; // texte de la question posée
	@OneToMany(mappedBy="question",fetch=FetchType.EAGER)
	private Collection<Reponse> reponses;	// ensembles des réponses possibles à une question
	@ManyToOne
	private Checkpoint checkpoint; // checkpoint auquel est associée la question
	
	//**********CONSTRUCTEURS**********
	
	public Question() {}
	
	public Question(String texteQuestion) {
		this.texteQuestion = texteQuestion;
	}
	
	//**********GETTERS/SETTERS**********
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexteQuestion() {
		return texteQuestion;
	}

	public void setTexteQuestion(String texteQuestion) {
		this.texteQuestion = texteQuestion;
	}

	public Collection<Reponse> getChoix() {
		return reponses;
	}

	public void setChoix(Collection<Reponse> reponses) {
		this.reponses = reponses;
	}

	public Checkpoint getCheckpoint() {
		return checkpoint;
	}

	public void setCheckpoint(Checkpoint checkpoint) {
		this.checkpoint = checkpoint;
	}
}
