package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Kolya", "Sergeevich", "Cherentaev", "nikolaii", "hello", "world", "Balakhna city", "16", "123456", "qa", "123-456"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnHomePage();
  }

}
