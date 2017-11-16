package fr.vincent.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.vincent.beans.Adresse;
import fr.vincent.beans.Personne;
import fr.vincent.beans.Repertoire;
import fr.vincent.utils.Constantes;
import fr.vincent.utils.Groupe;

@WebServlet(
		name="groupe",
		description="Equivalence à /repertoire",
		urlPatterns="/repertoire/*",
		initParams= {@WebInitParam(name="BgColor", value="red")}
		)
public class Principale extends HttpServlet implements Constantes {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Repertoire> reps = (ArrayList<Repertoire>) getServletContext().getAttribute("reps");
		
		if	(null == reps) {
			reps = new ArrayList<Repertoire>();
			
			reps.add(new Repertoire("Repertoire principale"));
			reps.add(new Repertoire("Repertoire secondaire"));	
			
			Personne personne;
			
			int cpt = 0;

			for (String[][] contact : CONTACTS) {
				personne = new Personne(
						contact[1][0],
						contact[1][1],
						contact[1][2],                 
						new Adresse(
								contact[0][0],
								contact[0][1],
								contact[0][2]
								)
						);
				
				reps.get(cpt%2).addPersonne(personne);
				cpt++;
			}
			
			getServletContext().setAttribute("reps", reps);
        }
		
		boolean redirect = checkIsFavori(req, reps);
		
		  List<Groupe> groupes = new ArrayList<Groupe>();
		  groupes.add(Groupe.AMIS);
		  groupes.add(Groupe.FAMILLE);
		  groupes.add(Groupe.COLLEGUE);
		
		  req.setAttribute("groupes", groupes);
       
        if (!redirect) {
        	getServletContext().getRequestDispatcher("/templates/repertoire.jsp").forward(req, resp);        	        	
        } else {
        	resp.sendRedirect(req.getContextPath()+"/repertoire");
        }
       
	}
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Repertoire> reps = (ArrayList<Repertoire>) getServletContext().getAttribute("reps");
		String uri = req.getRequestURI();
		String[] tab = uri.split("/");
		String nomPersonne = tab[tab.length-1];
		nomPersonne = URLDecoder.decode(nomPersonne, "UTF-8");
		String groupeStr = req.getParameter("choixGroupe");
		Groupe groupe = Groupe.valueOf(groupeStr.toUpperCase());
		
		for	(Repertoire rep : reps) {
			for	(Personne p : rep.getListePersonnes()) {
				if (nomPersonne.equals(p.getNom())) {
					p.setGroupe(groupe);
				}
			}
		}
		
		resp.sendRedirect(req.getContextPath()+"/repertoire");
	}
	
		private boolean checkIsFavori(HttpServletRequest req, List<Repertoire> rs) throws UnsupportedEncodingException {
			
			String uri = req.getRequestURI();
			boolean uriContaintsFavori = uri.contains("favori");
			String[] tab = uri.split("/");
			String nomPersonne = uriContaintsFavori ? tab[tab.length-2] : tab[tab.length-1];
			nomPersonne = URLDecoder.decode(nomPersonne, "UTF-8");
			
			for (Repertoire rep : rs) {
				for (Personne p : rep.getListePersonnes()) {
					if	(nomPersonne.equals(p.getNom())) {
						p.setFav(uriContaintsFavori);
						return true;
						
					}
				}
			}
			
			return false;
		}
	
}

