package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import model.AcaClassDTO;
import model.AcaTeacherDTO;

public class AcaClassDAO {
	
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	public AcaClassDAO(ServletContext ctx) {
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
	
	
	//게시물 수 - 강의시간표
	public int getTotalRecordCount(Map map)
	{
		int totalCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM AcaClass ";
			
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
	
	//리스트 페이징 처리 - 강의시간표
	public java.util.List<AcaClassDTO> selectPaging(Map map)
	{
		java.util.List<AcaClassDTO> bbs = new Vector<AcaClassDTO>();
		
		String sql = ""
				+" select * from ( "
				+"	    select Tb.*, ROWNUM rNum from "
				+"	        ( "
				/*+"	            select * from AcaClass ";*/
				+"	            select to_char(acastartDate, 'yyyy-mm-dd'),to_char(acaendDate, 'yyyy-mm-dd'),acaday,to_char(acastarttime, 'HH24:mi:ss'),to_char(acaendtime, 'HH24:mi:ss'),acaclassname,numberofparticipants,classidx,teaidx,pay from AcaClass ";
			
				sql += " ORDER BY ClassIdx DESC"
				
				+"	        ) Tb "
				+"	) "
				+"	where rNum between "+map.get("start")+" and "+map.get("end");
			
			System.out.println("쿼리문:" + sql);
			
			try {
				psmt = con.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {
					AcaClassDTO dto = new AcaClassDTO();
					
					//위에서 추가한 아이콘을 제목앞에 연결함.
					dto.setAcastartdate(rs.getString(1));
					dto.setAcaenddate(rs.getString(2));
					dto.setAcaday(rs.getString(3));
					dto.setAcastarttime(rs.getString(4));
					dto.setAcaendtime(rs.getString(5));
					dto.setAcaclassname(rs.getString(6));
					dto.setNumberofparticipants(rs.getString(7));
					dto.setClassidx(rs.getString(8));
					dto.setTeaidx(rs.getString(9));
					dto.setPay(rs.getString(10));
					
					bbs.add(dto);
				}
				
			} catch (Exception e) {
				System.out.println("Select시 예외발생");
				e.printStackTrace();
			}
			
			return bbs;
	}
	
	//강의시간표 삭제하기
	public int delete(String classidx) {
		
		int affected = 0;
		try {
			String query = "DELETE FROM AcaClass "
					+ " WHERE ClassIdx=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, classidx);
			
			affected = psmt.executeUpdate();
			System.out.println("게시물 삭제 성공:" + affected);
		} catch (Exception e) {
			System.out.println("delete중 예외발생");
			e.printStackTrace();
		}
		
		return affected;
		
	}
	
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
