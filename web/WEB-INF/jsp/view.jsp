<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Резюме ${resume.fullName}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://use.fontawesome.com/ff6389a879.js"></script>
    <link rel="icon" href="img/icon.png"/>
</head>
<body class="d-flex flex-column h-100">
    <jsp:include page="fragments/header-item.jsp"/>

    <main role="main" class="flex-shrink-0">
    <div class="container">
        <div class="row">
            <div class="col-6">
                <h4>Контакты</h4>
                <ul class="contacts-list">
                    <c:forEach items="${resume.getContacts()}" var="contact">
                        <li>${contact.getKey().toHtml(contact.getValue())}</li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-6">
                <!--maybe photo -->
            </div>
        </div>
        <c:forEach items="${resume.getSections()}" var="section">
            <h4>${section.getKey().getTitle()}</h4><hr>
            <p>${section.getValue().toHTML()}</p>
        </c:forEach>
    </div>
    </main>

    <div class="container">
        <div class="row">
            <div class="col-3">
                <p><a class="btn btn-primary btn-md" href="resume?uuid=${resume.uuid}&action=edit" role="button">Редактировать &raquo;</a></p>
            </div>
            <div class="col-3">
                <p><a class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/resume" role="button">К списку резюме &raquo;</a></p>
            </div>
        </div>
    </div>

    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
