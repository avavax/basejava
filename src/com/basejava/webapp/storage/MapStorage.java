package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.get((String) searchKey) != null;
    }

    @Override
    protected void insertToStorage(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);
    }

    @Override
    protected void updateOnStorage(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);
    }

    @Override
    protected void removeFromStorage(Object searchKey) {
        map.remove((String) searchKey);
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        return map.get((String) searchKey);
    }
}
