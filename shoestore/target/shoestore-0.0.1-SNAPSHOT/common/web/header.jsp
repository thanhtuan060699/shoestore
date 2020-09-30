<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="shoestore.dto.MyUser"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="shoestore.util.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Start Header Area -->
	<header class="header_area sticky-header">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light main_box">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<a class="navbar-brand logo_h" href="index.html"><img src="/template/web/img/logo.png" alt=""></a>
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
					 aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav ml-auto">
							<li class="nav-item active"><a class="nav-link" href="/home">Home</a></li>
							<li class="nav-item active"><a class="nav-link" href="/karma/listproduct?page=1&limit=9">Shop</a></li>
							
							<li class="nav-item submenu dropdown">
								<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
								 aria-expanded="false">Blog</a>
								<ul class="dropdown-menu">
									<li class="nav-item"><a class="nav-link" href="blog.html">Blog</a></li>
									<li class="nav-item"><a class="nav-link" href="single-blog.html">Blog Details</a></li>
								</ul>
							</li>
							<c:if test="${not empty pageContext.request.userPrincipal.name}">
							<li class="nav-item submenu dropdown">
								<a href="${contextPath}/account/profile" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
								 aria-expanded="false">${pageContext.request.userPrincipal.name}</a>
								<ul class="dropdown-menu">
									<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
									<li class="nav-item"><a class="nav-link" href="tracking.html">Tracking</a></li>
									<li class="nav-item"><a class="nav-link" href="elements.html">Elements</a></li>
								</ul>
							</li>
							</c:if>
							
							<c:if test="${empty pageContext.request.userPrincipal.name}">
							<li class="nav-item"><a class="nav-link" href="/login">Login
							<i class="fa fa-sign-in" aria-hidden="true"></i></a>
							</li>
							</c:if>
							
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="nav-item">
								<a href="/karma/cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i>
								<c:if test="${not empty amounts }">
									<span class="numberCart">(${amounts })</span></a>
								</c:if>
								<c:if test="${empty amounts }">
									<span class="numberCart">(0)</span></a>
								</c:if>
								
								
							</li>
							<li class="nav-item">
								<button class="search"><span class="lnr lnr-magnifier" id="search"></span></button>
							</li>
							
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<div class="search_input" id="search_input_box">
			<div class="container">
				<form class="d-flex justify-content-between">
					<input type="text" class="form-control" id="search_input" placeholder="Search Here">
					<button type="submit" class="btn"></button>
					<span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
				</form>
			</div>
		</div>
	</header>
	<!-- End Header Area -->