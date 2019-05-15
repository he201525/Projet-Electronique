

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame implements Observer{
	private JLabel lbTemp,lblY;
	private Temperature temperature;
	private PIC pic;
	
	GUI(/*Temperature temp, PIC pic*/) { 		//en commentaire car sinon je ne peux pas tester le gui
												// Mais ce sont les données qui viennent de la carte
		//this.temperature = temp;
		this.pic = pic;
		
		//temp.addObserver(this);
		
		JPanel contentPane;
		JTextField textField;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblApplicationPortSrie = new JLabel("Application");
		lblApplicationPortSrie.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApplicationPortSrie.setBounds(10, 11, 157, 32);
		contentPane.add(lblApplicationPortSrie);
		
		JLabel lblTemprature = new JLabel("Temp\u00E9rature:");
		lblTemprature.setBounds(10, 54, 90, 14);
		contentPane.add(lblTemprature);
		
		JLabel lblModificationDeLa = new JLabel("Modification de la Temp\u00E9rature seuil:");
		lblModificationDeLa.setBounds(10, 104, 400, 14);
		contentPane.add(lblModificationDeLa);
		
		textField = new JTextField();
		textField.setBounds(10, 129, 173, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		this.lbTemp = new JLabel("0°C");
		lbTemp.setBounds(100, 54, 46, 14);
		contentPane.add(lbTemp);
		
		JLabel lblNewLabel = new JLabel("Temp. seuil:");
		lblNewLabel.setBounds(10, 79, 90, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblY = new JLabel("0°C");
		lblY.setBounds(100, 79, 46, 14);
		contentPane.add(lblY);
		
		JButton btnSend = new JButton("Send");
		btnSend.setIcon(new ImageIcon(GUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() < 4){				//je sais pas trop
					if (textField.getText().matches("[0-9]+")){		//je sais pas trop
						
						int i = Integer.parseInt(textField.getText());	// pour introduire seulement entre 0-100
						if(i <= 100 && i >= 0){
							pic.send((byte) i);
							lblY.setText(Integer.toString(i) + " °C");
						}
						
					}
				}
				
			}
		});
		btnSend.setBounds(195, 129, 89, 23);
		contentPane.add(btnSend);
		this.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		       pic.setRunning(false);
		       dispose();
		    }
		});
		JLabel Groupe = new JLabel("Groupe 3");
		Groupe.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		Groupe.setVerticalAlignment(SwingConstants.BOTTOM);
		Groupe.setBounds(6, 242, 90, 30);
		contentPane.add(Groupe);
	}
	
	
	@Override
	// pour écrire en rouge ou en vert en fonction de la temperature
	public void update(Observable obs, Object obj) {			
		this.lbTemp.setText(this.temperature.getTemp() + " °C");
	
		if (temperature.isOverHeating()){
			this.lbTemp.setForeground(Color.RED);
		} else {
			this.lbTemp.setForeground(Color.BLACK);
		}
	}
}
