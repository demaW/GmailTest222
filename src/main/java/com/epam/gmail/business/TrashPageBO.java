package com.epam.gmail.business;

import com.epam.gmail.objects.TrashPage;

public class TrashPageBO extends IPageBO{
    private TrashPage trashPage;

    public boolean verifyEmailPresence(String subject){
        trashPage = new TrashPage();
        boolean result = checkEmailPresence(trashPage.getSubjects(), subject);
        return result;
    }


}
