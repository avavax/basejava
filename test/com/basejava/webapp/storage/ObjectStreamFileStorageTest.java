package com.basejava.webapp.storage;

import java.io.File;

import static com.basejava.webapp.model.ResumeTestData.STORAGE_DIR;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {
    public ObjectStreamFileStorageTest() {
        super(new ObjectStreamFileStorage(STORAGE_DIR));
    }
}