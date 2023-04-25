package quizAppliication;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Login extends JFrame{
	
	//reference variables
	private JLabel loginImage;
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton startButton;
	private Font font;
	
	//constructor 
	public Login(){
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		super.getContentPane().setBackground(Color.WHITE);
		super.setIconImage(new ImageIcon("resources/icon.png").getImage());
		super.setLayout(null);
		super.setLocation(250,80);
		super.setSize(1000,600);
		super.setResizable(false);
		
		
		this.prepareGUI();
		
		super.setVisible(true);
	}
	
	private void prepareGUI() {
		
		//image handling
		loginImage = new JLabel();
		loginImage.setIcon(new ImageIcon("resources/logo.png"));
		loginImage.setBounds(0,0,600,600);
		
		//text handling
		font = new Font("Verdana", Font.BOLD,26);
		nameLabel = new JLabel("Player Name");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setFont(font);
		nameLabel.setForeground(new Color(248,0,78));
		nameLabel.setBounds(550,150,400,50);
		
		//text field handling
		nameField = new JTextField();
		nameField.setFont(new Font("Verdana",Font.PLAIN,20));
		nameField.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
		nameField.setHorizontalAlignment(JTextField.CENTER);
		nameField.setForeground(Color.DARK_GRAY);
		nameField.setBackground(new Color(255,238,212));
		nameField.setBounds(550,210,400,40);
		
		//button handling
		startButton = new JButton("START");
		startButton.setFont(font);
		startButton.setBackground(new Color(248,0,78));
		startButton.setForeground(Color.WHITE);
		startButton.setFocusable(false);
		startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		startButton.setBorder(BorderFactory.createLineBorder(new Color(248,0,78),4,true));
		startButton.setBounds(650,325,205,50);
		startButton.addActionListener(event -> { if(nameField.getText().isEmpty()) {
			 										 JOptionPane.showMessageDialog(this,"Player name required!","Warning",JOptionPane.WARNING_MESSAGE);
												 }else {
													 new RuleWindow(this, nameField.getText());
												 }
		});
		
		this.getContentPane().add(startButton);
		this.getContentPane().add(loginImage);
		this.getContentPane().add(nameField);
		this.getContentPane().add(nameLabel);
	
		
		
		
	}

	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0");
		new Login();
	}
}
