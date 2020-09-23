<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	 <!-- Start Banner Area -->
    <section class="banner-area organic-breadcrumb">
        <div class="container">
            <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
                <div class="col-first">
                    <h1>Shopping Cart</h1>
                    <nav class="d-flex align-items-center">
                        <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                        <a href="category.html">Cart</a>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    <!-- End Banner Area -->

    <!--================Cart Area =================-->
    <section class="cart_area">
    
   	
        <div class="container">
        <div class="alert-quantity">
        </div>
            <div class="cart_inner">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Product</th>
                                <th scope="col">Color</th>
                                <th scope="col">Size</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Price</th>
                                <th scope="col">Total</th>
                                <th scope="col">Delete</th>
                            </tr>
                        </thead>
                        <tbody class="add-delete">
                           <c:forEach var="item" items="${cart}">
                            <tr>
                                <td>
                                    <div class="media">
                                        <div class="d-flex">
                                            <img src="/template/web/img/cart.jpg" alt="">
                                        </div>
                                        <div class="media-body cart-name" data-name="${item.name }" data-id="${item.id }">
                                            <p>${item.name }</p>
                                        </div>
                                    </div>
                                </td>
                                <td class="cart-color" data-color="${item.color }">
                                    <h5>${item.color }</h5>
                                </td>
                                <td  class="cart-size" data-color="${item.size }">
                                    <h5>${item.size }</h5>
                                </td>
                                <td>
                                    <div class="product_count cart-quantity" data-quantity="${item.quantity}" data-maxquantity="${item.maxQuantity}">
                                    	<div class="quantity-change-cart" onchange="changeQuantityCart(${item.id})">
                                    		<input type="text" name="qty" id="sst" maxlength="12" value="${item.quantity }" title="Quantity:"
                                            class="input-text qty qtyChange" >
                                    	</div>
                                        
                                        <button  class="increase items-count" onclick="increaseCart(${item.id})"><i class="lnr lnr-chevron-up"></i></button>
										<button  class="reduced items-count"   onclick="reduceCart(${item.id})"><i class="lnr lnr-chevron-down"></i></button>
                                    </div>
                                </td>
                                <td class="cart-price" data-price="${item.price}">
                                    <h5>${item.price} VND</h5>
                                </td>
                                <td class="cart-total" data-total="${item.total}">
                                    <h5>${item.total} VND</h5>
                                </td>
                                <td>
                                	 <h5 style="font-size: 20px;cursor: pointer;"><i class="fa fa-trash-o" aria-hidden="true" onclick="deleteCart(${item.id})"></i></h5>
                                	 
                                </td>
                            </tr>
                            
                            </c:forEach>
                            <tr class="bottom_button">
                                <td>
                                    <a class="gray_btn" href="#">Update Cart</a>
                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <div class="cupon_text d-flex align-items-center">
                                        <input type="text" placeholder="Coupon Code">
                                        <a class="primary-btn" href="#">Apply</a>
                                        <a class="gray_btn" href="#">Close Coupon</a>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <h5>Subtotal</h5>
                                </td>
                                <td>
                                    <h5 class="sub-total">${subTotal } VND</h5>
                                </td>
                            </tr>
                            <tr class="shipping_area">
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <h5>Shipping</h5>
                                </td>
                                <td>
                                    <div class="shipping_box">
                                        <ul class="list">
                                            <li><a href="#">Flat Rate: $5.00</a></li>
                                            <li><a href="#">Free Shipping</a></li>
                                            <li><a href="#">Flat Rate: $10.00</a></li>
                                            <li class="active"><a href="#">Local Delivery: $2.00</a></li>
                                        </ul>
                                        <h6>Calculate Shipping <i class="fa fa-caret-down" aria-hidden="true"></i></h6>
                                        <select class="shipping_select">
                                            <option value="1">Bangladesh</option>
                                            <option value="2">India</option>
                                            <option value="4">Pakistan</option>
                                        </select>
                                        <select class="shipping_select">
                                            <option value="1">Select a State</option>
                                            <option value="2">Select a State</option>
                                            <option value="4">Select a State</option>
                                        </select>
                                        <input type="text" placeholder="Postcode/Zipcode">
                                        <a class="gray_btn" href="#">Update Details</a>
                                    </div>
                                </td>
                            </tr>
                            <tr class="out_button_area">
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <div class="checkout_btn_inner d-flex align-items-center">
                                        <a class="gray_btn" href="#">Continue Shopping</a>
                                        <a class="primary-btn" href="/karma/checkout">Proceed to checkout</a>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="zero-product"></div>
                </div>
            </div>
        </div>
    </section>
    <!--================End Cart Area =================-->
    <script src="<c:url value='/template/web/js/vendor/jquery-2.2.4.min.js' />"></script>
    <script src="<c:url value='/template/web/js/jsmain/cart.js' />"></script>
</body>
</html>