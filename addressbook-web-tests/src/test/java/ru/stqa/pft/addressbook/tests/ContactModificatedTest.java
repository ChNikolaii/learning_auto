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
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().creat(new ContactData().withFirstname("Kolya").withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii").withCompany("world").withTitle("hello").withAddress("Balakhna city").withHomePhone("16").withMobilePhone("123456").withWorkPhone("qa").withFax("123-456"));
        }
    }
    @Test
    public void testContactModificated() {

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Kolya").withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii").withCompany("world").withTitle("hello").withAddress("Balakhna city").withHomePhone("16").withMobilePhone("123456").withWorkPhone("qa").withFax("123-456");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());


        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}
