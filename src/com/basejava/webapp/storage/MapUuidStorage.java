package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapUuidStorage extends AbstractStorage<String> {

    protected Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
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
    protected boolean isExist(String searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected void insertToStorage(Resume resume, String searchKey) {
        map.put(searchKey, resume);
    }

    @Override
    protected void updateOnStorage(Resume resume, String searchKey) {
        map.put(searchKey, resume);
    }

    @Override
    protected void removeFromStorage(String searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected Resume getFromStorage(String searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected List<Resume> getListStorage() {
        return new ArrayList<>(map.values());
    }
}
