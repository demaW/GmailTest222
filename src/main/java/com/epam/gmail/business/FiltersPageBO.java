package com.epam.gmail.business;

import com.epam.gmail.objects.FiltersPage;

public class FiltersPageBO {
    FiltersPage filtersPage;

    public void createNewFilter(String username) {
        filtersPage = new FiltersPage();
        filtersPage.clickNewFilter();
        filtersPage.setFromInput(username);
        filtersPage.checkHasAttachment();
        filtersPage.clickCreateFilter();
    }

    public void setFiltersMatchers(){
        filtersPage.checkDeletedCheckbox();
        filtersPage.checkAlwaysMarkAsImportantCheckbox();
        filtersPage.clickCreateFilterButton();
        filtersPage.waitUntilFilter();
    }
}
