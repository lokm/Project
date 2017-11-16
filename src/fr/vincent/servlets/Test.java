package fr.vincent.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final String PAGE_AGE = "${pageContext.request.contextPath}/home";
	private final String PAGE_MAJEUR = "${pageContext.request.contextPath}/home";
	private final String PAGE_MINEUR = "${pageContext.request.contextPath}/home";
	
    public Test() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher;
		String ageStr = request.getParameter("age");
		String pageJsp = PAGE_AGE;
		request.setAttribute("page", "page");

		
		if	(ageStr != null && !ageStr.isEmpty()) {
			
			try {
				int age = Integer.parseInt(ageStr);
				age = Math.abs(age);
				
				if (age >= 18) {
					pageJsp = PAGE_MAJEUR;
				} else {
					pageJsp = PAGE_MINEUR;
				}
				
			} catch (NumberFormatException e) {}
			
			dispatcher = ctx.getRequestDispatcher(pageJsp);
			dispatcher.forward(request, response);
		}
	}
}
