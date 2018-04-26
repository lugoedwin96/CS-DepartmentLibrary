package DepartmentLibrary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditItem")
public class EditItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditItem() {
        super();
    }
    
 
	private DepartmentLibraryEntry getEntry( Integer id ) throws ServletException{
		
		// get all the current entries from the database and get rid of the servlet context
		 
		List<DepartmentLibraryEntry> libEntries = new ArrayList<DepartmentLibraryEntry>();

		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu15";
			String username = "cs3220stu15";
			String password = "thfs30eq";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from departmentlibrary where id = " + id);

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
        
        for( DepartmentLibraryEntry entry : libEntries )
            if( entry.getId().equals( id ) ) 
            	return entry;

        return null;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null){
			response.sendRedirect("LogIn");
			return;
		}
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
        DepartmentLibraryEntry entry = getEntry( id );
        getServletContext().setAttribute("entry", entry);
		request.getRequestDispatcher( "/WEB-INF/EditItem.jsp" ).forward( request, response );
		 // get the entry to be edited
 
      
        // form display
//        response.setContentType( "text/html" );
//        PrintWriter out = response.getWriter();
//        out.println("<html><head><title>Edit Item</title></head><body>");
//
//		out.println("<form action='EditItem' method='post'>");
//		
//		out.println("<table border='1' cellpadding='2' cellspacing='2'>");
//		out.println("<tr>");
//		out.println("<td>ID:</td>");		
//		out.println("<td>" + entry.getId() + "</td>");		
//		out.println("</tr>");
//		out.println("<tr>");
//		out.println("<td>Type:</td>");	
//		out.println("<td><select name='type' value='"+entry.getType()+"'><option value='Book'>Book</option><option value='Tablet'>Tablet</option> </select></td>");		
//		out.println("</tr>");
//		out.println("<tr>");
//		out.println("<td>Name:</td>");		
//		out.println("<td><input name='name' type='text' size='60' value='"+ entry.getName() +"'/></td>");		
//		out.println("</tr>");	
//		out.println("<tr>");	
//		out.println("<td>Additional Info:</td>");		
//		out.println("<td><input name='additionalInfo' type='text' size='60' value='" + entry.getAdditionalInfo() +"'/></td>");
//		out.println("</tr>");		
//		out.println("<tr>");
//        out.println( "<td><input type='submit' name='save' value='Save' /><br /></td>" );
//		out.println("</tr>");	
//		out.println("</table>");
//		out.println("<td><input type='hidden' name='id' value='"+entry.getId()+"'/></td>");
//		out.println("</form>");	
//		
//		out.println("</body></html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
        DepartmentLibraryEntry entry = getEntry( id );
        // instead of setVariable you need to update it on the database
        Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu15";
			String username = "cs3220stu15";
			String password = "thfs30eq";

		    String sql = "update departmentlibrary set type=?, name=?, additionalInfo=? where id = "+id;

            c = DriverManager.getConnection( url, username, password );
          
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setString( 1, request.getParameter( "type" ) );
            pstmt.setString( 2, request.getParameter( "name" ) );
            pstmt.setString( 3, request.getParameter( "additionalInfo" ) );
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
//        entry.setType(request.getParameter("type"));
//        entry.setName( request.getParameter( "name" ) );
//        entry.setAdditionalInfo( request.getParameter( "additionalInfo" ));
        
        response.sendRedirect( "DisplayItems" );
	}
	
}
