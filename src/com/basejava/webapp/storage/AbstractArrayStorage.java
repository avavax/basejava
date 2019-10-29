package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int position = getPosition(uuid);
        if (position < 0) {
            System.out.println("Резюме с uuid=" + uuid + " нет в базе");
            return null;
        }
        return storage[position];
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int position = getPosition(uuid);
        if (position < 0) {
            System.out.println("Резюме с uuid=" + uuid + " нет в базе");
        } else {
            storage[position] = resume;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public abstract void delete(String uuid) ;

    public abstract void save(Resume resume);

    protected abstract int getPosition(String uuid);
}
