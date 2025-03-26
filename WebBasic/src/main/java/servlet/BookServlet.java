package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 도메인 업무를 서블릿으로 처리
// 단순 crud를 위한 서블릿을 각각 만들지 않고, 업무별 세부 처리를 하나의 서블릿에서 처리하는 방식

// 1. sub url 로 구분
// @WebServlet("/book/*")
//    도서목록 : /book/list
//    도서상세 : /book/detail
//    도서등록 : /book/insert
//    도서 ....

// 2. 업무구분자를 parameter(job) 로 사용
// @WebServlet("/book")
// 도서목록 : /book?job=list
// 도서상세 : /book?job=detail
// 도서등록 : /book?job=insert
// 도서 ....

//@WebServlet("/book/*")
@WebServlet("/book")
public class BookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	// 여러 요청을 처리
	// sub url
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(request.getRequestURI());
//		System.out.println(request.getContextPath());
//		
//		String job = request.getRequestURI().substring(request.getContextPath().length());
//		switch(job) {
//			case "/book/list" : list(); break;
//			case "/book/detail" : detail(); break;
//			case "/book/insert" : insert(); break;
//		}
//	}
	
	// job parameter
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String job = request.getParameter("job"); // client 에서 무조건 job 가 전달된다.
		switch(job) {
			case "list" : list(); break;
			case "detail" : detail(); break;
			case "insert" : insert(); break;
		}
	}
	
	private void list() {	System.out.println("list()");	}
	private void detail() {	System.out.println("detail()");	}
	private void insert() {	System.out.println("insert()");	}
	
}
