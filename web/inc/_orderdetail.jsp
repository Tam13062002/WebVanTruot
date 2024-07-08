<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.API" %>
<%@ page import="Model.Category" %>
<%@ page import="Model.OrderDetail" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="content-wrapper" style="padding: 20px; margin-left: 250px">
    <section class="content">
        <div class="mb-3">
            <h2 style="display: inline-block;">Chi tiết đơn hàng</h2>
            <a href="./order"> <button type="button" class="btn btn-light" style="margin-left: 820px">Quay lại</button></a>
        </div>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>

                    <th scope="col">Mã chi tiết</th>
                    <th scope="col">Mã sản phẩm</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Số lượng</th>
                  
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listOrderdetail}" var="order">
                    <tr>
                        <td>${order.getOrderID()}</td>
                        <td>${order.getProductID()}</td>
                        <td>${order.price}</td>
                        <td>${order.quantity}</td>                     

                        




                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>
</div>
    </body>
    
</html>