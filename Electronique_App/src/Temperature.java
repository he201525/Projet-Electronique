
import java.util.Observable;

/**
 * @author Groupe 3
 * Almasy Zita 
 * Bohyn Gauthier 
 * Hermant Thibaut 
 * Ponjée Aymeric
 */

public class Temperature extends Observable {
	private int temp = 0;
	private boolean isOverHeating = false;
	//pour afficher la température 
	// en quelques sortes, pour mettre la température à jour sur le GUI des qu'elle change 
	synchronized public void setTemp(int temp) {
		System.out.println(0);
		if (this.temp != temp) {
			System.out.println(1);
			this.temp = temp;
			setChanged();
			notifyObservers();
		}
	}
	
	synchronized public int getTemp() {
		return this.temp;
	}
	
	synchronized public void setOverHeating(boolean b) {
		if (this.isOverHeating != b) {
			this.isOverHeating = b;
			setChanged();
			notifyObservers();
		}
	}
	
	synchronized public boolean isOverHeating() {
		return this.isOverHeating;
	}
}

