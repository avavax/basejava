package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    protected Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected void insertToStorage(Resume resume, Resume searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateOnStorage(Resume resume, Resume searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeFromStorage(Resume searchKey) {
        map.remove(searchKey.getUuid());
    }

    @Override
    protected Resume getFromStorage(Resume searchKey) {
        return searchKey;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected List<Resume> getListStorage() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}