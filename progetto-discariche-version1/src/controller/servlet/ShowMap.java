package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.service.CampaignService;
import model.beans.Campaign;
import model.beans.User;
import utilities.Utility;




public class ShowMap extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		Utility u = new Utility();
		Integer campaignId = u.convertToInteger(request.getParameter("campaignid"));

		// if wrong data redirect to home
		if (campaignId == null) {
			response.sendRedirect(request.getContextPath() + "/showHome");
			return;
		}
		
		CampaignService campaignService = new CampaignService();
		Campaign campaign =  campaignService.findcampaignById(campaignId);
		campaignService.close();

		if (campaign== null  ) {
			response.sendRedirect(request.getContextPath() + "/showHome");
			return;
			
		}
		
		
		

		request.getServletContext().getRequestDispatcher("/WEB-INF/view/map.jsp?campaignid=" + campaignId.toString()).forward(request, response);
		return;
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
