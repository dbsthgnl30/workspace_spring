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

 

public interface BoardService {

	void insert(BoardVO vo);

	List<BoardVO> list();

	BoardVO read(int bno);

}