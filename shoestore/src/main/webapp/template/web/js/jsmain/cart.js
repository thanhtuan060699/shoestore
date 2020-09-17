function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
document.addEventListener("DOMContentLoaded",function(){
    var sneakerSize=document.getElementsByClassName('sneaker-size');
    var data={};
    for(var i=0;i<sneakerSize.length;i++){
        sneakerSize[i].onclick=function(){
            for(var j=0;j<sneakerSize.length;j++){
                sneakerSize[j].classList.remove('change-size');
            }
            this.classList.add('change-size');
            var dataSize=this.getAttribute('data-size');
            var dataColor=this.getAttribute('data-color');
            data['size']=dataSize;
            data['color']=dataColor;
            data["productId"]=getUrlVars()["id"];
            $.ajax({
                type: "POST",
                url: "/api/customer/productattribute",
                data: JSON.stringify(data),
				dataType: "json",
			    contentType:"application/json",
                success: function (response) {
                    var quantity=document.getElementsByClassName('quantity-detail-product')[0];
                    quantity.innerHTML='<span>Availibility</span> : <b1>only '+response.quantity+ ' left in stock</b1>';
                    var price=document.getElementsByClassName('price-detail-product')[0];
                   
                    price.innerHTML=''+response.price + ' VND ';
                }
            });
        }
    }
},false);
