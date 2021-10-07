package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ContactDeletedTest extends TestBase {

    @Test
    public void testContactDeleted() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deletedContact();
        app.getContactHelper().acceptAlert();
    }

}
