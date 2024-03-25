package com.uma.servlet;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proId=request.getParameter("proId");
		int result=0;
		ProductDao dao=new ProductDao();
		 try {
			result=dao.deleteById(proId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	if(result==1)
	{
		//to send the result data to jsp file add the data into request object
		request.setAttribute("deleteresult",result);
		RequestDispatcher dispatcher=request.getRequestDispatcher("productListDisplay.jsp");
		dispatcher.forward(request, response);
	}
	else
	{
		request.setAttribute("deleteresult",result);
		RequestDispatcher dispatcher=request.getRequestDispatcher("productListDisplay.jsp");
		dispatcher.forward(request, response);
	}
}
}
