function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
function formatCurency(number) {
	var array = [],
	result = "",
	count = 0;
	var number = number.toString();
	if(number.length <3){return}
	for(var i = number.length-1; i>=0; i--){
	count+=1;
	array.push(number[i]);
	if(count==3 && i>=1){ array.push(".");
	count = 0;
	}
	}
	for(var i = array.length -1; i>=0; i--){
	result += array[i];
	}
	return result;
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
                    quantity.outerHTML='<li class="quantity-detail-product" data-quantity="'+response.quantity+'"><span>Availibility</span> : <b1>only '+response.quantity+ ' left in stock</b1></li>';
                    var price=document.getElementsByClassName('price-detail-product')[0];
                   
                    price.outerHTML='<h2 class="price-detail-product" data-price="'+response.price + '"> '+formatCurency(response.price) + ' VND </h2>';
                }
            });
        }
    }
},false);
//add cart
document.addEventListener("DOMContentLoaded",function(){
    var addCart=document.getElementsByClassName('add-cart')[0];
    addCart.onclick=function(){
        var sneakerSizes=document.getElementsByClassName('sneaker-size');
        var index =0;
        for(var i=0;i<sneakerSizes.length;i++){
            if( sneakerSizes[i].getAttribute('class').includes('change')==true){
                index=1;
            }
        }
        if(index==0){
            alert('You have not chosen your size and color');
        }else{
            var name=document.getElementsByClassName('name-detail-product')[0].getAttribute('data-name');
            var sneakerSize=document.getElementsByClassName('sneaker-size');
            var data={};
            var maxQuantity=document.getElementsByClassName('quantity-detail-product')[0].getAttribute('data-quantity');
            data['maxQuantity']=maxQuantity;
            data['name']=name;
            for(var i=0;i<sneakerSize.length;i++){
                if( sneakerSize[i].getAttribute('class').includes('change')==true){
                    var dataSize=sneakerSize[i].getAttribute('data-size');
                    var dataColor=sneakerSize[i].getAttribute('data-color');
                    var productAttributeId=sneakerSize[i].getAttribute('data-atr');
                    data['size']=dataSize;
                    data['color']=dataColor;
                    data['productAttributeId']=productAttributeId;
                }
            }
            var price=document.getElementsByClassName('price-detail-product')[0].getAttribute('data-price');
            data['price']=price;
            var quanntity=document.getElementsByClassName('qty')[0].value;
            data['quantity']=quanntity;
          
            $.ajax({
                type: "POST",
                url: "/api/cart/add",
                data: JSON.stringify(data),
                dataType: "json",
                contentType:"application/json",
                success: function (response) {
                	if(response.statement.message==='sold out and non login'){
                  	  $('#soldOutNonLoginModal').modal();
                    }else if(response.statement.message==='sold out and login'){
                  	  $('#soldOutLoginModal').modal();
                     }else if(response.statement.message==='success'){
                    	 $(function() {
                 		    index=0;
                 		    index=100+window.pageYOffset;
                 		    setTimeout(function() {
                 		        $.bootstrapGrowl("Add into the cart successful", {
                 		            type: 'success',
                 		            align: 'right',
                 		            width: '250',
                 		            delay: 1000,
                 		            offset: {from: 'top', amount: index},
                 		       
                 		        });
                 		    }, 0);
                 		  
                 		});
                         var numberCart=document.getElementsByClassName('numberCart')[0];
                         numberCart.outerHTML='<span class="numberCart">('+response.amount+')</span>';
                     }else if(response.statement.message==='product existed'){
                    	 Swal.fire({
                    		  title: 'Your product existed in the cart?',
                    		  text: "You want to go to the cart",
                    		  icon: 'warning',
                    		  showCancelButton: true,
                    		  confirmButtonColor: '#3085d6',
                    		  cancelButtonColor: '#d33',
                    		  confirmButtonText: 'Go to the cart'
                    		}).then((result) => {
                    		  if (result.isConfirmed) {
                    			  window.location.href="/karma/cart";
                    		  }
                    		})
                        var numberCart=document.getElementsByClassName('numberCart')[0];
                        numberCart.outerHTML='<span class="numberCart">('+response.amount+')</span>';
                	}
                    
                }
            });
        }
       
    }
   
},false);
//increase quantity of cart
document.addEventListener("DOMContentLoaded",function(){
    var increase=document.getElementsByClassName('increase')[0];
    increase.onclick=function(){
        var sneakerSizes=document.getElementsByClassName('sneaker-size');
        var index =0;
        for(var i=0;i<sneakerSizes.length;i++){
            if( sneakerSizes[i].getAttribute('class').includes('change')==true){
                index=1;
            }
        }
        if(index==0){
            alert('You have not chosen your size and color');
        }else{
            var data={};
            var quantity=document.getElementsByClassName('qty')[0].getAttribute('value');
            data['quantity']=quantity;
            var maxQuantity=document.getElementsByClassName('quantity-detail-product')[0].getAttribute('data-quantity');
            data['maxQuantity']=maxQuantity;
            $.ajax({
                type: "POST",
                url: "/api/cart/add/increase",
                data: JSON.stringify(data),
                dataType: "json",
                contentType:"application/json",
                success: function (response) {
                    var quanntityChange=document.getElementsByClassName('qty')[0];
                    quanntityChange.setAttribute('value',response.quantity);
                }
            });
        }
      
    }
},false)
//reduce quantity of cart
document.addEventListener("DOMContentLoaded",function(){
   
    var reduce=document.getElementsByClassName('reduced')[0];
    reduce.onclick=function(){
        var sneakerSizes=document.getElementsByClassName('sneaker-size');
        var index =0;
        for(var i=0;i<sneakerSizes.length;i++){
            if( sneakerSizes[i].getAttribute('class').includes('change')==true){
                index=1;
            }
        }
        if(index==0){
            alert('You have not chosen your size and color');
        }else{
            var data={};
            var quantity=document.getElementsByClassName('qty')[0].getAttribute('value');
            data['quantity']=quantity;
            var maxQuantity=document.getElementsByClassName('quantity-detail-product')[0].getAttribute('data-quantity');
            data['maxQuantity']=maxQuantity;
            $.ajax({
                type: "POST",
                url: "/api/cart/add/reduce",
                data: JSON.stringify(data),
                dataType: "json",
                contentType:"application/json",
                success: function (response) {
                    var quanntityChange=document.getElementsByClassName('qty')[0];
                    quanntityChange.setAttribute('value',response.quantity);
                }
            });
        }
     
    }
},false)
//change quantity of cart

