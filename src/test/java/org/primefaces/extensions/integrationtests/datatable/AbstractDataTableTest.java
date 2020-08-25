package org.primefaces.extensions.integrationtests.datatable;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.HasCapabilities;
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

    protected void logWebDriverCapabilites(DataTable003Test.Page page) {
        if (page.getWebDriver() instanceof HasCapabilities) {
            HasCapabilities hasCaps = (HasCapabilities) page.getWebDriver();
            System.out.println("BrowserName: " + hasCaps.getCapabilities().getBrowserName());
            System.out.println("Version: " + hasCaps.getCapabilities().getVersion());
            System.out.println("Platform: " + hasCaps.getCapabilities().getPlatform());
        }
        else {
            System.out.println("WebDriver does not implement HasCapabilities --> can´t show version etc");
        }
    }
}
