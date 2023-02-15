package com.springbook1.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook1.biz.user.UserService;
import com.springbook1.biz.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}
	public void insertUser(UserVO vo) {
		this.userDAO.insertUser(vo);
		System.out.println(vo.getName()+" ����� �߰� �Ϸ�");
	}
	public void updateUser(UserVO vo) {
		this.userDAO.updateUser(vo);
		System.out.println(vo.getId()+" id ����� ���� ���� �Ϸ�");
	}
	public void deleteUser(UserVO vo) {
		this.userDAO.deleteUser(vo);
		System.out.println(vo.getName()+" ����� ���� �Ϸ�");
	}
	
}
