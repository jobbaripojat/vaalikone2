package app;


import java.util.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/deletequestions")
public class DeleteQuestions {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("questions");
	
	@GET
	@Path("/deletequestion")
	@Produces(MediaType.APPLICATION_JSON)
	public Question readOneQuestion(@QueryParam("question_id") int id) {
		EntityManager em=emf.createEntityManager();
		Question q=em.find(Question.class, id);
		
		em.getTransaction().begin();
		em.remove(q);
		em.getTransaction().commit();
		
		return q;
	}
}
