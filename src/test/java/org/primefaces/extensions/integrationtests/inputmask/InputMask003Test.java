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
package org.primefaces.extensions.integrationtests.inputmask;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.InputMask;

public class InputMask003Test extends AbstractInputMaskTest {

    @Test
    @Order(1)
    @DisplayName("InputMask: Alphanumeric mask with invalid value")
    public void testAlphanumericInvalid(final Page page) {
        // Arrange
        final InputMask inputMask = page.alphanumeric;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("22-222-2222");
        page.button.click();

        // Assert
        Assertions.assertEquals("", inputMask.getValue());
        assertConfiguration(inputMask.getWidgetConfiguration(), "a*-999-a999");
    }

    @Test
    @Order(2)
    @DisplayName("InputMask: Alphanumeric mask with valid value")
    public void testAlphanumericValid(final Page page) {
        // Arrange
        final InputMask inputMask = page.alphanumeric;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("ab-123-c456");
        page.button.click();

        // Assert
        Assertions.assertEquals("ab-123-c456", inputMask.getValue());
        assertConfiguration(inputMask.getWidgetConfiguration(), "a*-999-a999");
    }

    @Test
    @Order(3)
    @DisplayName("InputMask: Optional value is omitted and value is OK")
    public void testOptionalWithoutExtension(final Page page) {
        // Arrange
        final InputMask inputMask = page.optional;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("(123) 456-7890");
        page.button.click();

        // Assert
        Assertions.assertEquals("(123) 456-7890", inputMask.getValue());
        assertConfiguration(inputMask.getWidgetConfiguration(), OPTIONAL_MASK);
    }

    @Test
    @Order(4)
    @DisplayName("InputMask: Optional value is included and value is OK")
    public void testOptionalWithExtension(final Page page) {
        // Arrange
        final InputMask inputMask = page.optional;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("(123) 456-7890 x12345");
        page.button.click();

        // Assert
        Assertions.assertEquals("(123) 456-7890 x12345", inputMask.getValue());
        assertConfiguration(inputMask.getWidgetConfiguration(), OPTIONAL_MASK);
    }

    private void assertConfiguration(JSONObject cfg, String mask) {
        assertNoJavascriptErrors();
        System.out.println("InputMask Config = " + cfg);
        Assertions.assertEquals(mask, cfg.getString("mask"));
        Assertions.assertFalse(cfg.has(AUTO_CLEAR));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:alphanumeric")
        InputMask alphanumeric;

        @FindBy(id = "form:optional")
        InputMask optional;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "inputmask/inputMask003.xhtml";
        }
    }
}
