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
import model.AcaiIntroduceDAO;
import model.AcaiIntroduceDTO;
import util.PagingUtil;

@WebServlet("/01Main/AcaTeacherDeleteCtrl.do")
public class AcaTeacherDeleteCtrl extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//학원소개 삭제
		String teaidx = req.getParameter("teaidx");
		String nowPage = req.getParameter("nowPage");
		
		req.setAttribute("nowPage", nowPage);
		
		ServletContext application = this.getServletContext();
		AcaDAO dao = new AcaDAO(application);
		 
		int sucOrFail;
		sucOrFail = dao.delete(teaidx);
		//레코드 삭제성공시 파일도 같이 삭제
		if(sucOrFail==1) {
			/*String file = dto.getAttachedfile();
			FileUtil.deleteFile(req, "/Upload", file);*/
		}
		
		//삭제후의 페이지 처리를 위해 리퀘스트 영역에 저장
		req.setAttribute("WHEREIS", "DELETE");
		req.setAttribute("SUC_FAIL", sucOrFail);
		
		req.getRequestDispatcher("../01Main/adminList.do").forward(req, resp);
	}

}
