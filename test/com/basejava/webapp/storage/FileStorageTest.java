package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializer.SerializeStreamObject;

import static com.basejava.webapp.model.ResumeTestData.STORAGE_DIR;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new SerializeStreamObject()));
    }
}