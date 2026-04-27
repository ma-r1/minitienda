<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<%
  Float total = (Float) session.getAttribute("total");
  if(total == null){
    total = 0.0f;
  }
%>

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
          <td><%= String.format("%.2f", total)%>€</td>
        </tr>
      </table>

      <a href="ServletCaja?accion=finalizar">Pagar y volver a la página principal</a>
    </body>
</html>