package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletedTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().creat(new ContactData().withFirstname("Kolya").withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii").withCompany("world").withTitle("hello").withAddress("Balakhna city").withHomePhone("16").withMobilePhone("123456").withWorkPhone("qa").withFax("123-456"));
        }
    }

    @Test
    public void testContactDeleted() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.without(deletedContact)));



    }


}
