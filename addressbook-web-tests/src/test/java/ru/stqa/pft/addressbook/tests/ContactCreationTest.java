package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;

import java.util.List;

public class ContactCreationTest extends TestBase {


    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstname("Kolya").withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii").withCompany("world").withTitle("hello").withAddress("Balakhna city").withHome("16").withMobile("123456").withWork("qa").withFax("123-456");
        app.contact().creat(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);


        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
