package controller.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.service.CampaignService;
import model.beans.Image;
import model.beans.Locality;
import model.beans.User;
import utilities.Utility;



public class CreateLocalityAndImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		
		//get data from request
		String locality = request.getParameter("localityWithSelect");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String name = request.getParameter("name");
		String town = request.getParameter("town");
		String region = request.getParameter("region");
		String image = request.getParameter("myImage");
		String origin = request.getParameter("origin");
		String shooting_date = request.getParameter("shooting_date");
		String resolution = request.getParameter("resolution");
		
		
		String campaign =request.getParameter("campaignid");
		int c = Integer.parseInt(campaign);
		
		if(!locality.equals("new") && (!(latitude.equals("")|| latitude ==null)  || !(longitude.equals("") || longitude== null)  || !(name.equals("") || name ==null ) || !(town.equals("") || town == null)   || !(region.equals("") || region  == null) )) {
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/detailsCampaign.jsp").forward(request, response);
			System.out.println("E' stata selezionata località preesistente e poi sono stati inseriti dati nella form");
		return;
		}
		CampaignService campaignService = new CampaignService();
		if(locality.equals("new") && (campaignService.findLocalityByName(name)!= null || latitude.equals("") || longitude.equals("") || name.equals("") || town.equals("")  || region.equals("") || latitude == null || longitude == null || name == null || town == null  || region == null  ) ) {
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/detailsCampaign.jsp").forward(request, response);
			System.out.println("E' stata selezionata località nuova e non è stato inserito uno delle nuove info nuova localita'");
		campaignService.close();
			return;
		}
		if(image.equals("") || image == null || origin.equals("") ||shooting_date.equals("") || resolution.equals("") || origin == null || shooting_date == null || resolution== null  ) {
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/detailsCampaign.jsp").forward(request, response);	
		System.out.println("Errore nell' inserimento immagine  ");
		campaignService.close();
		return;
		}

		if(locality.equals("new")) {
			Utility u = new Utility();
		
			Locality local = new Locality(0, u.convertToDouble(latitude),u.convertToDouble(longitude), name, town, region, c);
			local=  campaignService.addLocality(local);
			Date date = u.convertToSqlDate(shooting_date);
			Image i =new Image(0, image, resolution, date, origin,local.getId());
			campaignService.add
		}
		
		response.sendRedirect(request.getContextPath() + "/showDetailsCampaign?campaignid=" + c);

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

}
