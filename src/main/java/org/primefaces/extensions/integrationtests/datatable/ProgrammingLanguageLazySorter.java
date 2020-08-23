package org.primefaces.extensions.integrationtests.datatable;

import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.lang.reflect.Field;
import java.util.Comparator;

public class ProgrammingLanguageLazySorter implements Comparator<ProgrammingLanguage> {

    private String sortField;

    private SortOrder sortOrder;

    public ProgrammingLanguageLazySorter(SortMeta sortMeta) {
        this.sortField = sortMeta.getSortField();
        this.sortOrder = sortMeta.getSortOrder();
    }

    public ProgrammingLanguageLazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(ProgrammingLanguage lang1, ProgrammingLanguage lang2) {
        try {
            Field field = getLangField(this.sortField);
            Object value1 = field.get(lang1);
            Object value2 = field.get(lang2);

            int value = ((Comparable)value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }

    private Field getLangField(String name) throws NoSuchFieldException {
        Field field = ProgrammingLanguage.class.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }
}
