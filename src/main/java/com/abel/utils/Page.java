package com.abel.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;

/**
 * @ClassName: Page
 * @Description: bootstrap风格分页
 * @author abel_ujs@163.com
 * @date 2015年5月12日 下午8:44:46
 * @param <T>
 */
public class Page<T> {

	private long total;

	private List<T> result = new ArrayList<T>();

	private int pageNo = 1;

	/**
	 * pageSize=-1表示不使用分页
	 * 
	 * @Fields pageSize : TODO
	 */
	private int pageSize = 2;

	private int pre;

	private int next;

	private int first;

	private int last;
	
	private int firstIndex;

	/**
	 * @Fields length : 分页宽度
	 */
	private int length = 8;

	/**
	 * @Fields slider : 分页前后显示大小
	 */
	private int slider = 1;

	private boolean isFirst;
	private boolean isLast;

	private String funcName = "page";

	private String message = "";

	public Page() {
		super();
		this.pageSize = -1;
	}

	public Page(long total, List<T> result, int pageNo, int pageSize) {
		super();
		this.total = total;
		this.result = result;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Page(HttpServletRequest req, HttpServletResponse res,
			int defaultPageSize) {
		String no = req.getParameter("pageNo");
		String size = req.getParameter("pageSize");
		if (StringUtils.isNumeric(no)) {
			this.setPageNo(Integer.parseInt(no));
		}
		if (StringUtils.isNumeric("size")) {
			this.setPageSize(Integer.parseInt(size));
		}
		if (defaultPageSize != -2) {
			this.pageSize = defaultPageSize;
		}
	}

	public Page(HttpServletRequest req, HttpServletResponse res) {
		this(req, res, -2);
	}

	@Override
	public String toString() {

		init();

		StringBuilder sb = new StringBuilder();

		if (pageNo == first) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">&#171; 上一页</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "("
					+ pre + "," + pageSize + ");\">&#171; 上一页</a></li>\n");
		}

		int begin = pageNo - (length / 2);
		if (begin < first) {
			begin = first;
		}
		int end = pageNo + length / 2;
		if (end > last) {
			end = last;
			begin = end - length + 1;
			if (begin < first) {
				begin = first;
			}
		}
		if (begin > first) {
			int i = 0;
			for (i = first; i <first + slider; i++) {
				sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName
						+ "(" + i + "," + pageSize + ");\">"  + i
						+ "</a></li>");
			}
			if (i < begin) {
				sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			}
		}

		for (int i = begin; i <= end; i++) {
			if (i == pageNo) {
				sb.append("<li class=\"active\"><a href=\"javascript:\">"
						+ (i + 1 - first) + "</a></li>\n");
			} else {
				sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName
						+ "(" + i + "," + pageSize + ");\">" + (i + 1 - first)
						+ "</a></li>\n");
			}
		}

		if (last - end > slider) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			end = last - slider;
		}

		for (int i = end + 1; i <= last; i++) {
			sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "("
					+ i + "," + pageSize + ");\">" + i + "</a></li>\n");
		}

		if (pageNo == last) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">下一页 &#187;</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "("
					+ next + "," + pageSize + ");\">下一页 &#187;</a></li>\n");
		}

		sb.append("<li><a href=\"javascript:\">共 " + total + " 条" + (message != null ? message : "")
				+ "</a></li>\n");
		sb.insert(0, "<nav><ul class=\"pagination\">\n").append("</ul></nav>\n");
		sb.append("<div style=\"clear:both;\"></div>");
		return sb.toString();
	}

	private void init() {
		this.first = 1;

		this.last = (int) (this.total / this.pageSize);
		if ((this.total % this.pageSize) != 0) {
			this.last++;
		}

		if (this.pageNo == 1) {
			this.isFirst = true;
		}
		if (this.pageNo == this.last) {
			this.isLast = true;
		}

		if (this.pageNo < this.last) {
			this.next = this.pageNo + 1;
		} else {
			this.next = this.last;
		}

		if (this.pageNo > 1) {
			this.pre = this.pageNo - 1;
		} else {
			this.pre = this.first;
		}

	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public int getFirstIndex() {
		int firstIndex=pageSize*(pageNo-1);
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getSlider() {
		return slider;
	}

	public void setSlider(int slider) {
		this.slider = slider;
	}

}
