package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import app.Question;

@Path("/addquestions")
public class addQuestions {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("questions");
	
	@POST
	@Path("/addquestion")
	@Consumes("application/x-www-form-urlencoded")
	public Question addQuestion(@FormParam("question") String question) {
		EntityManager em=emf.createEntityManager();
		
		Question kysymys= new Question(question);
		
		em.getTransaction().begin();
		em.persist(kysymys);//The actual insertion line
		em.getTransaction().commit();
		return kysymys;
	}
}
