<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
                        <a href="single-product.html">Checkout</a>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    <!-- End Banner Area -->

    <!--================Checkout Area =================-->
    <section class="checkout_area section_gap">
        <div class="container">
            <div class="cupon_area">
                <input type="text" placeholder="Enter coupon code">
                <a class="tp_btn" href="#">Apply Coupon</a>
            </div>
            <div class="billing_details">
                <div class="row">
                    <div class="col-lg-8">
                        <h3>Billing Details</h3>
                        <form id="formEdit">
                            <div class="col-md-12 form-group p_star">
                                <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Full Name *">
                                <div class="inform-validate" style="color: red;"></div>
                            </div>
                            <div class="col-md-12 form-group p_star">
                                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Phone Number *">
                                <div class="inform-validate"></div>
                            </div>
                            <div class="col-md-12 form-group p_star">
                                <input type="text" class="form-control" id="email" name="email" placeholder="Email*">
                                <div class="inform-validate"></div>
                            </div>
                            
                            <div class="col-md-12 form-group p_star">
                                <input type="text" class="form-control" id="address" name="address" placeholder="Address*">
                                <div class="inform-validate"></div>
                            </div>
                            <div class="col-md-12 form-group p_star">
                                <input type="text" class="form-control" id="province" name="province" placeholder="Province*">
                                <div class="inform-validate"></div>
                            </div>
                        	 <div class="col-md-12 form-group p_star">
                                <input type="text" class="form-control" id="district" name="district" placeholder="District*">
                                <div class="inform-validate"></div>
                            </div>
                            <div class="col-md-12 form-group p_star">
                                <input type="text" class="form-control" id="ward" name="ward" placeholder="Ward*">
                                <div class="inform-validate"></div>
                            </div>
                            <div class="col-md-12 form-group">
                                <textarea class="form-control" name="message" id="message" rows="1" placeholder="Order Notes"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="col-lg-4">
                        <div class="order_box">
                            <h2>Your Order</h2>
                            <ul class="list">
                                <li>Product <span>Total</span></li>
                                <c:forEach var="item" items="${products}">
                               		 <li><span class="first" style="width: 100px">${item.name}</span> <span class="middle">x ${item.quantity}</span> <span class="last">${item.total} VND</span></li>
                               		 <li><span class="attribute-checkout">Size: ${item.size} VN / Color: ${item.color}</span></li>
                                </c:forEach>
                                
                            </ul>
                            <ul class="list list_2">
                                <li><a href="#">Subtotal <span>${sumTotalPrice} VND</span></a></li>
                                <li><a href="#">Shipping <span>Flat rate: O VND</span></a></li>
                                <li><a href="#">Total <span>$2210.00</span></a></li>
                            </ul>
                            <div class="payment_item">
                                <div class="radion_btn">
                                    <input type="radio" id="f-option5" name="selector">
                                    <label for="f-option5">Check payments</label>
                                    <div class="check"></div>
                                </div>
                                <p>Please send a check to Store Name, Store Street, Store Town, Store State / County,
                                    Store Postcode.</p>
                            </div>
                            <div class="payment_item active">
                                <div class="radion_btn">
                                    <input type="radio" id="f-option6" name="selector" data-payment="onePay">
                                    <label for="f-option6">Pay By ATM Card </label>
                                    <img src="/template/web/img/product/card.jpg" alt="">
                                    <div class="check"></div>
                                </div>
                             
                                <p>Pay via PayPal; you can pay with your credit card if you don’t have a PayPal
                                    account.</p>
                            </div>
                            <div class="payment_item ">
                                <div class="radion_btn">
                                    <input type="radio" id="f-option7" name="selector" data-payment="onePay">
                                    <label for="f-option7">Pay By Master Card,Visa Card </label>
                                    <img src="/template/web/img/product/card.jpg" alt="">
                                    <div class="check"></div>
                                </div>
                             
                                <p>Pay via PayPal; you can pay with your credit card if you don’t have a PayPal
                                    account.</p>
                            </div>
                            <div class="creat_account">
                                <input type="checkbox" id="f-option4" name="selector" >
                                <label for="f-option4" class="conditions">I’ve read and accept the </label>
                                <a href="#">terms & conditions*</a>
                            </div>
                           	    <button class="primary-btn btn-order" style="margin-left: 84px">Order</button>
                            </div>
                       
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--================End Checkout Area =================-->
    <script src="<c:url value='/template/web/js/vendor/jquery-2.2.4.min.js' />"></script>
    <script src="<c:url value='/template/web/js/jsmain/checkout.js' />"></script>
</body>
</html>