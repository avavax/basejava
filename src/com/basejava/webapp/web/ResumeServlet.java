package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.*;
import com.basejava.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private final static Storage STORAGE = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        if (!uuid.equals("")) {
            Resume resume = STORAGE.get(uuid);
            parseRequestToResume(request, resume);
            STORAGE.update(resume);
        } else {
            Resume resume = new Resume(request.getParameter("fullName"));
            parseRequestToResume(request, resume);
            STORAGE.save(resume);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", STORAGE.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                STORAGE.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                resume = STORAGE.get(uuid);
                break;
            case "create":
                resume = null;
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    private List<Organization> prepareOrganizationList(String[] orgs) {
        List<Organization> organizationList = new ArrayList<>();
        List<Position> positionsList;
        int i = 0;
        do {
            String name = orgs[i].trim();
            String url = orgs[i + 1].equals("") ? null : orgs[i + 1];
            i += 2;
            positionsList = new ArrayList<>();
            do {
                String start = orgs[i].trim();
                String finish = orgs[i + 1].trim();
                finish = finish.equals("сейчас") ? "3000-01" : finish;
                String title = orgs[i + 2].trim();
                String description = orgs[i + 3].trim();
                description = description.equals("") ? null : description;
                if (!start.equals("") && !finish.equals("") && !title.equals("")) {
                    positionsList.add(new Position(
                            YearMonth.parse(start),
                            YearMonth.parse(finish),
                            title,
                            description));
                }
                i += 4;
            } while (!orgs[i].equals("end"));
            i++;
            if (!name.equals("") && !positionsList.isEmpty()) {
                organizationList.add(new Organization(new Link(name, url), positionsList));
            }
        } while (orgs.length > i);
        return organizationList;
    }

    private List<String> prepareStringList(String[] str) {
        List<String> list = new ArrayList<>();
        for (String s : str) {
            s = s.trim();
            if (!s.equals("")) {
                list.add(s);
            }
        }
        return list;
    }

    private void parseRequestToResume(HttpServletRequest request, Resume resume) {
        String fullName = request.getParameter("fullName");
        resume.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    String value = request.getParameter(type.name()).trim();
                    resume.addSection(type, new SimpleSection(value));
                    break;
                case QUALIFICATIONS:
                case ACHIEVEMENT:
                    String[] params = request.getParameterValues(type.name());
                    if (params != null)
                        resume.addSection(type, new ListSection(prepareStringList(params)));
                    break;
                case EDUCATION:
                case EXPERIENCE:
                    String[] organizations = request.getParameterValues(type.name());
                    if (organizations != null)
                        resume.addSection(type, new OrganizationSection(prepareOrganizationList(organizations)));
                    break;
                default:
            }
        }
    }
}
