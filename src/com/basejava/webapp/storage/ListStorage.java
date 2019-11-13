package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage<Integer> {

    protected List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected boolean isExist(Integer searchKey) {
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
    protected void insertToStorage(Resume resume, Integer searchKey) {
        list.add(resume);
    }

    @Override
    protected Resume getFromStorage(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    protected void removeFromStorage(Integer searchKey) {
        list.remove(searchKey.intValue());
    }

    @Override
    protected void updateOnStorage(Resume resume, Integer searchKey) {
        list.set(searchKey, resume);
    }

    @Override
    protected List<Resume> getListStorage() {
        return new ArrayList<>(list);
    }
}
