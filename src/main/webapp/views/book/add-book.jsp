<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Book</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4">Add New Book</h2>

    <!-- Upload ảnh trước -->
    <div class="mb-3">
        <label for="image" class="form-label">Book Image</label>
        <input type="file" class="form-control" id="image" accept="image/*">
        <button type="button" class="btn btn-primary mt-2" onclick="uploadImage()">Upload Image</button>
        <div id="uploadStatus" class="mt-2 text-info"></div>
        <img id="preview" class="img-thumbnail mt-3 d-none" style="max-height: 200px;" />
    </div>

    <!-- Form điền thông tin -->
    <form action="${pageContext.request.contextPath}/book?action=add" method="post" onsubmit="return handleSubmit(event)">
        <input type="hidden" name="imageUrl" id="imageUrl">

        <!-- Title -->
        <div class="mb-3">
            <label for="title" class="form-label">Book Title</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>

        <!-- Author -->
        <div class="mb-3">
            <label for="author" class="form-label">Author</label>
            <select class="form-select" id="author" name="authorId" required>
                <option selected disabled value="">-- Select Author --</option>
                <c:forEach var="author" items="${authors}">
                    <option value="${author.id}">${author.name}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Category -->
        <div class="mb-3">
            <label for="category" class="form-label">Category</label>
            <select class="form-select" id="category" name="categoryId" required>
                <option selected disabled value="">-- Select Category --</option>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Price -->
        <div class="mb-3">
            <label for="price" class="form-label">Price (USD)</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" required>
        </div>

        <!-- Description -->
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
        </div>

        <!-- Publisher -->
        <div class="mb-3">
            <label for="publisher" class="form-label">Publisher</label>
            <input type="text" class="form-control" id="publisher" name="publisher">
        </div>

        <!-- Publish Year -->
        <div class="mb-3">
            <label for="publishYear" class="form-label">Publish Year</label>
            <input type="number" class="form-control" id="publishYear" name="publishYear" min="1000" max="2099">
        </div>

        <!-- Stock Quantity -->
        <div class="mb-3">
            <label for="stockQuantity" class="form-label">Stock Quantity</label>
            <input type="number" class="form-control" id="stockQuantity" name="stockQuantity" min="0">
        </div>

        <!-- Language -->
        <div class="mb-3">
            <label for="language" class="form-label">Language</label>
            <input type="text" class="form-control" id="language" name="language" placeholder="e.g. English">
        </div>

        <!-- Format -->
        <div class="mb-3">
            <label for="format" class="form-label">Format</label>
            <input type="text" class="form-control" id="format" name="format" placeholder="e.g. Paperback, eBook">
        </div>

        <input type="hidden" name="action" value="add">
        <button type="submit" class="btn btn-success" id="submitBtn" disabled>Add Book</button>
        <a href="../admin/manage-books.jsp" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<!-- Script -->
<script>
    const fileInput = document.getElementById("image");
    const preview = document.getElementById("preview");
    const uploadStatus = document.getElementById("uploadStatus");
    const submitBtn = document.getElementById("submitBtn");
    const imageUrlInput = document.getElementById("imageUrl");

    // Khi chọn ảnh từ hệ thống → hiện preview
    fileInput.addEventListener("change", () => {
        const file = fileInput.files[0];
        if (!file) {
            preview.classList.add("d-none");
            uploadStatus.textContent = "";
            submitBtn.disabled = true;
            imageUrlInput.value = "";
            return;
        }

        const reader = new FileReader();
        reader.onload = (e) => {
            preview.src = e.target.result;
            preview.classList.remove("d-none");
        };
        reader.readAsDataURL(file);

        uploadStatus.textContent = "Ready to upload.";
        submitBtn.disabled = true;
        imageUrlInput.value = "";
    });

    async function uploadImage() {
        const file = fileInput.files[0];
        if (!file) {
            uploadStatus.textContent = "Please choose a file!";
            return;
        }

        uploadStatus.textContent = "Uploading...";

        const formData = new FormData();
        formData.append("file", file);
        formData.append("upload_preset", "image_upload_preset"); // 👉 thay bằng preset thật
        formData.append("cloud_name", "drkkofk0h"); // 👉 thay bằng tên cloud của bạn

        try {
            const res = await fetch("https://api.cloudinary.com/v1_1/drkkofk0h/image/upload", {
                method: "POST",
                body: formData
            });

            const data = await res.json();
            if (data.url) {
                imageUrlInput.value = data.url;
                uploadStatus.textContent = "Image uploaded successfully!";
                submitBtn.disabled = false;
            } else {
                throw new Error("Upload failed.");
            }
        } catch (err) {
            uploadStatus.textContent = "Upload failed.";
            console.error(err);
        }
    }

    function handleSubmit(event) {
        if (!imageUrlInput.value) {
            alert("Please upload an image first.");
            event.preventDefault();
            return false;
        }
        return true;
    }
</script>

</body>
</html>
