package com.empty.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.mypage.service.MyPageService;

/**
 * Servlet implementation class CreateSeatServlet
 */
@WebServlet("/createSeat")
public class CreateSeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSeatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String storeId = request.getParameter("storeId");
		int col = Integer.parseInt(request.getParameter("col"));
		int row = Integer.parseInt(request.getParameter("row"));
		String seatCheck = request.getParameter("seatCheck");
		
		String[] check = seatCheck.split(",");
		String seatNum = "";
		List<String> seatNums = new ArrayList();
		for(int i=1; i<=check.length; i++) {
			if(!check[i-1].equals("0")) {
				seatNum += i + ",";
				seatNums.add(i+"");
			} else {
				seatNum += 0 + ",";
			}
		}
		seatNum = seatNum.substring(0, seatNum.lastIndexOf(","));
		
		int storeSeat = new MyPageService().insertStoreSeat(storeId, col, row, seatNum, seatCheck);
		int storeSeatCheck = new MyPageService().insertStoreSeatCheck(storeId, seatNums);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
