package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModifTest extends TestBase {
    @Test
    public void testGroupModif() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().creatGroup(new GroupData("test2", "test3", "test4"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModif();
        app.getGroupHelper().fillGroupForm(new GroupData("test2", "test3", "test4"));
        app.getGroupHelper().submitGroupModif();
        app.getGroupHelper().returnGroupPage();
    }

}
