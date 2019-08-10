package controller.servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.service.UserService;
import model.beans.User;
import utilities.Utility;

@MultipartConfig
public class ModifyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		
		//get data from request
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password =request.getParameter("password");
		String confirmPassword= request.getParameter("passwordconfirm");
	
		
		UserService userService = new UserService();
		String exp=null;
		InputStream inputStream =null;
		java.awt.Image image=null;
		//in case of error return to profile' s page
		if(user.getRole() ==true) {
			if(name== null  || email == null || password == null || confirmPassword==null ||
					name.contentEquals("") && email.contentEquals("")&& password.contentEquals("")
					&&confirmPassword.contentEquals("")|| !email.contentEquals("") && !new Utility().isValidEmailAddress(email)) {
				request.getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
				return;
			}

		}

		if(user.getRole() == false) {
			exp = request.getParameter("experience");
			inputStream = getInputStream(request);
			if(name== null || email == null || password == null || confirmPassword==null|| exp==null ||
					name.contentEquals("") && email.contentEquals("")&& password.contentEquals("") &&exp.contentEquals("")&&inputStream==null
					&&confirmPassword.contentEquals("")|| !email.contentEquals("") && !new Utility().isValidEmailAddress(email)) {
				request.getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
				return;
			}
			if(inputStream != null) {
				image = getImageFromIS(inputStream);
				if(image == null) {
					request.getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
					System.out.println("errore conversione immagine");
					return;
				}
			}
		}
		if((name != "" || email !="")  &&  userService.userExists(name, email)) {
			userService.close();
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
			return;

 		}
		//else get and remove that user from session

		session.removeAttribute("User");


		//update in user the element that have been modified
		if(!name.equals("") && name != null ) {
			user.setName(name);
			
		}
		if(!email.equals("") && email != null) {
			user.setEmail(email);
		}
		if(password !="" && password !=null && password.contentEquals("confirmPassword")) {
			user.setPassword(password);
		}
		if(user.getRole() == false) {
			if(exp != user.getExperience()) {
				user.setExperience(exp);
			}
			if(image!= null) {
				
				String pathToDelete = user.getPhoto();
				Path pathDelete = Paths.get(pathToDelete);
				Files.delete(pathDelete);
				String newPath = "C:\\eclipse-workspace\\progetto-discariche-version1\\WebContent\\profilePicture\\"+user.getName()+".jpg";
				saveImage(newPath , image);
				user.setPhoto(newPath);

			}
		}

		//write in database 
		userService.update(user);
		userService.close();

		//put in session
		session.setAttribute("User", user);
		response.sendRedirect(request.getContextPath() + "/showHome");
		return;


	}

	static private InputStream getInputStream(HttpServletRequest request) {
		InputStream inputStream = null;
		try {
			if (request.getPart("newphoto") != null) {
				inputStream = request.getPart("newphoto").getInputStream();
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
