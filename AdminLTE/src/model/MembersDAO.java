package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;


public class MembersDAO {
	
	//JDBC를 위한 멤버변수
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	
	public MembersDAO(ServletContext ctx) {
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
	
	
	
	
	
	//회원정보 확인후 Map 컬렉션으로 반환
	public java.util.Map<String, String> getMemberMap(String id, String pass){
		
		//회원정보를 담아서 반환하기 위한 변수
		java.util.Map<String, String> maps = new HashMap<String, String>();
		
		String query = "SELECT id, pass, name FROM admins "
				+ " WHERE id=? and pass=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			//결과셋이 있는 경우에만 레코드를 읽어온다.
			if(rs.next()) {
				//Map에 추가할때는 put()메소드를 사용한다.
				maps.put("id", rs.getString(1));
				maps.put("pass", rs.getString(2));
				maps.put("name", rs.getString(3));
				
				System.out.println(maps.put("id", rs.getString(1))+"님 로그인함");
			}
			else {
				System.out.println("일치하는 회원정보 없음");
			}
		}catch (SQLException e) {
			System.out.println("쿼리실행중 오류발생됨");
			e.printStackTrace();
		}
		
		return maps;
	}
	
	//전체 회원 수
	public int getTotalMemberCount(Map map)
	{
		int totalCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM Members";
			
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
	
	
	//리스트 가져오기 - 페이지 처리 있음
	public List<MembersDTO> selectPaging(Map map)
	{
		List<MembersDTO> bbs = new Vector<MembersDTO>();
		
		String sql = ""
			+" select * from ( "
			+"	    select Tb.*, ROWNUM rNum from "
			+"	        ( "
			+"	            select * from Members where grade=1";
		
			if(map.get("Word")!=null) {
				//검색어가 있다면 조건절 추가
				sql += " where "+ map.get("Column") +" "
					+ "	like '%"+ map.get("word") +"%' ";
			}
			
			sql += " ORDER BY id DESC"
			
			+"	        ) Tb "
			+"	) "
			+"	where rNum between "+map.get("start")+" and "+map.get("end");
			System.out.println("쿼리문:" + sql);
			
			try {
				psmt = con.prepareStatement(sql);
				
				//psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
				//psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
				
				rs = psmt.executeQuery();
				while (rs.next()) {
					MembersDTO dto = new MembersDTO();
					
					dto.setId(rs.getString(1));
					dto.setPass(rs.getString(2));
					dto.setRegidate(rs.getDate(3));
					dto.setEmailId(rs.getString(4));
					dto.setEmailDomain(rs.getString(5));
					dto.setMobile1(rs.getString(6));
					
					dto.setName(rs.getString(7));
					dto.setIdx(rs.getInt("idx")); 
					dto.setGrade(rs.getInt("GRADE"));
					dto.setTelephone1(rs.getString(10));
					dto.setAddress(rs.getString(11));
					dto.setDetailAddress(rs.getString(12));
					dto.setInterest(rs.getString(13));
					dto.setAcaName(rs.getString(14));
					dto.setMobile2(rs.getString(15));
					dto.setMobile3(rs.getString(16));
					dto.setTelephone2(rs.getString(17));
					dto.setTelephone3(rs.getString(18));
					
					bbs.add(dto);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return bbs;
						
	}
	
	//리스트 가져오기 - 페이지 처리 있음
	public List<MembersDTO> selectPaging2(Map map)
	{
		List<MembersDTO> bbs = new Vector<MembersDTO>();
		
		String sql = ""
			+" select * from ( "
			+"	    select Tb.*, ROWNUM rNum from "
			+"	        ( "
			+"	            select * from Members where grade=2";
		
			if(map.get("table_search")!=null) {
				//검색어가 있다면 조건절 추가
				sql	+= "	like '%"+ map.get("table_search") +"%' ";
			}
			
			sql += " ORDER BY id DESC"
			
			+"	        ) Tb "
			+"	) "
			+"	where rNum between "+map.get("start")+" and "+map.get("end");
			System.out.println("쿼리문:" + sql);
			
			try {
				psmt = con.prepareStatement(sql);
				
				//psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
				//psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
				
				rs = psmt.executeQuery();
				while (rs.next()) {
					MembersDTO dto = new MembersDTO();
					
					dto.setId(rs.getString(1));
					dto.setPass(rs.getString(2));
					dto.setRegidate(rs.getDate(3));
					dto.setEmailId(rs.getString(4));
					dto.setEmailDomain(rs.getString(5));
					dto.setMobile1(rs.getString(6));
					
					dto.setName(rs.getString(7));
					dto.setIdx(rs.getInt("idx")); 
					dto.setGrade(rs.getInt("GRADE"));
					dto.setTelephone1(rs.getString(10));
					dto.setAddress(rs.getString(11));
					dto.setDetailAddress(rs.getString(12));
					dto.setInterest(rs.getString(13));
					dto.setAcaName(rs.getString(14));
					dto.setMobile2(rs.getString(15));
					dto.setMobile3(rs.getString(16));
					dto.setTelephone2(rs.getString(17));
					dto.setTelephone3(rs.getString(18));
					
					bbs.add(dto);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return bbs;
						
	}
	
	
	//회원정보 삭제하기
	public int delete(String idx) {
		
		int affected = 0;
		try {
			String query = "DELETE FROM Members "
					+ " WHERE idx=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			
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
