package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import controller.database.service.CampaignService;

import dto.LocalityDTO;
import model.beans.Campaign;
import model.beans.Image;
import model.beans.Locality;
import model.beans.Note;
import model.beans.User;
import utilities.Utility;


public class ConvertToJson extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get session authenticator
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");

		// get data
		Utility u = new Utility();
		Integer campaignId = u.convertToInteger(request.getParameter("campaignid"));
		
		// if wrong data redirect to home
		if(campaignId==null) {
			response.sendRedirect(request.getContextPath() + "/showHome");
			return;
		}

		CampaignService campaignService = new CampaignService();
		Campaign c = campaignService.findcampaignById(campaignId);

		// if campaign does not exists or user is a worker but is not joined or user is
		// a manager but campaign belong to another manager redirect to home
		if (c == null ||  user.getRole() == false && !campaignService.isJoined(user.getId(), campaignId)
				|| user.getRole()== true && c.getUserId() != user.getId()) {
			campaignService.close();
			response.sendRedirect(request.getContextPath() + "/showHome");
			return;

		}
		ArrayList<LocalityDTO> listLocDTO = createListLocalityToSend(campaignId);
		campaignService.close();

		
		//else get all locality of that campaign and forward to the map
		response.getWriter().write(createJSONFrom(listLocDTO));
		return;
		//request.getServletContext().getRequestDispatcher("/WEB-INF/view/map.jsp?campaignid=" + campaignId.toString(), createJSONFrom(listLocDTO)) .forward(request, response);
		//return;
	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	//get locality to send 
	private ArrayList<LocalityDTO> createListLocalityToSend(Integer campaignId){

		// get all locality that belong to campaign with Id
		CampaignService campaignService = new CampaignService();
		ArrayList<Locality> localities = campaignService.findAllLocalityByCampaign(campaignId);
		ArrayList<LocalityDTO> locDTO = new ArrayList<>();
		// set locality color
		// red = #d50000; green = #4CAF50; yellow = #FFEA00;
		for(Locality l : localities) {
			Boolean conflict = false;
			String color = null;
			ArrayList<Image> listImage= campaignService.findAllImageByLocality(l.getId());
			int numberImage = campaignService.countImageByLocality(l.getId());
			if( numberImage == 0){
				color = "#FFEA00";
			}else {
				int numberNote =0;
				for(Image p : listImage) {
					

					numberNote += campaignService.countNoteByImage(p.getId());
					if(campaignService.isThereConflict(p.getId())) {
						System.out.println("here fdf");
						color = "#d50000";
						conflict =true;
						
						break;
					}
				}

				if(!conflict){
					if(numberNote ==0){
						color = "#FFEA00";
					}if(numberNote > 0) {
						color = "#4CAF50";
					}
				}


			}
			locDTO.add(new LocalityDTO(l.getId(), l.getLatitude(), l.getLongitude(), l.getName(), l.getTown(), l.getRegion(), l.getCampaignId(), color));


		}
		campaignService.close();
		return locDTO;
	}

	// Convert a locality list to JSON string

	private String createJSONFrom(ArrayList<LocalityDTO> localities) {
		try {
			String json = new Gson().toJson(localities, new TypeToken<ArrayList<Locality>>() {}.getType());
			return json;
		}catch(JsonIOException e) {
			e.printStackTrace();
		}
		return null;

	}

	

}

