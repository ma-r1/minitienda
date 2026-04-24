<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="minitienda.CD"%>
<%@page session="true"%>

<%
  ArrayList<CD> cds = (ArrayList<CD>)session.getAttribute("cds");
  double total = 0.0;
%>

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
        <%
          if (cds == null || cds.isEmpty()){ 
        %>
          <tr><td colspan=4>El carrito está vacío</td></tr>
        <%
          } else {
            for (int i = 0; i < cds.size(); i++){
              CD cd = cds.get(i);
              double valorfila=cd.getCantidad()*cd.getPrecio();
              total+=valorfila;
        %>
          <tr>
            <td><%=cd.getNombre()%></td>
            <td><%=cd.getCantidad()%></td>
            <td><%=String.format("%.2f", valorfila)%> €</td>
            <td><a href="/minitienda/ServletCarrito?accion=eliminar&indice=<%= i %>">Eliminar</a></td>
          </tr>
        <% }} %>
      </tbody>
    </table>

    <br>
    <table>
      <tr>
        <td><b>Importe total: </b></td>
        <td><b><%= String.format("%.2f", total) %> €</b></td>
      </tr>
    </table>
    <br>
    <a href="./index.html">Sigo comprando</a>
    <a href="./caja.html">A pagar</a>
  </body>
</html>
