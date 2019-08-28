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
import model.beans.User;


public class ShowHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		
		
		CampaignService campaignService = new CampaignService();
		if(user.getRole() == true) {   // means that it is a manager
		ArrayList<Campaign> campaigns = campaignService.findAllCampaignbyManagerID(user.getId());
		request.setAttribute("ListCampaign", campaigns);

		campaignService.close();
		request.getServletContext().getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
		return;
		}
		else {
		// if user is a worker ( value 0/false)
		ArrayList<Campaign> available = campaignService.findAllAvailableByWorker(user.getId());//campaign started and not chosen
		ArrayList<Campaign> choosen = campaignService.findAllStartedAndChosenByWorker(user.getId());

		campaignService.close();
		request.setAttribute("CampaignAvailable", available);
		request.setAttribute("CampaignChoosen", choosen);
		request.getServletContext().getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
		
		return;
		}
		
		
		
		

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
