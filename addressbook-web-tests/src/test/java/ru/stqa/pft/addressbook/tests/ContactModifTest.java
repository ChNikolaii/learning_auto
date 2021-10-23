package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Comparator;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactModifTest extends TestBase{
    @Test
    public void testContactModif() {
        app.goTo().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().creatContact(new ContactData("Kolya", "Sergeevich", "Cherentaev", "nikolaii", "hello", "world", "Balakhna city", "16", "123456", "qa", "123-456"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactMofid();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Kolya", "Sergeevich", "Cherentaev", "nikolaii", "hello", "world", "Balakhna city", "16", "123456", "qa", "123-456");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModif();
        app.getContactHelper().returnHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
