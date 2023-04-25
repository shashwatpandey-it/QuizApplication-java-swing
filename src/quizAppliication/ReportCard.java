package quizAppliication;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class ReportCard extends JFrame{
	//reference variables
	private String playerName;
	private int correct;
	private int inCorrect;
	private JLabel headingLabel;
	private JLabel correctLabel, correctLabel2;
	private JLabel incorrectLabel, incorrectLabel2;
	private JLabel scoreLabel, scoreLabel2;
	private JLabel remarksLabel;
	private JLabel colonLabel1, colonLabel2, colonLabel3;
	private JButton playAgainButton;
	private JButton exitButton;
	private Font headingFont;
	private Font font;
	private Font buttonFont;
	
	//constructor
	public ReportCard(QuizPage quizPageReference, String playerName, int correct, int inCorrect) {
		this.playerName = playerName;
		this.correct = correct;
		this.inCorrect = inCorrect;
		quizPageReference.dispose();;
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.getContentPane().setBackground(Color.WHITE);
		super.setIconImage(new ImageIcon(getClass().getResource("/Icon.png")).getImage());
		super.setLayout(null);
		super.setLocation(250,150);
		super.setSize(600,400);
		super.setResizable(false);
		
		this.prepareGUI();
		
		super.setVisible(true);
	}
	
	private void prepareGUI() {
		
		//handling font
		headingFont = new Font("Verdana", Font.BOLD, 22);
		font = new Font("Verdana", Font.PLAIN, 20);
		buttonFont = new Font("Verdana", Font.BOLD, 24);
		
		//handling labels
		headingLabel = new JLabel(playerName + "'s Performance Card");
		headingLabel.setFont(headingFont);
		headingLabel.setHorizontalAlignment(JLabel.CENTER);
		headingLabel.setForeground(new Color(248,0,78));
		headingLabel.setBounds(50,20,500,40);
		
		correctLabel = new JLabel("Correct Answers ");
		correctLabel.setFont(font);
		correctLabel.setHorizontalAlignment(JLabel.CENTER);
		correctLabel.setBounds(50,80,240,30);
		
		colonLabel1 = new JLabel(":");
		colonLabel1.setFont(font);
		colonLabel1.setHorizontalAlignment(JLabel.CENTER);
		colonLabel1.setBounds(290,80,20,30);
		
		correctLabel2 = new JLabel(" " + correct);
		correctLabel2.setFont(font);
		correctLabel2.setHorizontalAlignment(JLabel.CENTER);
		correctLabel2.setBounds(310,80,240,30);
		
		incorrectLabel = new JLabel("Incorrect Answers ");
		incorrectLabel.setFont(font);
		incorrectLabel.setHorizontalAlignment(JLabel.CENTER);
		incorrectLabel.setBounds(50,120,240,30);
		
		colonLabel2 = new JLabel(":");
		colonLabel2.setFont(font);
		colonLabel2.setHorizontalAlignment(JLabel.CENTER);
		colonLabel2.setBounds(290,120,20,30);
		
		incorrectLabel2 = new JLabel(" " + inCorrect);
		incorrectLabel2.setFont(font);
		incorrectLabel2.setHorizontalAlignment(JLabel.CENTER);
		incorrectLabel2.setBounds(310,120,240,30);
		
		scoreLabel = new JLabel("Score ");
		scoreLabel.setFont(font);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setBounds(50,160,240,30);
		
		colonLabel3 = new JLabel(":");
		colonLabel3.setFont(font);
		colonLabel3.setHorizontalAlignment(JLabel.CENTER);
		colonLabel3.setBounds(290,160,20,30);
		
		scoreLabel2 = new JLabel(" " + (correct*4) + "/" + 40);
		scoreLabel2.setFont(font);
		scoreLabel2.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel2.setBounds(310,160,240,30);
		
		remarksLabel = new JLabel();
		if(correct <= 3) remarksLabel.setText("Poor performance! Need to work hard.");
		if(correct > 3 && correct <= 7) remarksLabel.setText("Average Performance, can do better.");
		if(correct >7) remarksLabel.setText("Excellent performance!!");
		remarksLabel.setFont(font);
		remarksLabel.setHorizontalAlignment(JLabel.CENTER);
		remarksLabel.setBounds(50,200,500,30);
		
		//button handling
		playAgainButton = new JButton("PLAY AGAIN");
		playAgainButton.setFont(buttonFont);
		playAgainButton.setBackground(new Color(248,0,78));
		playAgainButton.setForeground(Color.WHITE);
		playAgainButton.setFocusable(false);
		playAgainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		playAgainButton.setBorder(BorderFactory.createLineBorder(new Color(248,0,78),4,true));
		playAgainButton.setBounds(70,260,200,50);
		playAgainButton.addActionListener(event -> { this.dispose();
														new Login();
		});
		
		exitButton = new JButton("EXIT");
		exitButton.setFont(buttonFont);
		exitButton.setBackground(new Color(248,0,78));
		exitButton.setForeground(Color.WHITE);
		exitButton.setFocusable(false);
		exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		exitButton.setBorder(BorderFactory.createLineBorder(new Color(248,0,78),4,true));
		exitButton.setBounds(330,260,200,50);
		exitButton.addActionListener(event -> this.dispose());
		
		
		this.getContentPane().add(headingLabel);
		this.getContentPane().add(correctLabel);
		this.getContentPane().add(colonLabel1);
		this.getContentPane().add(correctLabel2);
		this.getContentPane().add(incorrectLabel);
		this.getContentPane().add(colonLabel2);
		this.getContentPane().add(incorrectLabel2);
		this.getContentPane().add(scoreLabel);
		this.getContentPane().add(colonLabel3);
		this.getContentPane().add(scoreLabel2);
		this.getContentPane().add(remarksLabel);
		this.getContentPane().add(playAgainButton);
		this.getContentPane().add(exitButton);
		
	}

}
