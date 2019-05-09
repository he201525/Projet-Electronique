import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JLabel lbTemp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblApplication = new JLabel("Application");
		lblApplication.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApplication.setBounds(10, 11, 157, 32);
		contentPane.add(lblApplication);
		
		JLabel lblTemprature = new JLabel("Temp\u00E9rature:");
		lblTemprature.setBounds(10, 54, 90, 14);
		contentPane.add(lblTemprature);
		
		JLabel lblModificationDeLa = new JLabel("Modification de la Temp\u00E9rature seuil:");
		lblModificationDeLa.setBounds(10, 104, 400, 14);
		contentPane.add(lblModificationDeLa);
		
		
		
		this.lbTemp = new JLabel("0°C");
		lbTemp.setBounds(100, 54, 46, 14);
		contentPane.add(lbTemp);
		
		JLabel lblNewLabel = new JLabel("Temp. seuil:");
		lblNewLabel.setBounds(10, 79, 90, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblY = new JLabel("0°C");
		lblY.setBounds(100, 79, 46, 14);
		contentPane.add(lblY);
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 129, 173, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.setIcon(new ImageIcon(GUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		btnSend.addActionListener(new ActionListener() {
			
				public void actionPerformed(ActionEvent e) {
					
						if (textField.getText().matches("[0-9]+")){
							
							int i = Integer.parseInt(textField.getText());
							if(i <= 100 && i >= 0){
								
								lblY.setText(Integer.toString(i) + " °C");
							}
							
						
					}
					
				}
			});
		
		btnSend.setBounds(195, 129, 89, 23);
		contentPane.add(btnSend);
		
		JLabel Groupe = new JLabel("Groupe 3");
		Groupe.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		Groupe.setVerticalAlignment(SwingConstants.BOTTOM);
		Groupe.setBounds(20, 0, 90, 30);
		contentPane.add(Groupe);
		
		
	}



}
