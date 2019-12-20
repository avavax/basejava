<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru" class="h-100">
<head>
    <title>Список всех резюме</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" href="img/icon.png"/>
</head>
<body class="d-flex flex-column h-100">
    <jsp:include page="fragments/header.jsp"/>
    <main role="main" class="flex-shrink-0">
    <div class="container">
        <div class="row">
            <table class="table">
                <tr>
                    <th>Имя</th>
                    <th>Email</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${resumes}" var="resume">
                <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume"/>
                    <tr>
                        <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                        <td>${resume.getContact(ContactType.EMAIL)}</td>
                        <td><a href="resume?uuid=${resume.uuid}&action=delete"><i class="fa fa-minus-circle" aria-hidden="true"></i></a></td>
                        <td><a href="resume?uuid=${resume.uuid}&action=edit"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="row">
            <div class="col-3">
                <p><a class="btn btn-primary btn-md" href="resume?action=create" role="button">Создать резюме&raquo;</a></p>
            </div>
        </div>
    </div>
    </main>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
