package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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


    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("fax"), contactData.getFax());
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
        fillContactForm(contact);
        submitContactCreation();
        returnHomePage();
    }
    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModif();
        fillContactForm(contact);
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

            int id = Integer.parseInt(row.findElement(By.xpath("./td/input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }

        return contacts;
    }


}
