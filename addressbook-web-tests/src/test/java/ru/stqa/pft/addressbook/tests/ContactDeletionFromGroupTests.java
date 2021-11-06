package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
            if (app.db().contacts().size() == 0) {
                app.goTo().homePage();
                app.contact().creat(new ContactData()
                        .withFirstname("Kolya").withMiddlename("Sergeevich")
                        .withLastname("Cherentaev").withNickname("nikolaii").withCompany("world")
                        .withTitle("hello").withAddress("Balakhna city").withHomePhone("16")
                        .withMobilePhone("123456").withWorkPhone("qa").withFax("123-456"));
            }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().creat(new GroupData().withName("test2"));
        }
        if (app.db().contacts().iterator().next().getGroups().size() == 0) {
            Groups groups = app.db().groups();
            GroupData selectedGroup = groups.iterator().next();
            Contacts contacts = app.db().contacts();
            ContactData selectedContact = contacts.iterator().next();
            app.contact().addToGroup(selectedContact, selectedGroup);
        }
    }

    @Test
    public void testContactDeletingFromGroup() {
        Contacts beforeContact = app.db().contacts();
        Groups beforeGroups = app.db().groups();
        ContactData selectedContact = beforeContact.iterator().next();
        GroupData selectedGroup = beforeGroups.iterator().next();
        app.contact().deleteFromGroup(selectedContact, selectedGroup);
        Contacts afterContact = app.db().contacts();
        assertThat(afterContact.iterator().next().getGroups(), equalTo(beforeContact.iterator().next().getGroups().without(selectedGroup)));
        app.contact().showAllGroups();
    }
}