package kr.co.ca;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //ajax컨트롤러
public class RestTestController {
	
	
	
	@RequestMapping(value = "rt4", method = RequestMethod.POST)

	public String rt4(@RequestBody Map<String, Object> map) {

		

		List<Map<String, Object>> list 

			= (List<Map<String, Object>>)map.get("liststr");

		

		for(Map<String, Object> m : list) {

			System.out.println("::::::::::::::");

			System.out.println(m.get("id"));

			System.out.println(m.get("name"));

			System.out.println(m.get("age"));

			

			System.out.println("::::::::::::::");

		}

		

		return "world";

	}
	
	
	
	
	
	@RequestMapping(value = "rt3",method=RequestMethod.POST)
	public String rt3(@RequestBody Map<String, Object>map) {
		List<Map<String, Object>> list= (List<Map<String, Object>>) map.get("test4");
		
		
		for(Map<String, Object> m : list) {
	System.out.println(":::::::::::::::::::::::::::");
	System.out.println(m.get("id"));
	System.out.println(m.get("name"));
	System.out.println(m.get("age"));
	System.out.println(":::::::::::::::::::::::::::");
		}
		return "hello";
	}
	
	
	
	
	@RequestMapping(value = "rt2",method=RequestMethod.POST)
	public Map<String, Object> rt2(@RequestBody Map<String, Object> map) {
		System.out.println(map.get("test1"));
		System.out.println(map.get("test2"));
		return map;
	}

	
	@RequestMapping(value = "rt1",method= RequestMethod.POST)
	public String rt1(@RequestBody String test1) {
		System.out.println(test1);
		
		return "ok";
		
	}
}
