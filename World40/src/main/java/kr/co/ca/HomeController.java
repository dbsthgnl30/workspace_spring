package kr.co.ca;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value="intertest2",method=RequestMethod.GET)
	public void intertest2(Model model) {
		model.addAttribute("test", "test");
		
	}
	
	
	@RequestMapping("/intertest1")
	public void intertest1() {
		System.out.println("intertest1>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	@RequestMapping("test4")
	public String test4(RedirectAttributes rtts) {
		rtts.addFlashAttribute("str2", "who");
		return "redirect:/test5";
	}
	   @RequestMapping("test5")
	   public void test5(@ModelAttribute("str2") String mm) {
	      System.out.println(mm);
	   }
	   
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
