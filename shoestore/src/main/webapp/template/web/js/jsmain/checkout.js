document.addEventListener("DOMContentLoaded",function(){
    var btn_order=document.getElementsByClassName('btn-order')[0];
    var check_box_rule=document.getElementById('f-option4');
    btn_order.onclick=function(){
        var index=validate();
        if(index==7){
            var data={};
            var formData=$('#formEdit').serializeArray();
            $.each(formData,function(index,v){
                data[""+v.name+""]=v.value;
            });
            var atm=document.getElementById('f-option6');
            var payment=null;
           
            if(atm.checked){
              payment=atm.getAttribute('data-payment');
            }
            var master=document.getElementById('f-option7');
            if(master.checked){
            	payment=master.getAttribute('data-payment');
            }
            
            data["methodPayment"]=payment;
             if(check_box_rule.checked==false){
                 var condition=document.getElementsByClassName('conditions')[0];
                 condition.outerHTML='<label for="f-option4" style="color: red;font-size: 14px" class="conditions">I need to read and accept the </label>';
             }else{
                 $.ajax({
                     type: "POST",
                     url: "/api/customer/order/add",
                     data: JSON.stringify(data),
                     dataType: "json",
                     contentType:"application/json",
                     success: function (response) {
                       if(response.success==true){
                           window.location.href=""+response.message;
                       }
                    }
                 });
             }
            
        }
       
    }
},false);

function validate(){
        var index=0;
         //validate fullname
        var fullname=document.getElementById('fullName');
        if(fullname.value==null||fullname.value==''){
           var inform_validate=document.getElementsByClassName('inform-validate')[0];
           inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> You need to fill out the name column</div>';
        }else{
           index++;
    }
    //validate phone number
        var phoneNumber=document.getElementById('phoneNumber');
        var phonePattern=/^[0-9]*$/;
        console.log(phoneNumber.value.length);
        console.log(phonePattern.test(phoneNumber.value));
        if(phoneNumber.value==''){
            var inform_validate=document.getElementsByClassName('inform-validate')[1];
            inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> You need to fill out your name</div>';
        }else if(phoneNumber.value.length<10||phonePattern.test(phoneNumber.value)==false){
            var inform_validate=document.getElementsByClassName('inform-validate')[1];
            inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> Your phone number is wrong</div>';
        }else{
            index++;
            var inform_validate=document.getElementsByClassName('inform-validate')[1];
            inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> </div>';
        }
        //validate email
        var emailPattern=/^[0-9a-zA-Z]+@{1}[a-zA-Z0-9]+[.]{1}[a-zA-Z0-9]+$/;
        var email=document.getElementById('email');
        if(email.value==''){
            var inform_validate=document.getElementsByClassName('inform-validate')[2];
            inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> You need to fill out your email</div>';
        }else if(emailPattern.test(email.value)==false){
            var inform_validate=document.getElementsByClassName('inform-validate')[2];
            inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> Your email is wrong </div>';
        }else{
            index++;
            var inform_validate=document.getElementsByClassName('inform-validate')[2];
            inform_validate.innerHTML='';
        }

        //validate address
        var address=document.getElementById('address');
        if(address.value==''){
            var inform_validate=document.getElementsByClassName('inform-validate')[3];
            inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> Your need to fill out your address</div>';
        }else{
            index++;
            var inform_validate=document.getElementsByClassName('inform-validate')[3];
            inform_validate.innerHTML='';
        }

        //validate province
        var province=document.getElementById('province');
        if(province.value==''){
            var inform_validate=document.getElementsByClassName('inform-validate')[4];
            inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> Your need to fill out your province</div>';
        }else{
            index++;
            var inform_validate=document.getElementsByClassName('inform-validate')[4];
            inform_validate.innerHTML='';
        }

         //validate district
         var district=document.getElementById('district');
         if(district.value==''){
             var inform_validate=document.getElementsByClassName('inform-validate')[5];
             inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> Your need to fill out your district</div>';
         }else{
             index++;
             var inform_validate=document.getElementsByClassName('inform-validate')[5];
             inform_validate.innerHTML='';
         }

         //validate ward
         var ward=document.getElementById('ward');
         if(ward.value==''){
             var inform_validate=document.getElementsByClassName('inform-validate')[6];
             inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> Your need to fill out your ward</div>';
         }else{
             index++;
             var inform_validate=document.getElementsByClassName('inform-validate')[6];
             inform_validate.innerHTML='';
         }
    return index;
};