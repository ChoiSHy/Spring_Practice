package com.springbook1.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook1.biz.board.BoardService;
import com.springbook1.biz.board.BoardVO;

public class BoardServiceClient {
	
	public static void main(String[] args) {
		// 1. spring container run
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. Look up BoardServiceImpl from Spring container
		BoardService boardService = (BoardService)container.getBean("boardService");
		
		// 3. Insert test
		BoardVO vo = new BoardVO();
		vo.setTitle("title1");
		vo.setWriter("writer1");
		vo.setContent("temporary content 1...");
		boardService.insertBoard(vo);
		
		// 4. List test
		List<BoardVO> blist = boardService.getBoardList(vo);
		for(BoardVO board : blist)
			System.out.println("---> "+ board.toString());
		
		// 5. Container close
		container.close();
		
	}
}
