package com.uma.servlet;

import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

@WebServlet("/UpdateProductServlet")
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Read the form data
		String proId= request.getParameter("proId");
		String updatedProName= request.getParameter("proName");
		
		double updatedProductPrice= Double.parseDouble(request.getParameter("proPrice"));
		String updatedProductBrand= request.getParameter("proBrand");
		String updatedProductMadeIn= request.getParameter("proMadeIn");
		
		
		Date updatedProductMfgDate=Date.valueOf(request.getParameter("proMfgDate"));
		Date updatedProductExpDate=Date.valueOf(request.getParameter("proExpDate"));
		
		
		// Create a Product object with updated data
		Product product=new Product();
		product.setProId(proId);
		product.setProName(updatedProName);
		product.setProPrice(updatedProductPrice);
		product.setProBrand(updatedProductBrand);
		product.setProMadeIn(updatedProductMadeIn);
		product.setProMfgDate(updatedProductMfgDate);
		product.setProExpDate(updatedProductExpDate);
		
		Part filePart=request.getPart("newProImage");//"newProImage" is the name of your file input field
		
		if(filePart!=null && filePart.getSize()>0)
		{
			InputStream inputStream=filePart.getInputStream();
			byte[] newProImage=IOUtils.toByteArray(inputStream);
			product.setProImage(newProImage);
		}
		else
		{  
			// If no new image uploaded, set the existing image
			String s=request.getParameter("existingImage");
			byte[] existingImage=Base64.getDecoder().decode(s);
			product.setProImage(existingImage);
		}
		
		 // Update the product in the database
		//Giving the product object to Product DAO layer save method to save the data into database
		ProductDao productDao=new ProductDao();
		int result=0;
		result = productDao.updateById(product);
		
		if(result==1)
		{
			  // If update successful, redirect to the edit form page
			request.setAttribute("updateresult", result);

			RequestDispatcher dispatcher=request.getRequestDispatcher("productListDisplay.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("updateresult", result);
			RequestDispatcher dispatcher=request.getRequestDispatcher("productListDisplay.jsp");
			dispatcher.include(request, response);
		
		}
	}

}
