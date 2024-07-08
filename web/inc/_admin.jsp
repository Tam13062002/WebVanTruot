<%@ page import="java.util.List" %>
<%@ page import="Model.Product" %>
<%@ page import="utils.API" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>


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
                            <c:set var="s" value="${user.getName()}"></c:set>    Hi                  
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
<aside class="main-sidebar" style="background-color: #343a40; color: #ffffff; width: 250px; height: 100%; position: fixed; overflow-y: auto;">
    <section class="sidebar">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link" style="color: #ffffff; margin-left: 20px;" href="./admin">
                    <i class="fa fa-home"></i> <span>Home</span>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" style="color: #ffffff; margin-left: 20px;" data-bs-toggle="collapse" href="#manageData">
                    <i class="fa fa-folder"></i> <span> Quản lý Dữ liệu <i class="fa fa-caret-down"></i></span>
                </a>
                <ul class="collapse nav flex-column ml-3" id="manageData">
                    <li class="nav-item">
                        <a class="nav-link beautiful-link" style="color: #ffffff; margin-left: 40px;" href="./product">
                            <i class="fas fa-shopping-bag mr-1"></i> Mặt hàng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link beautiful-link" style="color: #ffffff; margin-left: 40px;" href="#">
                            <i class="fas fa-truck mr-1"></i> Nhà cung cấp
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link beautiful-link" style="color: #ffffff; margin-left: 40px;" href="#">
                            <i class="fas fa-users mr-1"></i> Khách hàng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link beautiful-link" style="color: #ffffff; margin-left: 40px;" href="#">
                            <i class="fas fa-truck-moving mr-1"></i> Người giao hàng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link beautiful-link" style="color: #ffffff; margin-left: 40px;" href="#">
                            <i class="fas fa-user-tie mr-1"></i> Nhân viên
                        </a>
                    </li>
                    <li class="nav-item">
                <a class="nav-link beautiful-link" style="color: #ffffff; margin-left: 40px;" href="#">
                    <i class="fas fa-box mr-1"></i> Loại hàng
                </a>
            </li>
                </ul>
                
            </li>

            

            <li class="nav-item">
                <a class="nav-link beautiful-link" style="color: #ffffff; margin-left: 20px;" data-bs-toggle="collapse" href="#manageData1">
                    <i class="fa fa-folder"></i> <span> Quản lý bán hàng <i class="fa fa-caret-down"></i></span>
                </a>
                <ul class="collapse nav flex-column ml-3" id="manageData1">
                    <li class="nav-item">
                        <a class="nav-link beautiful-link" style="color: #ffffff; margin-left: 40px;" href="./Order/Create">
                            <i class="fas fa-cart-plus mr-1"></i> Lập đơn hàng
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link beautiful-link" style="color: #ffffff; margin-left: 40px;" href="./order">
                            <i class="fas fa-tasks mr-1"></i> Xử lý đơn hàng
                        </a>
                    </li>
                </ul>
            </li>

        </ul>
    </section>
</aside>
