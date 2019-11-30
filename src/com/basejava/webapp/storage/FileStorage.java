package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private File directory;

    private SerializeStrategy serializer;

    public FileStorage(String dir, SerializeStrategy serialize) {
        File directory = new File(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.serializer = serialize;
    }

    @Override
    protected List<Resume> getListStorage() {
        List<Resume> listResumes = new ArrayList<>();
        File[] listFiles = directory.listFiles();
        if (listFiles != null) {
            for (File f : listFiles) {
                try {
                    listResumes.add(serializer.doRead(new BufferedInputStream(new FileInputStream(f))));
                } catch (IOException e) {
                    throw new StorageException("File read error", f.getName(), e);
                }
            }
        }
        return listResumes;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void insertToStorage(Resume resume, File file) {
        try {
            file.createNewFile();
            serializer.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Don't create file ", file.getName(), e);
        }
    }

    @Override
    protected void updateOnStorage(Resume resume, File file) {
        try {
            serializer.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", file.getName(), e);
        }
    }

    @Override
    protected void removeFromStorage(File file) {
        if (!file.delete()) {
            throw new StorageException( "File not delete", file.getName());
        }
    }

    @Override
    protected Resume getFromStorage(File file) {
        try {
            return serializer.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    public void clear() {
        File[] listFiles = directory.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                removeFromStorage(file);
            }
        }

    }

    @Override
    public int size() {
        String[] list = directory.list();
        return list != null ? list.length : 0;
    }
}
