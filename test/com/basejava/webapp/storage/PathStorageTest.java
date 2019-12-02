package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializer.SerializeStreamObject;

import static com.basejava.webapp.model.ResumeTestData.STORAGE_DIR;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR, new SerializeStreamObject()));
    }
}