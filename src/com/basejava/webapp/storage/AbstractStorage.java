package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        if (isExist(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertToStorage(resume);
        }
    }

    public Resume get(String uuid) {
        if (!isExist(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        } else {
            return getFromStorage(uuid);
        }
    }

    public void delete(String uuid) {
        if (!isExist(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        } else {
            removeFromStorage(uuid);
        }
    }

    public void update(Resume resume) {
        if (!isExist(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateOnStorage(resume);
        }
    }

    public abstract void clear();

    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract boolean isExist(Resume resume);

    protected abstract void insertToStorage(Resume resume);

    protected abstract void updateOnStorage(Resume resume);

    protected abstract void removeFromStorage(String uuid);

    protected abstract Resume getFromStorage(String uuid);

}
