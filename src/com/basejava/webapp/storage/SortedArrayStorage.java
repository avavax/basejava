package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> UUID_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "empty");
        return Arrays.binarySearch(storage, 0, size, searchKey, UUID_COMPARATOR);
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
