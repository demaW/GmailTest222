package com.epam.gmail;

import com.epam.gmail.business.*;
import com.epam.gmail.utils.DriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GmailClassSend {
    private static String EMAIL_SUBJECT_ATTACHMENT = "test subject";
    private static String EMAIL_SUBJECT = "test subject";
    private static final String USERNAME1 = "testautomation1d@gmail.com";
    private static final String PASSWORD1 = "!234Qwer";
    private static final String USERNAME2 = "testautomation2d@gmail.com";
    private static final String PASSWORD2 = "!234Qwer";

    private GmailHomePageBO gmailHomeBO;
    private GmailMainPageBO gmailMainPageBO;
    private InboxPageBO inboxPageBO;


    @BeforeClass
    public void initDriver() {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        EMAIL_SUBJECT += (int) (Math.random() * 2000);
        EMAIL_SUBJECT_ATTACHMENT += (int) (Math.random() * 3000);
    }

    @AfterClass
    public void afterClass() {
        DriverManager.closeDriver();
    }

    @Test
    public void tsendGmailTest() {
        //Step 1: login user 1
        gmailHomeBO = new GmailHomePageBO();
        gmailHomeBO.login(USERNAME1, PASSWORD1);
        //Step 2: Send letter with no attach to user2
        inboxPageBO = new InboxPageBO();
        inboxPageBO.writeMessageToUser(USERNAME2, EMAIL_SUBJECT);
        //TO-DO Step 3: Asserts in sent

    }
}
