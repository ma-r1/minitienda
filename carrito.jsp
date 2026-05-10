<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title> Carrito </title>
  </head>
  <body bgcolor="#FDF5E6">
    <h1>Carrito de compras</h1>
    <table border="1">
      <thead>
        <tr>
          <th>Título</th>
          <th>Cantidad</th>
          <th>Importe</th>
          <th>Eliminar</th>
        </tr>
      </thead>
      <tbody>
        <c:set var="vectorCD" value="${sessionScope.carrito}"/>
        <c:choose>
          <c:when test="${empty vectorCD}">
            <tr><td colspan=4>El carrito está vacío</td></tr>
          </c:when>
          <c:otherwise>
            <c:forEach items="${vectorCD}" var="CDactual" varStatus="loop">
              <tr>
                <td>${CDactual.nombre}</td>
                <td>${CDactual.cantidad}</td>
                <td>${CDactual.cantidad*CDactual.precio}</td>
                <td><a href="${pageContext.request.contextPath}/ServletFrontController?accion=eliminarcd&indice=${loop.index}">Eliminar</a></td>
              </tr>
            </c:forEach>
          </c:otherwise>
        </c:choose>
      </tbody>
    </table>

    <br>
    <table>
      <tr>
        <td><b>Importe total: </b></td>
        <td><b>${(sesionScope.total == 0) ? sessionScope.total : 0}€</b></td>
      </tr>
    </table>
    <br>
    <a href="./index.html">Sigo comprando</a>
    <a href="${pageContext.request.contextPath}/ServletFrontController?accion=mostrarcaja">A pagar</a>
  </body>
</html>
