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

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int position = getPosition(uuid);
        if (position > 0) {
            System.out.println("Резюме с uuid=" + uuid + " уже есть в базе");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Переполнение базы резюме");
        } else {
            insertToArray(resume, position);
            size++;
        }
    }

    public void delete(String uuid) {
        int position = getPosition(uuid);
        if (position < 0) {
            System.out.println("Резюме с uuid=" + uuid + " нет в базе");
        } else {
            removeFromArray(position);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getPosition(String uuid);

    protected abstract void insertToArray(Resume resume, int position);

    protected abstract void removeFromArray(int position);
}
