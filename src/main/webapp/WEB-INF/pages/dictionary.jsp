<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Dictionary</title>
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="/resources/css/main.css" rel="stylesheet">
	<link href="/resources/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
<%@include file="header.jsp" %>

	
	<section>
	<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<div class="search_box">
					<input type="text" value="" placeholder="Search" oninput="searchInput(this.value)" onfocus="cleanSearch()" id="searchtext"/>
					<button type="button" class="btn btn-default" onclick="cleanSearch()" onfocus="btnUpdateFocus()" onmouseout="btnUpdateBlur()" onmouseover="btnUpdateFocus()" onblur="btnUpdateBlur()">
						<i class="fa fa-redo" id="i_update"></i>
					</button>
				</div>
			</div>
			<div class="col-sm-9" id="search-failure" style="display: none">
				<label class="not_found_search">Not found in dictionary</label>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3">
				<div class="left-sidebar">
					<h2>Filter</h2>
				</div>
			</div>

			<div class="col-sm-9 padding-right">
				<div class="features_items">
					<h2 class="title text-center">Words</h2>
					
					<table id="tab">
						<tr>
						  <th class="word-cell">Word</th>
						  <th class="transl-cell">Translation</th>
						  <th class="speechp-cell">Speech Part</th>
						  <th>Category</th>
						 </tr>
						  
						<c:if test="${words ne null}">
							<c:forEach var="word" items="${words}">
							 <tr>
							  <td><a href="/word/${word.wordEng}">${word.wordEng}</a></td>
							  <td>${word.wordUkr}</td>
							  <td>${word.speechPart}</td>
							  <td>${word.category}</td>
							 </tr>
							</c:forEach> 
						</c:if>	
					</table>
				</div>
			</div>
		</div>
	</div>
</section>

<script src="/resources/js/myfunctions/dictionaryFunc.js"></script>
</body>
</html>