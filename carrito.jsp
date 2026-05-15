<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title> Carrito </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesheet.css">
  </head>
  <body>
    <h1>Carrito de compras</h1>
    <table border="1" class="tabla-carrito">
      <thead>
        <tr>
          <th>Título</th>
          <th>Autor</th>
          <th>País</th>
          <th>Precio</th>
          <th>Cantidad</th>
          <th>Importe</th>
          <th>Eliminar</th>
        </tr>
      </thead>
      <tbody>
        <!-- Establecemos una variable vectorCD que proviene del atributo de sesión carrito -->
        <!-- Se comprueba si el carrito está vacío y en ese se renderiza un aviso de ello -->
        <!-- En caso de que esté vacío cargamos los valores iterando sobre cada CD en el carrito -->

        <c:set var="vectorCD" value="${sessionScope.carrito}"/>
        <c:choose>
          <c:when test="${empty vectorCD}">
            <tr><td colspan=4>El carrito está vacío</td></tr>
          </c:when>
          <c:otherwise>
            <c:forEach items="${vectorCD}" var="CDactual" varStatus="loop">
              <tr>
                <td>${CDactual.titulo}</td>
                <td>${CDactual.autor}</td>
                <td>${CDactual.pais}</td>
                <td>${CDactual.precio}</td>
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
    <!-- Renderizamos en una tabla el importe total calculado previamente y almacenado en el atributo de sesión "total" -->
    <table>
      <tr>
        <td><b>Importe total: </b></td>
        <td><b>${(sessionScope.total == null) ? 0 : sessionScope.total} €</b></td>
      </tr>
    </table>
    <br>
    <a href="./index.html">Sigo comprando</a>
    <br>
    <br>
    <!-- Hacemos una comprobación de que el carrito no esté vacío para mostrar el botón -->
    <!-- Igualmente si se enviase la solicitud de una compra menor o igual a 0$ se redirige a index en lugar de procesarla -->
    <c:if test="${sessionScope.total > 0}">
      <a href="${pageContext.request.contextPath}/ServletFrontController?accion=mostrarcaja">A pagar</a>
    </c:if>
  </body>
</html>
