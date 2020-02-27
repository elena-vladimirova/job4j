package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

public class StoreXML {

    private File out;

    public StoreXML(File out) {
        this.out = out;
    }

    public void save(List<Entry> list) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(
                new Entries(list),
                out
        );

    }

    @XmlRootElement
    public static class Entries {

        private List<Entry> entry;

        public Entries() {
        }

        public Entries(List<Entry> field) {
            this.entry = field;
        }

        public List<Entry> getEntry() {
            return entry;
        }

        public void setEntry(List<Entry> entry) {
            this.entry = entry;
        }
    }

    @XmlRootElement
    public static class Entry {

        private int field;

        public Entry() {
        }

        Entry(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }
    }
}
