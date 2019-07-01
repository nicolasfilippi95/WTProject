
	function myFunction(){

	
	console.log("ciao");
	var select = document.getElementById("sel").value;
	if(select == "new"){
		document.getElementById("addLocality").style.display="block";
	}
	else{
		document.getElementById("addLocality").style.display="none";
	}

	}
