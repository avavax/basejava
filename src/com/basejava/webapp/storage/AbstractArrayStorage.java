package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void updateOnStorage(Resume resume, Integer position) {
        storage[position] = resume;
    }

    @Override
    public Resume getFromStorage(Integer position) {
        return storage[position];
    }

    @Override
    public void insertToStorage(Resume resume, Integer position) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        insertToArray(resume, position);
        size++;
    }

    @Override
    protected void removeFromStorage(Integer position) {
        removeFromArray(position);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Integer position) {
        return position >= 0;
    }

    @Override
    protected List<Resume> getListStorage() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insertToArray(Resume resume, int position);

    protected abstract void removeFromArray(int position);
}
