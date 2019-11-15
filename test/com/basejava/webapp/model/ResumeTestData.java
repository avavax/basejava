package com.basejava.webapp.model;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ResumeTestData {
    private static final String UUID_1 = "uuid1";
    private static Resume resume = new Resume(UUID_1, "Григорий Кислин");

    public static void main(String[] args) {
        // Раздел Контакты
        Map<ContactType, String> contacts = new HashMap<>();
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        contacts.put(ContactType.SYTE, "http://gkislin.ru/");
        resume.setContacts(contacts);

        // Раздел Секции
        Map<SectionType, Section> sections = new HashMap<>();

        SimpleSection objective = new SimpleSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        sections.put(SectionType.OBJECTIVE, objective);
        SimpleSection personal = new SimpleSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        sections.put(SectionType.PERSONAL, personal);

        ListSection achivement = new ListSection();
        ArrayList<ListItem> achivementList = new ArrayList<>();
        achivementList.add(new ListItem("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        achivementList.add(new ListItem("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)."));
        achivementList.add(new ListItem("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга."));
        achivementList.add(new ListItem("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера."));
        achivementList.add(new ListItem("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk."));
        achivementList.add(new ListItem("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."));
        achivement.setList(achivementList);
        sections.put(SectionType.ACHIEVEMENT, achivement);

        ListSection qualification = new ListSection();
        ArrayList<ListItem> qualificationList = new ArrayList<>();
        qualificationList.add(new ListItem("Родной русский, английский \"upper intermediate\""));
        qualificationList.add(new ListItem("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования"));
        qualificationList.add(new ListItem("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer."));
        qualificationList.add(new ListItem("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,"));
        qualificationList.add(new ListItem("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT."));
        qualificationList.add(new ListItem("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka"));
        qualificationList.add(new ListItem("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js"));
        qualificationList.add(new ListItem("Python: Django."));
        qualificationList.add(new ListItem("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)."));
        qualificationList.add(new ListItem("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,"));
        qualificationList.add(new ListItem("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,"));
        qualificationList.add(new ListItem("MySQL, SQLite, MS SQL, HSQLDB"));
        qualificationList.add(new ListItem("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,"));
        qualificationList.add(new ListItem("Version control: Subversion, Git, Mercury, ClearCase, Perforce"));
        qualificationList.add(new ListItem("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"));
        qualification.setList(qualificationList);
        sections.put(SectionType.QUALIFICATIONS, qualification);

        ListSection experience = new ListSection();
        ArrayList<ListItem> experienceList = new ArrayList<>();
        experienceList.add(new ListItem("Alcatel",
                "Инженер по аппаратному и программному тестированию",
                "http://www.alcatel.ru/",
                "09/1997",
                "01/2005",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        experienceList.add(new ListItem("Siemens AG",
                "Разработчик ПО",
                "https://www.siemens.com/ru/ru/home.html",
                "01/2005",
                "02/2007",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        experienceList.add(new ListItem("Enkata",
                "Разработчик ПО",
                "http://enkata.com/",
                "03/2007",
                "06/2008",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."));
        experience.setList(experienceList);
        sections.put(SectionType.EXPERIENCE, experience);

        ListSection education = new ListSection();
        ArrayList<ListItem> educationList = new ArrayList<>();
        educationList.add(new ListItem("Заочная физико-техническая школа при МФТИ",
                "Закончил с отличием",
                "http://www.school.mipt.ru/",
                "09/1984",
                "06/1987",
                null));
        educationList.add(new ListItem("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "Аспирантура (программист С, С++), Инженер (программист Fortran, C)",
                "http://www.ifmo.ru/",
                "09/1987",
                "07/1996",
                null));
        educationList.add(new ListItem("Alcatel",
                "\t6 месяцев обучения цифровым телефонным сетям (Москва)",
                "http://www.alcatel.ru/",
                "09/1997",
                "03/1998", null));
        education.setList(educationList);
        sections.put(SectionType.EDUCATION, education);

        resume.setSections(sections);

        // Проверки
        Assert.assertEquals("+7(921) 855-0482", resume.getContact(ContactType.PHONE));
        Assert.assertEquals("https://github.com/gkislin", resume.getContact(ContactType.GITHUB));
        Assert.assertEquals("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям",
                resume.getSections().get(SectionType.OBJECTIVE).getDescription());
        Assert.assertEquals("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.",
                resume.getSections().get(SectionType.PERSONAL).getDescription());
        Assert.assertEquals("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.",
                resume.getSection(SectionType.ACHIEVEMENT).getList().get(0).getDescription());
        Assert.assertEquals("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.",
                resume.getSection(SectionType.QUALIFICATIONS).getList().get(2).getDescription());
        Assert.assertEquals("Инженер по аппаратному и программному тестированию",
                resume.getSection(SectionType.EXPERIENCE).getList().get(0).getSubtitle());
        Assert.assertEquals("Заочная физико-техническая школа при МФТИ",
                resume.getSection(SectionType.EDUCATION).getList().get(0).getTitle());

    }
}