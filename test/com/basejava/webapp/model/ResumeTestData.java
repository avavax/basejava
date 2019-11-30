package com.basejava.webapp.model;
import java.io.File;
import java.time.YearMonth;
import java.util.EnumMap;
import java.util.Map;

public class ResumeTestData {
    public static final String STORAGE_DIR = "D:\\JAVA\\basejava\\storage";

    private static final String UUID = "uuid1";
    private static final String FULL_NAME = "Григорий Кислин";

    public static void main(String[] args) {
        Resume resume = fillResume(UUID, FULL_NAME);
        contactDisplay(resume);
        sectionDisplay(resume);
    }

    public static Resume fillResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        // Раздел Контакты
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        resume.addContact(ContactType.SITE, "http://gkislin.ru/");

        // Раздел Секции
        Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

        resume.addSection(SectionType.OBJECTIVE, new SimpleSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new SimpleSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников."));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(
                "Родной русский, английский \"upper intermediate\"",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Python: Django.",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,",
                "MySQL, SQLite, MS SQL, HSQLDB",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"));
        resume.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Alcatel", "http://www.alcatel.ru/",
                                new Position(YearMonth.of(1997, 9),
                                        YearMonth.of(2005, 1),
                                        "Инженер по аппаратному и программному тестированию",
                                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).")),
                        new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                                new Position(YearMonth.of(2005, 1),
                                        YearMonth.of(2007, 2),
                                        "Разработчик ПО",
                                        "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).")),
                        new Organization("Enkata", "http://enkata.com/",
                                new Position(YearMonth.of(2007, 3),
                                        YearMonth.of(2008, 6),
                                        "Разработчик ПО",
                                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."))));
        resume.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
                                new Position(YearMonth.of(1984, 9),
                                        YearMonth.of(1987, 6),
                                        "Закончил с отличием")),
                        new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
                                new Position(YearMonth.of(1987, 9),
                                        YearMonth.of(1993, 9),
                                        "Инженер (программист Fortran, C)"),
                                new Position(YearMonth.of(1993, 9),
                                        YearMonth.of(1996, 7),
                                        "Аспирантура (программист С, С++)")),
                        new Organization("Alcatel", "http://www.alcatel.ru/",
                                new Position(YearMonth.of(1997, 9),
                                        //YearMonth.of(1998, 3),
                                        null,
                                        "6 месяцев обучения цифровым телефонным сетям (Москва)"))));

        return resume;
    }

    public static void contactDisplay(Resume resume) {
        for (ContactType contact: ContactType.values()) {
            System.out.println(contact.getTitle() + " : " + resume.getContact(contact));
        }
        System.out.println("");
    }

    public static void sectionDisplay(Resume resume) {

        for (SectionType sectionName: SectionType.values()) {
            AbstractSection currentSection = resume.getSection(sectionName);
            System.out.println(sectionName.getTitle());
            System.out.println(currentSection.toString());
            System.out.println("");
        }
    }
}