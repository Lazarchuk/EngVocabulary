	/* Show/hide forms for edit word. Controller - WordController - eng/word*/
	function showForm(id) {
		var style = document.getElementById(id).style.display;
		var buttonStyle = document.getElementById('submitBtn').style.display;
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
	
	/* Button in DictionaryController - eng/dictionary*/
	function btnFocus(){
		document.getElementById('i').style.color = '#E90350';
	}
	
	function btnBlur(){
		document.getElementById('i').style.color = '#FFFFFF';
	}

