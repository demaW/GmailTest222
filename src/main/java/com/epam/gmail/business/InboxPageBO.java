package com.epam.gmail.business;

import com.epam.gmail.objects.InboxPage;

public class InboxPageBO extends IPageBO {
    InboxPage inboxPage;

    public void confirmForward() {
        inboxPage = new InboxPage();
        inboxPage.openMessage();
        inboxPage.clickVerfifcationLink();
        inboxPage.closeSuccessWindow();
        inboxPage.navigateInbox();
    }

    public void writeMessageWithAttachToUser(String userEmail, String subject) {
        inboxPage = new InboxPage();
        navigateInbox();
        inboxPage.clickComposeButton();
        inboxPage.fillSendToField(userEmail);
        inboxPage.clickAttachFiles();
        inboxPage.attachFile();
        inboxPage.writeSubjectMessage(subject);
        inboxPage.clickSendButton();
        inboxPage.waitMessageSend();
        navigateInbox();
    }

    public void writeMessageToUser(String userEmail, String subject) {
        inboxPage = new InboxPage();
        navigateInbox();
        inboxPage.clickComposeButton();
        inboxPage.fillSendToField(userEmail);
        inboxPage.writeSubjectMessage(subject);
        inboxPage.clickSendButton();
        inboxPage.waitMessageSend();
        navigateInbox();
    }

    public void navigateInbox() {
        inboxPage.navigateInbox();

        inboxPage.waitUntilInbox();
    }

    public boolean verifyEmailPresence(String subject) {
        inboxPage = new InboxPage();
        boolean result = checkEmailPresence(inboxPage.getSubjects(), subject);
        return result;
    }
}
