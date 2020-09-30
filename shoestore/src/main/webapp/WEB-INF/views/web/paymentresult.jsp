<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UFT-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Payment Result</title>
</head>
<body>
 <!-- Start Banner Area -->
    <section class="banner-area organic-breadcrumb">
        <div class="container">
            <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
                <div class="col-first">
                    <h1>Checkout</h1>
                    <nav class="d-flex align-items-center">
                        <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                        <a href="single-product.html">Payment</a>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    <!-- End Banner Area -->
    <div class="container">
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
                            <td class="col-md-6 text-center">${amount} VND</td>
                        </tr>
                    </tbody>
                </table>
                <div>
                    <h2 class="text-center text-uppercase">
                        Thank You
                    </h2>
                    
                </div>
      </div>
</body>
</html>