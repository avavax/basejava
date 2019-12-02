package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializer.SerializeJSON;

import static com.basejava.webapp.model.ResumeTestData.STORAGE_DIR;

public class JSONStorageTest extends AbstractStorageTest {
    public JSONStorageTest() {
        super(new FileStorage(STORAGE_DIR, new SerializeJSON()));
    }
}