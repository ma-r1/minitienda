<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!--JSP para renderizar el ticket después de una compra exitosa-->
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Compra confirmada</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesheet.css">
  </head>

  <body bgcolor="#FDF5E6">
    <h1>Compra realizada correctamente</h1>

    <p>Pedido registrado correctamente</p>

    <!--Recibe y utiliza el objeto pedido como atributo de la solicitud y lo muestra en una tabla resumen con sus datos correspondientes-->
    <table border="1">
      <tr>
        <th>Número de pedido</th>
        <td>${pedido.id}</td>
      </tr>
      <tr>
        <th>Usuario</th>
        <td>${pedido.correoUsuario}</td>
      </tr>
      <tr>
        <th>Importe final</th>
        <td>${pedido.importe} €</td>
      </tr>
    </table>

    <br>
    <!--Enlace para regresar al inicio y comenzar una nueva compra-->
    <a href="index.html">Volver a la página principal</a>
  </body>
</html>