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
import model.beans.Image;
import model.beans.Locality;
import model.beans.User;
import utilities.Utility;


public class ShowDetailsCampaign extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user= (User) session.getAttribute("User");

		Utility u = new Utility();
		Integer campaignId = u.convertToInteger(request.getParameter("campaignid"));
		//if user is worker or campaign is null redirect to home 
		if(user.getRole() == false || campaignId == null){
			response.sendRedirect(request.getContextPath() + "/showHome");
			return;
		}
		//else search campaign with that id  ad if campaign exist redirect to det2 ail page
		CampaignService campaignService = new CampaignService();
		Campaign campaign = campaignService.findcampaignById(campaignId);
		ArrayList<Locality> locality = campaignService.findAllLocalityByCampaign(campaignId);


		if(campaign != null && campaign.getUserId() == user.getId() ) {
			request.setAttribute("Campaign" ,campaign );
			request.setAttribute("ListLocality",locality);
			ArrayList<Image> ListImage= new ArrayList<Image>();
			for(Locality l : locality) {
				ArrayList<Image> image = campaignService.findAllImageByLocality(l.getId());
				for(Image pic : image) {
					ListImage.add(pic);
					System.out.println("hello" + pic.getPicture());
				}
			}
			request.setAttribute("ListImage", ListImage);
			campaignService.close();
	
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/detailsCampaign.jsp").forward(request, response);
			
			return;
		}  

		response.sendRedirect(request.getContextPath() + "/showHome");
		return;


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
