package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

public class AcaDAO {

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	//커넥션 연결 
	public AcaDAO(ServletContext ctx) {
		try {
			/*
			 java파일에서 web.xml의 초기화 파라미터를 가져옴 
			 */
			Class.forName(ctx.getInitParameter("JDBCDriver"));
			String url = ctx.getInitParameter("ConnectionURL");
			String id = "studycastle";
			String pw = "1234";

			con = DriverManager.getConnection(url, id, pw);
			System.out.println("DB 연결성공^^*");
		} catch (Exception e) {
			System.out.println("DB 연결실패ㅠㅠ");
		}
	}
	
	//게시물 수 - 학원선생님
	public int getTotalRecordCount(Map map)
	{
		int totalCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM AcaTeacher ";
			
			if(map.get("Word")!=null) {
				sql += " WHERE " + map.get("Column") + " "
					+ " LIKE '%" + map.get("Word") + "%' ";
			}
			
			//sql = " where grade=2";
			
			
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {}
		return totalCount;
	}
	
	//리스트 페이징 처리 - 학원선생님
	public Map selectPaging(Map map )
	{
		java.util.List<AcaTeacherDTO> bbs = new Vector<AcaTeacherDTO>();
		java.util.List<MembersDTO> bbs2 = new Vector<MembersDTO>();
		
		Map returnMap = new HashMap();
		
		String sql = ""
				+" select * from ( "
				+"	    select Tb.*, ROWNUM rNum from "
				+"	        ( "
				/*+"	            select teaidx, AcaName, teaimage, teaname, teaintro, subject, Members.id "*/
				/*+"	            select teaimage, teaname, teaintro, subject, AcaTeacher.id, AcaName "*/
				+"	            select * "
				+ " from AcaTeacher inner join Members "
				+ "				on AcaTeacher.id = Members.id	 ";
				
				sql += " ORDER BY TeaIdx desc"
				
				+"	        ) Tb "
				+"	) "
				+"	where rNum between "+map.get("start")+" and "+map.get("end");
			
			System.out.println("쿼리문:" + sql);
			
			try {
				psmt = con.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {
					AcaTeacherDTO dto = new AcaTeacherDTO();
					
					//위에서 추가한 아이콘을 제목앞에 연결함.
					dto.setTeaimage(rs.getString(1));
					dto.setTeaname(rs.getString(2));
					dto.setTeaintro(rs.getString(3));
					dto.setSubject(rs.getString(4));
					dto.setTeaidx(rs.getString(5));
					dto.setId(rs.getString(6));
					
					MembersDTO dto2 = new MembersDTO();
					dto2.setAcaName(rs.getString("acaname"));
					//bbs2.add(dto2);
					
					bbs.add(dto);
					bbs2.add(dto2);
				}
				
			} catch (Exception e) {
				System.out.println("Select시 예외발생");
				e.printStackTrace();
			}
			
			returnMap.put("AcaTeacherDTO", bbs);
			returnMap.put("MembersDTO", bbs2);
			return returnMap;
	}
	

	//게시물 삭제
	//학원소개 삭제하기
	public int delete(String TeaIdx) {
		
		int affected = 0;
		try {
			String query = "DELETE FROM AcaTeacher "
					+ " WHERE TeaIdx=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, TeaIdx);
			
			affected = psmt.executeUpdate();
			System.out.println("게시물 삭제 성공:" + affected);
		} catch (Exception e) {
			System.out.println("delete중 예외발생");
			e.printStackTrace();
		}
		
		return affected;
		
	}
	
	//자원반납
	//자원반납
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(psmt!=null) psmt.close();
			if(con!=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
