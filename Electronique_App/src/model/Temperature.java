package model;

import java.util.Observable;

/**
 * 
 * @author Groupe 3
 * Almasy Zita 
 * Bohyn Gauthier 
 * Hermant Thibaut 
 * Ponjée Aymeric
 *
 */

public class Temperature extends Observable{

	/**
	 * Temperature qui sera encodee par l'utilisateur
	 * 
	 */
	private int seuil;
	
	public static final int SEUIL_DEFAULT = 25;
	
	/**
	 * Temperature
	 */
	private int temperature = 20;
	
	
	public Temperature() {
		seuil = 20;
	}


	public int getSeuil() {
		return seuil;
	}


	public void setSeuil(int seuil) {
		this.seuil = seuil;
		setChanged();
		notifyObservers();

	}


	public int getTemperature() {
		return temperature;
	}


	public void setTemperature(int temperature) {
		this.temperature = temperature;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public String toString() {
		return  "\n\n\n\n Seuil de la temp�rature :   " + seuil +
				"\n Temperature :               " + temperature;
	}


	public static boolean isOverHeating() {
		return false;
	}
}
