<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Certificates</title>
    <script>
        function disableEmptyInputs(form) {
            var controls = form.elements;
            for (var i = 0, iLen = controls.length; i < iLen; i++) {
                controls[i].disabled = controls[i].value == '';
            }
        }
    </script>
    <style>
        table, td, th {
            border: 1px solid;
            border-collapse: collapse;
            text-align: center;
        }

        .price {
            width: 140px;
        }

        #submit {
            margin-left: 180px;
        }

        #tags {
            width: 460px;
        }

        button {
            margin-top: 5px;
        }

        #menu {
            margin-left: 450px;
        }
    </style>
</head>
<body>
<div id="menu">
    <a href="${pageContext.request.contextPath}/certificates">CERTIFICATES</a>&nbsp;
    <a href="${pageContext.request.contextPath}/tags">TAGS</a>
</div>
<form action="${pageContext.request.contextPath}/certificates" method="post" onsubmit="disableEmptyInputs(this)">
    <label>Name:
        <input type="text" name="certificate_name"/>
    </label>
    <label>Tag:
        <input type="text" name="tag_name"/>
    </label><br><br>
    <label>Price from:
        <input class="price" type="text" name="price_from"/>
    </label>
    <label>Price to:
        <input class="price" type="text" name="price_to"/>
    </label><br><br>
    <input id="submit" type="submit" value="Search"/>
</form>
<form action="${pageContext.request.contextPath}/certificates" method="post" onsubmit="disableEmptyInputs(this)">
    <input type="hidden" name="certificate_name" value="${params.certificate_name}"/>
    <input type="hidden" name="tag_name" value="${params.tag_name}"/>
    <input type="hidden" name="price_from" value="${params.price_from}"/>
    <input type="hidden" name="price_to" value="${params.price_to}"/>
    Sort by :
    <button name="sorting" value="order by price">price</button>
    <button name="sorting" value="order by certificate_name">name</button>
    <button name="sorting" value="order by creation_date">date</button>
    <button name="sorting" value="order by certificate_name, creation_date">name and date</button>
    <label>Sorting order:
        <select name="sorting_order">
            <option value="asc">Asc</option>
            <option value="desc">Desc</option>
        </select>
    </label>
</form>
<table>
    <tr>
        <th>Name:</th>
        <th>Price:</th>
        <th>Duration:</th>
        <th>Creation date:</th>
        <th>Last updated:</th>
        <th>Tags:</th>
        <th>Full info:</th>
    </tr>
    <c:forEach items="${certificates}" var="certificate">
        <tr>
            <td>${certificate.certificateName}</td>
            <td>${certificate.price}</td>
            <td>${certificate.duration}</td>
            <td>${certificate.creationDate}</td>
            <td>${certificate.lastUpdateTime}</td>
            <td id="tags">
                <form action="${pageContext.request.contextPath}/certificates" method="post">
                    <c:forEach items="${certificate.tags}" var="tag">
                        <button name="tag_name" value="${tag.tagName}">${tag.tagName}</button>
                    </c:forEach>
                </form>
            </td>
            <td>
              <a href="">Watch full info</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/add-certificate-form">Add certificate</a>
</body>
</html>
