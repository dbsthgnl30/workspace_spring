package kr.co.domain;

import java.util.List;

public class PageTO {
	private int curPage =1;
	private int perPage=10;
	private int pageLine=10;
	private int amount;//글의개수
	
	private int totalPage;
	private int startNum;
	private int endNum;
	private int beginPageNum;//눈에 보이는 페이징 시작번호 ex)1-10 이면 1
	private int stopPageNum;//눈에 보이는 페이징 마지막번호 ex)1-10 이면 10
	
	private List<BoardVO> list;//perPage에 따라 달라진다
	
	public PageTO() {
		executeAll();	// TODO Auto-generated constructor stub
	}
	
	public PageTO(int curPage) {
		super();
		this.curPage = curPage;
		executeAll();
	}
	

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
		executeAll();
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
		executeAll();
	}

	public int getPageLine() {
		return pageLine;
	}

	public void setPageLine(int pageLine) {
		this.pageLine = pageLine;
		executeAll();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		executeAll();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getBeginPageNum() {
		return beginPageNum;
	}

	public void setBeginPageNum(int beginPageNum) {
		this.beginPageNum = beginPageNum;
	}

	public int getStopPageNum() {
		return stopPageNum;
	}

	public void setStopPageNum(int stopPageNum) {
		this.stopPageNum = stopPageNum;
	}

	public List<BoardVO> getList() {
		return list;
	}

	public void setList(List<BoardVO> list) {
		this.list = list;
	}

	private void executeAll() {
		totalPage = (amount-1)/perPage +1;
		startNum= (curPage-1)*perPage +1;
		endNum =curPage*perPage;
		if(endNum>amount) {
			endNum= amount;
			
		}
		beginPageNum=((curPage-1)/pageLine)*pageLine+1;
		stopPageNum=beginPageNum+(pageLine-1);
		if(stopPageNum>totalPage) {
			stopPageNum=totalPage;
		}
	}
	
}
