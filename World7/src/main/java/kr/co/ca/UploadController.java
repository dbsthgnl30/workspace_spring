package kr.co.ca;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.utils.Utils;


@Controller
public class UploadController {

	@RequestMapping(value = "/uploadform",method=RequestMethod.POST)
	public void uploadform(MultipartHttpServletRequest request,Model model) throws Exception {
		
		
		MultipartFile file =request.getFile("file");
		String originalName=file.getOriginalFilename();
		
		String fileName =Utils.saveFile(originalName, file);
		model.addAttribute("fileName", fileName);
		
		
	}
	
	
	@RequestMapping(value = "/uploadform",method=RequestMethod.GET)
	public void uploadform() {
		
	}
}
