package kr.or.ddit.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

	private final BoardDAO boardDAO;

	@Override
	public int createBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		int totalRecord = boardDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> dataList = boardDAO.selectBoardList(pagingVO);
		pagingVO.setDataList(dataList);
	}

	@Override
	public BoardVO retrieveBoard(int boNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBoard(int boNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AttatchVO retrieveForDownload(int attNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}