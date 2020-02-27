package ru.job4j.magnit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser extends DefaultHandler {

    int sum = 0;
    int count = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("entry")) {
            sum += Integer.parseInt(attributes.getValue(0));
            count += 1;
        }
    }

    public double getAvg() {
        return sum/count;
    }
}
