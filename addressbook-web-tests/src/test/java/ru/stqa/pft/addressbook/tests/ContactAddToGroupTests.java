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

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().returnHomePage();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().creat(new GroupData().withName("test2"));
        }
        if (!app.contact().isThereAContact()) {
            app.contact().creat(new ContactData()
                    .withFirstname("Kolya").withMiddlename("Sergeevich")
                    .withLastname("Cherentaev").withNickname("nikolaii").withCompany("world")
                    .withTitle("hello").withAddress("Balakhna city").withHomePhone("16")
                    .withMobilePhone("123456").withWorkPhone("qa").withFax("123-456").withEmail("email")
                    .withEmail2("email2").withEmail3("email3"));
            app.contact().returnHomePage();
        }
    }

    @Test
    public void testContactAddToGroup() {
        Groups allGroups = app.db().groups();
        Contacts allContacts = app.db().contacts();
        Contacts contactsForAdding = new Contacts();
        GroupData selectedGroup;
        for (ContactData contactData : allContacts) {
            Groups groups = contactData.getGroups();
            if (groups.size() < allGroups.size()) {
                contactsForAdding.add(contactData);
            }
        }
        if (contactsForAdding.size() == 0) {
            GroupData newGroup = new GroupData().withName("test2");
            app.goTo().groupPage();
            app.group().creat(newGroup);
            allGroups = app.db().groups();
            contactsForAdding = allContacts;
            app.goTo().groupPage();
        }
        ContactData addedContactToGroup = contactsForAdding.iterator().next();
        selectedGroup = app.contact().findGroupForAdding(addedContactToGroup, allGroups).iterator().next();
        app.contact().addInGroup(addedContactToGroup, selectedGroup);
        app.contact().returnHomePage();
        app.contact().selectGroupList(selectedGroup);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), allContacts.size());
        for (ContactData contact : after) {
            if (contact.getId() == addedContactToGroup.getId()) {
                assertThat(addedContactToGroup.getGroups().withAdded(selectedGroup), equalTo(contact.getGroups()));
            }
        }
    }
}


