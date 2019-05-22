 window.onload= function() {
	var worker  = document.getElementById("manager_select");
	var manager = document.getElementById("worker_select");
	manager.onclick = function(){
		document.getElementById("work_extra").hidden = false;
	}
	worker.onclick = function(){ 
	document.getElementById("work_extra").hidden= true ;
	}
}
