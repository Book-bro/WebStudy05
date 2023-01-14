package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Mapper
public interface BoardDAO {
	public int insertBoard(BoardVO board);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO); // 게시글에 대해 상세검색을 해야할 시 페이징VO에 단순과 상세검색 조건이 들어있어 paging하면됨
	public BoardVO selectBoard(int boNo);
	public int updateBoard(BoardVO board);
	public int deleteBoard(int boNo);
}
