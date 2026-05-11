<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Compra confirmada</title>
  </head>

  <body bgcolor="#FDF5E6">
    <h1>Compra realizada correctamente</h1>

    <p>Pedido registrado correctamente</p>

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

    <a href="index.html">Volver a la página principal</a>
  </body>
</html>