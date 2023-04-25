package quizAppliication;

class QuizCard {
	
	private String category;
	private String id;
	private String correctAnswer;
	private String[] incorrectAnswers;
	private String question;
	private String[] tags;
	private String type;
	private String difficulty;
	private String[] regions;
	
	public String getCategory() {
		return category;
	}
	public String getId() {
		return id;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public String[] getIncorrectAnswers() {
		return incorrectAnswers;
	}
	public String getQuestion() {
		return question;
	}
	public String[] getTags() {
		return tags;
	}
	public String getType() {
		return type;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public String[] getRegions() {
		return regions;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public void setIncorrectAnswers(String[] incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public void setRegions(String[] regions) {
		this.regions = regions;
	}
	
	
}
