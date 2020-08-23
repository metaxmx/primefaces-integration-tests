package org.primefaces.extensions.integrationtests.datatable;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.MatchMode;
import org.primefaces.model.SortMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProgrammingLanguageLazyDataModel extends LazyDataModel<ProgrammingLanguage> {

    private List<ProgrammingLanguage> langs;

    public ProgrammingLanguageLazyDataModel() {
        langs = new ArrayList<>();
        for (int i = 1; i<=75; i++) {
            langs.add(new ProgrammingLanguage(i, "Language " + i, 1990 + (i % 10)));
        }
    }

    @Override
    public List<ProgrammingLanguage> load(int first, int pageSize, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
        Stream<ProgrammingLanguage> langsStream = langs.stream();

        if (filterMeta != null && !filterMeta.isEmpty()) {
            for (FilterMeta meta : filterMeta.values()) {
                if (meta.getFilterValue()!=null) {
                    langsStream = langsStream.filter(lang -> {
                        if (meta.getFilterField().equals("firstAppeared") && meta.getFilterMatchMode()==MatchMode.GREATER_THAN_EQUALS) {
                            int filterValueInt = Integer.parseInt((String)meta.getFilterValue());
                            return (int)lang.getFirstAppeared() >= filterValueInt;
                        }
                        return true; //TODO: add additional implementation when required
                    });
                }
            }
        }

        if (sortMeta != null && !sortMeta.isEmpty()) {
            for (SortMeta meta : sortMeta.values()) {
                langsStream = langsStream.sorted(new ProgrammingLanguageLazySorter(meta));
            }
        }

        return langsStream
                .skip(first).limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public int getRowCount() {
        return langs.size();
    }

    @Override
    public ProgrammingLanguage getRowData(String rowKey) {
        int rowKeyNumeric = Integer.parseInt(rowKey);
        return langs.stream().filter(lang -> lang.getId()==rowKeyNumeric).findFirst().get();
    }

    @Override
    public Object getRowKey(ProgrammingLanguage object) {
        return object.getId();
    }

    public List<ProgrammingLanguage> getLangs() {
        return langs;
    }
}
