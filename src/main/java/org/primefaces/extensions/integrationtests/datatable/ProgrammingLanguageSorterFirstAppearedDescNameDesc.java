package org.primefaces.extensions.integrationtests.datatable;

import java.util.Comparator;

public class ProgrammingLanguageSorterFirstAppearedDescNameDesc implements Comparator<ProgrammingLanguage> {

    public int compare(ProgrammingLanguage lang1, ProgrammingLanguage lang2) {
        try {
            if (lang1.getFirstAppeared() == lang2.getFirstAppeared()) {
                return lang2.getName().compareTo(lang1.getName());
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
