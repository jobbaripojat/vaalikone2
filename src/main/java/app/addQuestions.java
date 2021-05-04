package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import app.Question;

@Path("/addquestions")
public class addQuestions {

	
	@POST
	@Path("/addquestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Question addQuestion( @FormParam("question") String question, @FormParam("id") int question_id) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("questions");
		EntityManager em=emf.createEntityManager();
		EntityTransaction entr=em.getTransaction();
		entr.begin();
		
		Question kysymys = new Question();
		kysymys.setQUESTION(question);
		kysymys.setQUESTION_ID(question_id);
		
		em.persist(kysymys);
		entr.commit();
		em.close();
		
		
		return kysymys;
	}
}

