package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Comparator;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactModificatedTest extends TestBase{
    @BeforeMethod
            public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().creat(new ContactData().withFirstname("Kolya").withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii").withCompany("world").withTitle("hello").withAddress("Balakhna city").withHome("16").withMobile("123456").withWork("qa").withFax("123-456"));
        }
    }
    @Test
    public void testContactModificated() {

        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Kolya").withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii").withCompany("world").withTitle("hello").withAddress("Balakhna city").withHome("16").withMobile("123456").withWork("qa").withFax("123-456");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
