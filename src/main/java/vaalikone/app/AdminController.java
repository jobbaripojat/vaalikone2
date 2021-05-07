package vaalikone.app;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Servlet implementation class AdminController
 */
@SuppressWarnings("serial")
@WebServlet(name = "AdminController", urlPatterns = { "/admin" })
public class AdminController extends HttpServlet {

	AdminModel model = new AdminModel();
	DatabaseConnection db = new DatabaseConnection();

	static String formAction = "/add";

	public AdminController() {

	}

	
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws ServletException, IOException {

          HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            HttpSession session = request.getSession();

            if (session == null || session.getAttribute("User") == null) {
                  response.sendRedirect(request.getContextPath() + "/index.xhtml"); // No logged-in user found, so redirect to login page.
            } else {
                chain.doFilter(req, res); // Logged-in user found, so just continue request.
            }
    }

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Initialize();
			processRequest(request, response);
		} catch (Exception e) {
			System.out.println("Initialization failed!");
			e.printStackTrace();
		}
	}

	protected void Initialize() throws Exception {
		db.TestConnection();
		model.db = this.db;
		try {
			model.candidateCount = db.CountCandidates();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintAllCandidates(request, response);
	}

	/**
	 * Creates the HTML used to display a candidate in a table. Buttons direct to
	 * Delete.java and Fetch.java.
	 * 
	 * @param	idx				index of the candidate in the list
	 * @return 					a string that contains the HTML to create a cell for edit/delete list
	 */
	protected String GenerateCandidateCell(int idx) {
		ArrayList<String> CANDIDATE = model.LIST_OF_CANDIDATES.get(idx);

		String addToFile = "<tr>";

		addToFile += "<th scope='row'>" + CANDIDATE.get(0) + "</th>";
		addToFile += "<td>" + CANDIDATE.get(2) + "</td>";
		addToFile += "<td>" + CANDIDATE.get(1) + "</td>";
		addToFile += "<td><a href='/fetch?candidate_id=" + CANDIDATE.get(0)
				+ "' class='btn btn-primary' role='button'>Edit</button></td>";
		addToFile += "<td><a href='/delete?candidate_id=" + CANDIDATE.get(0)
				+ "' class='btn btn-danger' role='button'>Delete</button></td>";
		addToFile += "</tr>";

		return addToFile;
	}

	/**
	 * Goes through all candidates and uses GenerateCandidateCell to create the full
	 * table HTML. These are then sent to admin.jsp.
	 */
	protected void PrintAllCandidates(HttpServletRequest request, HttpServletResponse response) {
		try {
			model.GetCandidates();
			String addToFile = "";

			for (int i = 0; i < model.candidateCount; i++) {
				addToFile += GenerateCandidateCell(i);
			}

			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			request.setAttribute("form_action", formAction);
			formAction = "/add";
			request.setAttribute("candidates", addToFile);
			rd.forward(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}