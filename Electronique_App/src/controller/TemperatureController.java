package controller;

import model.Temperature;
import view.TemperatureVue;
import view.TemperatureVueConsole;
import view.TemperatureVueGui;

/**
 * 
 * @author Groupe 3
 * Almasy Zita 
 * Bohyn Gauthier 
 * Hermant Thibaut 
 * Ponjée Aymeric
 *
 */

public class TemperatureController {
	
	private Temperature model;
	private TemperatureVue vue;
	private String info = "Temp�rature normale.";
	
	public TemperatureController(Temperature model) {
		this.model = model;
	}
	
	public void modifierSeuil(int seuil) {
		model.setSeuil(seuil);
		temperatureEnvoi();
	}
	
	public void temperatureEnvoi() {
		
	}

	public void addVue(TemperatureVueGui gui) {
		this.vue = vue;
		
	}

	public void addVue(TemperatureVueConsole console) {
		this.vue = vue;
	}
}
