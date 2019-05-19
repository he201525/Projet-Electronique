package view;

import java.util.Observer;

import controller.TemperatureController;
import model.Temperature;

/**
 * 
 * @author Groupe 3
 * Almasy Zita 
 * Bohyn Gauthier 
 * Hermant Thibaut 
 * Ponjée Aymeric
 *
 */

public abstract class TemperatureVue implements Observer{

	protected Temperature model;
	protected TemperatureController controller;
	
	public TemperatureVue(Temperature model, TemperatureController controller) {
		this.model = model;
		this.controller = controller;
	}
	
	public abstract void affiche(String string);
}
