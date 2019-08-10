package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.service.CampaignService;
import model.beans.Campaign;
import model.beans.Locality;
import model.beans.User;
import utilities.Utility;



public class StartCampaign extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		if(campaign.getStatus().equals("CREATED")) {
			if(campaignService.countLocalityByCampaign(campaignId) >= 1) {

				ArrayList<Locality> localityList = new ArrayList<Locality>();
				localityList =campaignService.findAllLocalityByCampaign(campaignId);
				Boolean imageAssociate = false;
				for(Locality l : localityList) {
					if(campaignService.countImageByLocality(l.getId())>=1){
						imageAssociate =true;
					}
				}

				if(imageAssociate ==true ) {
					campaign.setStatus("STARTED");
					campaignService.updateCampaign(campaign);
				}
			}
		}
response.sendRedirect(request.getContextPath() + "/showDetailsCampaign?campaignid=" + campaign);
return;








	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

}
