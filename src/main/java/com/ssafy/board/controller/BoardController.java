package com.ssafy.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.util.BoardUtil;

import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController // @Controller+Responsebody
@RequestMapping("/board")
@CrossOrigin("*")
@Slf4j // log.사용할 때
public class BoardController extends HttpServlet {

	BoardService boardService;
	BoardUtil boardUtil = BoardUtil.getInstance();

	@Autowired
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	@PostMapping("/regist")
	protected ResponseEntity<?> regist(@RequestBody BoardDto boardDto,@RequestParam("upfile") MultipartFile[] files,ServletContext servletContext) throws IllegalStateException, IOException {
		if(files!=null) {
			
		// 파일 저장을 위한 로직..
		String realPath = servletContext.getRealPath("/upload");
		String today = new SimpleDateFormat("yymmdd").format(new Date());
		String saveFolder = realPath+File.separator+today;
		File folder = new File(saveFolder);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>()	;
		for (MultipartFile mfile : files) {
			FileInfoDto fileInfoDto = new FileInfoDto();
			String originalFileName = mfile.getOriginalFilename();
			if(!originalFileName.isEmpty()) {
				String saveFileName = UUID.randomUUID().toString()+originalFileName.substring(originalFileName.lastIndexOf('.'));
				fileInfoDto.setSaveFolder(today);
				fileInfoDto.setOriginalFile(originalFileName);
				fileInfoDto.setSaveFile(saveFileName);
				mfile.transferTo(new File(folder, saveFileName));
			}
			fileInfos.add(fileInfoDto);
		}
		}
		try {
			System.out.println(boardDto);
//			 비속어 필터
			if (boardUtil.filterSlangs(boardDto.getContent())) {
				System.out.println("비속어 감지");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			boardService.registerArticle(boardDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/list")
	protected ResponseEntity<?> list(
			@RequestParam @ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) Map<String, String> map) {
		try {
			BoardListDto list = boardService.searchListAll(map);

			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(list);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/view/{articleNo}")
	protected ResponseEntity<?> view(@PathVariable("articleNo") int articleNo) throws ServletException, IOException {
		try {
			BoardDto boardDto = boardService.viewArticle(articleNo);
			if (boardDto != null) {
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	@PutMapping("/modify")
	protected ResponseEntity<?> modify(@RequestBody BoardDto boardDto) {
		try {
//			 비속어 필터
			if (boardUtil.filterSlangs(boardDto.getContent())) {
				System.out.println("비속어 감지");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			boardService.modifyArticle(boardDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/delete/{articleNo}")
	protected ResponseEntity<?> delete(@PathVariable int articleNo) throws ServletException, IOException {
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
