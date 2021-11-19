package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

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
        if (beforeGroups.size() == 0) {
            app.goTo().groupPage();
            app.group().creat(new GroupData().withName("test2"));
            beforeGroups = app.db().groups();
        }
        GroupData selectedGroup = beforeGroups.iterator().next();
        app.goTo().homePage();
        app.contact().sortContactFromGroup(selectedGroup.getId());
        app.goTo().homePage();
        if (selectedGroup.getContacts().size() == 0) {
            app.goTo().homePage();
            if (app.contact().getContactCount() == 0){
                app.contact().creat(new ContactData().withFirstname("Kolya")
                        .withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii")
                        .withCompany("world").withTitle("hello").withAddress("Balakhna city").withHomePhone("16")
                        .withMobilePhone("123456").withWorkPhone("qa").withFax("123-456").withEmail("email")
                        .withEmail2("email2").withEmail3("email3"));
            }
            app.goTo().homePage();
            ContactData selectedContact = beforeContact.iterator().next();
            app.contact().addToGroup(selectedContact, selectedGroup);
        }
        app.goTo().homePage();
        ContactData selectedContact = beforeContact.iterator().next();
        app.contact().selectGroupById(selectedGroup.getId());
        app.contact().deleteFromGroup(selectedContact, selectedGroup);
        app.goTo().homePage();
        assertThat(selectedContact.getGroups().without(selectedGroup), equalTo(app.db().contacts().stream().
                filter((c) -> c.getId() == selectedContact.getId()).collect(Collectors.toList()).get(0).getGroups()));

    }
}