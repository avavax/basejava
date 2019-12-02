package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializer.SerializeStreamData;

import static com.basejava.webapp.model.ResumeTestData.STORAGE_DIR;

public class DataStreamStorageTest extends AbstractStorageTest {
    public DataStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new SerializeStreamData()));
    }
}