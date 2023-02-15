package com.springbook1.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook1.biz.common.JDBCUtil;
import com.springbook1.biz.user.UserVO;

@Repository("userDAO")
public class UserDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String User_Insert = "insert into users values(?,?,?,?)";
	private final String User_Update = "update users set password=?, name=?, role=? where id = ?";
	private final String User_Delete = "delete users where id=?";
	private final String User_Get = "select * from users where id = ?";
	private final String User_List = "select * from users order by id desc";
	
	public void insertUser(UserVO vo) {
		try {
			conn=JDBCUtil.getConnection();
			stmt = conn.prepareStatement(User_Insert);
			stmt.setString(1 ,vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	public void updateUser(UserVO vo) {
		try {
			conn=JDBCUtil.getConnection();
			stmt=conn.prepareStatement(User_Update);
			stmt.setString(1, vo.getPassword());
			stmt.setString(2, vo.getName());
			stmt.setString(3, vo.getRole());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	public void deleteUser(UserVO vo) {
		try {
			conn=JDBCUtil.getConnection();
			stmt=conn.prepareStatement(User_Delete);
			stmt.setString(1, vo.getId());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			conn=JDBCUtil.getConnection();
			stmt=conn.prepareStatement(User_Get);
			stmt.setString(1, vo.getId());
			rs=stmt.executeQuery();
			
			if(rs.next()) {
				user=new UserVO();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
			}
		}catch (Exception e) { e.printStackTrace();
			// TODO: handle exception
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}
	
	public List<UserVO> getUserList(UserVO vo){
		List<UserVO> ulist = new ArrayList<UserVO>();
		try {
			conn=JDBCUtil.getConnection();
			stmt=conn.prepareStatement(User_List);
			rs = stmt.executeQuery();
			while(rs.next()) {
				UserVO user = new UserVO();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				ulist.add(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil. close(rs, stmt, conn);
		}
		return ulist;
	}
}
