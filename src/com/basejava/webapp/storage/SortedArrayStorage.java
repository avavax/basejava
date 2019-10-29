package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void delete(String uuid) {
        int position = getPosition(uuid);
        if (position < 0) {
            System.out.println("Резюме с uuid=" + uuid + " нет в базе");
        } else {
            System.arraycopy(storage, position + 1, storage, position, size - position);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int position = getPosition(uuid);
        if (position >= 0) {
            System.out.println("Резюме с uuid=" + uuid + " уже есть в базе");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Переполнение базы резюме");
        } else {
            int newPosition = - 1 * Arrays.binarySearch(storage, 0, size, resume) - 1;
            System.arraycopy(storage, newPosition, storage, newPosition + 1, size - newPosition);
            storage[newPosition] = resume;
            size++;
        }
    }

    @Override
    protected int getPosition(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}