function changeQuantity(){
    var sneakerSizes=document.getElementsByClassName('sneaker-size');
    var index =0;
    for(var i=0;i<sneakerSizes.length;i++){
        if( sneakerSizes[i].getAttribute('class').includes('change')==true){
            index=1;
        }
    }
    if(index==0){
        alert('You have not chosen your size and color');
    }else{
        var qty=document.getElementsByClassName('qty')[0];
        var quantityCurrent=qty.getAttribute('value');
        var quantityChange=qty.value;
        if(quantityChange!=quantityCurrent){
           var data={};
           data['quantity']=quantityChange;
           var maxQuantity=document.getElementsByClassName('quantity-detail-product')[0].getAttribute('data-quantity');
           data['maxQuantity']=maxQuantity;
           $.ajax({
               type: "POST",
               url: "/api/cart/add/change",
               data: JSON.stringify(data),
               dataType: "json",
               contentType:"application/json",
               success: function (response) {
                   console.log(response);
                   var quantityC=document.getElementsByClassName('qtyChange')[0];
                   quantityC.outerHTML='<input type="number"  value="'+response.quantity+'" title="Quantity:" class="input-text qty qtyChange"  onchange="changeQuantity();">';
               }
           });
        }
    }
	
}

//contact
document.addEventListener("DOMContentLoaded",function(){
    var btnContact=document.getElementById('btnContact');
    var data={};
    btnContact.onclick=function(){
      var email=document.getElementById('contact-email').value;
      var phone=document.getElementById('contact-phone').value;
      var name=document.getElementById('contact-name').value;
      var shoeName=document.getElementsByClassName('name-detail-product')[0].getAttribute('data-name');
      var sneakerSize=document.getElementsByClassName('sneaker-size');
      var data={};
      var maxQuantity=document.getElementsByClassName('quantity-detail-product')[0].getAttribute('data-quantity');
      data['maxQuantity']=maxQuantity;
      data['name']=name;
      for(var i=0;i<sneakerSize.length;i++){
          if( sneakerSize[i].getAttribute('class').includes('change')==true){
              var dataSize=sneakerSize[i].getAttribute('data-size');
              var dataColor=sneakerSize[i].getAttribute('data-color');
              var productAttributeId=sneakerSize[i].getAttribute('data-atr');
              data['size']=dataSize;
              data['color']=dataColor;
        
          }
      }
      data['email']=email;
      data['phoneNumber']=phone;
      data['name']=name;
      data['shoeName']=shoeName;
  
      $.ajax({
        type: "POST",
        url: "/api/contact/add",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
        	if(response.notification.message=="add successful"){
        		Swal.fire(
        				  'Successful!',
        				  'We will contact with you as soon as possible',
        				  'success'
        				)
        	}else if(response.notification.message=="existed contact"){
        		Swal.fire(
      				  'We have alreay gotten your contact!',
      				  'We will contact with you as soon as possible',
      				  'success'
      				)
        	}
       }
    });
    }
},false)
   
