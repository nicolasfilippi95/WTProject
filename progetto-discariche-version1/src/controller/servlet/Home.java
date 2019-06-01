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


public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		
		
		CampaignService campaignService = new CampaignService();
		if(user.getRole()== true) {   // means that it is a manager
		ArrayList<Campaign> campaigns = campaignService.findAllCampaignbyManagerID(user.getId());
		request.setAttribute("Campaign", campaigns);
		campaignService.close();
		request.getServletContext().getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
		
		
		
		}
		
		
		
		

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
