<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Dashboard</title>
  <!-- Bootstrap CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column" style="height: 100vh;">

<jsp:include page="/views/common/admin-header.jsp" />

<div class="d-flex" style="flex: 1;">
  <jsp:include page="/views/common/admin-sidebar.jsp" />

  <div class="p-4" style="width: 100%;">
    <h2>📊 Thống kê tổng quan</h2>
    <div class="row mt-4">
      <div class="col-md-4">
        <div class="card text-white bg-primary mb-3">
          <div class="card-body">
            <h5 class="card-title">Tổng số sách</h5>
            <p class="card-text">120 sách</p>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card text-white bg-success mb-3">
          <div class="card-body">
            <h5 class="card-title">Người dùng</h5>
            <p class="card-text">87 người</p>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="card text-white bg-warning mb-3">
          <div class="card-body">
            <h5 class="card-title">Đơn hàng</h5>
            <p class="card-text">45 đơn</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/views/common/admin-footer.jsp" />

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
