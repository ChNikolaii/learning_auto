package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().creat(new GroupData().withName("test2"));
        }
        if (!app.contact().isThereAContact()) {
            app.contact().creat(new ContactData()
                    .withFirstname("Kolya").withMiddlename("Sergeevich")
                    .withLastname("Cherentaev").withNickname("nikolaii").withCompany("world")
                    .withTitle("hello").withAddress("Balakhna city").withHomePhone("16")
                    .withMobilePhone("123456").withWorkPhone("qa").withFax("123-456"));
            app.contact().returnHomePage();
        }
    }

    @Test
    public void testContactDeletingFromGroup() {
        Groups allGroups = app.db().groups();
        Contacts allContacts = app.db().contacts();
        Contacts contactsForRemoving = new Contacts();
        GroupData selectedGroup;
        ContactData deletedContactFromGroup;
        for (ContactData contactData : allContacts) {
            Groups groups = contactData.getGroups();
            if (groups.size() >= 1) {
                contactsForRemoving.add(contactData);
            }
        }
        if (contactsForRemoving.size() == 0) {
            ContactData addContactInGroup = allContacts.iterator().next();
            selectedGroup = allGroups.iterator().next();app.contact().addInGroup(addContactInGroup, selectedGroup);
            app.contact().returnHomePage();
            contactsForRemoving.add(addContactInGroup.inGroup(selectedGroup));
        }
        deletedContactFromGroup = contactsForRemoving.iterator().next();
        selectedGroup = deletedContactFromGroup.getGroups().iterator().next();
        app.contact().selectGroupList(selectedGroup);
        app.contact().removeInGroup(deletedContactFromGroup, selectedGroup);
        app.contact().returnHomePage();
        app.contact().selectGroupList(selectedGroup);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), allContacts.size());
        for (ContactData contact : after) {
            if (contact.getId() == deletedContactFromGroup.getId()) {
                assertThat(deletedContactFromGroup.getGroups().without(selectedGroup), equalTo(contact.getGroups()));
            }
        }
    }
}