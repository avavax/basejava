package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updateOnStorage(Resume resume) {
        int position = getPosition(resume.getUuid());
        storage[position] = resume;
    }

    @Override
    public Resume getFromStorage(String uuid) {
        return storage[getPosition(uuid)];
    }

    @Override
    public void insertToStorage(Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        int position = getPosition(resume.getUuid());
        insertToArray(resume, position);
        size++;
    }

    @Override
    protected void removeFromStorage(String uuid) {
        removeFromArray(getPosition(uuid));
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Resume resume) {
        return getPosition(resume.getUuid()) >= 0;
    }

    protected abstract int getPosition(String uuid);

    protected abstract void insertToArray(Resume resume, int position);

    protected abstract void removeFromArray(int position);
}
