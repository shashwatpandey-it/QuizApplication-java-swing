package quizAppliication;

import com.google.gson.Gson;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

class QuizPage extends JFrame {
	
	//reference variables
	private JLabel quizLabel;
	private JLabel questionNumber;
	private JLabel question;
	private JRadioButton opt1;
	private JRadioButton opt2;
	private JRadioButton opt3;
	private JRadioButton opt4;
	private ButtonGroup buttonGroup;
	private JButton nextButton;
	private JButton lifelineButton;
	private JButton submitButton;
	private Font buttonFont;
	private Font font;
	private Font optionFont;
	private HttpRequest request;
	private HttpClient client;
	private HttpResponse<String> response;
	private QuizCard[] cardList;
	private Questionnaire questionnaire;
	private String playerName;
	private String quizTopic;
	private int optionNumber = 0;
	private int pressed = 0;
	private int currentCard = 0;
	private int correct = 0;
	private int incorrect = 0;
	private int timer = 20;
	private URI uri;
	
	//constructor
	public QuizPage(RuleWindow ruleWindowReference, String playerName, String quizTopic) {
		this.playerName = playerName;
		this.quizTopic = quizTopic;
		ruleWindowReference.dispose();;
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.getContentPane().setBackground(Color.WHITE);
		super.setIconImage(new ImageIcon(getClass().getResource("/Icon.png")).getImage());
		super.setLayout(null);
		super.setLocation(50,30);
		super.setSize(1440,650);
		super.setResizable(false);
		
		try {
			this.fetchQuestions();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.prepareGUI();
		
		this.setQuizCard();
		
		super.setVisible(true);
		
	}
	
	private void fetchQuestions() throws URISyntaxException {
		
		switch (quizTopic) {
		case "General Knowledge":
			uri = new URI("https://opentdb.com/api.php?amount=10&category=9&type=multiple");
			break;
		
		case "Science and Nature":
			uri = new URI("https://opentdb.com/api.php?amount=10&category=17&type=multiple");
			break;
		
		case "Sports":
			uri = new URI("https://opentdb.com/api.php?amount=10&category=21&type=multiple");
			break;
			
		case "Geography":
			uri = new URI("https://opentdb.com/api.php?amount=10&category=22&type=multiple");
			break;
			
		case "History":
			uri = new URI("https://opentdb.com/api.php?amount=10&category=23&type=multiple");
			break;
			
		case "Politics":
			uri = new URI("https://opentdb.com/api.php?amount=10&category=24&type=multiple");
			break;
			
		case "Vehicles":
			uri = new URI("https://opentdb.com/api.php?amount=10&category=28&type=multiple");
			break;
			
		default:
			break;
		}
		
		try {
			//handling request
			request = HttpRequest.newBuilder()
								.uri(uri)
								.GET()
								.build();
			//client container
			client = HttpClient.newBuilder().build();
			//handling incoming response
			response = client.send(request,HttpResponse.BodyHandlers.ofString());
			//converting JSON to java object for useful application building
			questionnaire = new Gson().fromJson(response.body(),Questionnaire.class);
			cardList = questionnaire.getResults();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setQuizCard() {
		
		//clearing previous selected radio button at each next questions appearance
		buttonGroup.clearSelection();
		//re-enabling all options if in case life-line facility was used previously
		opt1.setEnabled(true);
		opt2.setEnabled(true);
		opt3.setEnabled(true);
		opt4.setEnabled(true);
		
		if(currentCard < cardList.length) {
			
			//switching enabled buttons
			if(currentCard == cardList.length-1) {
				nextButton.setEnabled(false);
				submitButton.setEnabled(true);
			}
			
			//helping resources
			optionNumber = new Random().nextInt(4);
			String[] options = new String[4];
			
			//handling question
			questionNumber.setText("Q." + (currentCard+1));
			question.setText("<html>" + cardList[currentCard].getQuestion() + "</html>");
			
			//creating options array
			for(int i = 0, j = 0; i < 3; i++, j++) {
				options[i] = ("<html>" + cardList[currentCard].getIncorrect_answers()[j] + "</html>");
			}
			if(options[optionNumber] == null) {
				options[optionNumber] = ("<html>" + cardList[currentCard].getCorrect_answer() + "</html>");
			}
			else {
				String tempString = options[optionNumber];
				options[optionNumber] = ("<html>" + cardList[currentCard].getCorrect_answer() + "</html>");
				options[3] = tempString;
			}
			
			//assigning values to option buttons
			opt1.setText(options[0]);
			opt1.setActionCommand(options[0]);
			opt2.setText(options[1]);
			opt2.setActionCommand(options[1]);
			opt3.setText(options[2]);
			opt3.setActionCommand(options[2]);
			opt4.setText(options[3]);
			opt4.setActionCommand(options[3]);
			
			/*testing code
			System.out.println(Arrays.toString(options));
			System.out.println(opt1.getActionCommand() + " " + opt2.getActionCommand() + " " + opt3.getActionCommand() + " " + opt4.getActionCommand());
			System.out.println(cardList[currentCard].getCorrectAnswer());
			*/
			
			//updating card tracker
			currentCard++;
			
		}
		
	}
	
	private void matchAnswer() {
		
		if(buttonGroup.getSelection() == null) {
			incorrect++;
		}
		else if((buttonGroup.getSelection().getActionCommand()).equals("<html>" + cardList[currentCard-1].getCorrect_answer() + "</html>")) {
			correct++;
		}
		else {
			incorrect++;
		}
		//System.out.println("correct: " + correct + " incorrect: " + incorrect);
		
	}
	
	private int getRandomWithExclude(int start ,int end, int[] exclude) {
		
		int randomNumber = start + (new Random().nextInt(end - start + 1 - exclude.length));
		for(int ex : exclude) {
			if(randomNumber < ex) {
				break;
			}
			randomNumber++;
		}
		return randomNumber;
		
	}
	
	private void lifelineFunctionality() {
		
		int[] exclude = {optionNumber};
		int firstEliminate = getRandomWithExclude(0,3,exclude);
		int[] updatedExclude = {optionNumber,firstEliminate};
		Arrays.sort(updatedExclude);
		int secondEliminate = getRandomWithExclude(0,3,updatedExclude);
		
		if(opt1.getName().equals(Integer.toString(firstEliminate)) || opt1.getName().equals(Integer.toString(secondEliminate))) {
			opt1.setEnabled(false);
		}
		if(opt2.getName().equals(Integer.toString(firstEliminate)) || opt2.getName().equals(Integer.toString(secondEliminate))) {
			opt2.setEnabled(false);
		}
		if(opt3.getName().equals(Integer.toString(firstEliminate)) || opt3.getName().equals(Integer.toString(secondEliminate))) {
			opt3.setEnabled(false);
		}
		if(opt4.getName().equals(Integer.toString(firstEliminate)) || opt4.getName().equals(Integer.toString(secondEliminate))) {
			opt4.setEnabled(false);
		}
		
		pressed++;
		
		if(pressed == 2) {
			lifelineButton.setEnabled(false);
		}
		
	}
	
	private void prepareGUI() {
		
		//font handling
		buttonFont = new Font("Verdana", Font.BOLD, 24);
		font = new Font("Veranda",Font.BOLD,20);
		optionFont = new Font("Veranda", Font.PLAIN, 18);

		//handling quizLabel
		quizLabel = new JLabel();
		quizLabel.setIcon(new ImageIcon(getClass().getResource("/Quiz.png")));
		quizLabel.setBounds(0,0,600,600);
		
		//handling questionNumber 
		questionNumber = new JLabel();
		questionNumber.setVerticalAlignment(JLabel.TOP);
		questionNumber.setBounds(600,90,50,60);
		questionNumber.setFont(font);
		
		//handling question
		question = new JLabel();
		question.setFont(font);
		question.setVerticalAlignment(JLabel.TOP);
		question.setBounds(650,90,760,90);
		
		//handling option buttons
		opt1 = new JRadioButton();
		opt1.setName("0");
		opt1.setBackground(Color.WHITE);
		opt1.setFocusable(false);
		opt1.setIconTextGap(8);
		opt1.setFont(optionFont);
		opt1.setBounds(620,180,760,50);
		
		opt2 = new JRadioButton();
		opt2.setName("1");
		opt2.setBackground(Color.WHITE);
		opt2.setFocusable(false);
		opt2.setIconTextGap(8);
		opt2.setFont(optionFont);
		opt2.setBounds(620,240,760,50);
		
		opt3 = new JRadioButton();
		opt3.setName("2");		opt3.setBackground(Color.WHITE);
		opt3.setFocusable(false);
		opt3.setIconTextGap(8);
		opt3.setFont(optionFont);
		opt3.setBounds(620,300,760,50);
		
		opt4 = new JRadioButton();
		opt4.setName("3");
		opt4.setBackground(Color.WHITE);
		opt4.setFocusable(false);
		opt4.setIconTextGap(8);
		opt4.setFont(optionFont);
		opt4.setBounds(620,360,760,50);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(opt1);
		buttonGroup.add(opt2);
		buttonGroup.add(opt3);
		buttonGroup.add(opt4);
		
		//handling buttons
		nextButton = new JButton("NEXT");
		nextButton.setFont(buttonFont);
		nextButton.setBackground(new Color(248,0,78));
		nextButton.setForeground(Color.WHITE);
		nextButton.setFocusable(false);
		nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nextButton.setBorder(BorderFactory.createLineBorder(new Color(248,0,78),4,true));
		nextButton.setBounds(660,480,200,50);
		nextButton.addActionListener(event -> { timer = 20;
												repaint();
												this.matchAnswer();
												this.setQuizCard();
			}
		);
		
		lifelineButton = new JButton("50-50");
		lifelineButton.setFont(buttonFont);
		lifelineButton.setBackground(new Color(248,0,78));
		lifelineButton.setForeground(Color.WHITE);
		lifelineButton.setFocusable(false);
		lifelineButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lifelineButton.setBorder(BorderFactory.createLineBorder(new Color(248,0,78),4,true));
		lifelineButton.setBounds(920,480,200,50);
		lifelineButton.addActionListener(event -> this.lifelineFunctionality());
		
		submitButton = new JButton("SUBMIT");
		submitButton.setFont(buttonFont);
		submitButton.setBackground(new Color(248,0,78));
		submitButton.setForeground(Color.WHITE);
		submitButton.setFocusable(false);
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submitButton.setBorder(BorderFactory.createLineBorder(new Color(248,0,78),4,true));
		submitButton.setBounds(1180,480,200,50);
		submitButton.setEnabled(false);
		submitButton.addActionListener(event -> { this.matchAnswer();
													new ReportCard(this, playerName, correct, incorrect);
		});

		//adding components to the display area
		this.getContentPane().add(quizLabel);
		this.getContentPane().add(questionNumber);
		this.getContentPane().add(question);
		this.getContentPane().add(opt1);
		this.getContentPane().add(opt2);
		this.getContentPane().add(opt3);
		this.getContentPane().add(opt4);
		this.getContentPane().add(nextButton);
		this.getContentPane().add(lifelineButton);
		this.getContentPane().add(submitButton);

	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
		String clockDisplay = "Time Left : " + timer + " seconds";
		g.setFont(optionFont);
		
		if(timer > 0) {
			if(timer <= 5) {
				g.setColor(Color.RED);
			}
			g.drawString(clockDisplay,1200,80);
		}
		else if(timer == 0 && currentCard == 10) {
			this.matchAnswer();
			new ReportCard(this, playerName, correct, incorrect);
		}
		else if(timer == 0){
			g.setColor(Color.RED);
			g.drawString("Time Up!!", 1200,80);
			this.matchAnswer();
			this.setQuizCard();
			timer = 21;
		}
		
		timer--;
		
		try {
			Thread.sleep(1000);
			repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
