package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.TestBase;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.gotoGroupPage();
        app.selectGroup();
        app.deleteSelectedGroups();
        app.returnGroupPage();
    }

}
