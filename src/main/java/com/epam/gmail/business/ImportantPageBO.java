package com.epam.gmail.business;

import com.epam.gmail.objects.ImportantPage;

public class ImportantPageBO extends IPageBO {
    ImportantPage importantPage;

    public boolean verifyEmailPresence(String subject){
        importantPage = new ImportantPage();
        boolean result = importantPage.checkEmailPresenceImportant(importantPage.getSubjects(), subject);
        return result;
    }
}
