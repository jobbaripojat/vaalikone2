package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/questions")
public class ListQuestions {
	private int questionCount;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("questions");
	
	@GET
	@Path("/list")
	@Produces(MediaType.TEXT_HTML)
	public String SayHello() {
		readFish();
		EntityManager em = emf.createEntityManager();
		String addToFile = "";
		for (int i = 1; i <= questionCount; i++) {
			em.getTransaction().begin();
			Question q = em.find(Question.class, i);
			em.getTransaction().commit();
			addToFile += "<div class='row question-box pt-3 pb-4 mt-5 d-flex justify-content-center'>";
			addToFile += "<div class='col-12 row d-flex align-items-center'>";
			addToFile += "<div class='col-3'></div>";
			addToFile += "<h5 class='col-6 mt-2 mb-3 text-center'>";
			addToFile += q.getQUESTION();
			addToFile += "</h5>";
			addToFile += "<div class='col-3'></div>";
			addToFile += "</div>";
			addToFile += "</div>";
		}
		return addToFile;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Question> readFish() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Question> list = em.createQuery("SELECT x FROM Question x").getResultList();		
		em.getTransaction().commit();
		questionCount = list.size();
		System.out.println(questionCount);
		return list;
	}	
}