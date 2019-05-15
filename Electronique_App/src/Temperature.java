
import java.util.Observable;

public class Temperature extends Observable {
	private int temp = 0;
	private boolean isOverHeating = false;
	
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

