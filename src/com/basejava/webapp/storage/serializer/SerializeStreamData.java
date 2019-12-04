package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SerializeStreamData implements SerializeStrategy {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeSection(dos, contacts.entrySet(), el -> {
                dos.writeUTF(el.getKey().name());
                dos.writeUTF(el.getValue());
            });

            Map<SectionType, AbstractSection> sections = r.getSections();
            writeSection(dos, sections.entrySet(), el -> {
                SectionType type = el.getKey();
                AbstractSection value = el.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((SimpleSection)value).getDescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> items = (((ListSection)value).getList());
                        writeSection(dos, items, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = (((OrganizationSection)value).getList());
                        writeSection(dos, organizations, org -> {
                            dos.writeUTF(org.getLink().getUrl() != null ? org.getLink().getUrl() : "");
                            dos.writeUTF(org.getLink().getName());
                            List<Position> positions = org.getPositions();
                            writeSection(dos, positions, pos -> {
                                dos.writeUTF(pos.getStart().toString());
                                dos.writeUTF(pos.getFinish().toString());
                                dos.writeUTF(pos.getTitle());
                                dos.writeUTF(pos.getDescription() != null ? pos.getDescription() : "");
                            });
                        });
                    default:
                }
            });
        }
    }

    private interface Function<T> {
        void apply(T t) throws IOException;
    }

    private <T> void writeSection(DataOutputStream dos, Collection<T> col, Function<T> writer) throws IOException {
        dos.writeInt(col.size());
        for (T item : col) {
            writer.apply(item);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readElements(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            readElements(dis, () -> {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(type, new SimpleSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(type, new ListSection(readCollection(dis, new ArrayList<>(), dis::readUTF)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = readCollection(dis, new ArrayList<>(), () -> {
                            String url = dis.readUTF();
                            url = url.equals("") ? null : url;
                            String name = dis.readUTF();
                            List<Position> positions = readCollection(dis, new ArrayList<>(), () -> {
                                YearMonth start = YearMonth.parse(dis.readUTF());
                                YearMonth finish = YearMonth.parse(dis.readUTF());
                                String title = dis.readUTF();
                                String description = dis.readUTF();
                                description = description.equals("") ? null : description;
                                return new Position(start, finish, title, description);
                            });
                            return new Organization(new Link(name, url), positions);
                        });
                        resume.addSection(type, new OrganizationSection(organizations));
                    default:
                }
            });
            return resume;
        }
    }

    private interface ReadFunction<T> {
        T apply() throws IOException;
    }

    private <T> List<T> readCollection(DataInputStream dis, List<T> list, ReadFunction<T> reader) throws IOException {
        int itemNumbers = dis.readInt();
        for (int i = 0; i < itemNumbers; i++) {
            list.add(reader.apply());
        }
        return list;
    }

    private interface ReadFunctionMethod {
        void apply() throws IOException;
    }

    private void readElements(DataInputStream dis, ReadFunctionMethod method) throws IOException  {
        int itemNumbers = dis.readInt();
        for (int i = 0; i < itemNumbers; i++) {
            method.apply();
        }
    }

    @Override
    public String getFileName(String uuid) {
        return uuid + ".data";
    }
}
