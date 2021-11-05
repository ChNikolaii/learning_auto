package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificatedTest extends TestBase{
    @BeforeMethod
            public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().creat(new ContactData()
                    .withFirstname("Kolya").withMiddlename("Sergeevich")
                    .withLastname("Cherentaev").withNickname("nikolaii").withCompany("world")
                    .withTitle("hello").withAddress("Balakhna city").withHomePhone("16")
                    .withMobilePhone("123456").withWorkPhone("qa").withFax("123-456").withEmail("email")
                    .withEmail2("email2").withEmail3("email3"));
        }

    }
    @Test
    public void testContactModificated() {

        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Kolya")
                .withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii")
                .withCompany("world").withTitle("hello").withAddress("Balakhna city").withHomePhone("16")
                .withMobilePhone("123456").withWorkPhone("qa").withFax("123-456").withEmail("email")
                .withEmail2("email2").withEmail3("email3");
        app.goTo().homePage();
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());


        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}
