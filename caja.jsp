<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

  <!DOCTYPE html>
  <html>
    <head>
      <meta charset="utf-8">
      <title>Caja</title>
    </head>

    <body bgcolor="#FDF5E6">
      <h1>Caja</h1>
      
      <table>
        <table>
        <tr>
          <th>Total a pagar:</th>
          <td>${sessionScope.total}€</td>
        </tr>
      </table>

      <a href="${pageContext.request.contextPath}/ServletFrontController?accion=pagar">Pagar y volver a la página principal</a>
    </body>
</html>