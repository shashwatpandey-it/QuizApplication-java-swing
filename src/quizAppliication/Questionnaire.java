package quizAppliication;

public class Questionnaire {

	private int response_code;
	private QuizCard[] results;
	
	public int getResponse_code() {
		return response_code;
	}
	public QuizCard[] getResults() {
		return results;
	}
	public void setResponse_code(int response_code) {
		this.response_code = response_code;
	}
	public void setResults(QuizCard[] results) {
		this.results = results;
	}
	
	public Questionnaire() {
	
	}
	
	public Questionnaire(int response_code, QuizCard[] results) {
		super();
		this.response_code = response_code;
		this.results = results;
	}
	
}
