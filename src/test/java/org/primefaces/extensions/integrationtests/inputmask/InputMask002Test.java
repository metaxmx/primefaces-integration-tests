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

public class InputMask002Test extends AbstractInputMaskTest {

    @Test
    @Order(1)
    @DisplayName("InputMask: Auto clearing the value if it does not match the mask completely")
    public void testAutoClearNoMatch(final Page page) {
        // Arrange
        final InputMask inputMask = page.inputMask;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("222");
        page.button.click();

        // Assert
        Assertions.assertEquals("", inputMask.getValue());
        assertConfiguration(inputMask.getWidgetConfiguration());
    }

    @Test
    @Order(2)
    @DisplayName("InputMask: Value matches the mask completely")
    public void testAutoClearMatch(final Page page) {
        // Arrange
        final InputMask inputMask = page.inputMask;
        Assertions.assertEquals("", inputMask.getValue());

        // Act
        inputMask.setValue("222-22-2222");
        page.button.click();

        // Assert
        Assertions.assertEquals("222-22-2222", inputMask.getValue());
        assertConfiguration(inputMask.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        System.out.println("InputMask Config = " + cfg);
        Assertions.assertEquals("999-99-9999", cfg.getString("mask"));
        Assertions.assertEquals("*", cfg.getString("placeholder"));
        Assertions.assertFalse(cfg.has(AUTO_CLEAR));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:inputmask")
        InputMask inputMask;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "inputmask/inputMask002.xhtml";
        }
    }
}
