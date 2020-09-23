//increase

 function increaseCart(id){ 
    var cartName=document.getElementsByClassName('cart-name');
     for(var i=0;i<cartName.length;i++){
         if(id==cartName[i].getAttribute('data-id')){
            var name=document.getElementsByClassName('cart-name')[i].getAttribute('data-name');
            var color=document.getElementsByClassName('cart-color')[i].getAttribute('data-color');
            var size=document.getElementsByClassName('cart-size')[i].getAttribute('data-size');
            var quantity=document.getElementsByClassName('qty')[i].value;
            var maxQuantity=document.getElementsByClassName('cart-quantity')[i].getAttribute('data-maxquantity');
            var price=document.getElementsByClassName('cart-price')[i].getAttribute('data-price');
            data={};
            data['id']=id;
            data['quantity']=quantity;
            data['maxQuantity']=maxQuantity;
            data['price']=price;
            $.ajax({
                type: "POST",
                url: "/api/cart/edit/increase",
                data: JSON.stringify(data),
                dataType: "json",
                contentType:"application/json",
                success: function (response) {
                    console.log(i);
                    console.log(response.cartChange.total);
                    if(response.quantity==maxQuantity){
                    var alert=document.getElementsByClassName('alert-quantity')[0];
                    alert.innerHTML='<div class="alert alert-danger">This product just have '+maxQuantity+' in the stonk</div>';
                    var totalChange=document.getElementsByClassName('cart-total')[i];
                    totalChange.innerHTML='<h5>'+maxQuantity*price+' VND</h5>';
                    }else{
                        var totalChange=document.getElementsByClassName('cart-total')[i];
                        totalChange.innerHTML='<h5>'+response.cartChange.total+' VND</h5>';
                    }
                        var quanntityChange=document.getElementsByClassName('qty')[i];
                        quanntityChange.outerHTML=' <input type="text" name="qty" id="sst" maxlength="12" value="'+response.cartChange.quantity+'" title="Quantity:" class="input-text qty qtyChange">';
                        var subTotal=document.getElementsByClassName('sub-total')[0];
                        subTotal.innerHTML=response.subTotal+' VND';
                        var numberCart=document.getElementsByClassName('numberCart')[0];
                        numberCart.innerHTML=response.subQuantity+'';
                    }
                    });
                 break;
           }
    
           
    }
}
      
//reduce

function  reduceCart(id) { 
    var cartName=document.getElementsByClassName('cart-name');
    for(var i=0;i<cartName.length;i++){
        if(id==cartName[i].getAttribute('data-id')){
            var name=document.getElementsByClassName('cart-name')[i].getAttribute('data-name');
            var id=document.getElementsByClassName('cart-name')[i].getAttribute('data-id');
            var color=document.getElementsByClassName('cart-color')[i].getAttribute('data-color');
            var size=document.getElementsByClassName('cart-size')[i].getAttribute('data-size');
            var quantity=document.getElementsByClassName('qty')[i].value;
            var maxQuantity=document.getElementsByClassName('cart-quantity')[i].getAttribute('data-maxquantity');
            var price=document.getElementsByClassName('cart-price')[i].getAttribute('data-price');
            data={};
            data['id']=id;
            data['quantity']=quantity;
            data['maxQuantity']=maxQuantity;
            data['price']=price; 
            $.ajax({
                type: "POST",
                url: "/api/cart/edit/reduce",
                data: JSON.stringify(data),
                dataType: "json",
                contentType:"application/json",
                success: function (response) {
                    var totalChange=document.getElementsByClassName('cart-total')[i];
                    totalChange.innerHTML='<h5>'+response.cartChange.total+' VND</h5>';
                    var quanntityChange=document.getElementsByClassName('qty')[i];
                    quanntityChange.outerHTML=' <input type="text" name="qty" id="sst" maxlength="12" value="'+response.cartChange.quantity+'" title="Quantity:" class="input-text qty qtyChange">';
                    var alert=document.getElementsByClassName('alert-quantity')[0];
                    alert.innerHTML='';
                    var subTotal=document.getElementsByClassName('sub-total')[0];
                    subTotal.innerHTML=response.subTotal+' VND';
                    var numberCart=document.getElementsByClassName('numberCart')[0];
                    numberCart.innerHTML=response.subQuantity+'';
                }
                });
                    break;
                }
                }
}

