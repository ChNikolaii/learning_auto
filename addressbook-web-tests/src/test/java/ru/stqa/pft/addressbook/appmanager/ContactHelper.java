package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
    public FirefoxDriver wd;

    public ContactHelper(FirefoxDriver wd) {

        super(wd);
    }


    public void returnHomePage() {
        click(By.linkText("home page"));
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

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deletedContact() {
        click(By.xpath("//form[2]/input[2]"));
    }


    public void initContactMofid() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModif() {
        click(By.xpath("//input[22]"));
    }
}
