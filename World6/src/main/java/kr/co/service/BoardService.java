/*
 * package kr.co.service;
 * 
 * import kr.co.domain.BoardVO;
 * 
 * public interface BoardService { void insert(BoardVO vo); }
 */
package kr.co.service;

 

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;

 

public interface BoardService {

	void insert(BoardVO vo);

	List<BoardVO> list();

	BoardVO read(int bno);

	BoardVO updateUI(int bno);

	void update(BoardVO vo);

	void delete(int bno);

	PageTO<BoardVO> list(PageTO<BoardVO> to);

	List<BoardVO> searchlist(String searchType, String keyword);



}