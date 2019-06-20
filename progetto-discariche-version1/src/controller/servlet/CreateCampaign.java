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
		if(user.getRole()== false || name==null || customer==null || name.contentEquals("") || customer.contentEquals("")) {
	    response.sendRedirect(request.getContextPath() + "/showHome");
		return;
		}
		
		//aggiungi nuova campagna
		CampaignService campaignService =new CampaignService();
		Campaign c= new Campaign(0, user.getId() , name, customer, "CREATED");
		c = campaignService.add(c);
		System.out.println(c.getId());
		campaignService.close();
		response.sendRedirect(request.getContextPath() + "/showDetailsCampaign?campaignid="+c.getId());
		return;
		
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
