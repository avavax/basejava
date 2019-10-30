package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getPosition(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertToArray(Resume resume, int position) {
        storage[size] = resume;
    }

    @Override
    protected void removeFromArray(int position) {
        storage[position] = storage[size - 1];
    }
}
