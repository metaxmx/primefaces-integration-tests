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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.PrimeExpectedConditions;
import org.primefaces.extensions.selenium.PrimeSelenium;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.DataTable;
import org.primefaces.extensions.selenium.component.Messages;
import org.primefaces.extensions.selenium.component.base.ComponentUtils;
import org.primefaces.extensions.selenium.component.model.data.Paginator;
import org.primefaces.extensions.selenium.component.model.datatable.Header;
import org.primefaces.extensions.selenium.component.model.datatable.Row;

import java.util.List;
import java.util.stream.Collectors;

public class DataTable007Test extends AbstractDataTableTest {

    private final List<ProgrammingLanguage> langs = new ProgrammingLanguageService().getLangs();

    @Test
    @Order(1)
    @DisplayName("DataTable: Edit-Row")
    public void testEditRow(Page page) {
        // Arrange
        DataTable dataTable = page.dataTable;
        Assertions.assertNotNull(dataTable);

        // Act - edit and cancel
        Row row = dataTable.getRow(1);
        row.getCell(3).getWebElement().findElement(By.className("ui-row-editor-pencil")).click();
        row.getCell(1).getWebElement().findElement(By.tagName("input")).clear();
        row.getCell(1).getWebElement().findElement(By.tagName("input")).sendKeys("xyz");
        row.getCell(2).getWebElement().findElement(By.tagName("input")).clear();
        row.getCell(2).getWebElement().findElement(By.tagName("input")).sendKeys("2000");
        PrimeSelenium.guardAjax(row.getCell(3).getWebElement().findElement(By.className("ui-row-editor-close"))).click();

        // Assert
        row = dataTable.getRow(1);
        Assertions.assertTrue(row.getCell(3).getWebElement().findElement(By.className("ui-row-editor-pencil")).isDisplayed());
        Assertions.assertFalse(row.getCell(3).getWebElement().findElement(By.className("ui-row-editor-close")).isDisplayed());
        Assertions.assertEquals(langs.get(1).getName(), row.getCell(1).getText());
        Assertions.assertEquals(Integer.toString(langs.get(1).getFirstAppeared()), row.getCell(2).getText());
        Assertions.assertEquals("Edit Cancelled", page.messages.getMessage(0).getSummary());
        Assertions.assertEquals(Integer.toString(langs.get(1).getId()), page.messages.getMessage(0).getDetail());

        // Act - edit and accept
        row = dataTable.getRow(2);
        row.getCell(3).getWebElement().findElement(By.className("ui-row-editor-pencil")).click();
        row.getCell(1).getWebElement().findElement(By.tagName("input")).clear();
        row.getCell(1).getWebElement().findElement(By.tagName("input")).sendKeys("abc");
        row.getCell(2).getWebElement().findElement(By.tagName("input")).clear();
        row.getCell(2).getWebElement().findElement(By.tagName("input")).sendKeys("2020");
        PrimeSelenium.guardAjax(row.getCell(3).getWebElement().findElement(By.className("ui-row-editor-check"))).click();

        // Assert
        row = dataTable.getRow(2);
        Assertions.assertTrue(row.getCell(3).getWebElement().findElement(By.className("ui-row-editor-pencil")).isDisplayed());
        Assertions.assertFalse(row.getCell(3).getWebElement().findElement(By.className("ui-row-editor-close")).isDisplayed());
        Assertions.assertEquals("abc", row.getCell(1).getText());
        Assertions.assertEquals("2020", row.getCell(2).getText());
        Assertions.assertEquals("ProgrammingLanguage Edited", page.messages.getMessage(0).getSummary());
        Assertions.assertEquals(Integer.toString(langs.get(2).getId()), page.messages.getMessage(0).getDetail());

        // Act - submit
        page.button.click();

        // Assert
        row = dataTable.getRow(2);
        Assertions.assertEquals("abc", row.getCell(1).getText());
        Assertions.assertEquals("2020", row.getCell(2).getText());

        assertConfiguration(dataTable.getWidgetConfiguration());
    }

    @Test
    @Order(2)
    @DisplayName("DataTable: Filter combined with Edit-Row; https://github.com/primefaces/primefaces/issues/1442")
    public void testFilterAndEditRow(Page page) {
        // Arrange
        DataTable dataTable = page.dataTable;
        Assertions.assertNotNull(dataTable);

        // Act - filter
        dataTable.filter("Name", "Java");

        // Act - edit and accept
        Row row = dataTable.getRow(1);
        row.getCell(3).getWebElement().findElement(By.className("ui-row-editor-pencil")).click();
        row.getCell(1).getWebElement().findElement(By.tagName("input")).clear();
        row.getCell(1).getWebElement().findElement(By.tagName("input")).sendKeys("abc");
        row.getCell(2).getWebElement().findElement(By.tagName("input")).clear();
        row.getCell(2).getWebElement().findElement(By.tagName("input")).sendKeys("2020");
        PrimeSelenium.guardAjax(row.getCell(3).getWebElement().findElement(By.className("ui-row-editor-check"))).click();

        // Act - remove filter
        // TODO: add remove-filter functionality to PF Selenium
        dataTable.filter("Name", "x");
        dataTable.getHeader().getCell("Name").get().getColumnFilter().sendKeys(Keys.BACK_SPACE);
        try {
            // default-filter runs delayed - so wait...
            Thread.sleep(500);
        }
        catch (InterruptedException ex) {
        }
        PrimeSelenium.waitGui().until(PrimeExpectedConditions.jQueryNotActive());

        // Act - submit
        page.button.click();

        // Assert
        row = dataTable.getRow(2);
        Assertions.assertEquals("abc", row.getCell(1).getText());
        Assertions.assertEquals("2020", row.getCell(2).getText());

        assertConfiguration(dataTable.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("DataTable Config = " + cfg);
        Assertions.assertTrue(cfg.has("editable"));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:msgs")
        Messages messages;

        @FindBy(id = "form:datatable")
        DataTable dataTable;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "datatable/dataTable007.xhtml";
        }
    }
}
