
  window.onload = function() {
          // create a heatmap instance
          var heatmap = h337.create({
            container: document.getElementById('heatmapContainer'),
            maxOpacity: .6,
            radius: 50,
            blur: .90,
            // backgroundColor with alpha so you can see through it 
            backgroundColor: 'rgb(67 67 74 / 96%)'
          });
          var heatmapContainer = document.getElementById('heatmapContainerWrapper');
          var max=4
          var min=0
          var input={};
          input['url']=window.location.href;
          var points=[]
          $.ajax({
              type: "POST",
              url: "/api/heatmap/click/get",
              data: JSON.stringify(input),
				dataType: "json",
			    contentType:"application/json",
              success: function (response) {
            	  var point={};
                for(let i=0;i<response.points.length;i++){
                	point={x:response.points[i].xValue,y:response.points[i].yValue,value:response.points[i].value,radius:50};
                	points[i]=point;
                	console.log(point);
                }
                var data={
                	  max:response.max,
                	  min:response.min-1,
                	  data:points,
                }
                console.log(max);
                heatmap.setData(data)
            
              }
          });

    };