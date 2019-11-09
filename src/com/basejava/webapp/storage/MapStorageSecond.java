package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class MapStorageSecond extends MapStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        Resume resume = map.get(uuid);
        return resume != null ? resume : new Resume(uuid, "empty");
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsValue(searchKey);
    }

    @Override
    protected void insertToStorage(Resume resume, Object searchKey) {
        map.put(searchKey.toString(), resume);
    }

    @Override
    protected void updateOnStorage(Resume resume, Object searchKey) {
        map.put(searchKey.toString(), resume);
    }

    @Override
    protected void removeFromStorage(Object searchKey) {
        map.remove(searchKey.toString());
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        return map.get(searchKey.toString());
    }
}