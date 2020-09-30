/*
 * Copyright 2011-2020 PrimeFaces Extensions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.extensions.integrationtests.datatable;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.PrimeExpectedConditions;
import org.primefaces.extensions.selenium.PrimeSelenium;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.DataTable;
import org.primefaces.extensions.selenium.component.model.data.Paginator;
import org.primefaces.extensions.selenium.component.model.datatable.Header;
import org.primefaces.extensions.selenium.component.model.datatable.Row;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataTable012Test extends AbstractDataTableTest {

    private final List<ProgrammingLanguage> langs = new ProgrammingLanguageService().getLangs();

    @Test
    @Order(1)
    @DisplayName("DataTable: single sort; sortBy on p:column; initial sort via sortBy on dataTable")
    public void testSortByWithDefault(Page page) {
        // Arrange
        DataTable dataTable = page.dataTable1;
        Assertions.assertNotNull(dataTable);

        // Act


        // Assert - ascending (initial)
        List<ProgrammingLanguage> langsSorted = langs.stream()
                    .sorted(Comparator.comparing(ProgrammingLanguage::getName))
                    .collect(Collectors.toList());
        assertRows(dataTable, langsSorted);

        // Act - descending
        dataTable.sort("Name");

        // Assert - descending
        Collections.reverse(langsSorted);
        assertRows(dataTable, langsSorted);

        assertConfiguration(dataTable.getWidgetConfiguration());
    }

    @Test
    @Order(2)
    @Disabled("p:column field=\"xx\" does not seem to work for sorting for PF 9-SNAPSHOT as of september 2020 (at least for single sort)")
    @DisplayName("DataTable: single sort; field on p:column; initial sort via sortBy on dataTable")
    public void testFieldWithDefault(Page page) {

        // Arrange
        DataTable dataTable = page.dataTable2;
        Assertions.assertNotNull(dataTable);

        // Act


        // Assert - ascending (initial)
        List<ProgrammingLanguage> langsSorted = langs.stream()
                .sorted(Comparator.comparing(ProgrammingLanguage::getName))
                .collect(Collectors.toList());
        assertRows(dataTable, langsSorted);

        // Act - descending
        //dataTable.sort("Name");
        PrimeSelenium.guardAjax(dataTable.getHeader().getCell("Name").get().getWebElement().findElement(By.className("ui-column-title"))).click();

        // Assert - descending
        Collections.reverse(langsSorted);
        assertRows(dataTable, langsSorted);

        assertConfiguration(dataTable.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("DataTable Config = " + cfg);
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form1:datatable1")
        DataTable dataTable1;

        @FindBy(id = "form1:button1")
        CommandButton button1;

        @FindBy(id = "form1:buttonResetTable1")
        CommandButton buttonResetTable1;

        @FindBy(id = "form2:datatable2")
        DataTable dataTable2;

        @FindBy(id = "form2:button2")
        CommandButton button2;

        @FindBy(id = "form2:buttonResetTable2")
        CommandButton buttonResetTable2;

        @Override
        public String getLocation() {
            return "datatable/dataTable012.xhtml";
        }
    }
}
