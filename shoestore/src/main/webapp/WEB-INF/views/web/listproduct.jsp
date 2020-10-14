<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Products</title>
</head>
<body>
		
	<!-- End Banner Area -->
	<div class="container" style="padding-top: 200px">
		<div class="row">
			<div class="col-xl-3 col-lg-4 col-md-5">
				
					
				<div class="sidebar-filter mt-50">
					<div class="top-filter-head">Product Filters</div>
					<div class="common-filter">
						<div class="head">Brands</div>
						<form action="#">
							<ul>
							<c:forEach items="${brands}" var="item">
								<li class="filter-list"><input class="pixel-radio" type="radio" id="apple" name="brand" data-brandid="${item.id}" onclick="getSneakersByBrand(${item.id})"><label for="apple">${item.name}<span>(${item.totalQuantity})</span></label></li>
							</c:forEach>
							</ul>
						</form>
					</div>
					<div class="common-filter">
						<div class="head">Color</div>
						<form action="#">
							<ul>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="black" name="color"><label for="black">Black<span>(29)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="balckleather" name="color"><label for="balckleather">Black
										Leather<span>(29)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="blackred" name="color"><label for="blackred">Black
										with red<span>(19)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="gold" name="color"><label for="gold">Gold<span>(19)</span></label></li>
								<li class="filter-list"><input class="pixel-radio" type="radio" id="spacegrey" name="color"><label for="spacegrey">Spacegrey<span>(19)</span></label></li>
							</ul>
						</form>
					</div>
					
				</div>
			</div>
			<div class="col-xl-9 col-lg-8 col-md-7">
				<!-- Start Filter Bar -->
				<div class="filter-bar d-flex flex-wrap align-items-center">
					<div class="sorting">
						<select>
							<option value="1">Default sorting</option>
							<option value="1">Default sorting</option>
							<option value="1">Default sorting</option>
						</select>
					</div>
					<div class="sorting mr-auto">
						<select>
							<option value="1">Show 12</option>
							<option value="1">Show 12</option>
							<option value="1">Show 12</option>
						</select>
					</div>
					<ul  id="pagination" class="pagination"></ul>
				</div>
				<!-- End Filter Bar -->
				<!-- Start Best Seller -->
				<section class="lattest-product-area pb-40 category-list">
					<div class="row">
					
						<c:forEach var="item" items="${listProducts }">
						
						<!-- single product -->
						
						<div class="col-lg-4 col-md-6">
							<div class="single-product">
								<img class="img-fluid" src="/usr/var/thumbnail/${item.image }" alt="" data-image="${item.image }">
								<div class="product-details">
									<h6>${item.name }</h6>
									<div class="listproduct-price" data-price="${item.price }" data-name="${item.name }">
										<h6><fmt:formatNumber value = "${item.price}" type = "currency" currencySymbol="$" currencyCode="VND" pattern="###,##0"/> VND</h6>
										
										<!-- <h6 class="l-through">$210.00</h6> -->
									</div>
									<div class="listproduct-color" data-color="${item.color }" data-quantity="${item.quantity}">
										<h6>Color : ${item.color}</h6>
										<!-- <h6 class="l-through">$210.00</h6> -->
									</div>
									<div class="listproduct-size" data-size="${item.size}" data-at="${item.productAttributeId }">
										<h6>Size : ${item.size} VN</h6>
										<!-- <h6 class="l-through">$210.00</h6> -->
									</div>
									<div class="prd-bottom">
										<div class="social-info btn-add-cart " data-id="${item.id}"  onclick="addCart(${item.id})">
											<div class="btn-cart">
												<span class="ti-bag"></span>
												<p class="hover-text " >add to bag</p>
											</div>
											
										</div>
										<a href="" class="social-info">
											<span class="lnr lnr-heart"></span>
											<p class="hover-text">Wishlist</p>
										</a>
										<a href="" class="social-info">
											<span class="lnr lnr-sync"></span>
											<p class="hover-text">compare</p>
										</a>
										<a href="/karma/detailproduct?id=${item.id }" class="social-info">
											<span class="lnr lnr-move"></span>
											<p class="hover-text">view more</p>
										</a>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>
				
						</div>
				</section>
				<!-- End Best Seller -->
				<!-- Start Filter Bar -->
				<div class="filter-bar d-flex flex-wrap align-items-center">
					<div class="sorting mr-auto">
						<select>
							<option value="1">Show 12</option>
							<option value="1">Show 12</option>
							<option value="1">Show 12</option>
						</select>
					</div>
					
						<ul  id="pagination1" class="pagination"></ul>
						
					
					
				</div>
				<!-- End Filter Bar -->
			</div>
		</div>
	</div>

	<!-- Start related-product Area -->
	<section class="related-product-area section_gap">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-6 text-center">
					<div class="section-title">
						<h1>Deals of the Week</h1>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore
							magna aliqua.</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-9">
					<div class="row">
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="/template/web/img/r1.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="/template/web/img/r2.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="/template/web/img/r3.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="/template/web/img/r5.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="/template/web/img/r6.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 mb-20">
							<div class="single-related-product d-flex">
								<a href="#"><img src="/template/web/img/r7.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="single-related-product d-flex">
								<a href="#"><img src="/template/web/img/r9.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="single-related-product d-flex">
								<a href="#"><img src="/template/web/img/r10.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6">
							<div class="single-related-product d-flex">
								<a href="#"><img src="/template/web/img/r11.jpg" alt=""></a>
								<div class="desc">
									<a href="#" class="title">Black lace Heels</a>
									<div class="price">
										<h6>$189.00</h6>
										<h6 class="l-through">$210.00</h6>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="ctg-right">
						<a href="#" target="_blank">
							<img class="img-fluid d-block mx-auto" src="/template/web/img/category/c5.jpg" alt="">
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div  class="save-contact" data-name="" data-size="" data-color="">
	</div>
	<div id="soldOutLoginModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
		  
			  <!-- Modal content-->
			  <div class="modal-content">
				<div class="modal-header">
				 <h4 class="modal-title" style="text-align: left;">This product was sold out.Please fill out your contact. We will
				  contact with you as soon as possible if we have this one</h4>
				  <button type="button" class="close" data-dismiss="modal">&times;</button>
				 
				</div>
				<div class="modal-body">
				   <input type="text" id="contact-email" class="form-control"  placeholder="Email" style="margin-bottom: 20px">
				   <div class="inform-validate"></div>
				   <input type="text" id="contact-phone" class="form-control"  placeholder="Phone Number" style="margin-bottom: 20px">
				   <div class="inform-validate"></div>
				   <input type="text" id="contact-name" class="form-control"  placeholder="Full Name">
				   <div class="inform-validate"></div>
				</div>
				<div class="modal-footer" style="text-align: center;">
				   <button type="button" class="btn btn-danger" data-dismiss="modal" id="btnContact">Send</button>
				   <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
				</div>
			  </div>
			</div>
	</div>
	<div id="soldOutNonLoginModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
		  
			  <!-- Modal content-->
			  <div class="modal-content">
				<div class="modal-header">
				 <h4 class="modal-title" style="text-align: left;">This product was sold out.You can 
				 fill out your contact to get new information about this one.Login to filling out</h4>
				  <button type="button" class="close" data-dismiss="modal">&times;</button>
				 
				</div>
		
				<div class="modal-footer" style="text-align: center;">
				  <a href="/login"><button class="btn btn-danger"  >Login</button></a>
				  <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
				</div>
			  </div>
			</div>
	</div>
	<!-- End related-product Area -->
	<script src="<c:url value='/template/web/js/vendor/jquery-2.2.4.min.js' />"></script>
	<script src="<c:url value='/template/web/js/jsmain/listproduct.js' />"></script>
	<script >
	var totalPages = ${totalPage};
	var currentPage = ${page};
    $(function () {
    	
        window.pagObj = $('#pagination').twbsPagination({
        	totalPages: totalPages,
            visiblePages: 3,
            startPage: currentPage,
            onPageClick: function (event, page) {
            	if (currentPage != page) {
            		window.location.href="/karma/listproduct?page="+page+"&limit=9";
				}
            }
        });
    });
</script>
<script >
	var totalPages = ${totalPage};
	var currentPage = ${page};
    $(function () {
    	
        window.pagObj = $('#pagination1').twbsPagination({
        	totalPages: totalPages,
            visiblePages: 3,
            startPage: currentPage,
            onPageClick: function (event, page) {
            	if (currentPage != page) {
            		window.location.href="/karma/listproduct?page="+page+"&limit=9";
				}
            }
        });
    });
</script>
</body>
</html>