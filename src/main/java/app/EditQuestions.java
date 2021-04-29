package app;


import java.util.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/editquestions")
public class EditQuestions {
	
	ListQuestions k = new ListQuestions();
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("questions");
	
	@POST
	@Path("/getquestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	public Question readOneQuestion(@FormParam("QUESTION_ID") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question q=em.find(Question.class, id);
		em.getTransaction().commit();
		System.out.println("Haloo");
		return q;
	}
	@PUT
	@Path("/updatequestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> updateQuestion(Question Question) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question q=em.find(Question.class, Question.getQUESTION_ID());
		if (q!=null) {
			em.merge(Question);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readFish() of this service
		List<Question> list=k.readFish();		
		return list;
	}	

	
}
	
	