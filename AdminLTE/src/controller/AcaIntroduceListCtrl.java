package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AcaDAO;
import model.AcaTeacherDTO;
import model.AcaiIntroduceDAO;
import model.AcaiIntroduceDTO;

@WebServlet("/01Main/AcaIntroduceList.do")
public class AcaIntroduceListCtrl extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext application = this.getServletContext();
		AcaiIntroduceDAO dao = new AcaiIntroduceDAO(application);
		
		Map param = new HashMap();
		
		String addQueryString = "";
		
		//학원소개 게시물갯수 반환
		int totalRecordCount2 = dao.getTotalRecordCount2(param);
		param.put("totalCount", totalRecordCount2);
		
		
		/////////////////////////
		int pageSize = Integer.parseInt(
	 			application.getInitParameter("PAGE_SIZE"));
	 	int blockPage = Integer.parseInt(
	 			application.getInitParameter("BLOCK_PAGE"));
	 	
	 	//전체페이지수 계산 - 학원소개
	 	int totalPage2 =
	 			(int)Math.ceil((double)totalRecordCount2/pageSize);
		
	 	System.out.println("전체레코드수2:" + totalRecordCount2);
	 	System.out.println("전체페이지수2:" + totalPage2); 
	 	
	 	int nowPage = (req.getParameter("nowPage")==null
	 			|| req.getParameter("nowPage").equals(""))
	 			?
	 			1
	 			:
	 			Integer.parseInt(req.getParameter("nowPage"));
	 	
	 	int start = (nowPage-1) * pageSize + 1;
	 	int end = nowPage * pageSize;
	 	
	 	param.put("start", start);
	 	param.put("end", end);
	 	
	 	param.put("nowPage", nowPage);//현재페이지
	 	param.put("pageSize", pageSize);//한페이지에 출력할 게시물갯수
	 	
	 	param.put("totalPage2", totalPage2);
	 	param.put("totalCount2", totalRecordCount2);//전체레코드갯수
	 	
	 	Map map = dao.selectPaging2(param);
	 	List lists2 = (List)map.get("AcaTeacherDTO");
	 	
	 	//학원 소개
	 	//List<AcaiIntroduceDTO> lists2 = dao.selectPaging2(param);
	 	String pagingImg2 = util.PagingUtil.pagingImgServlet(
				totalRecordCount2,pageSize,
				blockPage, nowPage,
				"../01Main/AcaIntroduceList.do?"+addQueryString);
	 	
	 	//자원해제
	 	dao.close();
	 	
	 	req.setAttribute("lists3", map.get("MembersDTO"));
	 	req.setAttribute("lists2", lists2);
		req.setAttribute("map", param);
		req.setAttribute("pagingImg2", pagingImg2);
		
		RequestDispatcher dis =
				req.getRequestDispatcher("/01Main/AcaIntroduceList.jsp");
		dis.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
