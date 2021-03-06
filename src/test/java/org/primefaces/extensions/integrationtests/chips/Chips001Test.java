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
package org.primefaces.extensions.integrationtests.chips;

import org.apache.openejb.jee.Web;
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
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.PrimeExpectedConditions;
import org.primefaces.extensions.selenium.PrimeSelenium;
import org.primefaces.extensions.selenium.component.AutoComplete;
import org.primefaces.extensions.selenium.component.Chips;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.Messages;
import org.primefaces.extensions.selenium.component.base.AbstractComponent;

import java.util.List;
import java.util.stream.Collectors;

public class Chips001Test extends AbstractPrimePageTest {

    @Test
    @Order(1)
    @DisplayName("Chips: add and remove value")
    public void testBasic(Page page) {
        // Arrange
        Chips chips = page.chips;

        // Assert initial state
        Assertions.assertEquals("Defect\nFeature", chips.getText());
        List<String> values = chips.getValues();
        Assertions.assertEquals(2, values.size());
        Assertions.assertEquals("Defect", values.get(0));
        Assertions.assertEquals("Feature", values.get(1));

        // Act - add value
        chips.addValue("Question");

        // Assert - itemSelect-event
        Assertions.assertEquals("itemSelect", page.messages.getMessage(0).getSummary());
        Assertions.assertEquals("Question", page.messages.getMessage(0).getDetail());

        // Act - submit
        page.button.click();

        // Assert
        Assertions.assertEquals("Defect, Feature, Question", page.messages.getMessage(0).getSummary());

        // Act - remove value
        chips.removeValue("Defect");

        // Assert - itemSelect-event
        Assertions.assertEquals("itemUnselect", page.messages.getMessage(0).getSummary());
        Assertions.assertEquals("Defect", page.messages.getMessage(0).getDetail());

        // Act - submit
        page.button.click();

        // Assert
        Assertions.assertEquals("Feature, Question", page.messages.getMessage(0).getSummary());
        values = chips.getValues();
        Assertions.assertEquals(2, values.size());
        Assertions.assertEquals("Feature", values.get(0));
        Assertions.assertEquals("Question", values.get(1));

        assertConfiguration(chips.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("Chips Config = " + cfg);
        Assertions.assertTrue(cfg.has("id"));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:msgs")
        Messages messages;

        @FindBy(id = "form:chips")
        Chips chips;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "chips/chips001.xhtml";
        }
    }
}
