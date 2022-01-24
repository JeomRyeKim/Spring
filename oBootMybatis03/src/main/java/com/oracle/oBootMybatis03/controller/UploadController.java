package com.oracle.oBootMybatis03.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	// uploadForm 시작 화면
	@RequestMapping(value = "upLoadFormStart")
	public String upLoadFormStart(Model model) {
		System.out.println("UploadController upLoadFormStart start");
		return "upLoadFormStart";
	}
	
	@RequestMapping(value = "uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
		System.out.println("UploadController uploadForm GET start");
		System.out.println();
	}
	
	@RequestMapping(value = "uploadForm", method = RequestMethod.POST)
	public String uploadForm(HttpServletRequest request, MultipartFile file1, Model model) throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		
		System.out.println("uploadForm POST start");
		logger.info("OriginalFilename : " + file1.getOriginalFilename());
		//logger.info("UploadController title->" + title);
		logger.info("Size : " + file1.getSize());
		logger.info("ContentType : " + file1.getContentType()); // 영상도 올릴 수 있음
		logger.info("uploadPath : " + uploadPath);
		
		// 업로드 하기 위한 메소드
		String savedName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		logger.info("savedName : " + savedName);
		model.addAttribute("savedName", savedName);
		return "uploadResult";		
	}

	private String uploadFile(String originalName, byte[] fileData, String uploadPath) throws Exception {
		UUID uid = UUID.randomUUID(); // UUID : 세계적으로 유일한 번호
		// requestPath = requestPath + "/resource/image";
		System.out.println("uploadPath->" + uploadPath);
		
		// Directory 생성 <- 폴더 = 디렉토리 같은 의미
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) { // 폴더가 존재하지 않을 때
			fileDirectory.mkdirs();	// 폴더를 만들어주는 로직
			System.out.println("업로드용 폴더 생성 : " + uploadPath);
		}
		
		String savedName = uid.toString() + "_" + originalName;
		logger.info("savedName : " + savedName);
		File target = new File(uploadPath, savedName); // 진짜로 업로드 처리
//		File target = new File(requestPath, savedName);
		FileCopyUtils.copy(fileData, target); //org.springframework.util.FileCopyUtils;
		
		return savedName;
	}
	
	@RequestMapping(value = "uploadFileDelete", method = RequestMethod.GET)
	public String uploadFileDelete(HttpServletRequest request, Model model) throws Exception {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		String deleteFile = uploadPath + "036b6fc5-f078-48e4-9cf9-27e9f4da7719_jung1.jpg";
		logger.info("deleteFile : " + deleteFile);
		System.out.println("uploadFileDelete POST start");
		int delResult = uploadFileDelete(deleteFile);
		logger.info("deleteFile result : " + deleteFile);
		model.addAttribute("deleteFile", deleteFile);
		model.addAttribute("delResult", delResult);
				
		return "uploadResult";
	}

	private int uploadFileDelete(String deleteFileName) throws Exception {
		int result = 0;
		logger.info("deleteFileName result : " + deleteFileName);
		File file = new File(deleteFileName);
		
		if(file.exists()) { // 파일이 존재할 때
			if(file.delete()) { // 파일이 존재해서 삭제했을 때
				System.out.println("파일 삭제 성공");
				result = 1;
			}else { // 파일이 존재하지만 삭제하지 못 했을 때
				System.out.println("파일 삭제 실패");
				result = 0;
			}
		}else { // 파일이 없을 때
			System.out.println("파일이 존재하지 않습니다.");
			result = -1;
		}
		return result;
	}
	
}
