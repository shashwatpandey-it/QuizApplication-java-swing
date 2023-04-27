package quizAppliication;

class QuizCard {
	
	private String category;
	private String type;
	private String difficulty;
	private String question;
	private String correct_answer;
	private String[] incorrect_answers;
	
	
	public String getCategory() {
		return category;
	}
	public String getType() {
		return type;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public String getQuestion() {
		return question;
	}
	public String getCorrect_answer() {
		return correct_answer;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}
	public void setIncorrect_answers(String[] incorrect_answers) {
		this.incorrect_answers = incorrect_answers;
	}
	public String[] getIncorrect_answers() {
		return incorrect_answers;
	}

}
