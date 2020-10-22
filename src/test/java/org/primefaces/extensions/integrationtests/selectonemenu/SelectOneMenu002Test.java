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
package org.primefaces.extensions.integrationtests.selectonemenu;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.PrimeExpectedConditions;
import org.primefaces.extensions.selenium.PrimeSelenium;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.SelectOneMenu;

import java.util.List;

public class SelectOneMenu002Test extends AbstractPrimePageTest {

    @Test
    @Order(1)
    @DisplayName("SelectOneMenu: SelectItemGroup")
    public void testSelectItemGroup(Page page) {
        // Arrange
        SelectOneMenu selectOneMenu = page.selectOneMenu;
        Assertions.assertEquals("Select One", selectOneMenu.getSelectedLabel());

        // Act
        selectOneMenu.select("Volkswagen");
        page.button.click();

        // Assert - part 1
        Assertions.assertEquals("Volkswagen", selectOneMenu.getSelectedLabel());
        assertConfiguration(selectOneMenu.getWidgetConfiguration());

        // Act
        selectOneMenu.toggleDropdown();

        // Assert - part 2
        // items
        List<WebElement> optgroups = selectOneMenu.getInput().findElements(By.tagName("optgroup"));
        Assertions.assertEquals(2, optgroups.size());
        List<WebElement> options= selectOneMenu.getInput().findElements(By.tagName("option"));
        Assertions.assertEquals(8, options.size());
        // panel-content
        optgroups = selectOneMenu.getItems().findElements(By.className("ui-selectonemenu-item-group"));
        Assertions.assertEquals(2, optgroups.size());
        options= selectOneMenu.getItems().findElements(By.className("ui-selectonemenu-item"));
        Assertions.assertEquals(8, options.size());

        assertConfiguration(selectOneMenu.getWidgetConfiguration());
    }

    @Test
    @Order(2)
    @DisplayName("SelectOneMenu: escape=true/false")
    public void testEscape(Page page) {
        // Arrange
        SelectOneMenu selectOneMenu = page.selectOneMenu;

        // Act
        selectOneMenu.toggleDropdown();

        // Assert
        List<WebElement> optgroups = selectOneMenu.getItems().findElements(By.className("ui-selectonemenu-item-group"));
        Assertions.assertEquals("German Cars", optgroups.get(0).getText());
        Assertions.assertEquals("American <Cars>", optgroups.get(1).getText());

        List<WebElement> options= selectOneMenu.getItems().findElements(By.className("ui-selectonemenu-item"));
        Assertions.assertEquals("Mercedes", options.get(2).getText());
        Assertions.assertEquals("Chry<sler", options.get(4).getText());

        assertConfiguration(selectOneMenu.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("SelectOneMenu Config = " + cfg);
        Assertions.assertTrue(cfg.has("appendTo"));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:selectonemenu")
        SelectOneMenu selectOneMenu;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "selectonemenu/selectOneMenu002.xhtml";
        }
    }
}
