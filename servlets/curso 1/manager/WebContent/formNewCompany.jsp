<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
 
 <c:url value="/newCompany" var="linkServletNewCompany" />
 
<html>
<head>
	<meta charset="UTF-8">
	<title>Nova Empresa</title>
</head>
<body>

	<form action="${ linkServletNewCompany }" method="post" >
		Nome: <input type="text" name="name" />
		Data de Abertura: <input type="date" name="date" />
		<input type="submit" />
	</form>
</body>
