package dbam;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Checkpoint {

	//**********ATTRIBUTS*********
	
	@Id
	@Generatedvalue(strategy=GenerationType.AUTO)
	private int id;	// identifiant du checkpoint
	private int nbVictRequis; 	// nombre de réponses correctes attendues pour passer au checkpoint suivant
	private int nbDefMax;		// nombre de réponse fausse nécessaire pour recommencer ce checkpoint
	private String texteVictoire; // texte affiché une fois le checkpoint passé
	private String texteDefaite;  // texte affiché une fois le checkpoint perdu
	@OneToMany(mappedBy="checkpoint",fetch=FetchType.EAGER)
	private Collection<Question> questions;	// ensembles des questions définissant le checkpoint
	@ManyToOne
	Scenario scenario;

	//**********CONSTRUCTEURS**********
	
	public Checkpoint(){}
	
	public Checkpoint()
}
