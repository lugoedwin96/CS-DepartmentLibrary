package DepartmentLibrary.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DisplayItems", loadOnStartup = 1)
public class DisplayItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayItems() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 if (request.getSession().getAttribute("user") == null) {
		 response.sendRedirect("LogIn");
		 return;
		 }
		 
		List<DepartmentLibraryEntry> libEntries = new ArrayList<DepartmentLibraryEntry>();

		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu15";
			String username = "cs3220stu15";
			String password = "thfs30eq";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from departmentlibrary");

			while (rs.next())
				libEntries.add(new DepartmentLibraryEntry(
						rs.getInt("id"), 
						rs.getString("type"), 
						rs.getString("name"),
						rs.getString("additionalInfo"), 
						rs.getString("available")));
			c.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

		request.setAttribute("libEntries", libEntries);
		request.getRequestDispatcher("/WEB-INF/DisplayItems.jsp").forward(request, response);

		// get the data
		// @SuppressWarnings("unchecked")
		// List<DepartmentLibraryEntry> libEntries =
		// (List<DepartmentLibraryEntry>)
		// getServletContext().getAttribute("libEntries");
		//
		// // display the data
		// response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		// out.println("<html><head><title>Department
		// Library</title></head><body>");
		// out.println("<p>User: " +
		// request.getSession().getAttribute("user").toString() + "</p>");
		// out.println("<table border='1' >");
		// out.println("<tr><th>ID</th><th>Type</th><th>Name</th><th>Additional
		// Info</th><th>Available</th><th>Operation</th></tr>");
		// for( DepartmentLibraryEntry entry : libEntries ){
		// out.println(
		// "<tr>"
		// + "<td>" + entry.getId() + "</td>"
		// + "<td>" + entry.getType() + "</td>"
		// + "<td>" + entry.getName() + "</td>"
		// + "<td>" + entry.getAdditionalInfo() + "</td>"
		// + "<td>" + entry.getAvailable() + "</td>"
		// + "<td><a href='EditItem?id=" + entry.getId() + "'>Edit</a></td>"
		// + "</tr>"
		// );
		// }
		//
		// out.println( "</table>" );
		// out.println( "<p><a href='AddItems'>Add Items</a> | <a
		// href='LogOut'>Logout</a></p>" );
		//
		// out.println( "</body></html>" );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}