package com.uma.servlet;

import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;


@WebServlet("/AddProductServlet")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//Read the data from form 
		String proId= request.getParameter("proId");
		String proName= request.getParameter("proName");
		
		double proPrice= Double.parseDouble(request.getParameter("proPrice"));
		String proBrand= request.getParameter("proBrand");
		String proMadeIn= request.getParameter("proMadeIn");
		
		
		Date proMfgDate=Date.valueOf(request.getParameter("proMfgDate"));
		Date proExpDate=Date.valueOf(request.getParameter("proExpDate"));
		
		
		Part part = request.getPart("proImage");
		InputStream inputStream=part.getInputStream();
		byte[] proImage = IOUtils.toByteArray(inputStream);
		
		//taking the input and convert to InputStream and byte array
				Part part1 = request.getPart("proAudio");
				InputStream inputStream1 = part1.getInputStream();
				byte[] proAudio = IOUtils.toByteArray(inputStream1);
				
				Part part2 = request.getPart("proVideo");
				InputStream inputStream2=part2.getInputStream();
				byte[] proVideo = IOUtils.toByteArray(inputStream2);

		
		
		//Using Above details create the product object
		Product product=new Product();
		product.setProId(proId);
		product.setProName(proName);
		product.setProPrice(proPrice);
		product.setProBrand(proBrand);
		product.setProMadeIn(proMadeIn);
		product.setProMfgDate(proMfgDate);
		product.setProExpDate(proExpDate);
		product.setProImage(proImage);
		product.setProAudio(proAudio);
		product.setProVideo(proVideo);

		
	
		
		//Giving the Product object to DAO layer
		
		ProductDao productDao=new ProductDao();
		int result=0;
		try {
			result = productDao.save(product);
		} 
		catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		if(result==1)
		{
			//to send the result data to jsp file add the data into request object
			request.setAttribute("saveresult", result);
			RequestDispatcher dispatcher=request.getRequestDispatcher("productListDisplay.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter writer=response.getWriter();
			writer.println("Data Insertion Fail Check Once..."+result);
			RequestDispatcher dispatcher=request.getRequestDispatcher("add-product.html");
			dispatcher.include(request, response);
		
		}
	}

}



