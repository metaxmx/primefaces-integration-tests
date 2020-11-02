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

public class InputMask004Test extends AbstractInputMaskTest {

    @Test
    @Order(1)
    @DisplayName("InputMask: GitHub #6469 Optional test with server side validation.")
    public void testOptionalAreaCode1(final Page page) {
        // Arrange
        final InputMask inputMask = page.optional;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("1");
        page.button.click();

        // Assert
        Assertions.assertEquals("1", inputMask.getValue());
        Assertions.assertEquals("1", inputMask.getWidgetValueUnmasked());
        assertConfiguration(inputMask.getWidgetConfiguration(), "9[999]");
    }

    @Test
    @Order(2)
    @DisplayName("InputMask: GitHub #6469 Optional test with server side validation.")
    public void testOptionalAreaCode2(final Page page) {
        // Arrange
        final InputMask inputMask = page.optional;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("12");
        page.button.click();

        // Assert
        Assertions.assertEquals("12__", inputMask.getValue());
        Assertions.assertEquals("12", inputMask.getWidgetValueUnmasked());
        assertConfiguration(inputMask.getWidgetConfiguration(), "9[999]");
    }

    @Test
    @Order(2)
    @DisplayName("InputMask: GitHub #6469 Optional test with server side validation.")
    public void testOptionalAreaCode3(final Page page) {
        // Arrange
        final InputMask inputMask = page.optional;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("123");
        page.button.click();

        // Assert
        Assertions.assertEquals("123_", inputMask.getValue());
        Assertions.assertEquals("123", inputMask.getWidgetValueUnmasked());
        assertConfiguration(inputMask.getWidgetConfiguration(), "9[999]");
    }

    @Test
    @Order(4)
    @DisplayName("InputMask: GitHub #6469 Optional test with server side validation.")
    public void testOptionalAreaCode4(final Page page) {
        // Arrange
        final InputMask inputMask = page.optional;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("1234");
        page.button.click();

        // Assert
        Assertions.assertEquals("1234", inputMask.getValue());
        Assertions.assertEquals("1234", inputMask.getWidgetValueUnmasked());
        assertConfiguration(inputMask.getWidgetConfiguration(), "9[999]");
    }

    private void assertConfiguration(JSONObject cfg, String mask) {
        assertNoJavascriptErrors();
        System.out.println("InputMask Config = " + cfg);
        Assertions.assertEquals(mask, cfg.getString("mask"));
        Assertions.assertFalse(cfg.has(AbstractInputMaskTest.AUTO_CLEAR));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:optional")
        InputMask optional;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "inputmask/inputMask004.xhtml";
        }
    }
}
