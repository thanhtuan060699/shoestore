    function  addCart(id){
       var product_id=document.getElementsByClassName('btn-add-cart');
       for(var i=0;i<product_id.length;i++){
          var data_id=product_id[i].getAttribute('data-id');
          if(data_id==id){
            var name=document.getElementsByClassName('listproduct-price')[i].getAttribute('data-name');
            var price=document.getElementsByClassName('listproduct-price')[i].getAttribute('data-price');
            var color=document.getElementsByClassName('listproduct-color')[i].getAttribute('data-color');
            var quantity=document.getElementsByClassName('listproduct-color')[i].getAttribute('data-quantity');
            var size=document.getElementsByClassName('listproduct-size')[i].getAttribute('data-size');
            var productAttributeId=document.getElementsByClassName('listproduct-size')[i].getAttribute('data-at');
            var data={};
            data['name']=name;
            data['price']=price;
            data['color']=color;
            data['maxQuantity']=quantity;
            data['size']=size;
            data['productAttributeId']=productAttributeId;
            $.ajax({
                type: "POST",
                url: "/api/cart/add/list",
                data: JSON.stringify(data),
                dataType: "json",
                contentType:"application/json",
                success: function (response) {
                    if(response==0){
                       alert('This product have already existed');
                    }else{
                       alert('Add in cart successful')
                       var numberCart=document.getElementsByClassName('numberCart')[0];
                       numberCart.outerHTML='<span class="numberCart">('+response+')</span>';
                    }
                        
             }
         });
            break;
          }
        }
               
}
   
