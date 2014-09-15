package com.epam.gmail.business;

import com.epam.gmail.objects.SettingsPage;

public class SettingsPageBO extends IPageBO {
    SettingsPage settingsPage;

    public void  openForwarding(){
        settingsPage = new SettingsPage();
        settingsPage.clickForwarding();
    }
    public void setForwarding(String username){
        settingsPage = new SettingsPage();
        openForwarding();
        settingsPage.mailForwarding(username);
    }

    public void selectForwardCopyAction(){
        settingsPage.selectForwardCopyRadiobutton();
        settingsPage.clickSaveButton();
    }

    public void goToFiltersTab(){
        settingsPage.clickFilters();
    }
}
