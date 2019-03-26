package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AcaiIntroduceDAO;
import model.MembersDAO;
import model.ReviewDAO;
import model.ReviewWriteDTO;
import util.FileUtil;

@WebServlet("/01Main/ReviewDelete.do")
public class ReviewDeleteCtrl extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String reviewidx = req.getParameter("reviewidx");
		String nowPage = req.getParameter("nowPage");
		
		req.setAttribute("nowPage", nowPage);
		
		ServletContext application = this.getServletContext();
		ReviewDAO dao = new ReviewDAO(application);
		
		int sucOrFail;
		sucOrFail = dao.delete(reviewidx);
		//레코드 삭제성공시 파일도 같이 삭제
		if(sucOrFail==1) {
			
		}
		
		//삭제후의 페이지 처리를 위해 리퀘스트 영역에 저장
		req.setAttribute("WHEREIS", "DELETE");
		req.setAttribute("SUC_FAIL", sucOrFail);
		
		req.getRequestDispatcher("../01Main/ReviewList.do").forward(req, resp);
	}

}
