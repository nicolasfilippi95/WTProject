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
import model.beans.Campaign;
import model.beans.Locality;
import model.beans.User;
import utilities.Utility;


public class ShowStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user= (User) session.getAttribute("User");

		
		Utility u = new Utility();
		Integer campaignId = u.convertToInteger(request.getParameter("campaignid"));
		
		//if user is worker or campaign is null redirect to home 
		if(user.getRole() == false || campaignId == null ){
			response.sendRedirect(request.getContextPath() + "/showHome");
			return;
		}
		CampaignService campaignService = new CampaignService();
		Campaign campaign = campaignService.findcampaignById(campaignId);
		if(campaign.getStatus().equals("CREATED")) {
			response.sendRedirect(request.getContextPath() + "/showDetailsCampaign?campaignid=" +campaignId);
			return;
		}
		int numberLocality = campaignService.countLocalityByCampaign(campaignId);
		ArrayList<Locality> listLocality = campaignService.findAllLocalityByCampaign(campaignId);
		int numberImage=0;
		for(Locality l: listLocality) {
			numberImage = numberImage + campaignService.countImageByLocality(l.getId());
		}
		double averageNumberImage = numberImage/ numberLocality;
		request.setAttribute("Campaign" ,campaign);
		request.setAttribute("numberLocality" , numberLocality);
		request.setAttribute("numberImage" , numberImage);
		request.setAttribute("averageNumberImage", averageNumberImage);
		request.getServletContext().getRequestDispatcher("/WEB-INF/view/statistics.jsp").forward(request, response);
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
