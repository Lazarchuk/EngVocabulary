	/*Variable shows if table is original or not*/
	var isOriginalTable = true;
	
	/* Buttons in DictionaryController*/
	/*function btnSearchFocus(){
		document.getElementById("i_search").style.color = '#E90350';
	}
	function btnSearchBlur(){
		document.getElementById("i_search").style.color = '#FFFFFF';
	}*/
	function btnUpdateFocus(){
		document.getElementById("i_update").style.color = '#E90350';
	}
	function btnUpdateBlur(){
		document.getElementById("i_update").style.color = '#FFFFFF';
	}
	
	/* Buttons in WordController*/
	function btnDelFocus(id){
		document.getElementById(id).style.color = '#E90350';
	}
	function btnDelBlur(id){
		document.getElementById(id).style.color = '#FFFFFF';
	}

	/*Table tr onmouseover, onmouseout*/
	function tableButtonShow(divId){
		document.getElementById(divId).style.display = 'block';
	}
	function tableButtonHide(divId){
		document.getElementById(divId).style.display = 'none';
	}
	
	
	/*Save all body in variable*/
	var original_table = document.getElementById("tab").innerHTML;
	
	
	/*OLD*/
	/*Search words onclick button*/
	/*function searchButton(){
		document.getElementById("searchbutton").blur();
		$.ajax({
			type: "POST",
			url: "/search/result",
			data: {search: document.getElementById("searchtext").value},
			success: function(result){
				if (result.length == 0){
					document.getElementById('search-failure').style.display = 'block';
					/*Delete all words from table*/
					/*var table = document.getElementById("tab");
					table.innerHTML = '<tr><th class="word-cell">Word</th><th class="transl-cell">Translation</th><th class="speechp-cell">Speech Part</th><th>Category</th></tr>';
					isOriginalTable = false;
				}
				if (result.length > 0){
					isOriginalTable = false;
					document.getElementById('search-failure').style.display = 'none';					
					/*Delete all words from table*/
					/*var allElements = document.getElementsByTagName("tr");	
					var size = allElements.length;
					for (var i = 0; i < size-1; i++){
						allElements[1].parentNode.removeChild(allElements[1]);
					}
					
					/*Add all found words to the table*/
					/*for (var i = 0; i < result.length; i++){
						var wordEng = result[i].wordEng;
						var wordUkr = result[i].wordUkr;
						var part = result[i].speechPart;
						var category = result[i].category;
						var item = '<td><a href="/word/'+wordEng+'">'+wordEng+'</a></td><td>'+wordUkr+'</td><td>'+part+'</td><td>'+category+'</td>';
						var tr = document.createElement("tr");
						tr.innerHTML = item;
						document.getElementsByTagName('tbody')[0].appendChild(tr);
					}
					
				}
			}
		});
	}*/
	
	/* Dynamic search */
	function searchInput(searchText){
		if (searchText.length > 2){	
			setTimeout(function(){
				$.ajax({
					type: "POST",
					url: "/search/result",
					data: {search: searchText},
					success: function(result){
						if (result.length == 0){
							document.getElementById('search-failure').style.display = 'block';
							/*Delete all words from table*/
							var table = document.getElementById("tab");
							table.innerHTML = '<tr><th class="word-cell">Word</th><th class="transl-cell">Translation</th><th class="speechp-cell">Speech Part</th><th>Category</th></tr>';
							isOriginalTable = false;
						}
						if (result.length > 0){
							isOriginalTable = false;
							document.getElementById('search-failure').style.display = 'none';					
							/*Delete all words from table*/
							var table = document.getElementById("tab");
							table.innerHTML = '<tr><th class="word-cell">Word</th><th class="transl-cell">Translation</th><th class="speechp-cell">Speech Part</th><th>Category</th></tr>';
							
							/*Add all found words to the table*/
							for (var i = 0; i < result.length; i++){
								var wordEng = result[i].wordEng;
								var wordUkr = result[i].wordUkr;
								var part = result[i].speechPart;
								var category = '';
								if (result[i].category != null){
									category = result[i].category.name;
								}
								/*var category = result[i].category*/
								var item = '<td><a href="/word/'+wordEng+'">'+wordEng+'</a></td><td>'+wordUkr+'</td><td>'+part+'</td><td>'+category+'</td>';
								var tr = document.createElement("tr");
								tr.innerHTML = item;
								document.getElementsByTagName('tbody')[0].appendChild(tr);
							}
						}
					},
					error: function(){
						console.log('Error');
					}
				});
			},
			1000);
		}
	}

	
	/*Clean search_input onfocus and return original body*/
	function cleanSearch(){
		if (!isOriginalTable){
			document.getElementById("searchtext").value = "";
			document.getElementById('search-failure').style.display = 'none';
			document.getElementById("tab").innerHTML = original_table;
			isOriginalTable = true;
		}
	}
	
	/*Delete word by id in dictionary view*/
	function deleteWord(wordIdToDelete){
		$.ajax({
			type: "POST",
			url: "/delete/word",
			data:{wordId: wordIdToDelete},
			success: function(result){
				console.log('Delete success');
				document.getElementById('tr'+wordIdToDelete).remove();
			},
			error: function(){
				console.log('Delete feilure');
			}
			
		});
	}
	
	
	window.onclick = function(event) {
		document.getElementById("searchtext").value = "";
		document.getElementById('search-failure').style.display = 'none';
		document.getElementById("tab").innerHTML = original_table;
		isOriginalTable = true;
	}
