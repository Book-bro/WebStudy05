package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.exception.NotExistBoardException;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

	private final BoardDAO boardDAO;
	private final AttatchDAO attatchDAO;
	private final PasswordEncoder encoder;
	
	@Value("#{appInfo.saveFiles}") //properties 파일을 찾아줌
	private File saveFiles; //saveFiles가 서버의 폴더역할
	
	@PostConstruct
	public void init() throws IOException {
		log.info("EL로 주입된 첨부파일 저장 경로 : {}", saveFiles.getCanonicalPath());
	}
	
	private int processAttatchList(BoardVO board) {
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList==null || attatchList.isEmpty()) return 0;
		//1. metadata 저장 - DB (ATTATCH)
		int rowcnt = attatchDAO.insertAttatches(board);
		//2. binary 저장 - Middle Tier : (D:\saveFiles)
		try {
			for( AttatchVO attatch : attatchList) {
//				if(1==1)
//					throw new RuntimeException("강제 발생 예외");
				attatch.saveTo(saveFiles); 
			}
			return rowcnt;
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Transactional
	@Override
	public int createBoard(BoardVO board) {
		String plain = board.getBoPass();
		String encoded = encoder.encode(plain);
		board.setBoPass(encoded);
		
		int rowcnt = boardDAO.insertBoard(board);
		// 첨부 파일 등록
		rowcnt += processAttatchList(board);
		
		return rowcnt;
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
		BoardVO board = boardDAO.selectBoard(boNo);
		
		if(board==null) {
			throw new NotExistBoardException(boNo); //커스텀 exception 사용
		}
		boardDAO.incrementHit(boNo);
		return board;
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
