package ru.stqa.pft.addressbook.model;

public class GroupData {
    private String name;
    private String header;
    private String footer;
    private GroupData group;

    public GroupData(String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public GroupData(GroupData group) {

        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

}
