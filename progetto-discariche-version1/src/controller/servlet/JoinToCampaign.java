package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.service.CampaignService;
import model.beans.User;
import utilities.Utility;




public class JoinToCampaign extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get session authenticator
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");

		// get data
		Utility u = new Utility();
		Integer campaignId = u.convertToInteger(request.getParameter("campaignid"));

		// if user is manager redirect to home
		if (user.getRole() == true || campaignId == null) {
			response.sendRedirect(request.getContextPath() + "/showHome");
			return;
		}

		CampaignService campaignService = new  CampaignService();

		// if worker is not already joined, join and redirect to map
		if (campaignService.workerCanJoin(user.getId(), campaignId)) {
			campaignService.joinToCampaign(user.getId(), campaignId);
			campaignService.close();

			response.sendRedirect(request.getContextPath() + "/showMap?campaignid=" + campaignId);
			return;
		}
		campaignService.close();
		// else redirect to home
		response.sendRedirect(request.getContextPath() + "/showHome");
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
