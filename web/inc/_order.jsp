<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    function updateOrderStatus(selectElement) {
        var orderId = selectElement.name.split("_")[1]; // Lấy id từ tên của select
        var newStatus = selectElement.value;

        $.post("order", {orderId: orderId, newStatus: newStatus}, function (data) {
            // Xử lý kết quả nếu cần
            // Ví dụ: có thể cập nhật lại trang JSP sau khi cập nhật trạng thái thành công
            location.reload(); // Tải lại trang
        });
    }
    function disableUnwantedOptions(selectedValue) {
        var options = document.querySelectorAll('.select option');
        options.forEach(function (option) {
            if (option.value !== selectedValue) {
                option.disabled = true;
            } else {
                option.disabled = false;
            }
        });
    }
</script>
<div class="content-wrapper" style="padding: 20px; margin-left: 250px">
    <section class="content">
        <div class="mb-3">
            <h2 style="display: inline-block;">Danh sách đơn hàng</h2>
            <a href="./addProduct"> <button type="button" class="btn btn-success ml-auto" style="margin-left: 820px">Thêm</button></a>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>

                    <th scope="col">Tên khách hàng</th>
                    <th scope="col">Ngày đặt hàng</th>
                    <th scope="col">Phương thức thanh toán</th>
                    <th scope="col">Tổng tiền</th>
                    <th scope="col">Status</th>
                    <th></th>  
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listOrder}" var="order">
                    <tr>
                        <td>${order.customerID}</td>
                        <td>${order.orderTime}</td>
                        <td>${order.payment}</td>
                        <td>$${order.total}</td>                     

                        <td>
                            <select class="select" name="orderstatus_${order.orderID}" style="margin-left: 20px;with: 100px; " onchange="updateOrderStatus(this)">
                                <c:forEach items="${listOrderStatus}" var="orderstatus">
                                    <hr>
                                            <option value="${orderstatus.status}" ${orderstatus.status eq order.status ? 'selected' : ''}>${orderstatus.description}</option>
                                            

                                </c:forEach>
                            </select>
                        </td>

                        <td> <a href="orderdetail?id=${order.orderID}" class="btn btn-info">
                                Xem
                            </a> </td>



                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>
</div>
