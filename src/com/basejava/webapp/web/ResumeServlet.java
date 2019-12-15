package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.FileStorage;
import com.basejava.webapp.storage.Storage;
import com.basejava.webapp.storage.serializer.SerializeXML;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private final static String STORAGE_DIR = Config.get().getStorageDir().getAbsolutePath();
    private final static Storage STORAGE = new FileStorage(STORAGE_DIR, new SerializeXML());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String uuid = request.getParameter("uuid");
        response.getWriter().write(uuid == null ? getAllResumesTable() : getResumeTable(uuid));
    }

    private String getAllResumesTable() {
        StringBuilder table = new StringBuilder();
        table.append("<table>");
        table.append("<tr><td>id</td><td>Full Name</td><tr>");
        for (Resume r : STORAGE.getAllSorted()) {
            String uuid = r.getUuid();
            table.append("<tr><td>" + uuid + "</td><td><a href=\"?uuid=" + uuid + "\">" + r.getFullName() + "</td></tr>");
        }
        table.append("</table>");
        return table.toString();
    }

    private String getResumeTable(String uuid) {
        StringBuilder table = new StringBuilder();
        Resume r = STORAGE.get(uuid);
        table.append("<table>");
        table.append("<tr><td>id</td><td>" + r.getUuid() + "</td></tr>");
        table.append("<tr><td>Имя</td><td>" + r.getFullName()+ "</td></tr>");
        table.append("</table>");
        return table.toString();
    }
}
