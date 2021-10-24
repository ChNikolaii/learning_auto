package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().creat(new ContactData().withFirstname("Kolya")
                    .withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii")
                    .withCompany("world").withTitle("hello").withAddress("Balakhna city").withEmail("123@123")
                    .withHomePhone("16").withMobilePhone("123456").withWorkPhone("qa").withFax("123-456"));
        }
    }

    @Test
    public void testContactEmail() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmail(), equalTo(contactInfoFromEditForm.getAllEmail()));

    }


}
