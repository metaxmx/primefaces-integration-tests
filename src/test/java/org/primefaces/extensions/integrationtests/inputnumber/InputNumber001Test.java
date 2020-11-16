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
package org.primefaces.extensions.integrationtests.inputnumber;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.InputNumber;

public class InputNumber001Test extends AbstractPrimePageTest {

    @Test
    @Order(1)
    @DisplayName("InputNumber: Default AJAX event fires on blur")
    public void testAjaxChangeEvent(final Page page) {
        // Arrange
        InputNumber inputNumber = page.inputnumber;
        Assertions.assertEquals("50", inputNumber.getValue());

        // Act
        inputNumber.setValue("33");

        // Assert
        Assertions.assertEquals("33", inputNumber.getValue());
        assertConfiguration(inputNumber.getWidgetConfiguration());
    }

    @Test
    @Order(2)
    @DisplayName("InputNumber: Test integer input without decimal places default to 0")
    public void testInteger(Page page) {
        // Arrange
        InputNumber inputNumber = page.inputnumber;
        Assertions.assertEquals("50", inputNumber.getValue());

        // Act
        inputNumber.setValue("98.54");
        page.button.click();

        // Assert
        Assertions.assertEquals("99", inputNumber.getValue());
        assertConfiguration(inputNumber.getWidgetConfiguration());
    }

    @Test
    @Order(3)
    @DisplayName("InputNumber: Test widget getValue() function returns Integer values with the correct format")
    public void testIntegerWidgetValue(Page page) {
        // Arrange
        InputNumber inputNumber = page.inputnumber;
        Assertions.assertEquals("50", inputNumber.getValue());

        // Act
        inputNumber.setValue("42");

        // Assert
        Assertions.assertEquals("42", inputNumber.getWidgetValue());
        assertConfiguration(inputNumber.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("InputNumber Config = " + cfg);
        Assertions.assertEquals("0", cfg.get("decimalPlaces"));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:inputnumber")
        InputNumber inputnumber;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "inputnumber/inputNumber001.xhtml";
        }
    }
}
