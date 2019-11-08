package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> FULLNAME_COMPARATOR = (o1, o2) -> {
        if (o1.getFullName().equals(o2.getFullName())) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
        return o1.getFullName().compareTo(o2.getFullName());
    };

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

    protected abstract boolean isExist(Object searchKey);

    protected abstract void insertToStorage(Resume resume, Object searchKey);

    protected abstract void updateOnStorage(Resume resume, Object searchKey);

    protected abstract void removeFromStorage(Object searchKey);

    protected abstract Resume getFromStorage(Object searchKey);

}
