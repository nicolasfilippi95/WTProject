package controller.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.service.CampaignService;
import model.beans.Note;
import model.beans.User;
import utilities.Utility;


public class CreateNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user= (User) session.getAttribute("User");
		
		Utility u = new Utility();
		Integer imageId = u.convertToInteger(request.getParameter("imagetonote"));
		Integer campaignId = u.convertToInteger(request.getParameter("campaignid"));
		Integer localityId = u.convertToInteger(request.getParameter("localityid"));
		String validity = request.getParameter("validity");
		String reliability = request.getParameter("reliability");
		String comment = request.getParameter("comment");
		
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis); 
		
		if(imageId == null || validity== null || reliability == null || comment== null || date == null) {
			response.sendRedirect(request.getContextPath() + "/showDetailsLocality?localityid=" + localityId+"&campaignid="+ campaignId);
			return;
		}
		
		CampaignService c = new CampaignService();
		if(c.findNotesByUserAndImage(imageId, user.getId()).size() >=1) {
			response.sendRedirect(request.getContextPath() + "/showDetailsLocality?localityid=" + localityId+"&campaignid="+ campaignId);
			return;
		}
		
		Boolean val =false;
		if(validity.equals("true")) {
			val=true;
		}
		
		Note note = new Note(0,date  , val, reliability, comment, user.getId(), imageId);
		c.addNote(note);
		c.close();
		response.sendRedirect(request.getContextPath() + "/showHome");
		return;
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
