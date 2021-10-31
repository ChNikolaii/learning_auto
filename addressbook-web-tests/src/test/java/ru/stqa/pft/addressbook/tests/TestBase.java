package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.io.IOException;

import static org.openqa.selenium.remote.BrowserType.*;

public class TestBase {

    protected final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", CHROME));

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    public ApplicationManager getApp() {
        return app;
    }
}
