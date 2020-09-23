document.addEventListener("DOMContentLoaded",function(){
    var btn_order=document.getElementsByClassName('btn-order')[0];
    btn_order.onclick=function(){
        var data={};
		var formData=$('#formEdit').serializeArray();
		$.each(formData,function(index,v){
			data[""+v.name+""]=v.value;
        });
        $.ajax({
            type: "POST",
            url: "/api/customer/order/add",
            data: JSON.stringify(data),
            dataType: "json",
            contentType:"application/json",
            success: function (response) {
                
         }
        });
    }
},false)