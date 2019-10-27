package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int position = getResumePosition(uuid);
        if (position != -1) {
            System.out.println("Резюме с uuid=" + uuid + " уже есть в базе");
        } else if (size == storage.length) {
            System.out.println("Переполнение базы резюме");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int position = getResumePosition(uuid);
        if (position != -1) {
            return storage[position];
        } else {
            System.out.println("Резюме с uuid=" + uuid + " нет в базе");
            return null;
        }
    }

    public void delete(String uuid) {
        int position = getResumePosition(uuid);
        if (position != -1) {
            storage[position] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Резюме с uuid=" + uuid + " нет в базе");
        }
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int position = getResumePosition(uuid);
        if (position != -1) {
            storage[position] = resume;
        } else {
            System.out.println("Резюме с uuid=" + uuid + " нет в базе");
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

     private int getResumePosition(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
