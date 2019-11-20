package com.basejava.webapp.model;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        contacts.put(ContactType.SITE, "http://gkislin.ru/");
        resume.setContacts(contacts);

        // Раздел Секции
        Map<SectionType, AbstractSection> sections = new HashMap<>();

        SimpleSection objective = new SimpleSection();
        objective.setDescription("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        sections.put(SectionType.OBJECTIVE, objective);
        SimpleSection personal = new SimpleSection();
        personal.setDescription("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        sections.put(SectionType.PERSONAL, personal);

        ListSection achivement = new ListSection();
        ArrayList<String> achivementList = new ArrayList<>();
        achivementList.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        achivementList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achivementList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achivementList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achivementList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achivementList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achivement.setList(achivementList);
        sections.put(SectionType.ACHIEVEMENT, achivement);

        ListSection qualification = new ListSection();
        ArrayList<String> qualificationList = new ArrayList<>();
        qualificationList.add("Родной русский, английский \"upper intermediate\"");
        qualificationList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualificationList.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualificationList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualificationList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualificationList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualificationList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualificationList.add("Python: Django.");
        qualificationList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualificationList.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualificationList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualificationList.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualificationList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualificationList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.setList(qualificationList);
        sections.put(SectionType.QUALIFICATIONS, qualification);

        OrganizationSection experience = new OrganizationSection();
        ArrayList<Organization> experienceList = new ArrayList<>();
        experienceList.add(new Organization("Alcatel",
                "Инженер по аппаратному и программному тестированию",
                "http://www.alcatel.ru/",
                YearMonth.of(1997, 9),
                YearMonth.of(2005, 1),
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        experienceList.add(new Organization("Siemens AG",
                "Разработчик ПО",
                "https://www.siemens.com/ru/ru/home.html",
                YearMonth.of(2005, 1),
                YearMonth.of(2007, 2),
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        experienceList.add(new Organization("Enkata",
                "Разработчик ПО",
                "http://enkata.com/",
                YearMonth.of(2007, 3),
                YearMonth.of(2008, 6),
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."));
        experience.setList(experienceList);
        sections.put(SectionType.EXPERIENCE, experience);

        OrganizationSection education = new OrganizationSection();
        ArrayList<Organization> educationList = new ArrayList<>();
        educationList.add(new Organization("Заочная физико-техническая школа при МФТИ",
                "Закончил с отличием",
                "http://www.school.mipt.ru/",
                YearMonth.of(1984, 9),
                YearMonth.of(1987, 6),
                null));
        educationList.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "Аспирантура (программист С, С++), Инженер (программист Fortran, C)",
                "http://www.ifmo.ru/",
                YearMonth.of(1987, 9),
                YearMonth.of(1996, 7),
                null));
        educationList.add(new Organization("Alcatel",
                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                "http://www.alcatel.ru/",
                YearMonth.of(1997, 9),
                YearMonth.of(1998, 3),
                null));
        education.setList(educationList);
        sections.put(SectionType.EDUCATION, education);

        resume.setSections(sections);

        contactDisplay();

        sectionDisplay();
    }

    private static void contactDisplay() {
        for (ContactType contact: ContactType.values()) {
            System.out.println(contact.getTitle() + " : " + resume.getContact(contact));
        }
        System.out.println("");
    }

    private static void sectionDisplay() {

        for (SectionType sectionName: SectionType.values()) {
            AbstractSection currentSection = resume.getSection(sectionName);
            System.out.println(sectionName.getTitle());
            System.out.println(currentSection.toString());
            System.out.println("");
        }
    }
}