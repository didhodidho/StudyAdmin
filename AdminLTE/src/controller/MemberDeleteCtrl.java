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
import model.AcaiIntroduceDTO;
import model.MembersDAO;
import model.MembersDTO;
import util.PagingUtil;

@WebServlet("/01Main/MembersDelete.do")
public class MemberDeleteCtrl extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		String nowPage = req.getParameter("nowPage");
		
		req.setAttribute("nowPage", nowPage);
		
		ServletContext application = this.getServletContext();
		MembersDAO dao = new MembersDAO(application);
		//MembersDTO dto = dao.selectView(idx);
		
		int sucOrFail;
		sucOrFail = dao.delete(idx);
		//레코드 삭제성공시 파일도 같이 삭제
		if(sucOrFail==1) {
			/*String file = dto.getAttachedfile();
			FileUtil.deleteFile(req, "/Upload", file);*/
			
			
		}
		
		//삭제후의 페이지 처리를 위해 리퀘스트 영역에 저장
		req.setAttribute("WHEREIS", "DELETE");
		req.setAttribute("SUC_FAIL", sucOrFail);
		
		req.getRequestDispatcher("../01Main/MembersList.do").forward(req, resp);
	}

}
