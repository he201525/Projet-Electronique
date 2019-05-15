

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;


/**
 * @author Groupe 3
 * Almasy Zita 
 * Bohyn Gauthier 
 * Hermant Thibaut 
 * Ponjée Aymeric
 */

public class PIC implements Runnable {
	
	private final String PORT = "COM2";
	private final int TIMEOUT = 2000;
	private final int BAUDRATE = 2400;
	private final int BITSTOREAD = SerialPort.DATABITS_8;
	private final int STOPBITS = SerialPort.STOPBITS_1;
	private final int PARITY = SerialPort.PARITY_NONE;
	
	private SerialPort serialPort = null;
	private InputStream inStr = null;
	private OutputStream outStr = null;
	private Temperature temperature;
	private boolean running;
	
	/**
	 * Établir la connexion avec le port COM
	 */
	public PIC(Temperature temperature) {
		this.temperature = temperature;
		
		try {
			this.serialPort = (SerialPort) CommPortIdentifier
						      .getPortIdentifier(PORT)
						      .open("WeAreTheNumberOne", TIMEOUT);
			this.serialPort.setSerialPortParams(BAUDRATE, BITSTOREAD, STOPBITS, PARITY);
			this.inStr = serialPort.getInputStream();
			this.outStr = serialPort.getOutputStream();
		} catch (NoSuchPortException | PortInUseException e) {
			e.printStackTrace(System.err);
			System.exit(1);
		} catch (UnsupportedCommOperationException | IOException e) {
			e.printStackTrace(System.err);
			serialPort.close();
			System.exit(1);
		}
	}
	
	/**
	 * Lire les données du port et mettre à jour l'objet de température
	 */
	public void run() {
		byte b[] = new byte[1];
		
		setRunning(true);
		System.out.println("Reader started");
		while (getRunning()) {
			try {
				if (inStr.read(b, 0, 1) == -1) {
					System.out.println("End of stream");
					setRunning(false);
					break;
				}
			} catch (IOException e) {
				e.printStackTrace(System.err);
				setRunning(false);
				break;
			}
			System.out.println("Read from COM port: " + b[0]);
			temperature.setTemp((int) b[0] & 0b01111111);
			temperature.setOverHeating((b[0] >> 7) == -1);
		}
		System.out.println("Reader exited");
	}
	
	/**
	 * Envoyer un octet sur le port COM
	 * 
	 */
	public void send(byte b) {
		System.out.println("Send to COM port: " + b);
		try {
			this.outStr.write(b>>>0);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	synchronized public void setRunning(boolean b) {
		this.running = b;
	}
	
	synchronized public boolean getRunning() {
		return this.running;
	}
}
