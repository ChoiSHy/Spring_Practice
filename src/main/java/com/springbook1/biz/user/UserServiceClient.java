package com.springbook1.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {
	public static void main(String[] args) {
		// 1. Spring �����̳� ����
		AbstractApplicationContext container=
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. Spring �����̳ʷκ��� UserServiceImpl ��ü�� look up 
		UserService userService = (UserService) container.getBean("userService"); 
		
		// 3. �α��� ��� �׽�Ʈ
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test");
		
		UserVO user = userService.getUser(vo);
		if(user!=null)
			System.out.println(user.getName()+"�� ȯ���մϴ�");
		else
			System.out.println("�α��� ����");
		
		// 4.�ű� ����� �߰� 
		user.setId("user2");
		user.setName("�� ö ��");
		user.setPassword("user22");
		user.setRole("�����");
		userService.insertUser(user);
		
		// �߰� ���� Ȯ��
		vo.setId("user2");
		vo.setPassword("user22");
		user=null;
		user=userService.getUser(vo);
		if(user!=null)
			System.out.println(user.getName()+" �߰� Ȯ�ε�");
		else
			System.out.println("error");
		
		// 5. �߰��� ������ ����
		userService.deleteUser(user);
		
		// 6. Spring �����̳� ����
		container.close();
	}
}
