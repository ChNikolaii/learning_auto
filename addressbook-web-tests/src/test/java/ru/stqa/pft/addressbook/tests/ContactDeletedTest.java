package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletedTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().creat(new ContactData().withFirstname("Kolya").withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii").withCompany("world").withTitle("hello").withAddress("Balakhna city").withHome("16").withMobile("123456").withWork("qa").withFax("123-456"));
        }
    }

    @Test
    public void testContactDeleted() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);


    }


}
