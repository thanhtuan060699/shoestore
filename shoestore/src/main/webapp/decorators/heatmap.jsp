<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="shoestore.util.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  	<title><dec:title default="Admin Page"/></title>
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href='<c:url value='/template/web/fav.png'  />'>
	<!-- Author Meta -->
	<meta name="author" content="CodePixar">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	
	<!-- Site Title -->
    <title>Karma Shop</title>
	
	<link rel="stylesheet" href='<c:url value='/template/web/css/linearicons.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/font-awesome.min.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/themify-icons.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/bootstrap.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/main.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/owl.carousel.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/nouislider.min.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/ion.rangeSlider.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/ion.rangeSlider.skinFlat.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/magnific-popup.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/scss/main.scss'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/nice-select.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/scss/theme/_common1.scss'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/scss/theme/_product.scss'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/scss/theme/_predefine.scss'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/scss/theme/_reset.scss'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/custom.css'/>'>
	<link rel="stylesheet" href='<c:url value='/template/web/css/sweetalert2.min.css'/>'>
</head>
<body class="animsition">
	<div id="heatmapContainerWrapper">
      <div id="heatmapContainer">
	<!-- Header-->
	
	<%@ include file="/common/heatmap/header.jsp" %>
	

	<!-- End Header -->
		
		<!-- Begin body -->
		<dec:body/>
		<!-- End body -->
		
		
		<!-- Begin footer -->
    	<%@ include file="/common/heatmap/footer.jsp" %>
    	<!-- End footer -->
    	
	
		</div>
	</div>
	
	<script src="<c:url value='/template/web/js/vendor/jquery-2.2.4.min.js' />"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	 crossorigin="anonymous"></script>
	<script src="<c:url value='/template/web/js/vendor/bootstrap.min.js' />"></script>
	<script src="<c:url value='/template/web/js/jquery.twbsPagination.js' />"></script>
	<script src="<c:url value='/template/web/js/notification.js' />"></script>
	<script src="<c:url value='/template/web/js/sweetalert2.min.js' />"></script>
	<script src="<c:url value='/template/web/js/jquery.ajaxchimp.min.js' />"></script>
	<script src="<c:url value='/template/web/js/jquery.nice-select.min.js' />"></script>
	<script src="<c:url value='/template/web/js/jquery.sticky.js' />"></script>
	<script src="<c:url value='/template/web/js/nouislider.min.js' />"></script>
	<script src="<c:url value='/template/web/js/jquery.bootstrap-growl.min.js' />"></script>
	<script src="<c:url value='/template/web/js/jquery.magnific-popup.min.js' />"></script>
	<script src="<c:url value='/template/web/js/owl.carousel.min.js' />"></script>
	<!--gmaps Js-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="<c:url value='/template/web/js/gmaps.min.js' />"></script>
	<script src="<c:url value='/template/web/js/main.js' />"></script>
	<script src="<c:url value='/template/web/js/jsmain/heatmap.js' />"></script>
	<c:if test="${not empty checkoutlogin }">
		<script type="text/javascript">
		document.addEventListener("DOMContentLoaded",function(){
			Swal.fire(
  				  'You need to log in!',
  				  'You need to log in before checking out',
  				  'success'
  				)
		},false)
		
		</script>
		</c:if>
</body>
</html>