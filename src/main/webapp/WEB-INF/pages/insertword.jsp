<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert word</title>
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="/resources/css/main.css" rel="stylesheet">
</head>

<body>
<%@include file="header.jsp" %>
	
	<section>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="features_items">
					<h2 class="title text-center">Insert word</h2>
					
					<c:if test="${errors ne null}">	
						<div class="col-sm-12 word-row"> 
							<div class="word-wrapper">
								<ul class="error">
									<c:forEach items="${errors}" var="error">
										<li>${error}</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</c:if>	
					
					<form:form method="POST" modelAttribute="insertWord">
						<div class="col-sm-12 word-row"> 
							<div class="col-sm-6">
								<div class="word-wrapper">
									<label class="add-word-info">New word</label>
								</div>
								<div class="edit-form">
									<form:input path="wordEng" placeholder="New word" id="newword" pattern="^[a-zA-Z\s]+$" maxlength="40"/>
									<span class="error"></span>
								</div>
							</div>				
							<div class="col-sm-6">
								<div class="word-wrapper">
									<label class="add-word-info">Translation</label>								
								</div>
								<div class="edit-form">
									<form:input path="wordUkr" placeholder="Translation" maxlength="60"/>
								</div>
							</div>
						</div>
						
						<div class="col-sm-12 word-row">
							<div class="col-sm-6">
								<div class="word-wrapper">
									<label class="add-word-info">Speech part</label>
								</div>
								<div class="edit-form">
									<form:select path="speechPart">
										<form:option value="" label="--Select speech part--"/>
										<form:options items="${partsMap}"/>
									</form:select>
								</div>
							</div>					
							<div class="col-sm-6">
								<div class="word-wrapper">
									<label class="add-word-info">Category</label>
								</div>
								<div class="edit-form">
									<form:select path="category">
										<form:option value="" label="--Select category--"/>
										<form:options items="${categoryMap}"/>
									</form:select>
								</div>
							</div>
						</div>
						
						<div class="col-sm-12 word-row">				
							<div class="col-sm-12">
								<div class="word-wrapper">
									<label class="add-word-info">Meaning</label>
								</div>
								<div class="edit-form">
									<form:textarea path="meaning" cols="70" rows="4" cssStyle="height: 48px" placeholder="Meaning" maxlength="200"/>
								</div>
							</div>
						</div>
						
						<div class="col-sm-12 word-row">				
							<div class="col-sm-12">
								<div class="word-wrapper">
									<label class="add-word-info">Example</label>
								</div>
								<div class="edit-form">
									<form:textarea path="example" cols="70" rows="4" cssStyle="height: 48px" placeholder="Example" maxlength="200"/>
								</div>
							</div>
						</div>
						
						<div class="col-sm-12 pull-right word-row">
							<div class="edit-form">
								<button type="submit" class="btn btn-default">Submit</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</section>


</body>
</html>
<script>
	/*var input = document.getElementById("newword");
	input.setAttribute("pattern", "[A-Za-z]");*/
	
	/*var newWord = document.getElementById('newWord').value;
	if (newWord != null){
		if (!/[a-zA-Z]/.test(newWord)){
			document.getElementById('newWord').style.border = '2px solid #FF4100';
		}
		else{
			document.getElementById('newWord').style.border = 'medium none';
		}
	}	*/
	
	/*var form = document.getElementsByTagName('form');
	var newword = document.getElementById('newword');
	var error = document.querySelector('.error');
	newword.addEventListener("input", function (event) {
		if (!newword.validity.valid) {
			error.innerHTML = "Enter new word.";
			error.className = "error active";
			event.preventDefault();
		}
	}, false);*/
</script>
