package org.primefaces.extensions.integrationtests.datatable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProgrammingLanguage {
    private int id;
    private String name;
    private int firstAppeared;
}
