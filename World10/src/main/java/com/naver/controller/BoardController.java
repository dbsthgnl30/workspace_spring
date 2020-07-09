

  package com.naver.controller;


  import java.util.List;

import javax.inject.Inject;

   

  import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

  import org.springframework.web.bind.annotation.RequestMethod;

   

  import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.service.BoardService;


  @Controller

  @RequestMapping("board")

  public class BoardController {

  	
  	@Inject

  	private BoardService bService;
  	@RequestMapping(value = "/searchlist")
  	public String searchlist(Model model,String searchType, String keyword) {
  	 List<BoardVO> list = bService.searchlist(searchType,keyword);
  	 model.addAttribute("list", list);
  	 model.addAttribute("searchType", keyword);
  	
  	 return "board/searchlist";
  	}
  	
  	@RequestMapping(value = "delete/{bno}",method=RequestMethod.GET)
  	public String delete(@PathVariable("bno")int bno) {
  		bService.delete(bno);
  		return "redirect:/board/list";
  	}
  	
  	@RequestMapping(value="/update",method =RequestMethod.POST)
  	public String update(BoardVO vo) {
  		bService.update(vo);
  		
  		return "redirect:/board/read/"+vo.getBno();//몇번째 글에 대해 읽어오겠다
  	}
  	
  	
  	@RequestMapping(value = "/update/{bno}",method=RequestMethod.GET)
  	public String update(Model model,@PathVariable("bno")int bno) {
  			BoardVO vo =bService.updateUI(bno);
  			model.addAttribute("vo", vo);
  			return "board/update";
  	}

  	
  	@RequestMapping(value = "/read/{bno}", method= RequestMethod.GET)
  	public String read(Model model, @PathVariable("bno")int bno){
  		BoardVO vo =bService.read(bno);
  		model.addAttribute("vo", vo);
  		return "board/read";
  		
  	}
  	
  	@RequestMapping(value = "/list",method=RequestMethod.GET)
  	public void list(Model model,String curPage) {
  		
  		int page =-1;
  		if(curPage ==null) {
  			page=1;
  			
  		}else {
  			page=Integer.parseInt(curPage);
  		}
  		PageTO<BoardVO> to = new PageTO<BoardVO>(page);
  		to= bService.list(to);
  	model.addAttribute("to",to);
  	//list고치지 않고 사용하기 위해서
	model.addAttribute("list",to.getList());
  	}
  	

  	@RequestMapping(value = "/insert", method = RequestMethod.POST)

  	public String insert(BoardVO vo) {

  		bService.insert(vo);

  		

  		return "redirect:/board/list";

  	}

  	@RequestMapping(value = "/insert", method = RequestMethod.GET)

  	public void insert() {

  		

  	}

  }
