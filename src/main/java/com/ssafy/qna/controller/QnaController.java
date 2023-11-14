package com.ssafy.qna.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.ssafy.qna.model.QnaDto;
import com.ssafy.qna.model.service.QnaService;
import com.ssafy.user.controller.UserContoller;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/qna")
@CrossOrigin("*")
@Slf4j
public class QnaController {

	private final QnaService qnaService;

	public QnaController(QnaService qnaService) {
		this.qnaService = qnaService;
	}

//	void register(QnaDto qnaDto) throws SQLException;
	@PostMapping("/regist")
	public ResponseEntity<?> register(@RequestBody QnaDto qnaDto ) throws SQLException {
		qnaService.register(qnaDto);
		List<QnaDto> list = qnaService.searchAll();
		return new ResponseEntity<List<QnaDto>>(list, HttpStatus.OK);
	}

//	void update(QnaDto qnaDto) throws SQLException;
	@PutMapping("/update")
	protected  ResponseEntity<?> modify(@RequestBody QnaDto qnaDto) {
		try {
			qnaService.update(qnaDto);
			System.out.println(qnaDto);
			List<QnaDto> list = qnaService.searchAll();
			if (list != null && !list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {
				return new ResponseEntity<List>(Collections.EMPTY_LIST, HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	void deleteById(int id) throws SQLException;
	@DeleteMapping("/delete/{id}")
	protected ResponseEntity<?> delete(@PathVariable int id) throws ServletException, IOException {
		try {
			System.out.println("오나");
			System.out.println(id);
			
			qnaService.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
//	QnaDto findById(int id) throws SQLException;
	@GetMapping("/view/{id}")
	protected ResponseEntity<?> findById(@PathVariable("id") int id) throws ServletException, IOException {
		try {
			QnaDto qnaDto = qnaService.findById(id);
			if(qnaDto!=null) {
				return new ResponseEntity<QnaDto>(qnaDto, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	
	}
	
//	List<QnaDto> searchAll() throws SQLException;
	@GetMapping("/list")
	protected ResponseEntity<?> searchAll(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		List<QnaDto> list = qnaService.searchAll();
		try {

			if (list != null && !list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {
				return new ResponseEntity<List>(Collections.EMPTY_LIST, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	List<QnaDto> searchByUserId(String userId) throws SQLException;
	@PostMapping("/list")
	protected ResponseEntity<?> searchByUserId(
			@RequestBody Map<String, String> map) throws SQLException {
		List<QnaDto> list = qnaService.searchByKey(map.get("key"), map.get("word"));
		try {

			if (list != null && !list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {
				return new ResponseEntity<List>(Collections.EMPTY_LIST, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// open 에 따라서 공개여부를 결정하기
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
