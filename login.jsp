<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title> Login </title>
  </head>
  <body bgcolor="#FDF5E6">
    <h1>Identificación del usuario</h1>
    <p>Para finalizar su pedido de <b>${sessionScope.total}€</b>, identifíquese o regístrese.</p>

    <h2>Ya soy cliente</h2>
    <form action="ServletFrontController" method="get">
      <input type="hidden" name="accion" value="login">
      <label for="email">Correo electrónico:</label> <input name="email" type="email" required>
      <label for="password">Contraseña:</label> <input name="password" type="password" required>
      <input type="submit" value="Acceder y pagar">
    </form>

    <h2>Nuevo usuario</h2>
    <form action="ServletFrontController" method="get">
      <input type="hidden" name="accion" value="registrar">
      <label for="email">Correo electrónico:</label> <input name="email" type="email" required>
      <label for="password">Contraseña:</label> <input name="password" type="password" required>
      <label for="tipo_tarjeta">Tipo de tarjeta:</label>
        <select name="tipo_tarjeta">
          <option value="Visa">Visa</option>
          <option value="MasterCard">MasterCard</option>
        </select>
      <label for="num_tarjeta">Número de tarjeta:</label> <input name="num_tarjeta" type="text" required>
      <input type="submit" value="Registrar y pagar">
    </form>
    
    <a href="ServletFrontController?accion=mostrarcarrito">Volver al carrito</a>
    
  </body>
</html>
