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
package org.primefaces.extensions.integrationtests.messages;

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
import org.primefaces.extensions.selenium.PrimeSelenium;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.DatePicker;
import org.primefaces.extensions.selenium.component.InputText;
import org.primefaces.extensions.selenium.component.Messages;
import org.primefaces.extensions.selenium.component.model.Severity;

public class Messages001Test extends AbstractPrimePageTest {

    @Test
    @Order(1)
    @DisplayName("Messages: message and validation")
    public void testBasic(Page page) {
        // Arrange

        // Act
        page.buttonAction1.click();

        // Assert
        Assertions.assertTrue(PrimeSelenium.isElementDisplayed(page.messages));
        Assertions.assertEquals(1, page.messages.getAllMessages().size());
        Assertions.assertEquals("Value 2: must not be null", page.messages.getMessage(0).getSummary());
        Assertions.assertEquals(Severity.ERROR, page.messages.getMessage(0).getSeverity());
        Assertions.assertTrue(PrimeSelenium.hasCssClass(page.inputTextVal2, "ui-state-error"));

        // Act
        page.inputTextVal2.setValue("test123");
        page.buttonAction1.click();

        // Assert
        Assertions.assertTrue(PrimeSelenium.isElementDisplayed(page.messages));
        Assertions.assertEquals(1, page.messages.getAllMessages().size());
        Assertions.assertEquals("Action 1", page.messages.getMessage(0).getSummary());
        Assertions.assertEquals(Severity.INFO, page.messages.getMessage(0).getSeverity());

        assertNoJavascriptErrors();
    }

    @Test
    @Order(2)
    @DisplayName("Messages: error-message with ClientId")
    public void testErrorMessageWithClientId(Page page) {
        // Arrange
        page.inputTextVal2.setValue("test123");

        // Act
        page.buttonAction2.click();

        // Assert
        Assertions.assertTrue(PrimeSelenium.isElementDisplayed(page.messages));
        Assertions.assertEquals(1, page.messages.getAllMessages().size());
        Assertions.assertEquals("Action 2", page.messages.getMessage(0).getSummary());
        Assertions.assertEquals(Severity.ERROR, page.messages.getMessage(0).getSeverity());
        Assertions.assertTrue(PrimeSelenium.hasCssClass(page.inputTextVal1, "ui-state-error"));

        // Act
        page.buttonAction1.click();

        // Assert
        Assertions.assertTrue(PrimeSelenium.isElementDisplayed(page.messages));
        Assertions.assertEquals(1, page.messages.getAllMessages().size());
        Assertions.assertEquals("Action 1", page.messages.getMessage(0).getSummary());
        Assertions.assertEquals(Severity.INFO, page.messages.getMessage(0).getSeverity());
        Assertions.assertFalse(PrimeSelenium.hasCssClass(page.inputTextVal1, "ui-state-error"));

        assertNoJavascriptErrors();
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:messages")
        Messages messages;

        @FindBy(id = "form:val1")
        InputText inputTextVal1;

        @FindBy(id = "form:val2")
        InputText inputTextVal2;

        @FindBy(id = "form:buttonAction1")
        CommandButton buttonAction1;

        @FindBy(id = "form:buttonAction2")
        CommandButton buttonAction2;
        @Override
        public String getLocation() {
            return "messages/messages001.xhtml";
        }
    }
}
