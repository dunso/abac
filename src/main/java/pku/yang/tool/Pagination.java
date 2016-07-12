package pku.yang.tool;

import java.util.List;

public class Pagination<T> {
	int pagesize;
	int page;
	int pagenum;
	List<T> list;
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	

}
