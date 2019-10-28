package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10_000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int position = getPosition(uuid);
        if (position != -1) {
            System.out.println("Резюме с uuid=" + uuid + " уже есть в базе");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Переполнение базы резюме");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int position = getPosition(uuid);
        if (position == -1) {
            System.out.println("Резюме с uuid=" + uuid + " нет в базе");
            return null;
        }
        return storage[position];
    }

    public void delete(String uuid) {
        int position = getPosition(uuid);
        if (position == -1) {
            System.out.println("Резюме с uuid=" + uuid + " нет в базе");
        } else {
            storage[position] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int position = getPosition(uuid);
        if (position == -1) {
            System.out.println("Резюме с uuid=" + uuid + " нет в базе");
        } else {
            storage[position] = resume;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

     private int getPosition(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
