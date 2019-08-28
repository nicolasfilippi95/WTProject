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
import model.beans.Note;
import model.beans.User;
import utilities.Utility;


public class ShowDetailsImage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user= (User) session.getAttribute("User");

		Utility u = new Utility();
		Integer imageId = u.convertToInteger(request.getParameter("imageid"));
		if(imageId ==null){
			response.sendRedirect(request.getContextPath() + "/showStatistics");
			return;

		}
		CampaignService campaignService = new CampaignService();
		ArrayList<Note> noteList = campaignService.findAllNotesByImage(imageId);

		request.setAttribute("ListNote", noteList);

		campaignService.close();

		request.getServletContext().getRequestDispatcher("/WEB-INF/view/detailsImage.jsp").forward(request, response);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
