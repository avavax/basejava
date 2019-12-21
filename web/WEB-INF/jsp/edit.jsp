<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ page import="com.basejava.webapp.model.SectionType" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru" class="h-100">
<head>
    <title>Резюме ${resume.fullName}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://use.fontawesome.com/ff6389a879.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <link rel="icon" href="img/icon.png"/>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="fragments/header.jsp"/>
<main role="main" class="flex-shrink-0">
    <div class="container">
        <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="uuid" value="${resume.uuid}">
            <div class="form-group">
                <label for="fullName">Имя</label>
                <input type="text" class="form-control" id="fullName" name="fullName" value="${resume.fullName}">
            </div>
            <div class="row">
                <div class="col-6">
                    <h4>Контакты</h4>
                    <c:forEach var="type" items="<%=ContactType.values()%>">
                        <div class="form-group">
                            <label>${type.title}</label>
                            <input type="text" class="form-control" name="${type.name()}" value="${resume.getContact(type)}">
                        </div>
                    </c:forEach>
                </div>
                <div class="col-6">

                </div>
            </div>

            <c:forEach items="<%=SectionType.values()%>" var="type">
                <c:set var="value" value="${resume.getSection(type)}" />
                <h4>${type.getTitle()}</h4>
                <c:choose>
                    <c:when test="${(type eq 'OBJECTIVE') || (type eq 'PERSONAL')}">
                        <textarea class="form-control" name="${type}" rows="3">${value.getDescription().trim()}
                        </textarea><br>
                    </c:when>

                    <c:when test="${(type eq 'ACHIEVEMENT') || (type eq 'QUALIFICATIONS')}">
                        <c:forEach var="section" items="${value.getList()}">
                            <div class="form-group">
                                <input type="text" class="form-control" name="${type}" value="${section.toString()}">
                            </div>
                        </c:forEach>
                        <button type="button" id="ADD-${type}" class="btn btn-primary btn-md">Добавить</button><br><br>
                    </c:when>

                    <c:when test="${(type eq 'EXPERIENCE') || (type eq 'EDUCATION')}">
                        <c:forEach var="organization" items="${value.getList()}">
                            <div class="row">
                                <div class="col-6">
                                    <label>Название организации</label>
                                    <input type="text" class="form-control" name="${type}" value="${organization.getLink().getName()}">
                                </div>
                                <div class="col-6">
                                    <label>Ссылка</label>
                                    <input type="text" class="form-control" name="${type}" value="${organization.getLink().getUrl()}">
                                </div>
                            </div>
                            <c:forEach var="position" items="${organization.getPositions()}">
                                <div class="row">
                                    <div class="col-2">
                                        <label>Начало </label>
                                        <input type="text" class="form-control" placeholder="YYYY-MM" name="${type}" value="${position.getStart() == ('3000-01') ? 'сейчас' : position.getStart()}">
                                    </div>
                                    <div class="col-2">
                                        <label>Окончание</label>
                                        <input type="text" class="form-control" placeholder="YYYY-MM" name="${type}" value="${position.getFinish() == ('3000-01') ? 'сейчас' : position.getFinish()}">
                                    </div>
                                    <div class="col-8">
                                        <label>Специальность</label>
                                        <input type="text" class="form-control" name="${type}" value="${position.getTitle()}">
                                        <c:if test="${type eq 'EXPERIENCE'}">
                                            <label>Должностныe обязанности</label>
                                            <input type="text" class="form-control" name="${type}" value="${position.getDescription()}">
                                        </c:if>
                                        <c:if test="${type ne 'EXPERIENCE'}">
                                            <input type="hidden" class="form-control" name="${type}" value="">
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                            <button type="button" class="ADD-${type}-POSITION" class="btn btn-primary btn-sm">Добавить позицию</button><br><br>
                            <input type="hidden" class="form-control" name="${type}" value="end">
                        </c:forEach><br>

                        <button type="button" id="ADD-${type}" class="btn btn-primary btn-md">Добавить место</button><br><br>
                    </c:when>
                </c:choose>
            </c:forEach>

            <hr>
            <button type="submit" class="btn btn-primary btn-md">Сохранить</button>
            <button onclick="window.history.back()" class="btn btn-primary btn-md">Отменить</button><br><br>
        </form>
    </div>
</main>
<jsp:include page="fragments/footer.jsp"/>
<script src="js/script.js"></script>
</body>
</html>