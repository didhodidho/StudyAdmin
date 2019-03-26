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

import model.AcaClassDTO;
import model.AcaDAO;
import model.AcaTeacherDTO;
import model.AcaiIntroduceDTO;

@WebServlet("/01Main/AcaClassList.do")
public class AcaClassListCtrl extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext application = this.getServletContext();
		AcaClassDAO dao = new AcaClassDAO(application);
		
		Map param = new HashMap();
		
		String addQueryString = "";
		String searchColumn = req.getParameter("table_search");
		String searchWord = req.getParameter("searchWord");
		if(searchColumn!=null) {
			//파라미터를 쿼리스트링 형태로 만들어줌
			addQueryString = String.format("table_search=%s"
					+"&searchWord=%s&",
					searchColumn, searchWord);
			
			//파라미터를 맵에 저장
			param.put("Column", searchColumn);
			param.put("Word", searchWord);
		}
		
		
		//간의시간표 게시물갯수 반환
		int totalRecordCount = dao.getTotalRecordCount(param);
		param.put("totalCount", totalRecordCount);
		
		/////////////////////////
		int pageSize = Integer.parseInt(
	 			application.getInitParameter("PAGE_SIZE"));
	 	int blockPage = Integer.parseInt(
	 			application.getInitParameter("BLOCK_PAGE"));
	 	
	 	//전체페이지수 계산 - 강의시간표
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
	 	
	 	
	 	List<AcaClassDTO> lists = dao.selectPaging(param);
	 	String pagingImg = util.PagingUtil.pagingImgServlet(
	 			totalRecordCount,pageSize,
	 			blockPage, nowPage,
	 			"../01Main/AcaClassList.do?"+addQueryString);
	 	
	 	//자원해제
	 	dao.close();
	 	
	 	req.setAttribute("lists", lists);
		req.setAttribute("map", param);
		req.setAttribute("pagingImg", pagingImg);
		
		RequestDispatcher dis =
				req.getRequestDispatcher("/01Main/AcaClassList.jsp");
		dis.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
