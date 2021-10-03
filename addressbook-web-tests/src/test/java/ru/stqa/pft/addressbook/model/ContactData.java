package ru.stqa.pft.addressbook.model;

public class ContactData {
    public final String firstname;
    public final String middlename;
    public final String lastname;
    public final String nickname;
    public final String title;
    public final String company;
    public final String address;
    public final String home;
    public final String mobile;
    public final String work;
    public final String fax;

    public ContactData(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String home, String mobile, String work, String fax) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.fax = fax;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getFax() {
        return fax;
    }
}
