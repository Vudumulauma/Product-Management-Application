<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.util.Base64"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit-Form</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<script src="productList.js"></script>
</head>
<body>

	<div class="container mt-5 text-center">
		<h2 class="text-center font-italic mb-1">Edit Form...</h2>
		
		<form method="post" action="./UpdateProductServlet" enctype="multipart/form-data" onsubmit="return validationForm()">
		<div class="form-group">
				<label class="font-italic font-weight-bold" for="proId">Product ID:</label>
				<input type="text" class="form-control-sm" id="proId" name="proId" value="${existingproduct.proId}" required>
				
	        </div>
	        
	        <div class="form-group">
				<label class="font-italic font-weight-bold" for="proName">Product Name:</label>
				<input type="text" class="form-control-sm" id="proName" name="proName" value="${existingproduct.proName}" required>
				
	        </div>
	        <div class="form-group">
				<label class="font-italic font-weight-bold" for="proPrice">Product Price:</label>
				<input type="text" class="form-control-sm" id="proPrice" name="proPrice" value="${existingproduct.proPrice}" required>
				
	        </div>
	        
	        <div class="form-group">
				<label class="font-italic font-weight-bold" for="proBrand">Product Brand :</label>
				<input type="text" class="form-control-sm" id="proBrand" name="proBrand" value="${existingproduct.proBrand}" required>
				
	        </div>
	        
	        <div class="form-group">
				<label class="font-italic font-weight-bold" for="proMadeIn">Made In:</label>
				<input type="text" class="form-control-sm" id="proMadeIn" name="proMadeIn" value="${existingproduct.proMadeIn}" required>
				
	        </div>
	        
	        <div class="form-group">
				<label class="font-italic font-weight-bold" for="proMfgDate">Product MfgDate:</label>
				<input type="date" class="form-control-sm" id="proMfgDate" name="proMfgDate" value="${existingproduct.proMfgDate}" required>
		    </div>
	        
	        <div class="form-group">
				<label class="font-italic font-weight-bold" for="proExpDate">Product ExpDate:</label>
				<input type="date" class="form-control-sm" id="proExpDate" name="proExpDate" value="${existingproduct.proExpDate}" required>
				
	        </div>
	        
	      
	        <!-- Display the current product image -->
        <div class="form-group">
		<label class="font-italic font-weight-bold" for="proImage">Current Product Image:</label>
		<img src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(existingproduct.proImage)}" alt="Current Product Image" style="max-width: 100px; max-height:100px;">
		<input type="hidden" id="existingImage" name="existingImage" value="${Base64.getEncoder().encodeToString(existingproduct.proImage)}"/>
		</div>
		
		<!-- Allow user to upload a new product image -->
			<div class="form-group">
			<label class="font-italic font-weight-bold" for="proImage">New Product Image:</label>
			<input type="file" class="form-control-sm" id="newProImage" name="newProImage" accept="image/*">
			</div>
		
	        
	         
	        <div>
				<input type="submit" class="btn btn-success" value="Update product"/>
				
			</div>
			
			
			</form>
		
	</div>

</body>
</html>