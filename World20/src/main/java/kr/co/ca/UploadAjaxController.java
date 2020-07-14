package kr.co.ca;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.utils.Utils;

@Controller

public class UploadAjaxController {

	private String uploadPath = "C:" + File.separator + "upload";
	
	@ResponseBody
	@RequestMapping(value = "/deletefile",method=RequestMethod.POST)
	public String  deletefile(String filename) {
		filename.replace('/', File.separatorChar);
		int idx = filename.lastIndexOf(".");
		String format= filename.substring(idx+1);
		 MediaType mType= Utils.getMediaType(format);
		 if(mType !=null) {
			String pre= filename.substring(0, 12);
			String suf=filename.substring(14);
			String oriname=pre+suf;
			File orifile= new File(uploadPath+oriname);
			orifile.delete();
		 }
		
		
		File f = new File(uploadPath+filename);
		f.delete();
		
		
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/displayfile", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayfile(String filename){
		ResponseEntity<byte[]> entity = null;
		InputStream in =null;
		try {
			//이미지 파일인지 아닌지 
			int idx = filename.lastIndexOf(".");
			String format = filename.substring(idx+1);
			MediaType mType = Utils.getMediaType(format);
			//?뭘 연결해 주는 코드?
			HttpHeaders headers =new HttpHeaders();
			
			in= new FileInputStream(uploadPath+filename);
			if(mType !=null) {
				headers.setContentType(mType);
			}else {
				idx = filename.indexOf("_"); 
				 String originalName = filename.substring(idx+1);
				 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);//OCTET가 다운로드 하게 해줌   다운로드 하게해주는 마인타입
				 headers.add("Content-Disposition", "attachment;filename=\""+new String(originalName.getBytes("UTF-8"),"ISO-8859-1")); // \"는 데이터로 큰따움표 넣는거
				 				  
				  
				  
				  
				  
			}
			//이미지src에 소스 넣어주는 코드
			entity= new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
						headers,
						HttpStatus.OK);//썸네일, 헤더정보, 통신상태정보
			
			
	      } catch (Exception e) {
	         e.printStackTrace();
	         entity =new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST); //만약실패가 된다면통신상태만넘겨줌..
	         
	      } finally {

	         try {
	            if (in != null) {
	               in.close();
	            }

	         } catch (Exception e2) {
	            e2.printStackTrace();
	            
	            
	         }
	      }
	      return entity;
	 
	   }

	

	@ResponseBody
	@RequestMapping(value = "/uploadajax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")

	public String uploadajax(MultipartHttpServletRequest request) throws Exception {

		MultipartFile file = request.getFile("file");

		String originalName = file.getOriginalFilename();

		String saveFileName = Utils.saveFile(originalName, file, uploadPath);

		return saveFileName;

	}

	@RequestMapping(value = "/uploadajax", method = RequestMethod.GET)

	public void uploadajax() {
	}

}