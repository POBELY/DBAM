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
public class Scenario {

	//**********ATTRIBUTS*********
	
	public enum Statut {PRIVE, PUBLIC;}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;			// identifiant du scénario
	private String nom;		// nom du scénario
	private String description;	// description du scenario
	private String texteVictoire; // texte affiché une fois le scénario terminé
	private Statut statut; // accessibilité public/privé du scénario
	@OneToMany(mappedBy="scenario",fetch=FetchType.EAGER)
	private Collection<Checkpoint> checkpoints;	// ensembles des groupes de questions(checkpoint) ordonnées, définissant le scénario
	@ManyToOne
	private Utilisateur auteur;		// utilisateur ayant crée le scénario

	//**********CONSTRUCTEURS**********

	public Scenario() {}
	
	public Scenario(String nom, String description, String texteVictoire) {
		this.nom = nom;
		this.description = description;
		this.texteVictoire = texteVictoire;
		this.statut = Statut.PRIVE;
	}
	
	//**********GETTERS/SETTERS**********
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTexteVictoire() {
		return texteVictoire;
	}

	public void setTexteVictoire(String texteVictoire) {
		this.texteVictoire = texteVictoire;
	}

	public Collection<Checkpoint> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(Collection<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}
}
