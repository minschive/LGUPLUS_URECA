package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// HttpServlet 상속
// doGet() doPost() 재정의
// ...... 규칙
// ????
// main() 실행 <= tomcat
// tomcat 은 어떻게 우리가 작성한 코드를 실행할 수 있는가 ?
// Container vs Component  <= 미리 약속한 규칙이 존재
// 우리는 그 규칙대로 coding
// HttpServlet service() 가 get, post 등 method 를 확인하고 난 뒤 HelloServlet 의 doGet, doPost 를 호출
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Get Request");
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post Request");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Put Request");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Delete Request");
	}
}
