package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 브라우저(클라이언트)에서 로그인
// post
// <input type="text" name="username">
// <input type="password" name="password">

// get
// searchWord
//<input type="text" name="searchWord">

@WebServlet("/param")
public class ParamServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	// 검색어
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchWord = request.getParameter("searchWord");
		response.getWriter().append("<html><body><h1>Your searchWord : " + searchWord + "</h1></body></html>");
	}

	// 로그인
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		response.getWriter().append("<html><body><h1>Your username : " + username + "</h1></body></html>");
		
		String password = request.getParameter("password");
		System.out.println("Password : " + password);
	}
}
