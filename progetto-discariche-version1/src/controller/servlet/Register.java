package controller.servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		if (user != null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		String username = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");
		String role = request.getParameter("role");
		
		if(username == null || email == null || password == null || confirmPassword == null || role  == null
				|| !confirmPassword.contentEquals(password)  || 
				(! role.contentEquals("worker")  && !role.contentEquals("manager") )||
				( !new Utility().isValidEmailAddress(email)) ){
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
  		return;
		}

		UserService userService = new UserService();

		if (userService.userExists(username, email)) {
			userService.close();
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
			return;
		
		} else {

			user = new User(0, username, email, new Utility().convertToMD5(password), false, request.getParameter("experience"), request.getParameter("photo"));			

			user.setRole(role.contentEquals("manager"));// if role is manager role is setted to 1 else if is a manger set to 0

			if(user.getRole() == true) {
				user.setExperience(null);
				user.setPhoto(null);
			} else {
				InputStream inputStream = getInputStream(request);
				if(inputStream == null) {
					request.getServletContext().getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
					System.out.println("errore inserimento immagine");
					return;
				}
				java.awt.Image image = getImageFromIS(inputStream);
				if(image == null) {
					request.getServletContext().getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
					System.out.println("errore conversione immagine");
					return;
				}
				String path  = "C:\\eclipse-workspace\\progetto-discariche-version1\\WebContent\\profilePicture\\"+user.getName()+".jpg";
				saveImage(path , image);
				user.setPhoto(path);
			}
			userService.registerUser(user);

			response.sendRedirect(request.getContextPath() + "/login");

		}
		
		

	}
	static private InputStream getInputStream(HttpServletRequest request) {
		InputStream inputStream = null;
		try {
			if (request.getPart("photo") != null) {
				inputStream = request.getPart("photo").getInputStream();
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
