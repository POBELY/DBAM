package dbam;

public class PremierScenario {

	private Facade facade;
	
	public PremierScenario(Facade facade) {
		this.facade = facade;
	}
	
	public int ajoutScenarioTest() {
		int idScenario = facade.addScenario("Test", "Scenario de test","Bravo ;)", 1, Scenario.Statut.PUBLIC);
		
		// Premier checkPoint
		int idCp1 = facade.addCheckpoint(2, 2, "Bravo checkpoint 1 terminé :)", "defaite au checkpoint1, on recommence", idScenario);
		
		int idQ1A = facade.addQuestion("question A, Checkpoint 1", idCp1);
		int idR1A1 = facade.addReponse("reponse 1, question A, checkpoint 1 ", idQ1A);
		int idR1A2 = facade.addReponse("reponse 2, question A, checkpoint 1 ", idQ1A);
		int idR1A3 = facade.addReponse("reponse 3, question A, checkpoint 1 ", idQ1A);
		
		int idQ1B = facade.addQuestion("question B, Checkpoint 1", idCp1);
		int idR1B1 = facade.addReponse("reponse 1, question B, checkpoint 1 ", idQ1B);
		int idR1B2 = facade.addReponse("reponse 2, question B, checkpoint 1 ", idQ1B);
		
		int idQ1C = facade.addQuestion("question C, Checkpoint 1", idCp1);
		int idR1C1 = facade.addReponse("reponse 1, question C, checkpoint 1 ", idQ1C);
		int idR1C2 = facade.addReponse("reponse 2, question C, checkpoint 1 ", idQ1C);
		int idR1C3 = facade.addReponse("reponse 3, question C, checkpoint 1 ", idQ1C);
		
		// Deuxième checkPoint
		int idCp2 = facade.addCheckpoint(2, 2, "Bravo checkpoint 2 terminé :)", "defaite au checkpoint2, on recommence", idScenario);

		int idQ2A = facade.addQuestion("question A, Checkpoint 2", idCp2);
		int idR2A1 = facade.addReponse("reponse 1, question A, checkpoint 1 ", idQ2A);
		int idR2A2 = facade.addReponse("reponse 2, question A, checkpoint 1 ", idQ2A);
		int idR2A3 = facade.addReponse("reponse 3, question A, checkpoint 1 ", idQ2A);
		
		int idQ2B = facade.addQuestion("question B, Checkpoint 1", idCp2);
		int idR2B1 = facade.addReponse("reponse 1, question B, checkpoint 1 ", idQ2B);
		int idR2B2 = facade.addReponse("reponse 2, question B, checkpoint 1 ", idQ2B);
		
		int idQ2C = facade.addQuestion("question C, Checkpoint 1", idCp2);
		int idR2C1 = facade.addReponse("reponse 1, question C, checkpoint 1 ", idQ2C);
		int idR2C2 = facade.addReponse("reponse 2, question C, checkpoint 1 ", idQ2C);
		int idR2C3 = facade.addReponse("reponse 3, question C, checkpoint 1 ", idQ2C);
		
		return idScenario;
				
		


	}
	
	public void ajoutScenarioYanis() {
		
		int id1 = facade.addScenario("s1", "s1", "yes", 1, Scenario.Statut.PUBLIC);
		int id2 = facade.addCheckpoint(1, 1, "bonjour", "dommage", id1);
		int id3 = facade.addQuestion("pourquoi?", id2);
		int id4 = facade.addReponse("Oui", id3);
		int id5 = facade.addReponse("Non", id3);
		int idd1 = facade.addQuestion("pourquoi2?", id2);
		int idd2 = facade.addReponse("Oui2", idd1);
		int idd3 = facade.addReponse("Non2", idd1);
		int id6 = facade.addCheckpoint(1, 1, "on continue", "raté", id1);
		int id7= facade.addQuestion("Tu préfères qui ?", id6);
		int id8 = facade.addReponse("Maman", id7);
		int id9 = facade.addReponse("Papa", id7);
				
		int id10 = facade.addSession(id1, 1);

		int id11 = facade.addScenario("s2", "s2", "yes", 1, Scenario.Statut.PUBLIC);
		int id12 = facade.addCheckpoint(1, 1, "bienvenue", "echec", id11);
		int id13 = facade.addQuestion("?", id12);
		int id14 = facade.addReponse("0", id13);
		int id15 = facade.addReponse("1", id13);
	}
}
