package ru.job4j.magnit;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConvertXSQT {

    public static void convert(Path source, File dest, Path scheme) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
                new StreamSource(
                        new ByteArrayInputStream(Files.readAllBytes(scheme)))
        );
        transformer.transform(new StreamSource(
                        new ByteArrayInputStream(Files.readAllBytes(source))),
                new StreamResult(dest)
        );

        avg(dest);
    }

    private static void avg(File input) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxParser saxp = new SaxParser();
        parser.parse(input, saxp);
        System.out.println(saxp.getAvg());
    }

    public static void main(String[] args) throws Exception {

        Config config = new Config();
        config.init();
        StoreSQL storeSql = new StoreSQL(config);
        storeSql.generate(3);

        StoreXML storeXML = new StoreXML(new File("C:\\sqlite\\db\\entry.xml"));
        storeXML.save(storeSql.load());

        ConvertXSQT.convert(Paths.get("C:\\sqlite\\db\\entry.xml"),
                            new File("C:\\sqlite\\db\\entryXSQT.xml"),
                            Paths.get("junior_chapter_3\\src\\main\\resources\\scheme.xstl"));

    }
}
