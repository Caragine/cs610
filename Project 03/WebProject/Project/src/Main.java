import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
@WebServlet("/Main")

public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Main() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> htmlParameters = request.getParameterNames();
		PrintWriter out = response.getWriter();
		String characters = "abcdefghijklmnopqrsutvwxyz!?@#$%^&*";
		
		while (htmlParameters.hasMoreElements()) {
			String parameter = (String)htmlParameters.nextElement();
			String value = request.getParameter(parameter);
			
			if (parameter.equalsIgnoreCase("yourName") && value.isEmpty()) {
				out.println("<html><title>Hello</title><body>\n");
				out.println("<font color = blue>");
				out.println("<h1>You need to enter your name</h1>\n");
				out.println("</font>");
				out.println("</body></html>");
				out.close();
			}
			
			if (parameter.equalsIgnoreCase("yourAge")) {
				if (value.isEmpty()) {
					out.println("<html><title>Hello</title><body>\n");
					out.println("<font color = blue>");
					out.println("<h1>You need to enter your age</h1>\n");
					out.println("</font>");
					out.println("</body></html>");
					out.close();
				
				} else if (Integer.parseInt(value) < 0 || value.contains(characters)) {
					out.println("<html><title>Hello</title><body>\n");
					out.println("<font color = blue>");
					out.println("<h1>You need to enter a positive integer for your age</h1>\n");
					out.println("</font>");
					out.println("</body></html>");
					out.close();
				
				} else {
					
					if (Integer.parseInt(value) > 17) {
						out.println("<html><title>Hello</title><body>\n");
						out.println("<font color = blue>");
						out.println("<h1>You are eligible to drive</h1>\n");
						out.println("</font>");
						out.println("</body></html>");
						out.close();					
					
					} else {
						out.println("<html><title>Hello</title><body>\n");
						out.println("<font color = blue>");
						out.println("<h1>You are not eligible to drive</h1>\n");
						out.println("</font>");
						out.println("</body></html>");
						out.close();
					}
				}
			}
		}
	}
}
