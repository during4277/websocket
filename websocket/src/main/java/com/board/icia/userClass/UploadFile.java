package com.board.icia.userClass;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadFile {
	//파일 업로드 메소드	
	String fullPath="D:/Work/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVC-Board/resources/upload/";

	public Map<String,String> fileUp(MultipartHttpServletRequest multi){
		System.out.println("fileUp");
		//1.저장경로 찾기
		String root=multi.getSession().getServletContext().getRealPath("/");
		String path=root+"resources/upload/";
		//2.폴더 생성을 꼭 할것...
		File dir=new File(path);
		if(!dir.isDirectory()){  //폴더 없다면
			dir.mkdir();  //폴더 생성
		}
		//3.파일을 가져오기-일태그 이름들 반환
		Iterator<String> files=multi.getFileNames();
		Map<String,String> fMap=new HashMap<String, String>();
		while(files.hasNext()){
			String fileTagName=files.next();
			//파일 메모리에 저장
			MultipartFile mf=multi.getFile(fileTagName);
			String oriFileName=mf.getOriginalFilename();
			fMap.put("oriFileName", oriFileName);
			//4.시스템파일이름 생성  a.txt  ==>112323242424.txt
			String sysFileName=System.currentTimeMillis()+"."
					+oriFileName.substring(oriFileName.lastIndexOf(".")+1);
			fMap.put("sysFileName", sysFileName);
			//5.메모리->실제 파일 업로드
			try {
				mf.transferTo(new File(path+sysFileName));
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fMap;
	}
	
	//파일 다운로드 메소드
	//파일 삭제 메소드
	
	
}

