rdocument.addEventListener("DOMContentLoaded",function(){
   var sizes=document.getElementsByClassName('prod-cont-size');
   for(var i=0;i<sizes.length;i++){
        sizes[i].onclick=function(){
            for(var i=0;i<sizes.length;i++){
                sizes[i].classList.remove('change');
            }
            this.classList.add('change');
        }
   }
},false) 

document.addEventListener("DOMContentLoaded",function(){
    var down=document.getElementsByClassName('prod-minus')[0];
    down.onclick=function(){
        var c_amount=document.getElementsByClassName('amount')[0];
        var amount=c_amount.getAttribute('value');
        if(amount>1){
            amount--;
        }
        c_amount.setAttribute("value",amount);
        console.log(amount);
        
    }
   
},false)

document.addEventListener("DOMContentLoaded",function(){
    var up=document.getElementsByClassName('prod-plus')[0];
    up.onclick=function(){
        var sizes=document.getElementsByClassName('prod-cont-size');
        for(var i=0;i<sizes.length;i++){
        	 console.log(sizes[i]);
            if(sizes[i].getAttribute('class').includes('change')==true){
                var data_amount=sizes[i].getAttribute('data-amount');
                var c_amount=document.getElementsByClassName('amount')[0];
                amount=c_amount.getAttribute('value');
                if(amount<parseFloat(data_amount)){
                    amount++;
                }
                c_amount.setAttribute("value",amount);
            }
           
        
   }
}
},false) 

document.addEventListener("DOMContentLoaded",function(){
    var addCart=document.getElementsByClassName('prod-add')[0];
    addCart.onclick=function(){
        var sneakerName=document.getElementsByClassName('main-ttl')[0].getAttribute("data-sneakername");
        var data={};
        data["sneakerName"]=sneakerName;
        var price=document.getElementsByClassName("prod-price")[0].getAttribute("data-price");
        data["price"]=parseInt(price);
        var c_amount=document.getElementsByClassName('amount')[0];
        amount=c_amount.getAttribute('value');
        data["amount"]=amount;
        var sizes=document.getElementsByClassName('prod-cont-size');
        for(var i=0;i<sizes.length;i++){
        	 console.log(sizes[i]);
            if(sizes[i].getAttribute('class').includes('change')==true){
                var size=sizes[i].getAttribute("data-size");
                var amountMax=sizes[i].getAttribute("data-amount");
                data["amountMax"]=parseInt(amountMax);
                data["size"]=parseFloat(size);
            }
        }
        var image=document.getElementsByClassName('prod-slider-car')[0].getAttribute("data-image");
        data["nameImage"]=image;
        data["sneakerId"]=parseInt(document.getElementById('sneakerId').getAttribute('value'));
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/allstore/cart",
            data: JSON.stringify(data),
            dataType: "json",
            contentType:"application/json",

            success: function (response) {
                console.log(response);
                if(response==1){
                	window.location.href="/allstore/cart";
                }
                if(response==2){
                	alert("Bạn chưa chọn size! Làm ơn chọn size");
                }
                if(response==3){
                	alert("Đôi giày này đã có trong giỏ hàng !!! Bạn có thể sửa số lượng trong giỏ");
                	window.location.href="/allstore/cart?sneakerId="+parseInt(document.getElementById('sneakerId').getAttribute('value'));
                }
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
                location.reload(true);
            }
        });
        
    }
    
},false) 