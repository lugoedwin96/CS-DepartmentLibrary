package DepartmentLibrary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LogIn() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.getRequestDispatcher( "/WEB-INF/LogIn.jsp" ).forward( request, response );
//		
//		out.println( "<html>" );
//        out.println( "<head><title>Login</title></head>" );
//        out.println( "<body>" );
//
//        out.println( "<form action='LogIn' method='post'>" );
//        out.println( "Username: <input type='text' name='username' /> <br />" );
//        out.println( "Password: <input type='password' name='password' /> <br />" );
//        out.println( "<input type='submit' name='login' value='LogIn' /> <br />" );
//        out.println( "</form>" );
//        if(x > 0){
//        	out.println("<p>Incorrect password, try again.</p>");
//        }
//        out.println( "</body></html>" );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		if(request.getParameter("username").equals("cysun") && request.getParameter("password").equals("abcd")){
			request.getSession().setAttribute("user", "cysun");
			response.sendRedirect("DisplayItems");
		} else {
			response.sendRedirect("LogIn");
		}
//		} else if(!username.equals("cysun") && password.equals("abcd")){
//			out.println("<p>Wrong username.</p>");
//			response.sendRedirect("LogIn");
//		} else if(!username.equals("cysun") && !password.equals("abcd")){
//			out.println("<p>Wrong username and password.</p>");
//			response.sendRedirect("LogIn");		}
	}

}
