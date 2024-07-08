<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="bg-info text-white py-5">
    <div class="container py-5">
        <div class="row">
            <div class="col-md-6">
                <h1 class="display-4">
                    <c:if test="${id_category==null}" >
                        Best Categories & Products
                    </c:if>
                    <c:if test="${id_category!=null}" >
                        <c:forEach items="${listCategory}" var="category">
                            <c:if test="${id_category==category.id}" >
                                Best ${category.name} & Products
                            </c:if>
                        </c:forEach>
                    </c:if>
                </h1>
                <p class="lead">
                    Ván trượt chất lượng, giá tốt.Mại dô
                </p>
                <button type="button" class="btn btn-outline-light btn-lg">
                    Learn more
                </button>
                <button type="button" class="btn btn-light shadow-0 text-primary btn-lg mt-3">
                    <span class="pt-1">Purchase now</span>
                </button>
            </div>
            <div class="col-md-6">
                <!-- Add your image here -->
                <img src="./assets/images/loaivantruot.png" alt="Description of the image" class="img-fluid rounded">
            </div>
        </div>
    </div>
</div>