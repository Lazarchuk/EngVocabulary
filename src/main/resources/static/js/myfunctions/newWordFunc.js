	
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
	
	/* Add new category */
	function addCategory(){
		$.ajax({
			type: "POST",
			url: "/word/new/category",
			data:{
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