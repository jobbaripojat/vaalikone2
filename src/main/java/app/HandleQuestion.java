package app;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

@WebServlet(urlPatterns = { "/addquestion", "/deletequestion", "/updatequestion", "/readquestion",
		"/readtoupdatequestion" })
public class HandleQuestion extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getServletPath();
		List<Question> list = null;
		switch (action) {
		case "/addquestion":
			list = addquestion(request);
			break;
		case "/deletequestion":
			list = deletequestion(request);
			break;
		case "/updatequestion":
			list = updatequestion(request);
			break;
		case "/readquestion":
			list = readquestion(request);
			break;
		case "/readtoupdatequestion":
			Question q = readtoupdatequestion(request);
			request.setAttribute("question", q);
			RequestDispatcher rd = request.getRequestDispatcher("updateForm.jsp");
			rd.forward(request, response);
			return;
		}
		request.setAttribute("questionlist", list);
		RequestDispatcher rd = request.getRequestDispatcher("editableQuestions.jsp");
		rd.forward(request, response);
	}

	private Question readtoupdatequestion(HttpServletRequest request) {
		String id = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/vaalikone/questionservice/readtoupdatequestion/" + id;
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		Question question = b.get(Question.class);
		return question;
	}

	private List<Question> addquestion(HttpServletRequest request) {
		Question q = new Question(Integer.parseInt(request.getParameter("id")), request.getParameter("question"));
		String uri = "http://127.0.0.1:8080/vaalikone/questionservice/addquestion";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		System.out.println(q);
		Entity<Question> e = Entity.entity(q, MediaType.APPLICATION_JSON);
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {
		};

		List<Question> returnedList = b.post(e, genericList);
		return returnedList;
	}

	private List<Question> readquestion(HttpServletRequest request) {
		String uri = "http://127.0.0.1:8080/vaalikone/questionservice/readquestion";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {
		};

		List<Question> returnedList = b.get(genericList);
		return returnedList;
	}

	private List<Question> updatequestion(HttpServletRequest request) {
		Question q = new Question(Integer.parseInt(request.getParameter("id")), request.getParameter("question"));
		System.out.println(q);
		String uri = "http://127.0.0.1:8080/vaalikone/questionservice/updatequestion";
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		Entity<Question> e = Entity.entity(q, MediaType.APPLICATION_JSON);
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {
		};

		List<Question> returnedList = b.put(e, genericList);
		return returnedList;
	}

	private List<Question> deletequestion(HttpServletRequest request) {
		String id = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/vaalikone/questionservice/deletequestion/" + id;
		Client c = ClientBuilder.newClient();
		WebTarget wt = c.target(uri);
		Builder b = wt.request();
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {
		};

		List<Question> returnedList = b.delete(genericList);
		return returnedList;
	}

}
