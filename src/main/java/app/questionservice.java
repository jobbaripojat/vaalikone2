package app;


import java.util.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/questionservice")
public class questionservice {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("questions");

	@GET
	@Path("/readquestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> readQuestion() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Question> list = em.createQuery("select xyx from Question xyx").getResultList();		
		em.getTransaction().commit();
		return list;
	}	

	@GET
	@Path("/readtoupdatequestion/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Question readToUpdateQuestion(@PathParam("id") int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Question q = em.find(Question.class, id);
		em.getTransaction().commit();
		return q;
	}
	
	@PUT
	@Path("/updatequestion")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> updateQuestion(Question Question) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Question q = em.find(Question.class, Question.getQUESTION_ID());
		if (q != null) {
			em.merge(Question);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readQuestion() of this service
		List<Question> list = readQuestion();		
		return list;
	}	
		
	
	@DELETE
	@Path("/deletequestion/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> deleteQuestion(@PathParam("id") int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Question q = em.find(Question.class, id);
		if (q != null) {
			em.remove(q);
		}
		em.getTransaction().commit();
		List<Question> list = readQuestion();		
		return list;
	}	
	
	
	@POST
	@Path("/addquestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> addQuestion(Question question) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(question);
		em.getTransaction().commit();
		List<Question> list = readQuestion();		
		return list;
	}
}

	

	
	