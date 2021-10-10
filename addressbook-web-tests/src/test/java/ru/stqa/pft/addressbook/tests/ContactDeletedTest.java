package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletedTest extends TestBase {

    @Test
    public void testContactDeleted() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().creatContact(new ContactData("Kolya", "Sergeevich", "Cherentaev", "nikolaii", "hello", "world", "Balakhna city", "16", "123456", "qa", "123-456"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deletedContact();
        app.getContactHelper().acceptAlert();
    }

}