//change
function changeQuantityCart(id){
    var cartName=document.getElementsByClassName('cart-name');
    for(var i=0;i<cartName.length;i++){
        if(id==cartName[i].getAttribute('data-id')){
            var name=document.getElementsByClassName('cart-name')[i].getAttribute('data-name');
            var id=document.getElementsByClassName('cart-name')[i].getAttribute('data-id');
            var color=document.getElementsByClassName('cart-color')[i].getAttribute('data-color');
            var size=document.getElementsByClassName('cart-size')[i].getAttribute('data-size');
            var quantity=document.getElementsByClassName('qty')[i].value;
            var maxQuantity=document.getElementsByClassName('cart-quantity')[i].getAttribute('data-maxquantity');
            var price=document.getElementsByClassName('cart-price')[i].getAttribute('data-price');
            data={};
            data['id']=id;
            data['quantity']=quantity;
            data['maxQuantity']=maxQuantity;
            data['price']=price; 
            $.ajax({
                type: "POST",
                url: "/api/cart/edit/change",
                data: JSON.stringify(data),
                dataType: "json",
                contentType:"application/json",
                success: function (response) {
                    var totalChange=document.getElementsByClassName('cart-total')[i];
                    totalChange.innerHTML='<h5>'+response.cartChange.total+' VND</h5>';
                    var quanntityChange=document.getElementsByClassName('qty')[i];
                    quanntityChange.outerHTML=' <input type="text" name="qty" id="sst" maxlength="12" value="'+response.cartChange.quantity+'" title="Quantity:" class="input-text qty qtyChange" onchange="changeQuantityCart(${item.id})">';
                    if(response.quantity>=maxQuantity){
                        var alert=document.getElementsByClassName('alert-quantity')[0];
                        alert.innerHTML='<div class="alert alert-danger">This product just have '+maxQuantity+' in the stonk</div>';
                    }
                    else{
                    	var alert=document.getElementsByClassName('alert-quantity')[0];
                    	alert.innerHTML='';
                    }
                    var subTotal=document.getElementsByClassName('sub-total')[0];
                    subTotal.innerHTML=response.subTotal+' VND';
                    var numberCart=document.getElementsByClassName('numberCart')[0];
                    numberCart.innerHTML=response.subQuantity+'';
                }
                });
                    break;
                }
                }
}
//delete
function deleteCart(id){
    data={};
    data['id']=id;
    $.ajax({
        type: "POST",
        url: "/api/cart/delete",
        data: JSON.stringify(data),
        dataType: "json",
        contentType:"application/json",
        success: function (response) {
        	console.log(response);
            if(response.length==0){
                var cartDelete=document.getElementsByClassName('add-delete')[0];
                cartDelete.innerHTML='<div style="text-align: center;font-weight: bold;font-size: 20px;margin-left:100px" >Not have any products in cart !!</div> ';  
            }else{
            	 for(var i=0;i<response.length;i++){
                     var html='<tr>';
                     	html+= '<td>';
                     	html+= '<div class="media">';
                     	html+='<div class="d-flex">';
                     	html+='<img src="/template/web/img/cart.jpg" alt="">';
                     	html+=' </div>';
                     	html+='<div class="media-body cart-name" data-name="'+response[i].name+'" data-id="'+response[i].id+'">';
                     	html+='<p>'+response[i].name+'</p>';
                     	html+='</div>';
                     	html+='</div>';
                     	html+=' </td>';
                     	html+='<td class="cart-color" data-color="'+response[i].color+'">';;
                     	html+='<h5>'+response[i].color+'</h5>' ;
                     	html+='</td>';
                     	html+='<td  class="cart-size" data-color="'+response[i].size+'">';
                     	html+='<h5>'+response[i].size+'</h5>';
                     	html+='</td>';
                     	html+='<td>';
                     	html+=' <div class="product_count cart-quantity" data-quantity="'+response[i].quantity+'" data-maxquantity="'+response[i].maxQuantity+'">';
                     	html+='<div class="quantity-change-cart" onchange="changeQuantityCart('+response[i].id+')">';
                     	html+=' <input type="text" name="qty" id="sst" maxlength="12" value="'+response[i].quantity+'" title="Quantity:"';
                     	html+='class="input-text qty qtyChange" >';
                     	html+='</div>';
                             
                     	html+='<button  class="increase items-count" onclick="increaseCart('+response[i].id+')"><i class="lnr lnr-chevron-up"></i></button>';
                     	html+='<button  class="reduced items-count"  onclick="reduceCart('+response[i].id+')"><i class="lnr lnr-chevron-down"></i></button>';
                     	html+='</div>';
                     	html+='</td>';
                     	html+='<td class="cart-price" data-price="'+response[i].price+'">';
                     	html+='<h5>'+response[i].price+' VND</h5>';
                     	html+=' </td>';
                     	html+='<td class="cart-total" data-total="'+response[i].total+'">';
                     	html+='<h5>'+response[i].total+' VND</h5>';
                     	html+=' </td>';
                     	html+='<td>';
                     	html+=' <h5 style="font-size: 20px;cursor: pointer;"><i class="fa fa-trash-o" aria-hidden="true" onclick="deleteCart('+response[i].id+')"></i></h5>';
                          
                     	html+=' </td>';
                     	html+=' </tr>';
                 }
               var cartDelete=document.getElementsByClassName('add-delete')[0];
               cartDelete.innerHTML=html;  
            }
           
        }
        });
}
           
    