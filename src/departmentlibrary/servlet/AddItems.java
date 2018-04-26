package DepartmentLibrary.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddItems")
public class AddItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int idSeed = 3;

	public AddItems() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getSession().getAttribute("user") == null){
			response.sendRedirect("LogIn");
			return;
		}
		
		request.getRequestDispatcher( "/WEB-INF/AddItems.jsp" ).forward( request, response );
		
		// display form
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<html><head><title>Add Items</title></head><body>");
//
//		out.println("<form action='AddItems' method='post'>");
//		
//		out.println("<table border='1' cellpadding='2' cellspacing='2'>");
//		out.println("<tr>");
//		out.println("<td>Type:</td>");		
//		out.println("<td><select name='type'><option value='book'>Book</option><option value='tablet'>Tablet</option> </select></td>");		
//		out.println("</tr>");
//		out.println("<tr>");
//		out.println("<td>Name:</td>");		
//		out.println("<td><input name='name' size='60' /></td>");		
//		out.println("</tr>");	
//		out.println("<tr>");	
//		out.println("<td>Additional Info:</td>");		
//		out.println("<td><input name='additionalInfo' size='60' /></td>");
//		out.println("</tr>");	
//		out.println("<tr>");	
//		out.println("<td># of Copies:</td>");
//		out.println("<td><input name='numOfCopies' size='8' /></td>");	
//		out.println("</tr>");	
//		out.println("<tr>");	
//		out.println("<td colspan='2' rowspan='1'><input name='add' type='submit' value='Add' /></td>");	
//		out.println("</tr>");	
//		out.println("</table>");
//		out.println("</form>");	
//		out.println("</body></html>");

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer numOfCopies = Integer.parseInt(request.getParameter("numOfCopies"));
		if(numOfCopies == 1){
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu15";
            String username = "cs3220stu15";
            String password = "thfs30eq";

            String sql = "insert into departmentlibrary (id, type, name, additionalInfo, available) values (?, ?, ?, ?, ?)";

            c = (Connection) DriverManager.getConnection( url, username, password );
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setString( 1, request.getParameter( "id" ) );
            pstmt.setString( 2, request.getParameter( "type" ) );
            pstmt.setString( 3, request.getParameter( "name" ) );
            pstmt.setString( 4, request.getParameter( "additionalInfo" ) );
            pstmt.setString( 5, request.getParameter( "available" ) );
            pstmt.executeUpdate();
            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        finally
        {
            try
            {
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
        }
		} else if (numOfCopies > 1){
				Connection c = null;
		        try
		        {
		            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu15";
		            String username = "cs3220stu15";
		            String password = "thfs30eq";
		            for (int x = 0; x < numOfCopies; x++){
		            String sql = "insert into departmentlibrary (id, type, name, additionalInfo, available) values (?, ?, ?, ?, ?)";

		            c = (Connection) DriverManager.getConnection( url, username, password );
		            PreparedStatement pstmt = c.prepareStatement( sql );
		            pstmt.setString( 1, request.getParameter( "id" ) );
		            pstmt.setString( 2, request.getParameter( "type" ) );
		            pstmt.setString( 3, request.getParameter( "name" ) );
		            pstmt.setString( 4, request.getParameter( "additionalInfo" ) );
		            pstmt.setString( 5, request.getParameter( "available" ) );
		            pstmt.executeUpdate();
		            c.close();
		            }
		        }
		     
		        catch( SQLException e )
		        {
		            throw new ServletException( e );
		        }
		        finally
		        {
		            try
		            {
		                if( c != null ) c.close();
		            }
		            catch( SQLException e )
		            {
		                throw new ServletException( e );
		            }
			}
//				String type = request.getParameter("type");
//				String name = request.getParameter("name");
//				String additionalInfo = request.getParameter("additionalInfo");
//				
//				// create a new guest book entry
//				DepartmentLibraryEntry entry = new DepartmentLibraryEntry(idSeed++, type, name, additionalInfo, "Yes");
//
//				// add the new entry to the guest book
//				List<DepartmentLibraryEntry> libEntries = (List<DepartmentLibraryEntry>) getServletContext().getAttribute("libEntries");
//				libEntries.add(entry);
//			}

        response.sendRedirect( "DisplayItems" );
    }
		// get the user input
//		Integer numOfCopies = Integer.parseInt(request.getParameter("numOfCopies"));
//		if (numOfCopies > 1) {
//			for (int x = 0; x < numOfCopies; x++) {
//				String type = request.getParameter("type");
//				String name = request.getParameter("name");
//				String additionalInfo = request.getParameter("additionalInfo");
//				
//				// create a new guest book entry
//				DepartmentLibraryEntry entry = new DepartmentLibraryEntry(idSeed++, type, name, additionalInfo, "Yes");
//
//				// add the new entry to the guest book
//				List<DepartmentLibraryEntry> libEntries = (List<DepartmentLibraryEntry>) getServletContext().getAttribute("libEntries");
//				libEntries.add(entry);
//			}
//		} else if (numOfCopies == 1) {
//			String type = request.getParameter("type");
//			String name = request.getParameter("name");
//			String additionalInfo = request.getParameter("additionalInfo");
//			
//			// create a new guest book entry
//			DepartmentLibraryEntry entry = new DepartmentLibraryEntry(idSeed++, type, name, additionalInfo, " ");
//			
//			// add the new entry to the guest book
//			List<DepartmentLibraryEntry> libEntries = (List<DepartmentLibraryEntry>) getServletContext().getAttribute("libEntries");
//			libEntries.add(entry);
//			
//		}
//		// send the user back to the guest book page
//		response.sendRedirect("DisplayItems");
	}
	}
	

