	var targetWord = document.getElementsByTagName("title")[0].innerHTML;
		
	/* Buttons in WordController*/
	function btnAddFocus(id){
		document.getElementById(id).style.color = '#E90350';
	}
	function btnAddBlur(id){
		document.getElementById(id).style.color = '#FFFFFF';
	}
	function btnSubmitFocus(id){
		document.getElementById(id).style.color = '#E90350';
	}
	function btnSubmitBlur(id){
		document.getElementById(id).style.color = '#FFFFFF';
	}
	
	/* Show/hide forms for edit word. Controller - WordController - eng/word*/
	function showForm(id) {
		var style = document.getElementById(id).style.display;
		if (style == 'block') {
			document.getElementById(id).style.display = 'none';
		}
		if (style == 'none'){
			document.getElementById(id).style.display = 'block';
		}
		var form1 = document.getElementById('form1').style.display;
		var form2 = document.getElementById('form2').style.display;
		var form3 = document.getElementById('form3').style.display;
		var form4 = document.getElementById('form4').style.display;
		var form5 = document.getElementById('form5').style.display;
		var form6 = document.getElementById('form6').style.display;
		
		if (form1 == 'block' || form2 == 'block' || form3 == 'block' || form4 == 'block' || form5 == 'block' || form6 == 'block'){
			document.getElementById('submitBtn').style.display = 'block';
		}
		else{
			document.getElementById('submitBtn').style.display = 'none'
		}
	}
	
	
	/* Add synonym */
	function addNewSynonym(){
		$.ajax({
			type: "POST",
			url: "/word/new/synonym",
			data: {
				wordEng: targetWord,
				synonym: document.getElementById("add_syn").value
			},
			success: function(result){
				if (result.length == 0){
					location.reload();
				}
				else{
					document.getElementById("errors_box").style.display = 'block';
					for (var i = 0; i < result.length; i++){
						var error = result[i];
						var ul = document.getElementById("errors_list");
						var item = '<li>'+error+'</li>';
						ul.innerHTML = item;
					}
				}
			}
		});
	}
	
	
	/* Add antonym */
	function addNewAntonym(){
		$.ajax({
			type: "POST",
			url: "/word/new/antonym",
			data: {
				wordEng: targetWord,
				antonym: document.getElementById("add_ant").value
			},
			success: function(result){
				if (result.length == 0){
					location.reload();
				}
				else{
					document.getElementById("errors_box").style.display = 'block';
					for (var i = 0; i < result.length; i++){
						var error = result[i];
						var ul = document.getElementById("errors_list");
						var item = '<li>'+error+'</li>';
						ul.innerHTML = item;
					}
				}
			}
		});
	}
	
	/* Add synonym - dropdown*/
	function addSynonym(newSynonym){
		$.ajax({
			type: "POST",
			url: "/word/new/synonym",
			data: {
				wordEng: targetWord,
				synonym: newSynonym
			},
			success: function(result){
				if (result.length == 0){
					location.reload();
				}
				else{
					document.getElementById("errors_box").style.display = 'block';
					for (var i = 0; i < result.length; i++){
						var error = result[i];
						var ul = document.getElementById("errors_list");
						var item = '<li>'+error+'</li>';
						ul.innerHTML = item;
					}
				}
			}
		});
	}
	
	
	/* Add antonym - dropdown*/
	function addAntonym(newAntonym){
		$.ajax({
			type: "POST",
			url: "/word/new/antonym",
			data: {
				wordEng: targetWord,
				antonym: newAntonym
			},
			success: function(result){
				if (result.length == 0){
					location.reload();
				}
				else{
					document.getElementById("errors_box").style.display = 'block';
					for (var i = 0; i < result.length; i++){
						var error = result[i];
						var ul = document.getElementById("errors_list");
						var item = '<li>'+error+'</li>';
						ul.innerHTML = item;
					}
				}
			}
		});
	}
	
	/* Delete synonym */
	function deleteSynonym(delSynonym){
		$.ajax({
			type: "POST",
			url: "/word/delete/synonym",
			data:{
				wordEng: targetWord,
				synonym: delSynonym
			},
			success: function(result){
				if (result == true){
					location.reload();
				}
			}
		});
	}
	
	/* Delete antonym */
	function deleteAntonym(delAntonym){
		$.ajax({
			type: "POST",
			url: "/word/delete/antonym",
			data:{
				wordEng: targetWord,
				antonym: delAntonym
			},
			success: function(result){
				if (result == true){
					location.reload();
				}
			}
		});
	}
	
	/* Add new category */
	function addCategory(){
		$.ajax({
			type: "POST",
			url: "/word/new/category",
			data:{
				wordEng: targetWord,
				category: document.getElementById("new_category").value
			},
			success: function(result){
				if (result.length == 0){
					location.reload();
				}
				else{
					document.getElementById("errors_box").style.display = 'block';
					for (var i = 0; i < result.length; i++){
						var error = result[i];
						var ul = document.getElementById("errors_list");
						var item = '<li>'+error+'</li>';
						ul.innerHTML = item;
					}
				}
			}
		});
	}

/* Find word in DB - dropdown synonym*/
	function searchSyn(searchText){
		if (searchText.length > 2){	
			setTimeout(function(){
				$.ajax({
					type: "POST",
					url: "/search/result",
					data: {search: searchText},
					success: function(result){
						if (result.length == 0){
							document.getElementById("dropdown_syn").style.display = "none";
							document.getElementById("add_syn_btn").style.display = "inline-block";
							
						}
						if (result.length > 0){					
							/*Add all found words to the dropdown*/
							document.getElementById("dropdown_syn").style.display = "block";
							document.getElementById("add_syn_btn").style.display = "none";
							var ul = document.getElementById('dropdown_list_syn');
							ul.innerHTML = '';
							for (var i = 0; i < result.length; i++){
								var wordEng = result[i].wordEng;
								var li = document.createElement("li");
								li.setAttribute('onclick', 'addSynonym("'+wordEng+'")');
								li.innerHTML = wordEng;
								ul.appendChild(li);
							}
						}
					}
				});
			},
			1000);
		}
	}
	
	/* Find word in DB - dropdown antonym*/
	function searchAnt(searchText){
		if (searchText.length > 2){	
			setTimeout(function(){
				$.ajax({
					type: "POST",
					url: "/search/result",
					data: {search: searchText},
					success: function(result){
						if (result.length == 0){
							document.getElementById("dropdown_ant").style.display = "none";
							document.getElementById("add_ant_btn").style.display = "inline-block";
							
						}
						if (result.length > 0){					
							/*Add all found words to the dropdown*/
							document.getElementById("dropdown_ant").style.display = "block";
							document.getElementById("add_ant_btn").style.display = "none";
							var ul = document.getElementById('dropdown_list_ant');
							ul.innerHTML = '';
							for (var i = 0; i < result.length; i++){
								var wordEng = result[i].wordEng;
								var li = document.createElement("li");
								li.setAttribute('onclick', 'addAntonym("'+wordEng+'")');
								li.innerHTML = wordEng;
								ul.appendChild(li);
							}
						}
					}
				});
			},
			1000);
		}
	}
	
	window.onclick = function(event) {
		document.getElementById("dropdown_syn").style.display = 'none';
		document.getElementById("dropdown_ant").style.display = 'none';
		document.getElementById("add_syn_btn").style.display = 'none';
		document.getElementById("add_ant_btn").style.display = 'none';
	}

