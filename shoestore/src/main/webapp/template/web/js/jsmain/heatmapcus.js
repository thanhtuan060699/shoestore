
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
          var max=100
          var min=0
          var local1={x:100,y:300,value:99,radius:50}
          var local2={x:500,y:300,value:70,radius:50}
          var local3={x:700,y:300,value:40,radius:50}
          var points=[]
          points=points.concat(local1,local2,local3)
          var data={
        	  max:max,
        	  min:min,
        	  data:points
          }
          heatmap.setData(data)
          

    };