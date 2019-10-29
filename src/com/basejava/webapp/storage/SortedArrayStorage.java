package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getPosition(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertToArray(Resume resume) {
        int newPosition = - 1 * Arrays.binarySearch(storage, 0, size, resume) - 1;
        System.arraycopy(storage, newPosition, storage, newPosition + 1, size - newPosition);
        storage[newPosition] = resume;
        size++;
    }

    @Override
    protected void removeFromArray(int position) {
        System.arraycopy(storage, position + 1, storage, position, size - position);
        storage[size - 1] = null;
        size--;
    }
}
