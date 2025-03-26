package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebServlet 의 url 이 겹치면 안된다.
// html 코드가 길어지면 ??? 자바 코드 안에 html 이 존재하므로 복잡, 가독성, 유지보수 등등 어렵다.
// 프론트 쪽 비즈니스 로직이 변경되면 백엔드가 수정 ?????
// html 과 java 를 분리 !!! JSP 탄생 배경

@WebServlet("/html")
public class HtmlServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get Request");
		response.getWriter().append("<html><body><h1>Get Request</h1></body></html>");
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post Request");
		response.getWriter().append("<html><body><h1>Post Request</h1></body></html>");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Put Request");
		response.getWriter().append("<html><body><h1>Put Request</h1></body></html>");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Delete Request");
		response.getWriter().append("<html><body><h1>Delete Request</h1></body></html>");
	}
}
