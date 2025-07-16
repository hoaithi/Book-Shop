<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Book Management</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">BookStore Admin</a>
    <div class="d-flex">
      <a class="btn btn-outline-light btn-sm" href="logout.jsp">Logout</a>
    </div>
  </div>
</nav>

<div class="container mt-5">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Book Management</h2>
      <a href="${pageContext.request.contextPath}/book?action=add" class="btn btn-success">+ Add New Book</a>
  </div>

  <!-- Table of books -->
  <table class="table table-bordered table-striped align-middle">
    <thead class="table-dark">
    <tr>
      <th>ID</th>
      <th>Title</th>
      <th>Author</th>
      <th>Category</th>
      <th>Price ($)</th>
      <th>Description</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <!-- Example data row -->
    <c:forEach var="book" items="${books}">
    <tr>
      <td>${book.id}</td>
      <td>${book.title}</td>
      <td>${book.authorName}</td>
      <td>${book.categoryName}</td>
      <td>${book.price}</td>
      <td>${book.description}</td>
      <td>
        <a href="${pageContext.request.contextPath}/book?action=edit&id=${book.id}" class="btn btn-sm btn-warning">Edit</a>
        <a href="${pageContext.request.contextPath}/book?action=delete&id=${book.id}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure to delete this book?')">Delete</a>
      </td>
    </tr>
    </c:forEach>
    <!-- More rows can go here -->
    </tbody>
  </table>
</div>

</body>
</html>
