<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bookstore - Trang chủ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .book-card img {
            height: 220px;
            object-fit: cover;
        }
        .book-card:hover {
            transform: scale(1.02);
            box-shadow: 0 0.5rem 1rem rgba(0,0,0,0.1);
        }
        .card-link {
            color: inherit;
            text-decoration: none;
        }
    </style>
</head>
<body class="bg-light">

<jsp:include page="/views/common/header.jsp" />

<div class="container mt-5">
    <h2 class="text-center mb-4">📚 Danh sách sách mới</h2>
    <!-- 🔍 FORM TÌM KIẾM -->
    <form class="input-group mb-4" method="get" action="${pageContext.request.contextPath}/">
        <input type="text" name="keyword" value="${param.keyword}" class="form-control" placeholder="Tìm kiếm theo tên sách hoặc tác giả...">
        <button class="btn btn-outline-secondary" type="submit">Tìm kiếm</button>
    </form>

    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
        <c:forEach var="book" items="${books}">
            <div class="col">
                <!-- Card clickable wrapper -->
                <a href="${pageContext.request.contextPath}/book/detail?id=${book.id}" class="card-link">
                    <div class="card book-card h-100">
                        <img src="${book.image}" class="card-img-top" alt="${book.title}">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${book.title}</h5>
                            <p class="card-text text-muted mb-1">${book.authorName}</p>
                            <p class="card-text text-danger fw-bold">${book.price} đ</p>
                            <form action="${pageContext.request.contextPath}/cart/add" method="post" class="mt-auto">
                                <input type="hidden" name="bookId" value="${book.id}" />
                                <button type="submit" class="btn btn-sm btn-success w-100">🛒 Thêm vào giỏ hàng</button>
                            </form>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
