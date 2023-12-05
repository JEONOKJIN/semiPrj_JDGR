package com.semi.jdgr.user.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.semi.jdgr.user.member.vo.MemberVo;
import com.semi.jdgr.util.JDBCTemplate;

public class MemberDao {

	public int join(Connection conn, MemberVo vo) throws Exception {
		// sql
				String sql = "INSERT INTO MEMBER (MEM_NO,MEM_NAME,MEM_ID,MEM_PWD,MEM_NICK,MEM_PHONE_NUM,MEM_EMAIL) VALUES (SEQ_MEMBER.NEXTVAL, ?,?, ?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getMemName());
				pstmt.setString(2, vo.getMemId());
				pstmt.setString(3, vo.getMemPwd());
				pstmt.setString(4, vo.getMemNick());
				pstmt.setString(5, vo.getMemPhoneNum());
				pstmt.setString(6, vo.getMemEmail());
				int result = pstmt.executeUpdate();
				
				
				// close
				JDBCTemplate.close(pstmt);
				
				return result;
				
	}

	public boolean checkIdDup(Connection conn, String joinId) throws Exception {
		String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, joinId);
		ResultSet rs = pstmt.executeQuery();
		
		boolean result = true;
		if(rs.next()) {
			result = false;
		}
		
		System.out.println(result);
		return result;
	}
	
	public boolean checkNickDup(Connection conn, String joinNick) throws Exception {
		String sql = "SELECT * FROM MEMBER WHERE MEM_NICK = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, joinNick);
		ResultSet rs = pstmt.executeQuery();
		
		boolean result = true;
		if(rs.next()) {
			result = false;
		}
		
		System.out.println(result);
		return result;
	}


	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ? AND MEM_PWD = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemId());
		pstmt.setString(2, vo.getMemPwd());
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo loginMember =null;
		
		if(rs.next()) {
			String memNo = rs.getString("MEM_NO");
			String memName = rs.getString("MEM_NAME");
			String memId = rs.getString("MEM_ID");
			String memPwd = rs.getString("MEM_PWD");
			String memNick = rs.getString("MEM_NICK");
			String memPhoneNum = rs.getString("MEM_PHONE_NUM");
			String memEmail = rs.getString("MEM_EMAIL");
			String quitYn = rs.getString("QUIT_YN");
			String enrollDate = rs.getString("ENROLL_DATE");
			String updateDate = rs.getString("UPDATE_DATE");
			
			loginMember = new MemberVo();
			loginMember.setMemNo(memNo);
			loginMember.setMemName(memName);
			loginMember.setMemId(memId);
			loginMember.setMemPwd(memPwd);
			loginMember.setMemNick(memNick);
			loginMember.setMemPhoneNum(memPhoneNum);
			loginMember.setMemEmail(memEmail);
			loginMember.setQuitYn(quitYn);
			loginMember.setEnrollDate(enrollDate);
			loginMember.setUpdateDate(updateDate);
					
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return loginMember;
	}

	public int updateMemberInfo(Connection conn, MemberVo vo) throws Exception {

		String sql = "UPDATE MEMBER SET MEM_NAME = ? , MEM_PWD = ? , MEM_NICK = ? , MEM_PHONE_NUM =? , UPDATE_DATE = SYSDATE WHERE MEM_EMAIL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemName());
		pstmt.setString(2, vo.getMemPwd());
		pstmt.setString(3, vo.getMemNick());
		pstmt.setString(4, vo.getMemPhoneNum());
		pstmt.setString(5, vo.getMemEmail());
		
		int result = pstmt.executeUpdate();
		   
		JDBCTemplate.close(pstmt);
		   
		return result; 
		
	}

	public MemberVo printId(Connection conn, String nowEmail) throws Exception {
		
		String sql = "SELECT * FROM MEMBER WHERE MEM_EMAIL = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nowEmail);
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo loginMember =null;
		
		if(rs.next()) {
			String memNo = rs.getString("MEM_NO");
			String memName = rs.getString("MEM_NAME");
			String memId = rs.getString("MEM_ID");
			String memPwd = rs.getString("MEM_PWD");
			String memNick = rs.getString("MEM_NICK");
			String memPhoneNum = rs.getString("MEM_PHONE_NUM");
			String memEmail = rs.getString("MEM_EMAIL");
			String quitYn = rs.getString("QUIT_YN");
			String enrollDate = rs.getString("ENROLL_DATE");
			String updateDate = rs.getString("UPDATE_DATE");
			
			loginMember = new MemberVo();
			loginMember.setMemNo(memNo);
			loginMember.setMemName(memName);
			loginMember.setMemId(memId);
			loginMember.setMemPwd(memPwd);
			loginMember.setMemNick(memNick);
			loginMember.setMemPhoneNum(memPhoneNum);
			loginMember.setMemEmail(memEmail);
			loginMember.setQuitYn(quitYn);
			loginMember.setEnrollDate(enrollDate);
			loginMember.setUpdateDate(updateDate);
					
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return loginMember;
	}


}
