package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModifTest extends TestBase{
    @Test
    public void testContactModif() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactMofid();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanovich", "Ivanov", "Vano", "123", "qa", "NN", "10", "123456", "manual", "123-456"));
        app.getContactHelper().submitContactModif();
        app.getContactHelper().returnHomePage();
    }
}
