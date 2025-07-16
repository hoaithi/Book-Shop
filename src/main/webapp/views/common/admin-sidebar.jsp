<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 6/28/2025
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <!-- Đặt trong <head> -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
  <div class="d-flex flex-column flex-shrink-0 p-3 bg-light" style="width: 220px; height: 100vh;">
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-link active">🏠 Dashboard</a>
      </li>
      <li>
        <a href="${pageContext.request.contextPath}/admin/book" class="nav-link">📚 Quản lý sách</a>
      </li>
      <li>
        <a href="${pageContext.request.contextPath}/admin/category" class="nav-link">📂 Thể loại</a>
      </li>
      <li>
        <a href="${pageContext.request.contextPath}/admin/user" class="nav-link">👤 Người dùng</a>
      </li>
      <li>
        <a href="${pageContext.request.contextPath}/admin/order" class="nav-link">🧾 Đơn hàng</a>
      </li>
    </ul>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
