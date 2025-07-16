<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Manage Authors</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Author Management</h2>
    <a href="add-author.jsp" class="btn btn-success">+ Add New Author</a>
  </div>

  <table class="table table-bordered table-striped align-middle">
    <thead class="table-dark">
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="author" items="${authors}">
      <tr>
        <td>${author.id}</td>
        <td>${author.name}</td>
        <td>
          <a href="edit-author.jsp?id=${author.id}" class="btn btn-sm btn-warning">Edit</a>
          <a href="delete-author?id=${author.id}" class="btn btn-sm btn-danger"
             onclick="return confirm('Are you sure you want to delete this author?')">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>
