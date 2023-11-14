package com.ssafy.qna.controller;

import java.sql.SQLException;
import java.util.List;

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

import com.ssafy.qna.model.QnaAnswerDto;
import com.ssafy.qna.model.service.QnaAnswerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/answer")
@CrossOrigin("*")
@Slf4j
public class QnaAnswerController {

	private final QnaAnswerService qnaAnswerService;
	
	public QnaAnswerController(QnaAnswerService qnaAnswerService) {
		this.qnaAnswerService = qnaAnswerService;
	}
	
	//C
	@PostMapping("/regist")
	public ResponseEntity<?> register(@RequestBody QnaAnswerDto qnaAnswerDto) throws SQLException {
		System.out.println(qnaAnswerDto.getContent());
		qnaAnswerService.register(qnaAnswerDto);
		List<QnaAnswerDto> list = qnaAnswerService.searchByQnaId(qnaAnswerDto.getQnaId());
		return new ResponseEntity<List<QnaAnswerDto>>(list, HttpStatus.OK);
	}
	
	//R -> searchByQnaId
	@GetMapping("/search/{qnaId}")
	public ResponseEntity<?> searchByQnaId(@PathVariable int qnaId) throws SQLException {
		List<QnaAnswerDto> list = qnaAnswerService.searchByQnaId(qnaId);
		return new ResponseEntity<List<QnaAnswerDto>>(list, HttpStatus.OK);
	}
	
	//U dto
	@PutMapping("/update")
	public ResponseEntity<?> update(QnaAnswerDto qnaAnswerDto) throws SQLException {
		qnaAnswerService.update(qnaAnswerDto);
		List<QnaAnswerDto> list = qnaAnswerService.searchByQnaId(qnaAnswerDto.getQnaId());
		return new ResponseEntity<List<QnaAnswerDto>>(list, HttpStatus.OK);
	}
	
	//D
	@DeleteMapping("/delete/{qnaAnswerId}")
	public ResponseEntity<?> delete(@PathVariable int qnaAnswerId) throws SQLException {
		try {
			qnaAnswerService.deleteById(qnaAnswerId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
