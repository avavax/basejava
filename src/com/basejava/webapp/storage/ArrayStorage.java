package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
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

    @Override
    public void delete(String uuid) {
        int position = getPosition(uuid);
        if (position < 0) {
            System.out.println("Резюме с uuid=" + uuid + " нет в базе");
        } else {
            storage[position] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getPosition(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
