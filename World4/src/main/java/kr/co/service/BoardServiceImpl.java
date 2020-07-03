/*
 * package kr.co.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import kr.co.domain.BoardVO; import kr.co.persistence.BoardDAO;
 * 
 * @Service
 * 
 * @Transactional public class BoardServiceImpl implements BoardService{
 * 
 * @Autowired private BoardDAO bDAO;
 * 
 * @Override public void insert(BoardVO vo) { bDAO.insert(vo);
 * 
 * } }
 */
package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.domain.BoardVO;
import kr.co.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO bDao;
	
	@Override
	public void insert(BoardVO vo) {
		bDao.insert(vo);
	}

	@Override
	public List<BoardVO> list() {
		// TODO Auto-generated method stub
		return bDao.list();
	}

	@Override
	public BoardVO read(int bno) {
		// TODO Auto-generated method stub
		return bDao.read(bno);
	}
}