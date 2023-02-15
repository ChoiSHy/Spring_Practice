package com.springbook1.biz.user;


public interface UserService {
	public void insertUser(UserVO vo);
	public void updateUser(UserVO vo);
	public void deleteUser(UserVO vo);
	public UserVO getUser(UserVO vo);
}
