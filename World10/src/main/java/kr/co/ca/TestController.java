package kr.co.ca;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.MemberDTO;

@Controller
public class TestController {
	
	@RequestMapping(value = "rt4", method = RequestMethod.GET)

	public void rt4(Model model) {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		list.add(new MemberDTO("m001", "kim", 33));
		list.add(new MemberDTO("m002", "lee", 55));
		list.add(new MemberDTO("m003", "park", 77));
		model.addAttribute("list", list);

	}
	
	@RequestMapping(value = "rt3",method=RequestMethod.GET)
	public void rt3() {
	}

	
	@RequestMapping(value = "/rt2",method =RequestMethod.GET)
	public void rt2() {
		
	}
	
	@RequestMapping(value = "/rt1", method = RequestMethod.GET)
	public void rt1() {

	}
}
