package fr.vincent.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Principale extends HttpServlet {
	private static final long serialVersionUID = 2L;
    
    public Principale() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("test", "Bonjour Monsieur"); //ajoute attibut test de valeur string
		ServletContext ctx = getServletContext(); //recupere le context
		RequestDispatcher dispatcher = ctx.getRequestDispatcher("/templates/repertoire.jsp"); //envoie request vers jsp cible
		dispatcher.forward(request, response); //recupere le resultat apres envoie request
		
		//getServletContext().getRequestDispatcher("/templates/repertoire.jsp").forward(request, response);
	}

}
