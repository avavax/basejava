package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SerializeStreamData implements SerializeStrategy {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements sections
            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().toString());
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((SimpleSection)entry.getValue()).getDescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> items = (((ListSection)entry.getValue()).getList());
                        dos.writeInt(items.size());
                        for (String item : items) {
                            dos.writeUTF(item);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> orgs = (((OrganizationSection)entry.getValue()).getList());
                        dos.writeInt(orgs.size());
                        for (Organization org : orgs) {
                            dos.writeUTF(org.getLink().getUrl());
                            dos.writeUTF(org.getLink().getName());
                            List<Position> poses = org.getPositions();
                            dos.writeInt(poses.size());
                            for (Position pos : poses) {
                                dos.writeUTF(pos.getStart().toString());
                                dos.writeUTF(pos.getFinish().toString());
                                dos.writeUTF(pos.getTitle());
                                dos.writeUTF(pos.getDescription() != null ? pos.getDescription() : "null");
                            }
                        }
                    default:
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections
            int numbersSection = dis.readInt();
            for (int i = 0; i < numbersSection; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(type, new SimpleSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int stringNumber = dis.readInt();
                        List<String> items = new ArrayList<>();
                        for (int j = 0; j < stringNumber; j++) {
                            items.add(dis.readUTF());
                        }
                        resume.addSection(type, new ListSection(items));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int organizationNumber = dis.readInt();
                        List<Organization> orgs = new ArrayList<>();
                        for (int j = 0; j < organizationNumber; j++) {
                            String url = dis.readUTF();
                            String name = dis.readUTF();
                            List<Position> positions = new ArrayList<>();
                            int posesNumbers = dis.readInt();
                            for (int k = 0; k < posesNumbers; k++) {
                                YearMonth start = YearMonth.parse(dis.readUTF());
                                YearMonth finish = YearMonth.parse(dis.readUTF());
                                String title = dis.readUTF();
                                String description = dis.readUTF();
                                description = description.equals("null") ? null : description;
                                positions.add(new Position(start, finish, title, description));
                            }
                            orgs.add(new Organization(new Link(name, url), positions));
                        }
                        resume.addSection(type, new OrganizationSection(orgs));
                    default:
                }
            }
            return resume;
        }
    }
}
