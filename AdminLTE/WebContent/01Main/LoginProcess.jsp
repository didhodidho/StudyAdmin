<%@page import="java.util.Map"%>
<%@page import="model.MembersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String id = request.getParameter("id"); 
String pass = request.getParameter("pass");

//로그인후 돌아갈 페이지 URL 폼값 받기
String returnPage = request.getParameter("returnPage");

//String driver = application.getInitParameter("JDBCDriver");
//String url = application.getInitParameter("ConnectionURL");

MembersDAO dao = new MembersDAO(application);

Map<String, String> members = dao.getMemberMap(id, pass);
if(members.get("id")!=null){
	//회원인증 성공시
	session.setAttribute("USER_NAME", members.get("name"));
	session.setAttribute("USER_ID", id);
	//request.getRequestDispatcher("../AdminLTE/01Main/adminList.jsp").forward(request, response);
	
	if(returnPage.equals("null") || returnPage.equals("")){
		//돌아갈 페이지가 없다면 로그인 페이지로 돌아간다.
		//response.sendRedirect("../login.jsp");
		//response.sendRedirect(returnPage);
		response.sendRedirect("../01Main/adminList.do");
		
	}
	else{
		//돌아갈 페이지가 있을때에는 해당페이지로 돌아간다.
		//response.sendRedirect(returnPage);
		response.sendRedirect("../01Main/adminList.jsp");
		
	}
}else{
	//회원인증 실패시
	request.setAttribute("ERROR_MSG", "회원정보 오류");
	response.sendRedirect("../login.jsp");
}
%>    
