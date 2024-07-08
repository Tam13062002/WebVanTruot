<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Product" %>

<script type="text/javascript">
    function confirmDelete() {
        return confirm("Bạn có chắc chắn muốn xóa sản phẩm này?");
    }
</script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content-wrapper" style="padding: 20px; margin-left: 250px">
    <section class="content">
        <div class="mb-3">
            <h2 style="display: inline-block;">Danh sách sản phẩm</h2>
           

            <!-- Thêm thanh tìm kiếm -->
            <div class="input-group" style="width: 500px; margin-left: 730px;">
                <input type="text" class="form-control" name="keyword" placeholder="Tìm kiếm...">
                <div class="input-group-append">
                    <button class="btn btn-primary" type="button">
                        <i class="fas fa-search"></i>
                    </button>
                </div>
                 <a href="./addProduct">
                <button type="button" class="btn btn-success ml-auto" style="margin-left: 10px">
                    <i class="fas fa-add"></i> Thêm
                </button>
            </a>
            </div>
        </div>

        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th scope="col">Tên sản phẩm</th>
                    <th scope="col">Ảnh</th>
                    <th scope="col">Giá</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Loại hàng</th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listProduct}" var="product">
                    <tr>
                        <td>${product.name}</td>
                        <td><img src="./assets/images/${product.image}" alt="Product Image" width="50" height="50"></td>
                        <td>${product.price}</td>
                        <td>${product.quantity}</td>
                        <td>${product.id_category}</td>
                        <td>${product.status}</td>

                        <td>
                            <a href="UpdateProduct?id=${product.id}" class="btn btn-primary">
                                <i class="fas fa-edit"></i> <!-- Icon sửa chữa -->
                            </a>
                        </td>
                        <td>
                            <form action="product" method="post" onsubmit="return confirmDelete();">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="productId" value="${product.id}">
                                <button type="submit" class="btn btn-danger"> <i class="fas fa-trash"></i></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>
</div>