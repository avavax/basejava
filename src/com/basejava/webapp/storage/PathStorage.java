package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.serializer.SerializeStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;

    private SerializeStrategy serializer;

    public PathStorage(String dir, SerializeStrategy serialize) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.serializer = serialize;
    }

    @Override
    protected List<Resume> getListStorage() {
        List<Resume> listResumes = new ArrayList<>();
        try {
            Files.list(directory).forEach(path -> {
                listResumes.add(getFromStorage(path));
            });
        } catch (IOException e) {
            throw new StorageException("File read error", null, e);
        }
        return listResumes;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(serializer.getFileName(uuid));
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void insertToStorage(Resume resume, Path path) {
        try {
            Files.createFile(path);
            serializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Don't create file ", resume.getUuid(), e);
        }
    }

    @Override
    protected void updateOnStorage(Resume resume, Path path) {
        try {
            serializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void removeFromStorage(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File write error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume getFromStorage(Path path) {
        try {
            return serializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File read error", path.getFileName().toString(), e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::removeFromStorage);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }

    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("I/O error", null, e);
        }
    }
}
