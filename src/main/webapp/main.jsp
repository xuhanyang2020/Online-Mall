<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>UIUC电脑商城</title>
<link rel="stylesheet" type="text/css" href="css/public.css">
<style type="text/css">
a:link {
	font-size: 18px;
	color: #DB8400;
	text-decoration: none;
	font-weight: bolder;
}
a:visited {
	font-size: 18px;
	color: #DB8400;
	text-decoration: none;
	font-weight: bolder;
}
a:hover {
	font-size: 18px;
	color: #DB8400;
	text-decoration: underline;
	font-weight: bolder;
}
</style>
</head>

<body>
<div class="header">UIUC电脑商城</div>
<hr width="100%" />
<div>
  <p class="text1"> <img src="images/4.jpg"   align="absmiddle" /> <a href="controller?action=list">商品列表</a> </p>
  <p class="text2"> 您可以从产品列表中浏览感兴趣的产品进行购买 </p>
</div>
<hr width="100%" />
<div>
  <p class="text1"> <img src="images/mycar1.jpg" align="absmiddle"  /> <a href="cart.jsp">购物车</a> </p>
  <p class="text2"> 您可以把感兴趣的商品暂时放在购物车中 </p>
</div>
<div class="footer">
  <hr  width="100%" />
  Copyright © 智捷课堂 2008-2018. All Rights Reserved </div>
</body>
</html>
