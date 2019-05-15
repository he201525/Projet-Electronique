

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;


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
	 * Initiate the connection with the COM port
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
	 * Read data from the COM port and update the temperature object
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
	 * Send one byte on the COM port
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
