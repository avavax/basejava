package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey((String) searchKey);
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

    @Override
    protected List getListStorage() {
        return new ArrayList<>(map.values());
    }
}
