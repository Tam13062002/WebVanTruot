<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Product" %>
<section class="h-100 h-custom" style="background-color: #d2c9ff;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12">
                <div class="card card-registration card-registration-2" style="border-radius: 15px;">
                    <div class="card-body p-0">
                        <div class="row g-0">
                            <div class="col-lg-8">
                                <div class="p-5">
                                    <div class="d-flex justify-content-between align-items-center mb-5">
                                        <a href="home" class="btn btn-dark">
                                            <i class="fas fa-long-arrow-alt-left me-2"></i>Continue shopping
                                        </a>    
                                        <a href="cart?clear=OK" class="btn btn-info">
                                            Empty Cart
                                        </a>    
                                    </div>
                                    <hr>



                                    <div class="d-flex justify-content-between align-items-center mb-4">
                                        <div>
                                            <p class="mb-1">Shopping cart</p>
                                            <p class="mb-0">You have ${cart.size()} items in your cart</p>
                                        </div>

                                    </div>


                                    <c:set var="subtotal" value="0.0" />

                                    <c:forEach items="${cart}" var="cartItem">
                                        <div class="card mb-3">
                                            <div class="card-body">
                                                <div class="d-flex justify-content-between">
                                                    <div class="d-flex flex-row align-items-center">
                                                        <div>
                                                            <img src="./assets/images/${cartItem.image  }"
                                                                 class="img-fluid rounded-3" alt="Shopping item" style="width: 65px;">
                                                        </div>
                                                        <div class="ms-3">
                                                            <h5>${cartItem.getName()}</h5>
                                                        </div>
                                                    </div>  

                                                    <div class="d-flex flex-row align-items-center">
                                                        <form action="cart" method="post" style="width: 220px;">
                                                            <div style="float: left; width: 25%;">
                                                                <h5 class="mb-0">
                                                                    <input type="hidden" name="id_product" value="${cartItem.id}" >
                                                                    <input type="text" name="quantity_${cartItem.id}" value="${cartItem.getQuantity()}" style="width: 30px;text-align: right">
                                                                </h5>

                                                            </div>

                                                            <div style=" float: left;width: 40%;">
                                                                <h5 class="mb-0">$${cartItem.getPrice()}</h5>
                                                            </div>
                                                            <div style=" float: left;width: 35%;">
                                                                <h5 class="mb-0">
                                                                    <button type="submit" name="action" value="update" >
                                                                        <i class="fa fa-edit"></i>
                                                                    </button>
                                                                    <button type="submit" name="action" value="delete">
                                                                        <i class="fa fa-times"></i>
                                                                    </button>
                                                                </h5>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:set var="subtotal" value="${subtotal + (cartItem.price * cartItem.quantity)}" />
                                    </c:forEach>







                                </div>
                            </div>
                            <div class="col-lg-4 bg-grey" style="background: #C0C0C0">
                                <div class="p-5">
                                    <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                                    <hr class="my-4">


                                    <div class="d-flex justify-content-between mb-4">
                                        <h5 class="text-uppercase"> ${cart.size()} items: </h5>
                                        <h5>€ ${subtotal}</h5>
                                    </div>

                                    <h5 class="text-uppercase mb-3">Phương thức thanh toán:</h5>
                                    <form action="cart" method="post" id="checkoutForm"  >
                                        <input type="hidden" name="action" value="checkout">
                                        <input type="hidden" name="subtotal" value="${subtotal}">

                                        <div class="mb-4 pb-2">
                                            <select class="select" name="paymentMethod" style="height: 30px">
                                                <c:forEach items="${listPayment}" var="payment">
                                                    <option value="${payment.getId()}">  <i class="fas fa-university"></i>${payment.discription}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <hr class="my-4">
                                        <h4>Card type</h4>
                                        <a href="https://www.mbbank.com.vn/" type="submit" class="text-white" style="margin-left: 10px">
                                            <img src="./assets/icon/mb.png" width="40" height="40" />
                                        </a>
                                        <a href="https://www.vietcombank.com.vn/" type="submit" class="text-white" style="margin-left: 10px">
                                            <img src="./assets/icon/vietcombank.png" width="40" height="40" />
                                        </a>
                                        <a href="https://www.vietinbank.vn/web/home/vn/index.html" type="submit" class="text-white" style="margin-left: 10px">
                                            <img src="./assets/icon/vietinbank.png" width="40" height="40" />
                                        </a>
                                        <a href="https://www.agribank.com.vn/" type="submit" class="text-white" style="margin-left: 10px">

                                            <img src="./assets/icon/agribank.png" width="40" height="40" />
                                        </a>
                                        <a href="https://www.bidv.com.vn/" type="submit" class="text-white" style="margin-left: 10px">
                                            <img src="./assets/icon/bidv.png" width="40" height="40" />
                                        </a>


                                        <hr class="my-4">

                                        <div class="d-flex justify-content-between mb-5">
                                            <h5 class="text-uppercase">Total price</h5>
                                            <h5>€ ${subtotal}</h5>
                                        </div>


                                        <button type="submit" class="btn btn-info btn-block btn-dark"  value="checkout">
                                            <div class="d-flex justify-content-between" >
                                                <span>$${subtotal}     Check-out</span>
                                            </div>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
