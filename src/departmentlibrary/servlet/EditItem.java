package DepartmentLibrary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
    
   
	@SuppressWarnings({ "unchecked" })
	private DepartmentLibraryEntry getEntry( Integer id ){
        List<DepartmentLibraryEntry> libEntries = (List<DepartmentLibraryEntry>) getServletContext().getAttribute("libEntries" );

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
        entry.setType(request.getParameter("type"));
        entry.setName( request.getParameter( "name" ) );
        entry.setAdditionalInfo( request.getParameter( "additionalInfo" ));
        
        response.sendRedirect( "DisplayItems" );
	}
	
}
