package com.springbook1.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {
	public static void main(String[] args) {
		// 1. Spring 컨테이너 구동
		AbstractApplicationContext container=
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. Spring 컨테이너로부터 UserServiceImpl 객체를 look up 
		UserService userService = (UserService) container.getBean("userService"); 
		
		// 3. 로그인 기능 테스트
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test");
		
		UserVO user = userService.getUser(vo);
		if(user!=null)
			System.out.println(user.getName()+"님 환영합니다");
		else
			System.out.println("로그인 실패");
		
		// 4.신규 사용자 추가 
		user.setId("user2");
		user.setName("김 철 수");
		user.setPassword("user22");
		user.setRole("사용자");
		userService.insertUser(user);
		
		// 추가 여부 확인
		vo.setId("user2");
		vo.setPassword("user22");
		user=null;
		user=userService.getUser(vo);
		if(user!=null)
			System.out.println(user.getName()+" 추가 확인됨");
		else
			System.out.println("error");
		
		// 5. 추가한 데이터 삭제
		userService.deleteUser(user);
		
		// 6. Spring 컨테이너 종료
		container.close();
	}
}
