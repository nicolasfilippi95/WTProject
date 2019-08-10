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


public class CloseCampaign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get session authenticator
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");

		// get data
		Utility u = new Utility();
		String id = request.getParameter("campaignid");
		Integer campaignId = u.convertToInteger(request.getParameter("campaignid"));

		// if user is a worker redirect to home
		if (user.getRole() == false || campaignId == null) {
			response.sendRedirect(request.getContextPath() +  "/showHome");
			return;
		}
		
		CampaignService campaignService = new CampaignService();
		Campaign campaign = campaignService.findcampaignById(campaignId);
		if(campaign.getStatus().equals("STARTED")){
			campaign.setStatus("CLOSED");
			campaignService.updateCampaign(campaign);
		}
		response.sendRedirect(request.getContextPath() + "/showDetailsCampaign?campaignid=" + campaign);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
