package com.basejava.webapp.storage;

import com.basejava.webapp.Config;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import org.junit.Assert;
import org.junit.Test;

public class SQLStorageTest extends AbstractStorageTest {
    public SQLStorageTest() {
        super(new SQLStorage(Config.get().getDbUrl(),
                Config.get().getDbUser(),
                Config.get().getDbPassword()));
    }

    @Override
    @Test(expected = StorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME_4);
    }

    @Override
    @Test(expected = StorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = StorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Override
    @Test(expected = StorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }
}