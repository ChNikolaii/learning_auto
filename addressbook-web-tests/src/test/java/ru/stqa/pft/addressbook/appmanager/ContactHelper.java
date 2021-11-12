package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {

        super(wd);
    }


    public void returnHomePage() {
        click(By.linkText("home"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }


    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());

        // attach(By.name("photo"), contactData.getPhoto());
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }
    public void addToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        selectGroupById(group.getId());
        confirmAdding();
        goToHomePage();
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        selectDisplayGroup(group.getName());
        selectContactById(contact.getId());
        removeFromGroup(group.getName());
    }

    public void selectDisplayGroup(String name) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(name);
    }
    private void removeFromGroup(String name) {
        click(By.name("remove"));
    }

    public void deleteFromGroup(ContactData contact, GroupData group) {
        sortContactFromGroup(group.getId());
        selectContactById(contact.getId());
        confirmRemoving();
        goToHomePage();
    }
    private void selectGroupById(int id) {

        click(By.cssSelector("select[name=\"to_group\"] > option[value='" + id + "']"));
    }

    public void sortContactFromGroup(int id) {
        click(By.cssSelector("select[name=\"group\"] > option[value='" + id + "']"));
    }

    private void goToHomePage() {
        click(By.linkText("home"));
    }

    private void confirmAdding() {
        click(By.xpath("//input[@value='Add to']"));
    }

    private void confirmRemoving() {
        click(By.name("remove"));
    }

    public void showAllGroups() {
        click(By.cssSelector("select[name=\"group\"] > option[value='']"));
    }


    public void initNewContact() {
        click(By.linkText("add new"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deletedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }


    public void initContactModif() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModif() {
        click(By.xpath("//input[22]"));
    }

    public void acceptAlert() {

        wd.switchTo().alert().accept();

    }

    public void creat(ContactData contact) {
        initNewContact();
        fillContactForm(contact, true);
        submitContactCreation();
        returnHomePage();
    }
    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactForm(contact, true);
        submitContactModif();
        returnHomePage();

    }
    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deletedContact();
        acceptAlert();
        returnHomePage();

    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//td/input"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> listRows = wd.findElements(By.name("entry"));
        for (WebElement row : listRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String address = cells.get(3).getText();
            String allEmail = cells.get(4).getText();
            int id = Integer.parseInt(row.findElement(By.xpath("./td/input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones).withAddress(address).withAllEmail(allEmail));
        }

        return contacts;
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname)
                .withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);

    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }
}
