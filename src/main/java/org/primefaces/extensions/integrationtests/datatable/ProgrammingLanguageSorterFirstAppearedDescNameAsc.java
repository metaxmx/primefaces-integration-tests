package org.primefaces.extensions.integrationtests.datatable;

import java.util.Comparator;

public class ProgrammingLanguageSorterFirstAppearedDescNameAsc implements Comparator<ProgrammingLanguage> {

    @Override public int compare(ProgrammingLanguage lang1, ProgrammingLanguage lang2) {
        try {
            if (lang1.getFirstAppeared().equals(lang2.getFirstAppeared())) {
                return lang1.getName().compareTo(lang2.getName());
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
