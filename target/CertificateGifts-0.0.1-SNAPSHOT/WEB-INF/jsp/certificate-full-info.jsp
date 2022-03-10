<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Full info</title>
    <style>
        body{
            width: 70%;
        }
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/certificates">CERTIFICATES</a>&nbsp;
<a href="${pageContext.request.contextPath}/tags">TAGS</a>
<br><br>
Name: ${certificate.certificateName}<br><br>
Price: ${certificate.price}<br><br>
Duration: ${certificate.duration}<br><br>
Creation date: ${certificate.creationDate}<br><br>
Last update time: ${certificate.lastUpdateTime}<br><br>
Description: ${certificate.description}<br><br>
Tags:<br>
<form action="${pageContext.request.contextPath}/certificates" method="post">
    <c:forEach items="${certificate.tags}" var="tag">
        <button name="tag_name" value="${tag.tagName}">${tag.tagName}</button>
    </c:forEach>
</form>
<br><br>

<form method="post" action="${pageContext.request.contextPath}/edit-certificate">
    <button name="id" value="${certificate.giftCertificateId}">Edit</button>
</form>
<form method="post" action="${pageContext.request.contextPath}/delete-certificate">
    <button name="id" value="${certificate.giftCertificateId}">Delete</button>
</form>
</body>
</html>
