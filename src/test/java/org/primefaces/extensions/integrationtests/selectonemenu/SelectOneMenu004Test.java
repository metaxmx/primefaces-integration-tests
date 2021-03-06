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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.Messages;
import org.primefaces.extensions.selenium.component.SelectOneMenu;

import java.util.List;

public class SelectOneMenu004Test extends AbstractPrimePageTest {

    @Test
    @Order(1)
    @DisplayName("SelectOneMenu: dynamic - load items on demand")
    public void testDynamic(Page page) {
        // Arrange
        SelectOneMenu selectOneMenu = page.selectOneMenu;

        // Assert
        boolean contentPanelExists = false;
        try {
            selectOneMenu.getItems().getText();
            contentPanelExists = true;
        }
        catch (NoSuchElementException ex) {
            ;
        }
        Assertions.assertEquals(false, contentPanelExists);

        // Act
        selectOneMenu.toggleDropdown();

        // Assert
        List<WebElement> options = selectOneMenu.getItems().findElements(By.className("ui-selectonemenu-item"));
        Assertions.assertEquals(5, options.size());

        assertConfiguration(selectOneMenu.getWidgetConfiguration());
    }

    @Test
    @Order(2)
    @DisplayName("SelectOneMenu: dynamic - don´t loose selection on submit")
    public void testDynamic2(Page page) {
        // Arrange
        SelectOneMenu selectOneMenu = page.selectOneMenu2;

        // Act
        page.button.click();

        // Assert
        Assertions.assertTrue(page.messages.getMessage(0).getSummary().contains("console2"));
        Assertions.assertTrue(page.messages.getMessage(0).getDetail().contains("PS4"));

        assertConfiguration(selectOneMenu.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("SelectOneMenu Config = " + cfg);
        Assertions.assertTrue(cfg.has("dynamic"));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:msgs")
        Messages messages;

        @FindBy(id = "form:selectonemenu")
        SelectOneMenu selectOneMenu;

        @FindBy(id = "form:selectonemenu2")
        SelectOneMenu selectOneMenu2;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "selectonemenu/selectOneMenu004.xhtml";
        }
    }
}
