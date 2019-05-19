package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

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

public class TemperatureVueConsole extends TemperatureVue implements Observer{
	protected Scanner sc;
	private String alerte ="Alerte: La Temperature a atteint le seuil!\n";
	private String info = "Temperature normale.\n";
	
	public TemperatureVueConsole(Temperature model, TemperatureController controller) {
		super(model, controller);
		update(model, null);
		sc = new Scanner(System.in);
		new Thread(new ReadInput()).start();
	}
	
	public void informer() {
		affiche("Entrez: \n"
				+"--> 'm' si vous voulez modifier le seuil temperature \n	"
				+"ou  	"
				+"\n--> 'd' pour modifier le seuil par defaut: ");
	}
	
	private class ReadInput implements Runnable{
		public void run() {
			while(true) {
				try {
					String c = sc.next();
					switch(c) {
						case "m":
							affiche("Veuillez introduire une nombre entre 0 et 100");
							break;
						case "d":
							controller.modifierSeuil(25);
							break;
						default: 
							affiche ("\n");
							affiche("Introduire votre temperature: ");
					}
					
					int i = sc.nextInt();
					if(i < 0 || i > 100) {
						affiche("Valeur de donnee incorrecte!");					
					}
					else {
						controller.modifierSeuil(i);
					}					
				}
				catch (Exception e) {
					affiche("Veuillez introduire une temperature correcte: ");
				}
			}
		}
	}
	
	public void affiche(String Str) {
		System.out.println(Str);
	}

	@Override
	public void update(Observable o, Object arg) {
		affiche(model.toString());
		if(model.getSeuil() <= model.getTemperature()) {
			affiche(alerte);
		}
		else {
			affiche(info);
		}
		informer();	
	}

}