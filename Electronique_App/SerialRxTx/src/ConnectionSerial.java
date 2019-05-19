import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectionSerial
{
    public ConnectionSerial()
    {
        super();
    }
    
    void connect ( String port ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(port);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Le port est déja utilisé");
        }
        else
        {
            CommPort communicationPort = portIdentifier.open(this.getClass().getName(),5000);
            
            if ( communicationPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) communicationPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();
                
                (new Thread(new Read(in))).start();
                (new Thread(new Write(out))).start();

            }
            else
            {
                System.out.println("Error: Port incorrect");
            }
        }     
    }
    
    public static class Write implements Runnable 
    {
        OutputStream out;
        
        public Write ( OutputStream out )
        {
            this.out = out;
        }
        
        public void run ()
        {
            try
            {                
                int c = 0;
                while ( ( c = System.in.read()) > -1 )
                {
                    this.out.write(c);
                }                
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }
    
    public static class Read implements Runnable 
    {
        InputStream in;
        
        public Read ( InputStream in )
        {
            this.in = in;
        }
        
        public void run ()
        {
            byte[] buffer = new byte[1024];
            int length = -1;
            try
            {
                while ( ( length = this.in.read(buffer)) > -1 )
                {
                    System.out.print(new String(buffer,0,length));
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }

   
   
    
    public static void main ( String[] args )
    {
        try
        {
            (new ConnectionSerial()).connect("COM1");
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
} 