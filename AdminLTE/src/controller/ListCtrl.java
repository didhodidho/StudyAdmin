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
import model.MembersDAO;
import model.ReviewDAO;
import util.PagingUtil;

@WebServlet("/01Main/adminList.do")
public class ListCtrl extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext application = this.getServletContext();
		AcaDAO dao = new AcaDAO(application);
		
		Map param = new HashMap();
		
		//////////////심플 대시보드
		//회원 수
		MembersDAO mdao = new MembersDAO(application);
		int tMembers = mdao.getTotalMemberCount(param);
		param.put("totalCount", tMembers);
		//리뷰 수
		ReviewDAO rdao = new ReviewDAO(application);
		int tReview = rdao.getTotalRecordCount(param);
		param.put("ReviewCount", tReview);
		//학원 등록 수
		AcaiIntroduceDAO Acdao = new AcaiIntroduceDAO(application);
		int acaIntro = Acdao.getTotalRecordCount2(param);
		param.put("AcIntroCount", acaIntro);
		//모든 수 멥에 저장
		req.setAttribute("map", param);
		/////////////
		
		String addQueryString = "";
		
		
		//학원선생님 게시물갯수 반환
		int totalRecordCount = dao.getTotalRecordCount(param);
		param.put("totalCount", totalRecordCount);
		
		
		/////////////////////////
		int pageSize = Integer.parseInt(
	 			application.getInitParameter("PAGE_SIZE"));
	 	int blockPage = Integer.parseInt(
	 			application.getInitParameter("BLOCK_PAGE"));
	 	
	 	//전체페이지수 계산 - 학원선생님
	 	int totalPage =
	 			(int)Math.ceil((double)totalRecordCount/pageSize);
	 	
	 	System.out.println("전체레코드수:" + totalRecordCount);
	 	System.out.println("전체페이지수:" + totalPage);
	 	
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
	 	
	 	
	 	List<AcaTeacherDTO> lists = dao.selectPaging(param);
	 	String pagingImg = util.PagingUtil.pagingImgServlet(
	 			totalRecordCount,pageSize,
	 			blockPage, nowPage,
	 			"../01Main/adminList.do?"+addQueryString);
	 	
	 	
	 	//자원해제
	 	dao.close();
	 	
	 	req.setAttribute("lists", lists);
		req.setAttribute("map", param);
		req.setAttribute("pagingImg", pagingImg);
		
		
		RequestDispatcher dis =
				req.getRequestDispatcher("/01Main/adminList.jsp");
		dis.forward(req, resp);
	 	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
