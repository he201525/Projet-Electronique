package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Temperature;
import javax.swing.ImageIcon;


/**
 * 
 * @author Groupe 3
 * Almasy Zita 
 * Bohyn Gauthier 
 * Hermant Thibaut 
 * Ponjée Aymeric
 *
 */

public class TemperatureVueGui extends JFrame {

	private JPanel contentPane;
	private JLabel lbTemp;
	private Object temperature;
	public Object pic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TemperatureVueGui frame = new TemperatureVueGui();
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
	public TemperatureVueGui()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblApplicationPortSrie = new JLabel("Application Java");
		lblApplicationPortSrie.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApplicationPortSrie.setBounds(10, 11, 157, 32);
		contentPane.add(lblApplicationPortSrie);
		
		JLabel lblTemprature = new JLabel("Temperature:");
		lblTemprature.setBounds(10, 56, 89, 14);
		contentPane.add(lblTemprature);
		
		JLabel lblModificationDeLa = new JLabel("Modification du seuil:");
		lblModificationDeLa.setBounds(10, 126, 145, 14);
		contentPane.add(lblModificationDeLa);
		
		JTextField textField = new JTextField();
		textField.setBounds(151, 123, 46, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		this.lbTemp = new JLabel("0 °C");
		lbTemp.setBounds(234, 51, 46, 25);
		contentPane.add(lbTemp);
		
		JLabel lblNewLabel = new JLabel("Seuil de la temp.:");
		lblNewLabel.setBounds(10, 81, 182, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblY = new JLabel("0 °C");
		lblY.setBounds(234, 85, 71, 25);
		contentPane.add(lblY);
		
		JLabel Groupe = new JLabel("Groupe 3");
		Groupe.setEnabled(false);
        Groupe.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        Groupe.setVerticalAlignment(SwingConstants.BOTTOM);
        Groupe.setBounds(10, 236, 106, 30);
        contentPane.add(Groupe);
		
		JButton btnSend = new JButton("Send");
		btnSend.setIcon(new ImageIcon(TemperatureVueGui.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() < 4){
					if (textField.getText().matches("[0-9]+")){
						
						int i = Integer.parseInt(textField.getText());
						if(i <= 100 && i >= 0){
							lblY.setText(Integer.toString(i) + " °C");
						}
					}
				}
			}
		});
		
		btnSend.setBounds(209, 122, 78, 25);
		contentPane.add(btnSend);
		this.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		       dispose();
		    }
		});
	}
	
	public void update(Observable obs, Object obj) 
	{
		this.lbTemp.setText(this.temperature + " C");
	
		if (Temperature.isOverHeating())
		{
			this.lbTemp.setForeground(Color.RED);
		} else 
		{
			this.lbTemp.setForeground(Color.GREEN);
		}
	}
	
	
}
