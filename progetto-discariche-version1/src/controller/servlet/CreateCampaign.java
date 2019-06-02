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


public class CreateCampaign extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get User from session		
		HttpSession session =request.getSession();
		User user = (User) session.getAttribute("User");
		
		//get data from form of creation new campaign
		String name = request.getParameter("name");
		String customer = request.getParameter("customer");
		
		//if there is error return to home 
		if(name==null || customer==null || name.contentEquals("") || name.contentEquals("")) {
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
		}
		
		//aggiungi nuova campagna
		CampaignService campaignService =new CampaignService();
		campaignService.add(new Campaign(0, user.getId(), name, customer, "CREATED"));
		campaignService.close();
		
		
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
