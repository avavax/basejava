package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        Object searchKey = getNotExistedSearchKey(resume.getUuid());
        insertToStorage(resume, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return getFromStorage(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        removeFromStorage(searchKey);
    }

    public void update(Resume resume) {
        Object searchKey = getExistedSearchKey(resume.getUuid());
        updateOnStorage(resume, searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Object getSearchKey(String uuid);

    public abstract void clear();

    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract boolean isExist(Object searchKey);

    protected abstract void insertToStorage(Resume resume, Object searchKey);

    protected abstract void updateOnStorage(Resume resume, Object searchKey);

    protected abstract void removeFromStorage(Object searchKey);

    protected abstract Resume getFromStorage(Object searchKey);

}
