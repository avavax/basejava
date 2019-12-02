package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializer.SerializeXML;

import static com.basejava.webapp.model.ResumeTestData.STORAGE_DIR;

public class XMLStorageTest extends AbstractStorageTest {
    public XMLStorageTest() {
        super(new FileStorage(STORAGE_DIR, new SerializeXML()));
    }
}