package org.primefaces.extensions.integrationtests.datatable;

import java.util.Comparator;

public class ProgrammingLanguageSorterFirstAppearedDescNameDesc implements Comparator<ProgrammingLanguage> {
 
    public int compare(ProgrammingLanguage lang1, ProgrammingLanguage lang2) {
        try {
            if (lang1.getFirstAppeared().equals(lang2.getFirstAppeared())) {
                return lang2.getName().compareTo(lang1.getName());
            }
            else {
                return lang2.getFirstAppeared().compareTo(lang1.getFirstAppeared());
            }
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
