package dbam;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Utilisateur {

	//**********ATTRIBUTS*********
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;			// identifiant de l'utilisateur
	private String pseudo;	// pseudo utilisateur
	private String mdp;		// mot de passe utilisateur
	private String mail;	// mail utilisateur
	@OneToMany(mappedBy="auteur",fetch=FetchType.EAGER)
	private Collection<Scenario> mesScenarios;	// ensemble des scénarios créaient par l'utilisateur
	@OneToMany(mappedBy="joueur",fetch=FetchType.EAGER)
	private Collection<Session> sessions;		// ensemble des sessions jouaient par l'utilisateur
	@ManyToMany
	private Collection<Scenario> scenariosTermines;
	
	
	//**********CONSTRUCTEURS**********
	
	public Utilisateur() {}
	
	public Utilisateur(String pseudo, String mdp, String mail) {
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.mail = mail;
	}
	
	//**********GETTERS/SETTERS**********
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Collection<Scenario> getMesScenarios() {
		return mesScenarios;
	}

	public void setMesScenarios(Collection<Scenario> mesScenarios) {
		this.mesScenarios = mesScenarios;
	}

	public Collection<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Collection<Session> sessions) {
		this.sessions = sessions;
	}

	public Collection<Scenario> getScenariosTermines() {
		return scenariosTermines;
	}

	public void setScenariosTermines(Collection<Scenario> scenariosTermines) {
		this.scenariosTermines = scenariosTermines;
	}
	
	
	
}
