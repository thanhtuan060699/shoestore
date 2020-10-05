<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
</head>
<body>
	 <!-- Start Banner Area -->
 
    <!-- End Banner Area -->

    <!--================Cart Area =================-->
    <section class="cart_area" style="padding-top: 250px">
    
   	
        <div class="container">
        <div class="alert-quantity">
        </div>
            <div class="cart_inner">
            	<c:if test="${empty zeroProduct }">
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
                                            <img src="/usr/var/thumbnail/${item.thumbnailImage }" alt="" style="width: 152px;height: 100px">
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
                                    <h5><fmt:formatNumber value = "${item.price}" type = "currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/>VND</h5>
                                </td>
                                <td class="cart-total" data-total="${item.total}">
                                    <h5><fmt:formatNumber value = "${item.total}" type = "currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/> VND</h5>
                                </td>
                                <td>
                                	 <h5 style="font-size: 20px;cursor: pointer;" class="delete-cart"><i class="fa fa-trash-o" aria-hidden="true" onclick="deleteCart(${item.id})"></i></h5>
                                	 
                                </td>
                            </tr>
                            
                            </c:forEach>
                            <tr class="bottom_button" >
                                <td>
                                    <a class="gray_btn" href="#">Update Cart</a>
                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <div class="cupon_text d-flex align-items-center couponstyle">
                                        <input type="text" placeholder="Coupon Code">
                                        <a class="primary-btn" href="#">Apply</a>
                                       
                                    </div>
                                </td>
                            </tr>
                            <tr>
                              
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                             	  <td>

                                </td>
                                <td>
                                    <h5>Subtotal</h5>
                                </td>
                                <td>
                                    <h5 class="sub-total"><fmt:formatNumber value = "${subTotal}" type = "currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/> VND</h5>
                                </td>
                            </tr>
                            
                            <tr class="shipping_area">
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <h5>Shipping</h5>
                                </td>
                                <td>
                                    <div class="shipping_box shipping_cart">
                                        <ul class="list">
                                            <li><a href="#">Flat Rate: $5.00</a></li>
                                            <li><a href="#">Free Shipping</a></li>
                                            <li><a href="#">Flat Rate: $10.00</a></li>
                                            <li class="active"><a href="#">Local Delivery: $2.00</a></li>
                                        </ul>
                                        
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

                                </td>
                                 <td>

                                </td>
                                 <td>

                                </td>
                                <td>
                                    <div class="checkout_btn_inner d-flex align-items-center">
                                        <a class="gray_btn" href="/karma/listproduct?page=1&limit=9">Continue Shopping</a>
                                        <a class="primary-btn" href="/karma/checkout">Proceed to checkout</a>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                        
                    </table>
                    
                </div>
                </c:if>
                <c:if test="${not empty zeroProduct }">
                	<div class="zero-product-cart" style="text-align: center;font-weight: 200;font-size: 30px">Not Have Any Products In Cart</div>
                	<div class="continue-cart" style="text-align: center;margin-top: 10px"> <a class="primary-btn" href="/karma/listproduct?page=1&limit=9">Continue Shopping</a></div>
                </c:if>
              	    <div class="zero-product" style="text-align: center;font-weight: 200;font-size: 30px"></div>
              	    <div class="continue-cart" style="text-align: center;margin-top: 10px"> <a class="primary-btn" href="/karma/listproduct?page=1&limit=9"></a></div>
            </div>
        </div>
    </section>
    <!--================End Cart Area =================-->
    <script src="<c:url value='/template/web/js/vendor/jquery-2.2.4.min.js' />"></script>
    <script src="<c:url value='/template/web/js/jsmain/cart.js' />"></script>
</body>
</html>