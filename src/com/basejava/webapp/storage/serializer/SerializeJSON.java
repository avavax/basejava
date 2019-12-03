package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.model.Resume;
import com.basejava.webapp.util.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SerializeJSON implements SerializeStrategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            JSONParser.write(r, writer);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JSONParser.read(reader, Resume.class);
        }
    }

    @Override
    public String getFileName(String uuid) {
        return uuid + ".json";
    }
}
