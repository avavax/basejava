package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.model.*;
import com.basejava.webapp.util.XMLParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SerializeXML implements SerializeStrategy {
    private XMLParser XMLParser;

    public SerializeXML() {
        XMLParser = new XMLParser(
                Resume.class, Organization.class, Link.class,
                OrganizationSection.class, SimpleSection.class, ListSection.class, Position.class);
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            XMLParser.marshall(r, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return XMLParser.unmarshall(r);
        }
    }

    @Override
    public String getFileName(String uuid) {
        return uuid + ".xml";
    }
}
