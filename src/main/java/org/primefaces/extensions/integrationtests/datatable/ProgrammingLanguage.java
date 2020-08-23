package org.primefaces.extensions.integrationtests.datatable;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProgrammingLanguage implements Serializable {
    private static final long serialVersionUID = 398626647627541586L;
    private int id;
    private String name;
    private int firstAppeared;
}
