package controller.servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
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


@MultipartConfig
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
		String origin = request.getParameter("origin");
		String shooting_date = request.getParameter("shooting_date");
		String resolution = request.getParameter("resolution");
		Utility u = new Utility();
		int number =1;

		String campaign =request.getParameter("campaignid");
		int c = Integer.parseInt(campaign);
		Date date = u.convertToSqlDate(shooting_date);



		if(!locality.equals("new") && (!(latitude.equals("")|| latitude ==null)  || !(longitude.equals("") || longitude== null)  || !(name.equals("") || name ==null ) || !(town.equals("") || town == null)   || !(region.equals("") || region  == null) )) {
			response.sendRedirect(request.getContextPath() +"/showDetailsCampaign?campaignid=" + c);
			System.out.println("E' stata selezionata località preesistente e poi sono stati inseriti dati nella form");
			return;
		}

		CampaignService campaignService = new CampaignService();
		if(locality.equals("new") && ((campaignService.findLocalityByNameTownRegion(name,town,region))!= null || latitude.equals("") || longitude.equals("") || name.equals("") || town.equals("")  || region.equals("") || latitude == null || longitude == null || name == null || town == null  || region == null  ) ) {
			response.sendRedirect(request.getContextPath() +"/showDetailsCampaign?campaignid=" + c);
			System.out.println("E' stata selezionata località nuova e non è stato inserito uno delle nuove info nuova localita'");
			campaignService.close();
			return;
		}

		InputStream inputStream = getInputStream(request);

		if(inputStream == null || origin.equals("") ||shooting_date.equals("") || resolution.equals("") || origin == null || shooting_date == null || resolution== null  ) {
			
			response.sendRedirect(request.getContextPath() +"/showDetailsCampaign?campaignid=" + c);
			System.out.println("Errore nell' inserimento immagine");
			campaignService.close();
			return;
		}
		
		java.awt.Image image = getImageFromIS(inputStream);
		if(image == null) {
			response.sendRedirect(request.getContextPath() +"/showDetailsCampaign?campaignid=" + c);
			System.out.println("Errore nella conversione InputStream -immagine ");
			return;
		}
		
		Locality local;
		if(locality.equals("new")) {


			local = new Locality(0, u.convertToDouble(latitude),u.convertToDouble(longitude), name, town, region, c);
			local=  campaignService.addLocality(local);


		}else {
			int localityId = u.convertToInteger(locality);
			local = campaignService.findLocalityById(localityId );
			number = number + campaignService.countImageByLocality(localityId);
		}

		String path  = "C:\\eclipse-workspace\\progetto-discariche-version1\\WebContent\\images\\"+local.getName()+ number+".jpg";
		saveImage(path , image);

		Image img =new Image(0, path, resolution, date, origin, local.getId());
		campaignService.addImage(img);

		response.sendRedirect(request.getContextPath() + "/showDetailsCampaign?campaignid=" + c);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}


	static private InputStream getInputStream(HttpServletRequest request) {
		InputStream inputStream = null;
		try {
			if (request.getPart("aFile") != null) {
				inputStream = request.getPart("aFile").getInputStream();
			}
			if (inputStream == null || inputStream.available() == 0) {
				return null;
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
			return null;
		}
		return inputStream;
	}

	static private java.awt.Image getImageFromIS(InputStream inputStream) {
		try {
			return ImageIO.read(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}
	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(java.awt.Image img)
	{
		if (img instanceof BufferedImage)
		{
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}


	static private void saveImage(String path, java.awt.Image img) {
		//String path parametro 

		//	File file =new File(path);
		File file  = new File(path);


		BufferedImage bufferedImage = toBufferedImage(img);
		try {
			ImageIO.write(bufferedImage, "jpg", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
