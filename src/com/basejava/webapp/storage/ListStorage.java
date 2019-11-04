package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage {

    protected List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void insertToStorage(Resume resume, Object searchKey) {
        list.add(resume);
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    protected void removeFromStorage(Object searchKey) {
        list.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected void updateOnStorage(Resume resume, Object searchKey) {
        list.set((Integer) searchKey, resume);
    }
}
