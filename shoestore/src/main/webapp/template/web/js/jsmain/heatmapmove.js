 window.onload = function() {
	 document.querySelector('#heatmapContainerWrapper').onmousemove  = function(ev) {
		    var x= ev.pageX;
		    var y= ev.pageY;
		    var z= ev.pageX;
		    var t= ev.pageY;
		    var currentLocation = window.location;
		    var data={};
		    data['value']=1;
		    data['xValue']=x;
		    data['yValue']=y;
		    data['url']=currentLocation.href;
		    $.ajax({
                type: "POST",
                url: "/api/heatmap/move/add",
                data: JSON.stringify(data),
				dataType: "json",
			    contentType:"application/json",
                success: function (response) {
                    
                }
            });
		   
	 }
}