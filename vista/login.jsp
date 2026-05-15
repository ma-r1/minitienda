<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!--JSP de identificación al ir a pagar: permite acceder con un usuario existente o registrar uno nuevo-->
<!--Recibe opcionalmente el atributo "error" como request attribute si el intento previo falló-->
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title> Login </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesheet.css">
  </head>
  <body bgcolor="#FDF5E6">
    <h1>Identificación del usuario</h1>
    <!--Si HelperLogin o HelperRegistrar establecieron un mensaje de error lo mostramos en rojo-->
    <c:if test="${not empty error}">
    <p style="color:red;"><b>${error}</b></p>
    </c:if>
    <!--Mostramos el importe total recuperado del atributo de sesión "total"-->
    <p>Para finalizar su pedido de <b>${sessionScope.total}€</b>, identifíquese o regístrese.</p>

    <h2>Ya soy cliente</h2>
    <!--Formulario de identificación para usuarios ya registrados, envía accion=login al FrontController-->
    <form action="ServletFrontController" method="post">
      <input type="hidden" name="accion" value="login">
      <label for="email">Correo electrónico:</label> <input name="email" type="email" required>
      <label for="password">Contraseña:</label> <input name="password" type="password" required>
      <input type="submit" value="Acceder y pagar">
    </form>

    <h2>Nuevo usuario</h2>
    <!--Formulario de registro para nuevos usuarios, envía accion=registrar al FrontController con datos de tarjeta-->
    <form action="ServletFrontController" method="post">
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
    
    <!--Enlace de vuelta al carrito por si el usuario quiere modificar la compra antes de pagar-->
    <a href="ServletFrontController?accion=mostrarcarrito">Volver al carrito</a>
    
  </body>
</html>
