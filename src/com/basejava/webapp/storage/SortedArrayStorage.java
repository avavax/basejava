package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertToArray(Resume resume, int position) {
        int newPosition = -position - 1;
        System.arraycopy(storage, newPosition, storage, newPosition + 1, size - newPosition);
        storage[newPosition] = resume;
    }

    @Override
    protected void removeFromArray(int position) {
        int numMoved = size - position - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, position + 1, storage, position, numMoved);
        }

    }
}
