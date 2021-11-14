package ru.stqa.ptf.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.ptf.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class TestBase {

    protected final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", CHROME));

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }


}
