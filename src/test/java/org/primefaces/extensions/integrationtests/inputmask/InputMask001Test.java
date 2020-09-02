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

public class InputMask001Test extends AbstractInputMaskTest {

    @Test
    @Order(1)
    @DisplayName("InputMask: Default AJAX event fires on blur")
    public void testAjaxChangeEvent(final Page page) {
        // Arrange
        final InputMask inputMask = page.inputMask;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("123456789");

        // Assert
        Assertions.assertEquals("123-45-6789", inputMask.getValue());
        assertConfiguration(inputMask.getWidgetConfiguration());
    }

    @Test
    @Order(2)
    @DisplayName("InputMask: Value is allowed if it does not match the mask and autoClear='false'")
    public void testAutoClearFalse(final Page page) {
        // Arrange
        final InputMask inputMask = page.inputMask;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("12345");

        // Assert
        Assertions.assertEquals("123-45-____", inputMask.getValue());
        assertConfiguration(inputMask.getWidgetConfiguration());
    }

    @Test
    @Order(3)
    @DisplayName("InputMask: Server side validation should reject value if it does not match mask when validateMask='true'")
    public void testServerSideValidation(final Page page) {
        // Arrange
        final InputMask inputMask = page.inputMask;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("12345");
        page.button.click();

        // Assert
        Assertions.assertEquals("", inputMask.getValue());
        assertConfiguration(inputMask.getWidgetConfiguration());
    }

    @Test
    @Order(4)
    @DisplayName("InputMask: Client side widget get and set value methods")
    public void testWidgetGetSet(final Page page) {
        // Arrange
        final InputMask inputMask = page.inputMask;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setWidgetValue("45678");

        // Assert
        Assertions.assertEquals("456-78-____", inputMask.getWidgetValue());
        Assertions.assertEquals("45678", inputMask.getWidgetValueUnmasked());
        assertConfiguration(inputMask.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("InputMask Config = " + cfg);
        Assertions.assertEquals("999-99-9999", cfg.getString("mask"));
        Assertions.assertFalse(cfg.has("placeholder"));
        Assertions.assertFalse(cfg.getBoolean(AbstractInputMaskTest.AUTO_CLEAR));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:inputmask")
        InputMask inputMask;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "inputmask/inputMask001.xhtml";
        }
    }
}
