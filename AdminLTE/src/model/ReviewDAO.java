package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

public class ReviewDAO {
	
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	public ReviewDAO(ServletContext ctx) {
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
	
	//게시물 수 - 리뷰
	public int getTotalRecordCount(Map map)
	{
		int totalCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM ReviewWrite ";
			
			if(map.get("Word")!=null) {
				sql += " WHERE " + map.get("Column") + " "
					+ " LIKE '%" + map.get("Word") + "%' ";
			}
			
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {}
		return totalCount;
	}
	
	
	//리스트 페이징 처리 - 리뷰
	public Map selectPaging(Map map)
	{
		java.util.List<ReviewWriteDTO> bbs = new Vector<ReviewWriteDTO>();
		java.util.List<MembersDTO> bbs2 = new Vector<MembersDTO>();
		
		Map returnMap = new HashMap();
		
		String sql = ""
				+" select * from ( "
				+"	    select Tb.*, ROWNUM rNum from "
				+"	        ( "
				+"	            select r.*,(select acaname from members s where s.idx = r.acaidx) acaname from reviewwrite r ";
			
				if(map.get("Word")!=null) {
					//검색어가 있다면 조건절 추가
					sql += " where "+ map.get("Column") +" "
						+ "	like '%"+ map.get("word") +"%' ";
				}
				
				sql += " ORDER BY ReviewIdx DESC"
				
				+"	        ) Tb "
				+"	) "
				+"	where rNum between "+map.get("start")+" and "+map.get("end");
			
			System.out.println("쿼리문:" + sql);
			
			try {
				psmt = con.prepareStatement(sql);
				rs = psmt.executeQuery();
				while (rs.next()) {
					ReviewWriteDTO dto = new ReviewWriteDTO();
					
					//위에서 추가한 아이콘을 제목앞에 연결함.
					dto.setReviewcontents(rs.getString(1));
					dto.setScore(rs.getDouble("Score"));
					dto.setWritetime(rs.getString(3));
					dto.setId(rs.getString(4));
					dto.setAcaidx(rs.getString(5));
					dto.setReviewidx(rs.getString(6));
					
					MembersDTO dto2 = new MembersDTO();
					dto2.setAcaName(rs.getString("acaname"));
					bbs2.add(dto2);
					
					bbs.add(dto);
				}
				
			} catch (Exception e) {
				System.out.println("Select시 예외발생");
				e.printStackTrace();
			}
			
			returnMap.put("AcaTeacherDTO", bbs);
			returnMap.put("MembersDTO", bbs2);
			
			return returnMap;
	}
	
	//리뷰 삭제하기
	public int delete(String reviewidx) {
		
		int affected = 0;
		try {
			String query = "DELETE FROM ReviewWrite "
					+ " WHERE ReviewIdx=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, reviewidx);
			
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
