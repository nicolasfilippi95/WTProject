package controller.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;



import controller.database.service.CampaignService;
import model.beans.Image;
import model.beans.Locality;
import model.beans.User;
import utilities.Utility;

import javax.servlet.http.Part;


@MultipartConfig(fileSizeThreshold=1024*1024*2, 
maxFileSize=1024*1024*10,     
maxRequestSize=1024*1024*50)

public class CreateLocalityAndImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR="images";
	
	
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
		Utility u = new Utility();

		
		String campaign =request.getParameter("campaignid");
		int c = Integer.parseInt(campaign);

		  PrintWriter out = response.getWriter();
		   
		  if(!ServletFileUpload.isMultipartContent(request)){
		   out.println("Nothing to upload");
		   return; 
		  }
		  FileItemFactory itemfactory = new DiskFileItemFactory(); 
		 ServletFileUpload upload = new ServletFileUpload(itemfactory);
		  try{
			  System.out.println("i am here");
			  
			  
			 List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
			  if(items.isEmpty()) {
				  System.out.println("empty  list");
			  }
			  for(FileItem item:items){
		     System.out.println("also here");
		    String contentType = item.getContentType();
		    if(!contentType.equals("image/png")){
		     out.println("only png format image files supported");
		     continue;
		    }
		    File uploadDir = new File("C:\\");
		    File file = File.createTempFile("img",".png",uploadDir);
		    item.write(file);
		 
		    out.println("File Saved Successfully");
		   }
		  }
		  catch(FileUploadException e){
		    
		   out.println("upload fail");
		  }
		  catch(Exception ex){
		    
		   out.println("can't save");
		  }
		 
		
	    
		
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
		

	/*	if(image.equals("") || image == null || origin.equals("") ||shooting_date.equals("") || resolution.equals("") || origin == null || shooting_date == null || resolution== null  ) {
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/detailsCampaign.jsp").forward(request, response);	
		System.out.println("Errore nell' inserimento immagine  ");
		campaignService.close();
		return;
		}
*/
		if(locality.equals("new")) {
			
		
			Locality local = new Locality(0, u.convertToDouble(latitude),u.convertToDouble(longitude), name, town, region, c);
			local=  campaignService.addLocality(local);
			Date date = u.convertToSqlDate(shooting_date);
			Image i =new Image(0, image, resolution, date, origin,local.getId());
			
		}
		
		response.sendRedirect(request.getContextPath() + "/showDetailsCampaign?campaignid=" + c);

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

}
