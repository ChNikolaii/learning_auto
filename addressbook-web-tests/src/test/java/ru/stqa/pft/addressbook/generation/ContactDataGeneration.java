package ru.stqa.pft.addressbook.generation;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGeneration {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGeneration generator = new ContactDataGeneration();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }

        generator.run();



    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        }else if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        }else {
            System.out.println("Unrecognized format " + format);
        }

    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private  void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(),
                        contact.getMiddlename(), contact.getLastname(), contact.getNickname(),
                        contact.getCompany(), contact.getTitle(), contact.getAddress(),
                        contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getFax(),
                        contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
        }
        }
    }

    private  List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withLastname(String.format("lastname %s", i))
                    .withFirstname(String.format("firstname %s", i)).withMiddlename(String.format("middlename %s", i))
                    .withNickname(String.format("nickname %s", i)).withCompany(String.format("company %s", i))
                    .withTitle(String.format("title %s", i)).withAddress(String.format("address %s", i))
                    .withHomePhone(String.format("home %s", i)).withMobilePhone(String.format("mobile %s",i))
                    .withWorkPhone(String.format("work %s", i)).withFax(String.format("fax %s", i)).
                    withEmail(String.format("email %s", i)).withEmail2(String.format("email2 %s", i)).withEmail3(String.format("email3 %s", i)));
        }
        return contacts;
    }
}
