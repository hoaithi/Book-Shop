<%@ page import="com.hoaithidev.bookstore.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
  // Kiểm tra xem user có phải là admin không
  User user = (User) session.getAttribute("user");
    if (user.getRole() == null || !user.getRole().equals("admin")) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
  }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Trang quản trị</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">📚 Admin BookStore</a>
    <div class="d-flex">
      <span class="navbar-text text-white me-3">Xin chào, Admin</span>
      <a href="${pageContext.request.contextPath}/logout.jsp" class="btn btn-outline-light btn-sm">Đăng xuất</a>
    </div>
  </div>
</nav>

<div class="container mt-5">
  <h2 class="mb-4">Bảng điều khiển quản trị</h2>

  <div class="row g-4">
    <div class="row g-4">
      <div class="col-md-4">
        <div class="card shadow-sm">
          <div class="card-body text-center">
            <h5 class="card-title">📘 Manage Books</h5>
            <a href="${pageContext.request.contextPath}/admin?action=book" class="btn btn-primary">Go</a>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card shadow-sm">
          <div class="card-body text-center">
            <h5 class="card-title">🖋️ Manage Authors</h5>
            <a href="${pageContext.request.contextPath}/admin?action=author" class="btn btn-primary">Go</a>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card shadow-sm">
          <div class="card-body text-center">
            <h5 class="card-title">🏷️ Manage Categories</h5>
            <a href="${pageContext.request.contextPath}/admin?action=category" class="btn btn-primary">Go</a>
          </div>
        </div>
      </div>
    </div>


    <div class="col-md-4">
      <div class="card shadow-sm">
        <div class="card-body text-center">
          <h5 class="card-title">👤 User Management</h5>
          <a href="${pageContext.request.contextPath}/admin?action=user" class="btn btn-primary">Go</a>
        </div>
      </div>
    </div>

    <div class="col-md-4">
      <div class="card shadow-sm">
        <div class="card-body text-center">
          <h5 class="card-title">📦 Order Management</h5>
          <a href="${pageContext.request.contextPath}/admin?action=order" class="btn btn-primary">Go</a>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>
