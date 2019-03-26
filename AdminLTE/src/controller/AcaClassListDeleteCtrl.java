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

@WebServlet("/01Main/AcaClassListDelete.do")
public class AcaClassListDeleteCtrl extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String classidx = req.getParameter("classidx");
		String nowPage = req.getParameter("nowPage");
		
		req.setAttribute("nowPage", nowPage);
		
		ServletContext application = this.getServletContext();
		AcaClassDAO dao = new AcaClassDAO(application);
		 
		int sucOrFail;
		sucOrFail = dao.delete(classidx);
		//레코드 삭제성공시 파일도 같이 삭제
		if(sucOrFail==1) {
			/*String file = dto.getAttachedfile();
			FileUtil.deleteFile(req, "/Upload", file);*/
			
		}
		
		//삭제후의 페이지 처리를 위해 리퀘스트 영역에 저장
		req.setAttribute("WHEREIS", "DELETE");
		req.setAttribute("SUC_FAIL", sucOrFail);
		
		req.getRequestDispatcher("../01Main/AcaClassList.do").forward(req, resp);
	}

}
