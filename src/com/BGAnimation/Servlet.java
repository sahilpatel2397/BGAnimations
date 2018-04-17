package com.BGAnimation;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BGAnimation.objectLayer.User;
import com.BGAnimation.persistLayer.BGAnimationPersistImpl;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_23);
    Configuration cfg = null;
    SimpleHash root = new SimpleHash(df.build());
    private String templateDir = "/WEB-INF/templates";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		 cfg = new Configuration(Configuration.VERSION_2_3_23);
	     cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
	     cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
	}
	
	 public void runTemplate(HttpServletRequest request, HttpServletResponse response, String name) throws SQLException
	    {
	        // You can use this structure for all of your objects to be sent to browser
		 Template template = null;
	     String templateName = name + ".ftl";     
	     try
	     {
	        template = cfg.getTemplate(templateName);
	        response.setContentType("text/html");
	        Writer out = response.getWriter();
	        template.process(root, out);
	          
	     }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        catch (TemplateException e)
	        {
	            e.printStackTrace();
	        }
	       // DatabaseAccess.closeConnection(con);
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String button = request.getParameter("submit");
		HttpSession session = request.getSession();
		
		int promotion;
		//if there is a register request
		if (button.equals("Register")){
			if (request.getParameter("checkbox")!=null){
				promotion = 1;
			}else{
				promotion = 0;
			}
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String username = request.getParameter("email");
			String password = request.getParameter("password");
			String confPassword = request.getParameter("confirmPassword");
			String address = request.getParameter("billingAddress");
			int activationCode = 0;
			int isBanned = 0;
			int isAdmin = 0;
			User user = new User(firstName,lastName, username, address, password, isBanned, isAdmin, activationCode, promotion);
			try {
				BGAnimationPersistImpl.addUser(user);
				session.setAttribute("user", user);
				root.put("NAME", user.getFirstName());
				runTemplate(request,response,"myaccount");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("SQL Error");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		if (button.equals("My Account")){
			if (null==session.getAttribute("user")){
				try {
					runTemplate(request,response,"loginpage");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				User user = (User)request.getSession().getAttribute("user");
				root.put("NAME", user.getFirstName());
				try {
					runTemplate(request,response,"myaccount");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if (button.equals("Forget Password?")){
			try {
				BGAnimationPersistImpl.checkUser(request.getParameter("userName"));
			} catch (NoSuchAlgorithmException | NoSuchProviderException | SQLException e) {
				try {
					runTemplate(request,response,"usernamenotrecognized.ftl");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		
		//if there is a login request
		if (button.equals("Login")){
			System.out.println("ENTERS");
			String username = request.getParameter("userName");
			String password = request.getParameter("password");
			User user1 = null;
			try {
				boolean check = BGAnimationPersistImpl.authenticateUser(username, password);
				if(!check){
					runTemplate(request,response,"loginpage-error");
					return;
				}
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchProviderException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RuntimeException e1) {
				try {
					runTemplate(request,response,"loginpage-error");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				 user1 = BGAnimationPersistImpl.getUser(username);
				 session = request.getSession();
				 session.setAttribute("user", user1);
				 root.put("NAME", user1.getFirstName());
				 if (user1.getIsAdmin()==1){
					 runTemplate(request,response,"admin.ftl");
				 }
				 else{
				 runTemplate(request,response,"myaccount");
				 }
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//if the user wants to update the profile
		if(button.equals("Update Profile")){
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String password = request.getParameter("password");
			String confPassword = request.getParameter("confirmPassword");
			String streetAdd = request.getParameter("streetAddress");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zipCode = request.getParameter("zipCode");
			User user = (User)request.getSession().getAttribute("user");
			if (password.equals(confPassword)){
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setPassword(password);
				String address = ""+streetAdd+" "+city+" "+state+" "+zipCode+" ";
				user.setAddress(address);
				root.put("NAME", user);
				try {
					runTemplate(request,response,"myaccount");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				//return loginError page
			}
		}
		
		if(button.equals("Log Out")){
			request.getSession(false).invalidate();
			try{
			response.sendRedirect("/finalProj/");
			}catch(Exception e){
				
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
