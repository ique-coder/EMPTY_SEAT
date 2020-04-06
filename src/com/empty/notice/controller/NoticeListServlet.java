package com.empty.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.notice.model.vo.Notice;
import com.empty.notice.service.NoticeService;

@WebServlet("/notice")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NoticeListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage;

		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage = 1;
		}

		int numPerPage = 5;

		List<Notice> list = new NoticeService().searchNotice(cPage, numPerPage);

		int totalNotice = new NoticeService().noticeCount();

		int totalPage = (int)Math.ceil((double) totalNotice / numPerPage);

		int pageBarSize = 5;
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo+pageBarSize - 1;

		String pageBar = "";

		if(pageNo == 1) {
			pageBar += "<span> < 이전 </span>";
		}else {
			pageBar += "<a href='" + request.getContextPath() + "/notice?cPage=" + (pageNo - 1) + "'> < 이전 </a>";					
		}

		while(!(pageNo > pageEnd||pageNo > totalPage)) {
			if(pageNo == cPage) {
				pageBar += "<span> " + pageNo + " </span>";
			}else {
				pageBar += "<a href='" + request.getContextPath() + "/notice?cPage=" + (pageNo) + "'> " + pageNo + " </a>";
			}
			pageNo++;
		}

		if(pageNo > totalPage) {
			pageBar += "<span> 다음 > </span>";
		}else {
			pageBar += "<a href='"+request.getContextPath() + "/notice?cPage=" + (pageNo) + "'> 다음 > </a>";					
		}

		request.setAttribute("list",list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/views/notice/noticeList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
