package com.epam.gmail.business;

import com.epam.gmail.objects.GmailMainPage;
import com.epam.gmail.objects.ImportantPage;
import com.epam.gmail.objects.InboxPage;

public class GmailMainPageBO extends IPageBO {
    GmailMainPage gmailMainPage;

    public void openSettings() {
        gmailMainPage = new GmailMainPage();
        gmailMainPage.openSettings();
    }

    public void openSettingsWithWait() {
        gmailMainPage = new GmailMainPage();
        gmailMainPage.waitUntilInbox();
        gmailMainPage.openSettings();
    }

    public void logout(String email) {
        gmailMainPage = new GmailMainPage();
        gmailMainPage.logout(email);
    }

    public void navigateTrash() {
        gmailMainPage = new GmailMainPage();
        gmailMainPage.clickMore();
        gmailMainPage.clickTrashButton();
    }

    public void navigateImportant() {
        gmailMainPage = new GmailMainPage();
        ImportantPage importantPage = new ImportantPage();
        gmailMainPage.clickMore();
        gmailMainPage.clickImportantButton();
        importantPage.waitUntilImportant();
    }

    public void navigateInbox() {
        InboxPage inboxPage = new InboxPage();
        inboxPage.clickInbox();
        inboxPage.waitUntilInbox();
    }

    public void waintInbox() {
        gmailMainPage.waitUntilInbox();
    }
}
