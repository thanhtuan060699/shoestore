document.addEventListener("DOMContentLoaded",function(){
    var items=document.getElementsByClassName('nav-item');
    for(var i=0;i<items.length;i++){
        items[i].onclick=function(){
            for(var j=0;j<items.length;j++){
                items[j].classList.remove("active");
            }
            items[i].classList.add("active");
            
        }
      
    }
},false);