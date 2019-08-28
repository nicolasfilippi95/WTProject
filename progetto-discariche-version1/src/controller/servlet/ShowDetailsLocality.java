package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.service.CampaignService;
import model.beans.Image;
import model.beans.Locality;
import model.beans.Note;
import model.beans.User;
import utilities.Utility;



public class ShowDetailsLocality extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get session authenticator
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");

		// get data
		Utility u = new Utility();
		Integer localityId = u.convertToInteger(request.getParameter("localityid"));

		Integer campaignId = u.convertToInteger(request.getParameter("campaignid"));

		if (localityId == null ||  campaignId == null) {
			response.sendRedirect(request.getContextPath() + "/showHome");
			return;
		}

		CampaignService campaignService = new CampaignService();
		ArrayList<Image> listImage = campaignService.findAllImageByLocality(localityId);
		ArrayList<Note> listNote = new ArrayList<Note>();
	
		Locality loc = campaignService.findLocalityById(localityId);
if(loc != null) {
	
request.setAttribute("Locality", loc);
		for(Image l : listImage) {
			ArrayList<Note> noteByimage =	campaignService.findAllNotesByImage(l.getId());
			for(Note n : noteByimage ) {
				listNote.add(n);
			}}
			request.setAttribute("ListImage", listImage);
			request.setAttribute("ListNote", listNote);
			campaignService.close();
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/detailsLocality.jsp").forward(request,response);
			return;
		
		
		
}else {
	response.sendRedirect(request.getContextPath() + "/showHome");
	return;
}



	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

