<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!--Archivo para mostrar el importe total calculado final al usuario y darle paso a la "pasarela de pago"-->
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Caja</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesheet.css">
  </head>

  <!--Representamos el importe total, almacenado en la variable de sesión "total"-->
  <body bgcolor="#FDF5E6">
    <h1>Caja</h1>
    <table>
      <tr>
        <th>Total a pagar:</th>
        <td>${sessionScope.total}€</td>
      </tr>
    </table>

    <!--Paso a la "pasarela de pago" donde el usuario se puede identificar para completar su pedido-->
    <a href="${pageContext.request.contextPath}/ServletFrontController?accion=pagar">Identificarse</a>
  </body>
</html>