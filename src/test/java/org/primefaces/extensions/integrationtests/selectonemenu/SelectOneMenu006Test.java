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

import java.util.List;

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
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.Messages;
import org.primefaces.extensions.selenium.component.SelectOneMenu;
import org.primefaces.extensions.selenium.component.model.Msg;

public class SelectOneMenu006Test extends AbstractPrimePageTest {

    @Test
    @Order(1)
    @DisplayName("SelectOneMenu: editable=true choose selectItem value")
    public void testChooseSelectValue(Page page) {
        // Arrange
        SelectOneMenu selectOneMenu = page.selectOneMenu;
        selectOneMenu.toggleDropdown();

        // Assert
        List<WebElement> options = selectOneMenu.getItems().findElements(By.className("ui-selectonemenu-item"));
        Assertions.assertEquals(7, options.size());

        // Act
        selectOneMenu.select("Playstation 5");
        page.button.click();

        // Assert
        assertMessage(page, 0, "Console", "PS5");
        assertConfiguration(selectOneMenu.getWidgetConfiguration());
    }

    @Test
    @Order(2)
    @DisplayName("SelectOneMenu: editable=true enter user supplied value")
    public void testEnterUserSuppliedValue(Page page) {
        // Arrange
        SelectOneMenu selectOneMenu = page.selectOneMenu;
        WebElement editableInput = selectOneMenu.getEditableInput();

        // Act
        editableInput.clear();
        editableInput.sendKeys("Sega Genesis");
        page.button.click();

        // Assert
        assertMessage(page, 0, "Console", "Sega Genesis");
        assertConfiguration(selectOneMenu.getWidgetConfiguration());
    }

    private void assertMessage(Page page, int index, String summary, String detail) {
        Msg message = page.messages.getMessage(index);
        Assertions.assertEquals(summary, message.getSummary());
        Assertions.assertEquals(detail, message.getDetail());
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

        @FindBy(id = "form:msgs")
        Messages messages;

        @Override
        public String getLocation() {
            return "selectonemenu/selectOneMenu006.xhtml";
        }
    }
}
