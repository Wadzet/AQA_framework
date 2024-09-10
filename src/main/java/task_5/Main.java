package task_5;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();

        Person personFromJson = jsonMapper.readValue(new File("src/main/resources/task_5/input.json"), Person.class);
        System.out.println("Deserialized JSON from input.json: " + personFromJson);

        personFromJson.setName("Updated from JSON");
        personFromJson.getAddress().setCity("Seattle");

        jsonMapper.writeValue(new File("src/main/resources/task_5/output.json"), personFromJson);
        xmlMapper.writeValue(new File("src/main/resources/task_5/output.json.xml"), personFromJson);

        Person personFromXml = xmlMapper.readValue(new File("src/main/resources/task_5/input.xml"), Person.class);
        System.out.println("Deserialized XML from input.xml: " + personFromXml);

        personFromXml.setName("Updated from XML");
        personFromXml.getAddress().setState("WA");

        jsonMapper.writeValue(new File("src/main/resources/task_5/output_from_xml.json"), personFromXml);
        xmlMapper.writeValue(new File("src/main/resources/task_5/output_from_xml.xml"), personFromXml);
    }
}
