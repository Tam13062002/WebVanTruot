<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Product" %>
<%@ page import="Dao.Database" %>
<%@ page import="utils.API" %>
<%@ page import="Model.Category" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.min.css"/>
        <script src="./assets/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary sticky-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">
                    <img src="./assets/icon/images.png" width="50" height="50"/>TheGioiVanTruot
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="margin: 0 auto">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="./admin">Home</a>
                        </li>                       
                       

                        <c:if test="${user==null}" >
                            <li class="nav-item">
                                <a class="nav-link" href="login">Login</a>
                            </li>
                        </c:if>
                        <c:if test="${user!=null}" >
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <c:set var="s" value="${user.getName()}"></c:set>  Hi                    
                                    <%
                                                String name=(String )pageContext.getAttribute("s");
                                                out.print(API.getName(name));
                                    %>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Profile</a></li>
                                    <li><a class="dropdown-item" href="./views/logout.jsp">Logout</a></li>


                                </ul>
                            </li>
                        </c:if>


                    </ul>
                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
        <aside class="main-sidebar" style="background: grey; width: 250px; height: 100%;  position: fixed"> 
            <section class="sidebar">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" style="color: black"  href="./admin">
                            <i class="fa fa-home"></i> <span>Home</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="color: black"     data-bs-toggle="collapse" href="#manageData">
                            <i class="fa fa-folder"></i> <span> Quản lý Dữ liệu <img src="./assets/icon/xuong.png" width="16px" height="16x" alt="alt"/></span>
                        </a>
                        <ul class="collapse nav flex-column ml-3" id="manageData">
                            <li class="nav-item">
                                <a class="nav-link beautiful-link" style="color: black; margin-left: 20px;" href="./product">Mặt hàng</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link beautiful-link" style="color: black; margin-left: 20px;" href="#">Nhà cung cấp</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link beautiful-link" style="color: black; margin-left: 20px;" href="#">Khách hàng</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link beautiful-link" style="color: black; margin-left: 20px;" href="#">Người giao hàng</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link beautiful-link" style="color: black; margin-left: 20px;" href="#">Nhân viên</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link beautiful-link" style="color: black; margin-left: 20px;" href="#">Loại hàng</a>
                            </li>

                        </ul>
                    </li>
                    <a class="nav-link" style="color: black"     data-bs-toggle="collapse" href="#manageData1">
                        <i class="fa fa-folder"></i> <span> Quản lý bán hàng <img src="./assets/icon/xuong.png" width="16px" height="16x" alt="alt"/></span>
                    </a>

                    <ul class="collapse nav flex-column ml-3" id="manageData1">
                        <li class="nav-item"><a class="nav-link" style="color: black;margin-left: 20px;"   href="./Order/Create">Lập đơn hàng</a></li>
                        <li class="nav-item"><a class="nav-link" style="color: black;margin-left: 20px;" href="./order">Xử lý đơn hàng</a></li>
                    </ul>
                    </li>
                </ul>
            </section>

        </aside>
        <div class="content-wrapper" style="padding: 20px; margin-left: 500px">

            <h2>Cập nhật sản phẩm</h2>

            <form action="UpdateProduct" method="post"  style="margin-top: 50px">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="productId" value="${product.id}">
                <input type="hidden" id="fileName" name="fileName" value="">
                <div class="form-row">
                    <div class="form-group col-md-6" style="margin-top: 20px">
                        <label for="name">Tên sản phẩm: </label>
                        <input type="text" class="form-control" id="name" name="name" style="margin-top: 10px" required value="${product.getName()}">
                    </div>

                    <div class="form-group col-md-6" style="margin-top: 20px">
                        <label for="image">Ảnh:</label>
                        <div class="col-sm-10">
                            <label for="Photo-Uploader" style="cursor: pointer" title="Click để thay đổi ảnh">
                                <img id="photo" src="./assets/images/${product.image}" class="img-thumbnail" style="width: 125px; height: 140px;" alt="">
                            </label>
                            <input accept="image/*" type="file" id="Photo-Uploader" name="uploadPhoto" onchange="previewImage(event)">
                            <label id="fileNameLabel"></label>
                        </div>
                    </div>

                    <div class="form-group col-md-6" style="margin-top: 20px">
                        <label for="price">Giá:</label>
                        <input type="text" class="form-control" id="price" name="price" style="margin-top: 10px"  value="${product.price}">
                    </div>

                    <div class="form-group col-md-6" style="margin-top: 20px">
                        <label for="quantity">Số lượng:</label>
                        <input type="text" class="form-control" id="quantity" name="quantity" style="margin-top: 10px"  value="${product.getQuantity()}">
                    </div>

                    <div class="form-group col-md-6" style="margin-top: 20px">
                        <label for="id_category">Loại hàng:</label>
                        <select class="select" name="category" style="margin-left: 20px">
                            <c:forEach items="${listCategory}" var="category">
                                <option value="${category.getId()}"<c:if test="${category.getId() eq product.id_category}"> selected</c:if>>${category.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>

                </div>

                <div class="form-group" style="margin-top: 20px">
                    <button type="submit" class="btn btn-primary">Cập nhật</button>
                    <a href="product" class="btn btn-secondary ml-2" style="margin-left: 350px">Back</a>
                </div>

            </form>
        </div>

     <script>
    function previewImage(event) {
        var input = event.target;
        var img = document.getElementById('photo');
        var fileNameInput = document.getElementById('fileName');
        var fileNameLabel = document.getElementById('fileNameLabel');

        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                img.src = e.target.result;
                // Lưu trữ tên file vào input ẩn và hiển thị label
                fileNameInput.value = input.files[0].name;
                fileNameLabel.innerHTML = input.files[0].name;
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
</script>

    </body>
</html>