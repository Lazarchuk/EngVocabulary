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
	<title>${editWord.wordEng}</title>
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="/resources/css/main.css" rel="stylesheet">
	<link href="/resources/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
<%@include file="header.jsp" %>
	
	<section>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="features_items">
					<h2 class="title text-center">Word info</h2>
					
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
					
					<div class="col-sm-12 word-row" style="display: none" id="errors_box"> 
						<div class="word-wrapper">
							<ul class="error" id="errors_list">
							</ul>
						</div>
					</div>
					
					<form:form method="POST" modelAttribute="editWord">
						<div class="col-sm-12 word-row"> 
							<div class="col-sm-6">
								<div class="word-wrapper">
									<label class="main-word-info">${editWord.wordEng}</label>
									<div class="edit-button">
										<button type="button" class="btn btn-default" onclick="showForm('form1')" onfocus="btnAddFocus('i_edit1')" onmouseout="btnAddBlur('i_edit1')" onmouseover="btnAddFocus('i_edit1')" onblur="btnAddBlur('i_edit1')">
											<i class="fa fa-edit" id="i_edit1"></i>
										</button>			
									</div>
								</div>
								<div class="edit-form">
									<form:input path="wordEng" placeholder="New word" pattern="^[a-zA-Z\s]+$" maxlength="40" id="form1" cssStyle="display: none"/>
									<form:hidden path="id"/>
								</div>
							</div>
							
							<div class="col-sm-6">
								<div class="word-wrapper">
									<label class="main-word-info">${editWord.wordUkr}</label>
									<div class="edit-button">
										<button type="button" class="btn btn-default" onclick="showForm('form2')" onfocus="btnAddFocus('i_edit2')" onmouseout="btnAddBlur('i_edit2')" onmouseover="btnAddFocus('i_edit2')" onblur="btnAddBlur('i_edit2')">
											<i class="fa fa-edit" id="i_edit2"></i>
										</button>			
									</div>									
								</div>
								<div class="edit-form">
									<form:input path="wordUkr" placeholder="Translation" maxlength="60" id="form2" cssStyle="display: none"/>
								</div>
							</div>
						</div>
						
						<div class="col-sm-12 word-row">
							<div class="col-sm-6">
								<div class="word-wrapper">
									<label class="add-word-info">Speech part:  <span>${editWord.speechPart}</span>
									</label>
									<div class="edit-button">
										<button type="button" class="btn btn-default" onclick="showForm('form3')" onfocus="btnAddFocus('i_edit3')" onmouseout="btnAddBlur('i_edit3')" onmouseover="btnAddFocus('i_edit3')" onblur="btnAddBlur('i_edit3')">
											<i class="fa fa-edit" id="i_edit3"></i>
										</button>			
									</div>
								</div>
								<div class="edit-form">
									<form:select path="speechPart" id="form3" cssStyle="display: none">
										<form:option value="" label="--Select speech part--"/>
										<form:options items="${partsMap}"/>
									</form:select>
								</div>
							</div>			
							
							<div class="col-sm-6">
								<div class="word-wrapper">
									<label class="add-word-info">Category:  <span>${editWord.category}</span></label>
									<div class="edit-button">
										<button type="button" class="btn btn-default" onclick="showForm('form4')" onfocus="btnAddFocus('i_edit4')" onmouseout="btnAddBlur('i_edit4')" onmouseover="btnAddFocus('i_edit4')" onblur="btnAddBlur('i_edit4')">
											<i class="fa fa-edit" id="i_edit4"></i>
										</button>			
									</div>
								</div>
								<div class="edit-form" id="form4" style="display: none">
									<form:select path="category">
										<form:option value="" label="--Select category--"/>
										<form:options items="${categoryMap}"/>
									</form:select>
								<div id="add_category">
									<input type="text" value="" placeholder="Add category" onfocus="this.value=''" id="new_category"/>
									<button type="button" class="btn btn-default" onclick="addCategory()" onfocus="btnSubmitFocus('i_plus1')" onmouseout="btnSubmitBlur('i_plus1')" onmouseover="btnSubmitFocus('i_plus1')" onblur="btnSubmitBlur('i_plus1')">
										<i class="fa fa-plus" id="i_plus1"></i>
									</button>
								</div>
								</div>
							</div>
						</div>
						
						<div class="col-sm-12 word-row">				
							<div class="col-sm-12">
								<div class="word-wrapper">
									<label class="add-word-info">Meaning:  <span>${editWord.meaning}</span></label>
									<div class="edit-button">
										<button type="button" class="btn btn-default" onclick="showForm('form5')" onfocus="btnAddFocus('i_edit5')" onmouseout="btnAddBlur('i_edit5')" onmouseover="btnAddFocus('i_edit5')" onblur="btnAddBlur('i_edit5')">
											<i class="fa fa-edit" id="i_edit5"></i>
										</button>			
									</div>
								</div>
								<div class="edit-form">
									<form:textarea path="meaning" cols="70" rows="4" cssStyle="height: 48px; display: none" placeholder="Meaning" maxlength="200" id="form5"/>
								</div>	
							</div>
						</div>
						
						<div class="col-sm-12 word-row">				
							<div class="col-sm-12">
								<div class="word-wrapper">
									<label class="add-word-info">Example:  <span>${editWord.example}</span></label>
									<div class="edit-button">
										<button type="button" class="btn btn-default" onclick="showForm('form6')" onfocus="btnAddFocus('i_edit6')" onmouseout="btnAddBlur('i_edit6')" onmouseover="btnAddFocus('i_edit6')" onblur="btnAddBlur('i_edit6')">
											<i class="fa fa-edit" id="i_edit6"></i>
										</button>			
									</div>
								</div>
								<div class="edit-form">
									<form:textarea path="example" cols="70" rows="4" cssStyle="height: 48px; display:none" placeholder="Example" maxlength="200" id="form6"/>
								</div>
							</div>
						</div>
						
						<div class="col-sm-12 pull-right word-row" id="submitBtn" style="display:none">
							<div class="edit-form">
								<button type="submit" class="btn btn-default">Save</button>
							</div>
						</div>
					</form:form>			
					
					<div class="col-sm-12 word-row">				
						<div class="col-sm-6">
							<div class="word-wrapper">
								<label class="add-word-info">Synonyms:</label>
								<div class="dropdown">
									<div id="dropdown_syn" class="dropdown-content">
										<ul id="dropdown_list_syn">
										</ul>
									</div>
								</div>
								<div class="synonyms-box">
									<c:if test="${editWord.synonyms ne null}">										
										<ul>
											<c:forEach var="synonym" items="${editWord.synonyms}">
												<li><a href="/word/${synonym}">${synonym}</a>
													<div class="delete-syn-ant">
														<button type="button" class="btn btn-default" onclick="deleteSynonym('${synonym}')" onfocus="btnSubmitFocus('${synonym}')" onmouseout="btnSubmitBlur('${synonym}')" onmouseover="btnSubmitFocus('${synonym}')" onblur="btnSubmitBlur('${synonym}')">
															<i class="fa fa-minus" id="${synonym}"></i>
														</button>
													</div>
												</li>
											</c:forEach>
										</ul>
									</c:if>
									<div id="form7">
										<input type="text" value="" placeholder="Add synonym" onfocus="this.value=''" oninput="searchSyn(this.value)" id="add_syn"/>
										<button type="button" class="btn btn-default" id="add_syn_btn" onclick="addNewSynonym()" onfocus="btnSubmitFocus('i_plus2')" onmouseout="btnSubmitBlur('i_plus2')" onmouseover="btnSubmitFocus('i_plus2')" onblur="btnSubmitBlur('i_plus2')">
											<i class="fa fa-plus" id="i_plus2"></i>
										</button>
									</div>
								</div>
							</div>							
						</div>						
						
						<div class="col-sm-6">
							<div class="word-wrapper">
								<label class="add-word-info">Antonyms:</label>
								<div class="dropdown">
									<div id="dropdown_ant" class="dropdown-content">
										<ul id="dropdown_list_ant">
										</ul>
									</div>
								</div>
								<div class="synonyms-box">
									<c:if test="${editWord.antonyms ne null}">										
										<ul>
											<c:forEach var="antonym" items="${editWord.antonyms}">
												<li><a href="/word/${antonym}">${antonym}</a>
													<div class="delete-syn-ant">
														<button type="button" class="btn btn-default" onclick="deleteAntonym('${antonym}')" onfocus="btnSubmitFocus('${antonym}')" onmouseout="btnSubmitBlur('${antonym}')" onmouseover="btnSubmitFocus('${antonym}')" onblur="btnSubmitBlur('${antonym}')">
															<i class="fa fa-minus" id="${antonym}"></i>
														</button>
													</div>
												</li>
											</c:forEach>
										</ul>
									</c:if>
									<div id="form8">
										<input type="text" value="" placeholder="Add antonym" onfocus="this.value=''" oninput="searchAnt(this.value)" id="add_ant"/>
										<button type="button" class="btn btn-default" id="add_ant_btn" onclick="addNewAntonym()" onfocus="btnSubmitFocus('i_plus3')" onmouseout="btnSubmitBlur('i_plus3')" onmouseover="btnSubmitFocus('i_plus3')" onblur="btnSubmitBlur('i_plus3')">
											<i class="fa fa-plus" id="i_plus3"></i>
										</button>
									</div>
								</div>
							</div>
						</div>							
					</div>
				</div>
			</div>
			
			<!-- <div class="col-sm-2 padding-left">
				<div class="left-sidebar">
					<h2>Filter</h2>
				</div>
			</div> -->
		</div>
	</div>
</section>


<script src="/resources/js/myfunctions/worditemFunc.js"></script>
</body>
</html>
