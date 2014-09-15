package com.epam.gmail;

import com.epam.gmail.business.*;
import com.epam.gmail.utils.DriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GmailTest {
    private static String EMAIL_SUBJECT_ATTACHMENT = "test subject";
    private static String EMAIL_SUBJECT = "test subject";
    private static final String USERNAME1 = "testautomation1d@gmail.com";
    private static final String PASSWORD1 = "!234Qwer";
    private static final String USERNAME2 = "testautomation2d@gmail.com";
    private static final String PASSWORD2 = "!234Qwer";
    private static final String USERNAME3 = "testautomation3d@gmail.com";
    private static final String PASSWORD3 = "qwerty56";

    private GmailHomePageBO gmailHomeBO;
    private GmailMainPageBO gmailMainPageBO;
    private SettingsPageBO settingsPageBO;
    private InboxPageBO inboxPageBO;
    private FiltersPageBO filtersPage;
    private TrashPageBO trashPageBO;
    private ImportantPageBO  importantPageBO;
    @BeforeClass
    public void initDriver() {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        EMAIL_SUBJECT += (int) (Math.random()*2000);
        EMAIL_SUBJECT_ATTACHMENT += (int) (Math.random()*3000);
    }

    @AfterClass
    public void afterMethod() {
        DriverManager.closeDriver();
    }

    @Test
    public void gmailTest() {
        //Step 1: login user2
        gmailHomeBO = new GmailHomePageBO();
        gmailHomeBO.login(USERNAME2,PASSWORD2);
        //Step 2,3: open settings
        gmailMainPageBO = new GmailMainPageBO();
        gmailMainPageBO.openSettings();
        //Step 4,5: set Forwarding
        settingsPageBO = new SettingsPageBO();
        settingsPageBO.setForwarding(USERNAME3);
        gmailMainPageBO.logout(USERNAME2);
        //Step 6: login user3
        gmailHomeBO.login(USERNAME3,PASSWORD3);
        //Step 7: confirm forward
        inboxPageBO = new InboxPageBO();
        inboxPageBO.confirmForward();
        //Step 8: login user 2
        gmailMainPageBO.logout(USERNAME3);
        gmailHomeBO.login(USERNAME2,PASSWORD2);
        //Step 9: go to forward page
        gmailMainPageBO.openSettings();
        settingsPageBO.openForwarding();
        //Step 10: Choose radiobutton Forward a copy of incoming mail to
        settingsPageBO.selectForwardCopyAction();
        //Step 11: Choose Filters tab
        gmailMainPageBO.openSettingsWithWait();
        settingsPageBO.goToFiltersTab();
        //Step 12: Create new filter with settings
        filtersPage = new FiltersPageBO();
        filtersPage.createNewFilter(USERNAME1);
        filtersPage.setFiltersMatchers();
        //Step 13: login user 1
        gmailMainPageBO.logout(USERNAME2);
        gmailHomeBO.login(USERNAME1, PASSWORD1);
        //Step 14: Send letter with no attach to user2
        inboxPageBO.writeMessageWithAttachToUser(USERNAME2,EMAIL_SUBJECT_ATTACHMENT); // filepath: D:\fileToSend.txt
        //Step 15: Send letter with no attach to user2
        inboxPageBO.writeMessageToUser(USERNAME2,EMAIL_SUBJECT);
        //Step 16: login user 2
        gmailMainPageBO.navigateInbox();
        gmailMainPageBO.logout(USERNAME1);
        gmailHomeBO.login(USERNAME2, PASSWORD2);
        //Step 17: check letter from user 1 with attachment in Trash
        trashPageBO = new TrashPageBO();
        gmailMainPageBO.navigateTrash();
        Assert.assertTrue(trashPageBO.verifyEmailPresence(EMAIL_SUBJECT_ATTACHMENT), "message was not found in Trash");
        //Step 18: check letter from user1 without attachment in Inbox
        inboxPageBO.navigateInbox();
        Assert.assertTrue(inboxPageBO.verifyEmailPresence(EMAIL_SUBJECT), "message was not found in Inbox");
        importantPageBO = new ImportantPageBO();
        /*gmailMainPageBO.navigateImportant();
        Assert.assertTrue(importantPageBO.verifyEmailPresence(EMAIL_SUBJECT), "message was not found in Important");*/
        //Step 19: login user3
        gmailMainPageBO.navigateInbox();
        gmailMainPageBO.logout(USERNAME2);
        gmailHomeBO.login(USERNAME3,PASSWORD3);
        //Step 20: check that user letter from user1 in Inbox
        inboxPageBO.navigateInbox();
        Assert.assertTrue(inboxPageBO.verifyEmailPresence(EMAIL_SUBJECT), "message was not found in Inbox");
        gmailMainPageBO.navigateImportant();
        Assert.assertTrue(importantPageBO.verifyEmailPresence(EMAIL_SUBJECT), "message was not found in Important");
    }
}
