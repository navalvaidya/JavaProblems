

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import servletTest.fileupload;

/**
 * Servlet implementation class FoleUpload
 */
@WebServlet("/FoleUpload")
public class FoleUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoleUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		boolean isMultiPart=ServletFileUpload.isMultipartContent(request);
		if(isMultiPart)
		{
			ServletFileUpload upload = new ServletFileUpload();
			try
			{
				FileItemIterator itr = upload.getItemIterator(request);
				if(itr.hasNext())
				{
					FileItemStream item = itr.next();
					if(item.isFormField())
					{
						String fieldName =item.getFieldName();
						
						InputStream is =item.openStream();
						byte [] b = new byte[is.available()];
						is.read(b);
						String value = new String(b);
						response.getWriter().println(fieldName+":"+value+"<br/>");
					}
					else
					{
						String path = getServletContext().getRealPath("/"); 
						if(fileupload.processFile(path, item))
							response.getWriter().println("file upload successful");
						else
							response.getWriter().println("file upload failed");
					}
				}
			}catch(FileUploadException fe)
			{
			fe.printStackTrace();	
			}
		}
	}

}
