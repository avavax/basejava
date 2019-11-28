package com.basejava.webapp.storage;

import static com.basejava.webapp.model.ResumeTestData.STORAGE_DIR;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR));
    }
}