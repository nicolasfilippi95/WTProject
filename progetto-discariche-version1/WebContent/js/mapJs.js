$(document).ready(function(){	

	var campaignId = document.getElementById("cid");

	
    $.ajax({
    	
        
    	url: "http://localhost:8080/progetto-discariche-version1/convertToJson?campaignid=" + campaignId.value,
        success: function(data) {
        	

        	var json = JSON.parse(data)
        	        	loadMap(json);
      
        },
        
        error: function() {
        	console.log("errore");
        }
    });

});

 /*function loadMap(locality){
	
	var mymap = L.map('mapid').setView([51.505, -0.09], 13);
	L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png?access_token={accessToken}', {
	    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
	    maxZoom: 18,	
	    id: 'mapbox.streets',
	    accessToken: 'your.mapbox.access.token'
	}).addTo(mymap);
	
	for(var i = 0 ; i< locality.length; i++ ){
		  var rocketIcon = L.MakiMarkers.icon({
	            icon: "rocket",
	            color: locality[i].color,
	            size: "m"
	        });
		
	
		var marker = L.marker([locality[i].latitude, locality[i].longitude],{icon: rocketIcon}).addTo(mymap).bindPopup("<b>Dettagli</b><br>Nome: " + locality[i].name+ "<br>Latitudine: " + locality[i].latitude +"<br>Longitudine:" + locality[i].longitude)
	}
	//L.marker([51.5, -0.09]).addTo(mymap)
	//.bindPopup("<b>Hello world!</b><br />I am a popup.").openPopup();
	
	var popup = L.popup();
	function onMapClick(e) {
		popup
			.setLatLng(e.latlng) 
			.setContent("You clicked the map at " + e.latlng.toString())
			.openOn(mymap);
	}

	mymap.on('click', onMapClick);
 }


*/

function loadMap(locality){ 
	//First, specify your Mapbox API access token
	L.MakiMarkers.accessToken = "pk.eyJ1Ijoic3BhZ29kZXYiLCJhIjoiY2poOHV1ZzdkMDYyYzNkbW0wcXdiY2w5NSJ9.Fx8Xar8vl1DbfAZlqJZL_w";

	var mymap = L.map('mapid').setView([51.505, -0.09], 13);
    L.tileLayer('https://api.mapbox.com/styles/v1/spagodev/cjjspgka92n322spj9lnrcptk/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1Ijoic3BhZ29kZXYiLCJhIjoiY2poOHV1ZzdkMDYyYzNkbW0wcXdiY2w5NSJ9.Fx8Xar8vl1DbfAZlqJZL_w', {
    	maxZoom: 50
    }).addTo(mymap);
    
   // var markers = L.markerClusterGroup();
    var markerArray = [];
    for (var i = 0; i < locality.length; i++) {
        var locIcon = L.MakiMarkers.icon({
            icon: "star",
            color: locality[i].color,
            size: "m"
        });
  
       
		var marker = L.marker([locality[i].latitude, locality[i].longitude],{icon: locIcon}).addTo(mymap).bindPopup("<b>Dettagli</b><br>Nome: " + locality[i].name+ "<br>Latitudine: " + locality[i].latitude +"<br>Longitudine:" + locality[i].longitude +"<br>"+"<a href='showDetailsLocality?localityid=" + locality[i].id + "&campaignid=" + locality[i].campaignId + "'>Altri dettagli</a>")
	//var marker = L.marker([locality[i].latitude, locality[i].longitude],{icon: locIcon}).bindPopup("<b>Dettagli</b><br>Nome: " + locality[i].name+ "<br>Latitudine: " + locality[i].latitude +"<br>Longitudine:" + locality[i].longitude +"<br>"+"<a href='showDetailsLocality?localityid=" + locality[i].id + "&campaignid=" + locality[i].campaignId + "'>Altri dettagli</a>")
	
	    //  markers.addLayer(marker);
        markerArray.push(marker);
        
	
	if(i==locality.length-1){//this is the case when all the markers would be added to array
	        var group = L.featureGroup(markerArray); //add markers array to featureGroup
	        mymap.fitBounds(group.getBounds());   
	    }
	
    }


    //map.setView(markersLayer.getBounds().getCenter());
	//L.marker([51.5, -0.09]).addTo(mymap)
	//.bindPopup("<b>Hello world!</b><br />I am a popup.").openPopup();
	
	var popup = L.popup();
	function onMapClick(e) {
		popup
			.setLatLng(e.latlng)
			.setContent("You clicked the map at " + e.latlng.toString())
			.openOn(mymap);
	}

	mymap.on('click', onMapClick);
}
