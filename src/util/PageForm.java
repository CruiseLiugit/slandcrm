package util;

public class PageForm {
	// 当前页数
	private int currentPageNo;

	// 要跳转到的页数
	private int toPageNo;

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public int getToPageNo() {
		return toPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public void setToPageNo(int toPageNo) {
		this.toPageNo = toPageNo;
	}

}
