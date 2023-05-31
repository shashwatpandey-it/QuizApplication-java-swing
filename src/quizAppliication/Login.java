package quizAppliication;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Login extends JFrame{
	
	//reference variables
	private JLabel loginImage;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel typeLabel;
	private ComboBox<String> box;
	private JButton startButton;
	private Font font;
	
	//constructor 
	public Login(){
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		super.getContentPane().setBackground(Color.WHITE);
		super.setIconImage(new ImageIcon(getClass().getResource("/Icon.png")).getImage());
		super.setLayout(null);
		super.setSize(1000,600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (screenSize.width - super.getWidth())/2;
		int centerY = (screenSize.height - super.getHeight())/2;
		super.setLocation(centerX,centerY);
		super.setResizable(false);
		
		
		this.prepareGUI();
		
		super.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	private void prepareGUI() {
		
		//image handling
		loginImage = new JLabel();
		loginImage.setIcon(new ImageIcon(getClass().getResource("/Logo.png")));
		loginImage.setBounds(0,0,600,600);
		
		//text handling
		font = new Font("Verdana", Font.BOLD,26);
		nameLabel = new JLabel("Player Name");
		nameLabel.setHorizontalAlignment(JLabel.LEADING);
		nameLabel.setFont(font);
		nameLabel.setForeground(new Color(248,0,78));
		nameLabel.setBounds(550,100,400,50);
		
		//text field handling
		nameField = new JTextField();
		nameField.setFont(new Font("Verdana",Font.PLAIN,20));
		nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		nameField.setForeground(Color.DARK_GRAY);
		nameField.setBackground(new Color(255,238,212));
		nameField.setBounds(550,160,400,40);
		
		//type label handling
		font = new Font("Verdana", Font.BOLD,26);
		typeLabel = new JLabel("Quiz Topic");
		typeLabel.setHorizontalAlignment(JLabel.LEADING);
		typeLabel.setFont(font);
		typeLabel.setForeground(new Color(248,0,78));
		typeLabel.setBounds(550,220,400,50);
		
		//box field handling
		String[] types = {"General Knowledge", "Science and Nature", "Sports", "Geography", "History", "Politics", "Vehicles"};
 		box = new ComboBox<String>();
 		for(String type : types) {
 			box.addItem(type);
 		}
 		box.setFont(new Font("Verdana",Font.PLAIN,20));
 		box.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
 		box.setEditable(false);
 		box.setFocusable(false);
 		box.setMaximumRowCount(3);
 		box.setForeground(Color.DARK_GRAY);
 		box.setBackground(new Color(255,238,212));
 		box.setBounds(550,280,400,40);
 		
		
		//button handling
		startButton = new JButton("START");
		startButton.setFont(font);
		startButton.setBackground(new Color(248,0,78));
		startButton.setForeground(Color.WHITE);
		startButton.setFocusable(false);
		startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		startButton.setBorder(BorderFactory.createLineBorder(new Color(248,0,78),4,true));
		startButton.setBounds(650,380,205,50);
		startButton.addActionListener(event -> { if(nameField.getText().isEmpty()) {
			 										 JOptionPane.showMessageDialog(this,"Player name required!","Warning",JOptionPane.WARNING_MESSAGE);
												 }else {
													 new RuleWindow(this, nameField.getText(), String.valueOf(box.getSelectedItem()));
												 }
		});
		
		this.getContentPane().add(startButton);
		this.getContentPane().add(loginImage);
		this.getContentPane().add(nameField);
		this.getContentPane().add(nameLabel);
		this.getContentPane().add(typeLabel);
		this.getContentPane().add(box);
	
		
		
		
	}

	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0");
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Login();

			}
		});
	}
}
