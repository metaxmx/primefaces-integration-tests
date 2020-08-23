package org.primefaces.extensions.integrationtests.datatable;

import java.util.Comparator;

public class ProgrammingLanguageSorterFirstAppearedDescNameAsc implements Comparator<ProgrammingLanguage> {

    public int compare(ProgrammingLanguage lang1, ProgrammingLanguage lang2) {
        try {
            if (lang1.getFirstAppeared() == lang2.getFirstAppeared()) {
                return lang1.getName().compareTo(lang2.getName());
            }
            else if (lang1.getFirstAppeared() < lang2.getFirstAppeared()) {
                return 1;
            }
            else return -1;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
