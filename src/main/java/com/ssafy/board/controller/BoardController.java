package com.ssafy.board.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.util.BoardUtil;

@RestController // @Controller+Responsebody
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController extends HttpServlet {

	BoardService boardService;
	BoardUtil boardUtil = BoardUtil.getInstance();
	
	
	
	

	@Autowired
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	@PostMapping("/regist")
	protected ResponseEntity<?> regist(@RequestBody BoardDto boardDto) {
		try {
			System.out.println(boardDto);
			
//			 비속어 필터
				if (boardUtil.filterSlangs(boardDto.getContent())) {
					System.out.println("비속어 감지");
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
				boardService.registerArticle(boardDto);
//				List<BoardDto> list = boardService.searchListAll();
				return new ResponseEntity<Void>(HttpStatus.CREATED);
//			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/list")
	protected ResponseEntity<?> list(HttpServletRequest request, HttpServletResponse response) {
		List<BoardDto> list = boardService.searchListAll();
		try {

			if (list != null && !list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {
				return new ResponseEntity<List>(Collections.EMPTY_LIST, HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/view/{articleNo}")
	protected ResponseEntity<?> view(@PathVariable("articleNo") int articleNo) throws ServletException, IOException {
		try {
			BoardDto boardDto = boardService.viewArticle(articleNo);
			if(boardDto!=null) {
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	
	}

	@PutMapping("/modify")
	protected  ResponseEntity<?> modify(@RequestBody BoardDto boardDto) {
		try {
//			 비속어 필터
			if (boardUtil.filterSlangs(boardDto.getContent())) {
				System.out.println("비속어 감지");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			boardService.modifyArticle(boardDto);
//			System.out.println(boardDto);
			List<BoardDto> list = boardService.searchListAll();
			if (list != null && !list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {
				return new ResponseEntity<List>(Collections.EMPTY_LIST, HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/delete/{articleNo}")
    protected ResponseEntity<?> delete(@PathVariable int articleNo)
            throws ServletException, IOException {
        try {
            boardService.deleteArticle(articleNo);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
