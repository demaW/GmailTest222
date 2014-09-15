package com.epam.gmail.business;

import com.epam.gmail.objects.GmailHomePage;

public class GmailHomePageBO {
    private GmailHomePage gmailHomePage;

    public void login(String username, String password) {
        gmailHomePage = new GmailHomePage();
        gmailHomePage.insertUsername(username);
        gmailHomePage.insertPass(password);
        gmailHomePage.chechkSingedIn();
        gmailHomePage.login();
    }
}
