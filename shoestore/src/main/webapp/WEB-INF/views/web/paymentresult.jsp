<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Payment Result</title>
</head>
<body>
 <!-- Start Banner Area -->
 	<div class="containner" style="margin-top: 200px; margin-bottom: 200px">
 		<c:if test="${not empty unsuccessful }">
    	<div class="container">
    		<div class="zero-product-cart" style="text-align: center;font-weight: 200;font-size: 30px">
    			Payment is unsuccessful
    		</div>
            <div class="continue-cart" style="text-align: center;margin-top: 10px"> 
           		 <a class="primary-btn" href="/karma/checkout">
           			 Continue Payment
           		 </a>
            </div>
    	</div>
    </c:if>
    <c:if test="${empty unsuccessful }">
		<table class="table table-hover">
             <thead>
                  <tr>
                      <th></th>
                      <th></th>
                  </tr>
             </thead>
             <tbody>
                   <tr>
                      <td class="col-md-6">Transaction Number:</td>
                      <td class="col-md-6 text-center">${transactionNo}</td>
                   </tr>
                   <tr>
                       <td class="col-md-6">Order Information :</td>
                       <td class="col-md-6 text-center">${orderInfo}</td>
                   </tr>
                   <tr>
                       <td class="col-md-6">Purchase Amount:</td>
                       <td class="col-md-6 text-center"><fmt:formatNumber value = "${amount}" type = "currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/> VND</td>
                   </tr>
             </tbody>
         </table>
         <div>
             <h2 class="text-center text-uppercase">
                  Thank You For Using Our Service
             </h2>
		                    
		</div>
     	
    </c:if>
   
 	</div>
    
</body>
</html>