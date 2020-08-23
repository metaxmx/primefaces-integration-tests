package org.primefaces.extensions.integrationtests.datatable;

import org.junit.jupiter.api.Assertions;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.component.DataTable;
import org.primefaces.extensions.selenium.component.model.datatable.Row;

import java.util.List;

public abstract class AbstractDataTableTest extends AbstractPrimePageTest {
    protected void assertRows(DataTable dataTable, List<ProgrammingLanguage> langs) {
        List<Row> rows = dataTable.getRows();
        Assertions.assertNotNull(rows);
        Assertions.assertEquals(langs.size(), rows.size());

        int row = 0;
        for (ProgrammingLanguage programmingLanguage : langs) {
            Assertions.assertEquals(programmingLanguage.getId(), Integer.parseInt(rows.get(row).getCell(0).getText()));
            row++;
        }
    }
}
