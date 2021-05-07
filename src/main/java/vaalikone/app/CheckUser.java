package vaalikone.app;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckUser
 */
@WebServlet("/checkuser")
public class CheckUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("username");
		String pass=request.getParameter("userpass");
		
		//Checking the user and pass (should be checked from the database)
		if (user.compareTo("admin")==0 && pass.compareTo("63a9f0ea7bb98050796b649e85481845")==0) {
			HttpSession session=request.getSession(true);
			session.setAttribute("AuthOk", "ok");
			response.sendRedirect("/admin.jsp");
		}
		else {
			response.sendRedirect("/login.jsp");//Or perhaps to register page
			System.out.println("Haloo");
		}
	}

}