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
import model.ReviewDAO;
import model.ReviewWriteDTO;

@WebServlet("/01Main/ReviewList.do")
public class ReviewListCtrl extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext application = this.getServletContext();
		ReviewDAO dao = new ReviewDAO(application);
		
		Map param = new HashMap();
		String addQueryString = "";
		
		//리뷰 게시물갯수 반환
		int totalRecordCount = dao.getTotalRecordCount(param);
		param.put("totalCount", totalRecordCount);
		 
		int pageSize = Integer.parseInt(
	 			application.getInitParameter("PAGE_SIZE2"));
	 	int blockPage = Integer.parseInt(
	 			application.getInitParameter("BLOCK_PAGE"));
	 	
	 	//전체페이지수 계산 - 리뷰
	 	int totalPage =
	 			(int)Math.ceil((double)totalRecordCount/pageSize);
	 	
	 	System.out.println("리뷰 전체레코드수:" + totalRecordCount);
	 	System.out.println("리뷰 전체페이지수:" + totalPage);
	 	
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
	 	
	 	param.put("totalPage", totalPage);//전체페이지수
	 	param.put("nowPage", nowPage);//현재페이지
	 	param.put("totalCount", totalRecordCount);//전체레코드갯수
	 	param.put("pageSize", pageSize);//한페이지에 출력할 게시물갯수
	 	
	 	Map map = dao.selectPaging(param);
	 	List lists = (List)map.get("AcaTeacherDTO");
	 	//List<ReviewWriteDTO> lists = dao.selectPaging(param);
	 	String pagingImg = util.PagingUtil.pagingImgServlet(
	 			totalRecordCount,pageSize,
	 			blockPage, nowPage,
	 			"../01Main/ReviewList.do?"+addQueryString);
	 	
	 	//자원해제
	 	dao.close();
	 	
	 	req.setAttribute("lists2", map.get("MembersDTO"));
	 	req.setAttribute("lists", lists);
	 	req.setAttribute("map", param);
		req.setAttribute("pagingImg", pagingImg);
		
		RequestDispatcher dis =
				req.getRequestDispatcher("/01Main/ReviewList.jsp");
		dis.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
