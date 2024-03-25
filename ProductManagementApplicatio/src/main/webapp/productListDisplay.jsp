<%@page import="com.uma.servlet.ProductDao" import = "java.util.Base64" %>
<%@ page language="java" contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>

<title>Product List</title>
<!-- BootStract CDN link to Get the Support of BootStrap-->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>

<h1 class="text-center text-success">List of Available Products</h1>
<div>
<a class="btn btn-success" href="add-product.html">Save Product</a>
</div>
<div>
	<input type="text" placeholder="search"/>
	
</div>

<div>
<c:if test="${saveresult==1}">
<h3 class="text-center text-primary">Data Inserted Successfully...</h3> 
</c:if>
</div>

<div>
<c:if test="${deleteresult==1}">
<h3 class="text-center text-primary">Data Deleted Successfully...</h3> 
</c:if>

<c:if test="${deleteresult==0}">
<h3 class="text-center text-primary">Data Deleted Fail...</h3> 
</c:if>


</div>

<div>
        <c:if test="${updateresult==1}">
            <h2 class="text-center text-success">Data updated Successfully....</h2>
        </c:if>
    </div>
    <div>
        <c:if test="${updateresult==0}">
            <h2 class="text-center text-success">Data update failed....</h2>
        </c:if>
    </div>


<table class="table table-bordered table-striped">

<thead class="thead-dark">
	<tr>
	
		<th>Product ID</th>
		<th>Product Name</th>
		<th>Product Cost</th>
		<th>Brand</th>
		<th>Made In</th>
		<th>Manufacture Date</th>
		<th>Expire Date</th>
		<th>Image</th>
		<th>Audio</th>
		<th>Video</th>
		<th>Actions</th>
		
	</tr>
</thead>

<tbody>
	   
	<c:forEach var="product" items="<%=new ProductDao().findAll()%>">
	
		<tr>
			<td>${product.proId}</td>
			<td>${product.proName}</td>
			<td>${product.proPrice}</td>
			<td>${product.proBrand}</td>
			<td>${product.proMadeIn}</td>
			<td>${product.proMfgDate}</td>
			<td>${product.proExpDate}</td>
			
			  <td> 
            <img src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(product.proImage)}"  
           alt="Product Image" style="max-width: 100px; max-height: 100px;"> 
        </td>
        
         <!-- Display audio using audio tag -->
                <td>
                    <audio controls>
                        <source src="data:audio/mpeg;base64,${Base64.getEncoder().encodeToString(product.proAudio)}" type="audio/mpeg">
                    </audio>
                </td>
                
                <td>
                    <video controls width="100" height="80">
                        <source src="data:video/mp4;base64,${Base64.getEncoder().encodeToString(product.proVideo)}" type="video/mp4">
                    </video>
                </td>
        
       
 
        <td>
        	<a class="btn btn-danger" href="./DeleteProductServlet?proId=${product.proId}">Delete</a>
        	<a class="btn btn-secondary" href="./EditProductServlet?proId=${product.proId}">Edit</a>
        
        </td>
        
        
       </tr>
	
	
	</c:forEach>
</tbody>


</table>

</body>
</html>