package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FriendServlet") 
public class FriendServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String name = request.getParameter("name");
        String message = request.getParameter("message");

        System.out.println();
        System.out.println("=== 친구 신청이 접수되었습니다 ===");
        System.out.println("이름: " + name);
        System.out.println("메시지: " + message);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h3>친구 신청이 완료되었습니다!</h3>");
        response.getWriter().println("<p>이름: " + name + "</p>");
        response.getWriter().println("<p>메시지: " + message + "</p>");
	}

}
