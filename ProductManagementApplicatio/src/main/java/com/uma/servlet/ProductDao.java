package com.uma.servlet;

import java.sql.Connection;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
	
	private static final String statement = null;

	public int save(Product product) throws SQLException  
	{
		//Declare the resources
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		int result=0;
		
		try {
			
			//get the connection
			
			connection = DbConnection.createConnection();
			
			 
			
			//create the PreparedStatement Object
			 preparedStatement=connection.prepareStatement("insert into product5 values(?,?,?,?,?,?,?,?,?,?)");
			//read the data from Employee object and set to parameter
			
			preparedStatement.setString(1, product.getProId());
			preparedStatement.setString(2, product.getProName());
			
			
			preparedStatement.setDouble(3, product.getProPrice());
			preparedStatement.setString(4, product.getProBrand());
			preparedStatement.setString(5, product.getProMadeIn());
			
			preparedStatement.setDate(6, product.getProMfgDate());
			preparedStatement.setDate(7, product.getProExpDate());
			
			
			preparedStatement.setBytes(8, product.getProImage());
			preparedStatement.setBytes(9, product.getProAudio());
			preparedStatement.setBytes(10, product.getProVideo());
			
				
			result=preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		finally {
			
		//before release connection check the connection present or not
			
				
			if(preparedStatement != null)
				preparedStatement.close();
			if(connection!=null)
				connection.close();
			
			}
			
		
		return result;
	}
	
	
	
	public List<Product>findAll()
	{
		List<Product> productList=new ArrayList<Product>();
		try(Connection connection=DbConnection.createConnection();
				Statement statement=connection.createStatement())
		{
			ResultSet resultSet=statement.executeQuery("select * from product5");
			
			while(resultSet.next())
			{   // Populate productList
				Product product=new Product();
				product.setProId(resultSet.getString("proId"));
				product.setProName(resultSet.getString("proName"));
				product.setProPrice(resultSet.getDouble("proPrice"));
				product.setProBrand(resultSet.getString("proBrand"));
				product.setProMadeIn(resultSet.getString("proMadeIn"));
				product.setProMfgDate(resultSet.getDate("proMfgDate"));
				product.setProExpDate(resultSet.getDate("proExpDate"));
				
				product.setProImage(resultSet.getBytes("proImage"));
				product.setProAudio(resultSet.getBytes("proAudio"));
				product.setProVideo(resultSet.getBytes("proVideo"));

				
				
		
				productList.add(product);
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	    
		return productList;
		
	}
	public  int deleteById(String proId) throws SQLException
	{
		int result=0;
		
		Connection connection=DbConnection.createConnection();
		PreparedStatement preparedStatement=connection.prepareStatement("delete from product5 where proId=?");
		
			preparedStatement.setString(1, proId);
			 result=preparedStatement.executeUpdate();
		
	
		return result;
	}
	public Product findById(String proId) 
	{
		Product product=null;
		
		try(Connection connection=DbConnection.createConnection();
				PreparedStatement preparedStatement=connection.prepareStatement("select * from product5 where proId=?"))
		
		{
			preparedStatement.setString(1, proId);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			
			if(resultSet.next())
			{
				product=new Product();
				product.setProId(resultSet.getString("proId"));
				product.setProName(resultSet.getString("proName"));
				product.setProPrice(resultSet.getDouble("proPrice"));
				product.setProBrand(resultSet.getString("proBrand"));
				product.setProMadeIn(resultSet.getString("proMadeIn"));
				product.setProMfgDate(resultSet.getDate("proMfgDate"));
				product.setProExpDate(resultSet.getDate("proExpDate"));
				
				product.setProImage(resultSet.getBytes("proImage"));
				product.setProAudio(resultSet.getBytes("proAudio"));
				product.setProVideo(resultSet.getBytes("proVideo"));
				
				
				
			}
			
			
		}
		
		
	
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	return product;
	}
	
	public int updateById(Product product) 
	{
		String sql="update Product5 SET proName=?,proPrice=?,proBrand=?,proMadeIn=?,proMfgDate=?,proExpDate=?, proImage=? where proId=?";
		
		int updateResult = 0;
		try(Connection connection=DbConnection.createConnection()){
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, product.getProName());
				preparedStatement.setDouble(2, product.getProPrice());
				preparedStatement.setString(3, product.getProBrand());
				preparedStatement.setString(4, product.getProMadeIn());
				preparedStatement.setDate(5, product.getProMfgDate());
				preparedStatement.setDate(6, product.getProExpDate());
				preparedStatement.setBytes(7, product.getProImage());
			
				preparedStatement.setString(8, product.getProId());
				updateResult = preparedStatement.executeUpdate();
					
				}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return updateResult;
		
	}
	
	

}
