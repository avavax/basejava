package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage <SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected static final Comparator<Resume> FULLNAME_COMPARATOR =
            Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    public List<Resume> getAllSorted() {
        List<Resume> list = getListStorage();
        list.sort(FULLNAME_COMPARATOR);
        return list;
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getNotExistedSearchKey(resume.getUuid());
        insertToStorage(resume, searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        return getFromStorage(searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        removeFromStorage(searchKey);
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getExistedSearchKey(resume.getUuid());
        updateOnStorage(resume, searchKey);
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract List<Resume> getListStorage();

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void insertToStorage(Resume resume, SK searchKey);

    protected abstract void updateOnStorage(Resume resume, SK searchKey);

    protected abstract void removeFromStorage(SK searchKey);

    protected abstract Resume getFromStorage(SK searchKey);

}
