<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ page import="com.basejava.webapp.model.SectionType" %>
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
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <link rel="icon" href="img/icon.png"/>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="fragments/header-item.jsp"/>
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
                            <!--<button type="button" class="btn btn-sm btn-danger"><i class="fa fa-trash" aria-hidden="true"></i></button>-->
                        </div>
                    </c:forEach>
                </div>
                <div class="col-6">

                </div>
            </div>

            <jsp:useBean id="SimpleSection" class="com.basejava.webapp.model.SimpleSection" />

            <h4>Позиция</h4>
            <textarea class="form-control" name="OBJECTIVE" rows="3">${(resume.getSection(SectionType.OBJECTIVE)).getDescription()}
            </textarea><br>

            <h4>Личные качества</h4>
            <textarea class="form-control" name="PERSONAL" rows="3">${(resume.getSection(SectionType.PERSONAL)).getDescription()}
            </textarea><br>

            <h4>Достижения</h4>
            <c:forEach var="section" items="${resume.getSection(SectionType.ACHIEVEMENT).getList()}">
                <div class="form-group">
                    <input type="text" class="form-control" name="${SectionType.ACHIEVEMENT.name()}" value="${section.toString()}">
                </div>
            </c:forEach>
            <button type="button" id="add-achivement" class="btn btn-primary btn-md">Добавить достижение</button><br><br>

            <h4>Квалификация</h4>
            <c:forEach var="section" items="${resume.getSection(SectionType.QUALIFICATIONS).getList()}">
                <div class="form-group">
                    <input type="text" class="form-control" name="${SectionType.QUALIFICATIONS.name()}" value="${section.toString()}">
                </div>
            </c:forEach>
            <button type="button" id="add-qualification" class="btn btn-primary btn-md">Добавить навык</button><br><br>

            <h4>Опыт работы</h4>
            <c:forEach var="organization" items="${resume.getSection(SectionType.EXPERIENCE).getList()}">
                <div class="row">
                    <div class="col-6">
                        <label>Название организации</label>
                        <input type="text" class="form-control" name="EXPERIENCE" value="${organization.getLink().getName()}">
                    </div>
                    <div class="col-6">
                        <label>Ссылка</label>
                        <input type="text" class="form-control" name="EXPERIENCE" value="${organization.getLink().getUrl()}">
                    </div>
                </div>
                <c:forEach var="position" items="${organization.getPositions()}">
                    <div class="row">
                        <div class="col-2">
                            <label>Начало </label>
                            <input type="text" class="form-control" name="EXPERIENCE" value="${position.getStart()}">
                        </div>
                        <div class="col-2">
                            <label>Окончание</label>
                            <input type="text" class="form-control" name="EXPERIENCE" value="${position.getFinish()}">
                        </div>
                        <div class="col-8">
                            <label>Должность</label>
                            <input type="text" class="form-control" name="EXPERIENCE" value="${position.getTitle()}">
                            <label>Должностныей обязанности</label>
                            <input type="text" class="form-control" name="EXPERIENCE" value="${position.getDescription()}">
                        </div>
                    </div>
                    <button type="button" class="add-exp-position" class="btn btn-primary btn-sm">Добавить позицию</button><br><br>
                </c:forEach>
                <hr>
                <input type="hidden" class="form-control" name="EXPERIENCE" value="end">
            </c:forEach><br>
            <button type="button" id="add-expirience" class="btn btn-primary btn-md">Добавить место работы</button><br><br>

            <h4>Образование</h4>
            <c:forEach var="organization" items="${resume.getSection(SectionType.EDUCATION).getList()}">
                <div class="row">
                    <div class="col-6">
                        <label>Название организации</label>
                        <input type="text" class="form-control" name="EDUCATION" value="${organization.getLink().getName()}">
                    </div>
                    <div class="col-6">
                        <label>Ссылка</label>
                        <input type="text" class="form-control" name="EDUCATION" value="${organization.getLink().getUrl()}">
                    </div>
                </div>
                <c:forEach var="position" items="${organization.getPositions()}">
                    <div class="row">
                        <div class="col-2">
                            <label>Начало </label>
                            <input type="text" class="form-control" name="EDUCATION" value="${position.getStart()}">
                        </div>
                        <div class="col-2">
                            <label>Окончание</label>
                            <input type="text" class="form-control" name="EDUCATION" value="${position.getFinish()}">
                        </div>
                        <div class="col-8">
                            <label>Специальность</label>
                            <input type="text" class="form-control" name="EDUCATION" value="${position.getTitle()}">
                            <input type="hidden" class="form-control" name="EDUCATION" value="">
                        </div>
                    </div>
                </c:forEach>
                <hr>
                <button type="button" class="add-edu-position" class="btn btn-primary btn-sm">Добавить позицию</button><br><br>
                <input type="hidden" class="form-control" name="EDUCATION" value="end">
            </c:forEach><br>
            <button type="button" id="add-education" class="btn btn-primary btn-md">Добавить место учёбы</button><br><br>

            <hr>
            <button type="submit" class="btn btn-primary btn-md">Сохранить</button>
            <button onclick="window.history.back()" class="btn btn-primary btn-md">Отменить</button><br>
        </form>
    </div>
</main>
<jsp:include page="fragments/footer.jsp"/>
<script src="js/script.js"></script>
</body>
</html>