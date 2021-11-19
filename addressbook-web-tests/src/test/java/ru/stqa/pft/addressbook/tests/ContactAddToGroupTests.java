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

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().creat(new ContactData().withFirstname("Kolya")
                    .withMiddlename("Sergeevich").withLastname("Cherentaev").withNickname("nikolaii")
                    .withCompany("world").withTitle("hello").withAddress("Balakhna city").withHomePhone("16")
                    .withMobilePhone("123456").withWorkPhone("qa").withFax("123-456").withEmail("email")
                    .withEmail2("email2").withEmail3("email3"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().creat(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testContactAddToGroup() {
        Groups groups = app.db().groups();
        Contacts beforeContact = app.db().contacts();
        ContactData selectedContact = beforeContact.iterator().next();
        if (selectedContact.getGroups().size() == groups.size()) {
            app.goTo().groupPage();
            app.group().creat(new GroupData().withName("test2"));
            groups = app.db().groups();
        }
        for (GroupData group : groups) {
            if (!selectedContact.getGroups().contains(group)) {
                app.goTo().homePage();
                app.contact().addToGroup(selectedContact, group);
                app.contact().selectGroupById(group.getId());
                app.contact().addToGroup(selectedContact, group);
                assertThat(selectedContact.getGroups().withAdded(group), equalTo(app.db().contacts().stream().
                        filter((c) -> c.getId() == selectedContact.getId()).collect(Collectors.toList()).get(0).getGroups()));
                return;
            }
        }
    }
}


