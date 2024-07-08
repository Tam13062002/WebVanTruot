<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="Model.Product" %>
<%@ page import="Model.Category" %>
<div class="content-wrapper" style="padding: 20px; margin-left: 500px">

    <h2>Thêm sản phẩm</h2>

    <form action="addProduct" method="post" style="margin-top: 50px"> 
        <input type="hidden" name="action" value="add">
        <input type="hidden" id="fileName" name="fileName" value="">
        <div class="form-row" >
            <div class="form-group col-md-6" style="margin-top: 20px" >
                <label for="name">Tên sản phẩm:  </label>
                <input type="text" class="form-control" id="name" name="name" style="margin-top: 10px" required> 
            </div>

            <div class="form-group col-md-6" style="margin-top: 20px">
                <label for="image">Ảnh:</label>
                <div class="col-sm-10">
                    <label for="Photo-Uploader" style="cursor: pointer" title="Click để thay đổi ảnh">
                        <img id="photo" src="./assets/images/uploadPhoto" class="img-thumbnail" style="width: 125px; height: 140px;" alt="">
                    </label>
                    <input accept="image/*" type="file" id="Photo-Uploader" name="uploadPhoto" onchange="previewImage(event)">
                    <label id="fileNameLabel"></label> <!-- Thêm label để hiển thị tên file -->
                </div>
            </div>

            <div class="form-group col-md-6" style="margin-top: 20px">
                <label for="price">Giá:</label>
                <input type="text" class="form-control" id="price" name="price" style="margin-top: 10px" required oninput="validateNumericInput(this, 'priceError')">
                <span id="priceError" style="color: red;"></span>
            </div>

            <div class="form-group col-md-6" style="margin-top: 20px">
                <label for="quantity">Số lượng:</label>
                <input type="text" class="form-control" id="quantity" name="quantity" style="margin-top: 10px" required oninput="validateNumericInput(this, 'quantityError')">
                <span id="quantityError" style="color: red;"></span>
            </div>

            <div class="form-group col-md-6" style="margin-top: 20px">
                <label for="id_category">Loại hàng:</label>
                <select class="select" name="category" style="margin-left: 20px">
                    <c:forEach items="${listCategory}" var="category">
                        <option value="${category.getId()}">${category.getName()}</option>
                    </c:forEach>
                </select>
            </div>


        </div>

        <div class="form-group" style="margin-top: 20px">
            <button type="submit" class="btn btn-primary">Thêm</button>
            <a href="product" class="btn btn-secondary ml-2" style="margin-left: 350px">Back</a>
        </div>

    </form>
</div>



<script>
    function previewImage(event) {
        var input = event.target;
        var img = document.getElementById('photo');
        var fileNameInput = document.getElementById('fileName');

        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                img.src = e.target.result;
                // Lưu trữ tên file vào input ẩn
                fileNameInput.value = input.files[0].name;
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
    
    
</script>
<script>
    function validateNumericInput(inputField, errorSpanId) {
        var inputValue = inputField.value;

        // Check if the input is numeric
        if (!/^\d+$/.test(inputValue)) {
            // Display error message
            document.getElementById(errorSpanId).innerText = "Vui lòng chỉ nhập số.";
            // Clear the input field
            inputField.value = "";
        } else {
            // Clear error message when input is valid
            document.getElementById(errorSpanId).innerText = "";
        }

        // You can add additional validation logic if needed
    }
</script>