package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class ListStorage extends AbstractStorage {

    protected static Collection<Resume> collection = new ArrayList<>();

    @Override
    public void clear() {
        collection.clear();
    }

    @Override
    public Resume[] getAll() {
        return collection.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    protected boolean isExist(Resume resume) {
        return collection.contains(resume);
    }

    @Override
    protected void insertToStorage(Resume resume) {
        collection.add(resume);
    }

    @Override
    protected Resume getFromStorage(String uuid) {
        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (Objects.equals(resume.getUuid(), uuid)) {
                return resume;
            }
        }
        return null;
    }

    @Override
    protected void removeFromStorage(String uuid) {
        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (Objects.equals(resume.getUuid(), uuid)) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    protected void updateOnStorage(Resume resume) {
        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume keySearch = iterator.next();
            if (Objects.equals(keySearch, resume)) {
                iterator.remove();
                collection.add(resume);
                break;
            }
        }
    }
}
