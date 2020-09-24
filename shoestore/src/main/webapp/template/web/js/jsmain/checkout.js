document.addEventListener("DOMContentLoaded",function(){
    var btn_order=document.getElementsByClassName('btn-order')[0];
    var check_box_rule=document.getElementById('f-option4');
    btn_order.onclick=function(){
        var index=validate();
        if(index==2){
            var data={};
            var formData=$('#formEdit').serializeArray();
            $.each(formData,function(index,v){
                data[""+v.name+""]=v.value;
            });
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
                           alert('Your shoes will be delivered to your hand as soon as possible !!! Thanks for your supporting');
                           window.location.href="/home";
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
        var fullname=document.getElementById('fullname');
        if(fullname.value==null||fullname.value==''){
           var inform_validate=document.getElementsByClassName('inform-validate')[0];
           inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> You need to fill out the name column</div>';
        }else{
           index++;
    }
    //validate phone number
        var phoneNumber=document.getElementById('phoneNumber');
        var phonePattern=/^[0,9]*$/;
        if(phoneNumber.value==''){
            var inform_validate=document.getElementsByClassName('inform-validate')[1];
            inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> You need to fill out the name column</div>';
        }else if(phoneNumber.value.length<10||phonePattern.test(phoneNumber.value)==false){
            var inform_validate=document.getElementsByClassName('inform-validate')[1];
            inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> Your phone number is wrong</div>';
        }else{
            index++;
            inform_validate.outerHTML='<div class="inform-validate" style="color: red;"> </div>';
        }
    return index;
};