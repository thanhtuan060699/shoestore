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
            var image=document.getElementsByClassName('img-fluid')[i].getAttribute('data-image');
            var data={};
            data['name']=name;
            data['price']=price;
            data['color']=color;
            data['maxQuantity']=quantity;
            data['size']=size;
            data['productAttributeId']=productAttributeId;
            data['thumbnailImage']=image;
            $.ajax({
                type: "POST",
                url: "/api/cart/add/list",
                data: JSON.stringify(data),
                dataType: "json",
                contentType:"application/json",
                success: function (response) {
                	console.log(response);
                  if(response.statement.message==='sold out and non login'){
                	  $('#soldOutNonLoginModal').modal();
                   }else if(response.statement.message==='sold out and login'){
                     var saveContact=document.getElementsByClassName('save-contact')[0];
                     saveContact.setAttribute('data-name',name);
                     saveContact.setAttribute('data-size',size);
                     saveContact.setAttribute('data-color',color);
                	  $('#soldOutLoginModal').modal();
                   }else if(response.statement.message==='product existed'){
                    var numberCart=document.getElementsByClassName('numberCart')[0];
                    numberCart.outerHTML='<span class="numberCart">('+response.amounts+')</span>'; 
                  }else if(response.statement.message==='success') {
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
                    numberCart.outerHTML='<span class="numberCart">('+response.amounts+')</span>'; 
                  }else if(response.statement.message==='over quantity') {
                	  $(function() {
              		    index=0;
              		    index=100+window.pageYOffset;
              		    setTimeout(function() {
              		        $.bootstrapGrowl("This product just have "+response.amounts+" in the stonk", {
              		            type: 'info',
              		            align: 'right',
              		            width: '300',
              		            delay: 1000,
              		            offset: {from: 'top', amount: index},
              		       
              		        });
              		    }, 0);
              		  
              		});
                  }
                  
             }
            });
            break;
          }
        }
               
}

document.addEventListener("DOMContentLoaded",function(){
    var btnContact=document.getElementById('btnContact');
    var data={};
    btnContact.onclick=function(){
      var email=document.getElementById('contact-email').value;
      var phone=document.getElementById('contact-phone').value;
      var name=document.getElementById('contact-name').value;
      var index=0;
      if(email==null||email==''){
          var inform_validate=document.getElementsByClassName('inform-validate')[0];
          inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> You need to fill out your email</div>';
          index++;
      }
      if(phone==null||phone==''){
          var inform_validate=document.getElementsByClassName('inform-validate')[1];
          inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> You need to fill out your phonenumber</div>';
          index++;
      }
      if(name==null||name==''){
          var inform_validate=document.getElementsByClassName('inform-validate')[2];
          inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> You need to fill out your name</div>';
          index++;
      }
      var shoeName=document.getElementsByClassName('save-contact')[0].getAttribute('data-name');
      var color=document.getElementsByClassName('save-contact')[0].getAttribute('data-color');
      var size=document.getElementsByClassName('save-contact')[0].getAttribute('data-size');
      data['email']=email;
      data['phoneNumber']=phone;
      data['name']=name;
      data['shoeName']=shoeName;
      data['color']=color;
      data['size']=size;
      
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
//get sneakers by brand id
function getSneakersByBrand(brandId){
	 window.location.href="/karma/listproduct?brandId="+brandId+"&page=1&limit=9";
}