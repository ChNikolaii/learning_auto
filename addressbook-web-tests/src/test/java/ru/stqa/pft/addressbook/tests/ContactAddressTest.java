package ru.stqa.pft.addressbook.tests;

import io.netty.util.internal.ConstantTimeUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactAddressTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().creat(new ContactData().withFirstname("Kolya")
                    .withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii")
                    .withCompany("world").withTitle("hello").withAddress("Balakhna city")
                    .withHomePhone("16").withMobilePhone("123456").withWorkPhone("qa").withFax("123-456"));
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));


    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress()).stream().filter((s) -> ! s.equals(""))

                .collect(Collectors.joining("\n"));
    }

}

