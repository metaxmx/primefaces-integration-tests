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
package org.primefaces.extensions.integrationtests.datalist;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.integrationtests.datatable.AbstractDataTableTest;
import org.primefaces.extensions.integrationtests.datatable.ProgrammingLanguage;
import org.primefaces.extensions.integrationtests.datatable.ProgrammingLanguageService;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.PrimeExpectedConditions;
import org.primefaces.extensions.selenium.PrimeSelenium;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.DataList;
import org.primefaces.extensions.selenium.component.DataTable;
import org.primefaces.extensions.selenium.component.base.ComponentUtils;
import org.primefaces.extensions.selenium.component.model.data.Paginator;
import org.primefaces.extensions.selenium.component.model.datatable.Header;
import org.primefaces.extensions.selenium.component.model.datatable.Row;

import java.util.List;
import java.util.stream.Collectors;

public class DataList001Test extends AbstractDataTableTest {

    private final List<ProgrammingLanguage> langs = new ProgrammingLanguageService().getLangs();

    @Test
    @Order(1)
    @DisplayName("DataList: Basic & Paginator")
    public void testBasicAndPaginator(Page page) {
        // Arrange
        DataList dataList = page.dataList;
        Assertions.assertNotNull(dataList);

        // Act
        //page.button.click();

        // Assert
        Assertions.assertNotNull(dataList.getPaginatorWebElement());

        List<WebElement> rowElts = dataList.getRowsWebElement();
        Assertions.assertNotNull(rowElts);
        Assertions.assertEquals(3, rowElts.size());

        WebElement firstRowElt = dataList.getRowWebElement(0);
        Assertions.assertTrue(firstRowElt.getText().contains("1"));
        Assertions.assertTrue(firstRowElt.getText().contains("Java"));

        Paginator paginator = dataList.getPaginator();
        Assertions.assertNotNull(paginator);
        Assertions.assertNotNull(paginator.getPages());
        Assertions.assertEquals(2, paginator.getPages().size());
        Assertions.assertEquals(1, paginator.getPage(0).getNumber());
        Assertions.assertEquals(2, paginator.getPage(1).getNumber());

        assertConfiguration(dataList.getWidgetConfiguration());

        // Act
        dataList.selectPage(2);

        // Assert - Part 2
        rowElts = dataList.getRowsWebElement();
        Assertions.assertNotNull(rowElts);
        Assertions.assertEquals(2, rowElts.size());

        assertConfiguration(dataList.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("DataList Config = " + cfg);
        Assertions.assertTrue(cfg.has("paginator"));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:datalist")
        DataList dataList;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "datalist/dataList001.xhtml";
        }
    }
}
