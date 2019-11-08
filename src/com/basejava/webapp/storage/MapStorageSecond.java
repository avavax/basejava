package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageSecond extends AbstractStorage {

    protected Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return new Resume(uuid);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(map.values());
        list.sort(FULLNAME_COMPARATOR);
        return list;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey.toString());
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
