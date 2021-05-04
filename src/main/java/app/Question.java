package app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	
	private int QUESTION_ID;
	private String QUESTION;
	
	public int getQUESTION_ID() {
		return QUESTION_ID;
	}
	
	public void setQUESTION_ID(int QUESTION_ID) {
		this.QUESTION_ID = QUESTION_ID;
	}
	
	public String getQUESTION() {
		return QUESTION;
	}
	
	public void setQUESTION(String QUESTION) {
		this.QUESTION = QUESTION;
	}
	
	public Question() {
		super();
	}
	
	public Question(int QUESTION_ID) {
		
	}
	
	public Question(String QUESTION) {
		
	}
	
	public Question(int QUESTION_ID, String QUESTION) {
		this.QUESTION_ID=QUESTION_ID;
		this.QUESTION=QUESTION;
	}
	
	public String toString() {
		return QUESTION_ID+" "+QUESTION;
	}
	
}