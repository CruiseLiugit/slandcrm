package util;

import java.util.Collection;

public class PageController {

	// ������
	int totalRowsAmount;

	// 每页显示个数 ���
	int pageSize = 20;

	// 结果集在页面上显示的总页数
	int totalPages;

	// 当前页��
	int currentPage = 1;

	// 下一页
	int nextPage;

	// 上一页
	int previousPage;

	// 是否有下一页
	boolean hasNext;
	// 是否有上一页
	boolean hasPrevious;

	// 当前页显示的数据，开始行数
	int pageStartRow;

	// 当前页显示的数据，结束行数
	int pageEndRow;

	// 查询得到的结果
	private Collection data;

	/**
	 * 根据数据库中总条数、当前页数，给 PageController 类中所有属性赋值 构造方法
	 */
	public PageController(int totalRows, int currentPage) {
		setPageController(totalRows, currentPage);
	}

	public PageController(int totalRows, int currentPage, int pageSize) {
		this.pageSize = pageSize;
		this.setPageController(totalRows, currentPage);
	}

	public void setPageController(int totalRows, int currentPage) {

		setTotalRowsAmount(totalRows);
		setCurrentPage(currentPage);
	}

	/**
	 * 根据数据库总条数，和每页显示数据的条数，计算出总页数
	 * 
	 * @param rows
	 */
	private void setTotalRowsAmount(int rows) {
		if (rows < 0) {
			totalRowsAmount = 0;
		} else {
			totalRowsAmount = rows;
		}

		if (totalRowsAmount % pageSize == 0) {
			totalPages = totalRowsAmount / pageSize;
		} else {
			totalPages = totalRowsAmount / pageSize + 1;
		}
	}

	/**
	 * 根据用户所在当前页数，计算是否有下一页、上一页、下一页数目、上一页数目
	 * 
	 * @param i
	 */
	private void setCurrentPage(int curPage) {
		if (curPage <= 0) {
			currentPage = 1;
		} else if (curPage > totalPages) {
			currentPage = totalPages;
		} else {
			currentPage = curPage;
		}

		if (currentPage == 1) {
			hasPrevious = false;
		} else {
			hasPrevious = true;
		}

		if (currentPage == totalPages) {
			hasNext = false;
		} else {
			hasNext = true;
		}

		nextPage = currentPage + 1;
		previousPage = currentPage - 1;

		if (currentPage != totalPages) {

			pageStartRow = (currentPage - 1) * pageSize + 1;

		} else {
			pageStartRow = (currentPage - 1) * pageSize + 1;
		}

		// 数据库从哪条记录开始查询
		pageStartRow -= 1;
		// 数据库查询到哪里结束
		pageEndRow = pageStartRow + pageSize;

	}

	public int getCurrentPage() {
		return currentPage;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalRowsAmount() {
		return totalRowsAmount;
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public int getPageEndRow() {
		return pageEndRow;
	}

	public String description() {
		String description = "Total:" + this.getTotalRowsAmount() + " items "
				+ this.getTotalPages() + " pages,Current page:"
				+ this.currentPage + " Previous " + this.hasPrevious + " Next:"
				+ this.hasNext + " start row:" + this.pageStartRow
				+ " end row:" + this.pageEndRow;
		return description;
	}

	public void setData(Collection data) {
		this.data = data;
	}

	public Collection getData() {
		return data;
	}

	public static void main(String args[]) {
		PageController pc = new PageController(0, 2);
		System.out.println(pc.description());
	}

}
