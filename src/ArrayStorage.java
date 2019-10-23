import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume resume) {
        int position = this.size();
        if (position != storage.length) {
            storage[position] = resume;
        }
    }

    Resume get(String uuid) {
        for (Resume item:storage) {
            if (item != null && item.uuid.equals(uuid)) {
                return item;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int size = this.size();
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, this.size());
    }

    int size() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return i;
            }
        }
        return storage.length;
    }
}
