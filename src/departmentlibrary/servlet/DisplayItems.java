package DepartmentLibrary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DisplayItems", loadOnStartup = 2)
public class DisplayItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayItems() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// display test data
		List<DepartmentLibraryEntry> libEntries = new ArrayList<DepartmentLibraryEntry>();
		libEntries.add(new DepartmentLibraryEntry(1, "Tablet", "Samsung Galaxy Tab 10.1", "Android 3.0", "Yes"));
		libEntries.add(new DepartmentLibraryEntry(2, "Book", "Cracking the Code Interview (6th Ed)", "2015", "No"));

		// store data in scope to be accessed by this and any other servlet
		getServletContext().setAttribute("libEntries", libEntries);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("LogIn");
			return;
		}

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