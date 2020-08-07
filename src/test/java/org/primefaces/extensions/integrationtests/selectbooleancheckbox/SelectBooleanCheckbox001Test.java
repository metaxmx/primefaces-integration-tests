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
package org.primefaces.extensions.integrationtests.selectbooleancheckbox;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.SelectBooleanCheckbox;

public class SelectBooleanCheckbox001Test extends AbstractPrimePageTest {

    @Test
    public void testSubmit(Page page) {
        // Arrange
        SelectBooleanCheckbox checkbox = page.checkbox;
        Assertions.assertFalse(checkbox.getValue());

        // Act
        checkbox.click();
        page.button.click();

        // Assert
        Assertions.assertTrue(checkbox.getValue());
        assertConfiguration(checkbox.getWidgetConfiguration());
    }

    @Test
    public void testToggleTrue(Page page) {
        // Arrange
        SelectBooleanCheckbox checkbox = page.checkbox;
        Assertions.assertFalse(checkbox.getValue());

        // Act
        checkbox.toggle();

        // Assert
        Assertions.assertTrue(checkbox.getValue());
        assertConfiguration(checkbox.getWidgetConfiguration());
    }

    @Test
    public void testToggleFalse(Page page) {
        // Arrange
        SelectBooleanCheckbox checkbox = page.checkbox;
        checkbox.setValue(true);
        Assertions.assertTrue(checkbox.getValue());

        // Act
        checkbox.toggle();

        // Assert
        Assertions.assertFalse(checkbox.getValue());
        assertConfiguration(checkbox.getWidgetConfiguration());
    }

    @Test
    public void testUncheck(Page page) {
        // Arrange
        SelectBooleanCheckbox checkbox = page.checkbox;
        checkbox.setValue(true);
        Assertions.assertTrue(checkbox.getValue());

        // Act
        checkbox.uncheck();

        // Assert
        Assertions.assertFalse(checkbox.getValue());
        assertConfiguration(checkbox.getWidgetConfiguration());
    }

    @Test
    public void testCheck(Page page) {
        // Arrange
        SelectBooleanCheckbox checkbox = page.checkbox;
        checkbox.setValue(false);
        Assertions.assertFalse(checkbox.getValue());

        // Act
        checkbox.check();

        // Assert
        Assertions.assertTrue(checkbox.getValue());
        assertConfiguration(checkbox.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("SelectBooleanCheckbox Config = " + cfg);
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:checkbox")
        SelectBooleanCheckbox checkbox;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "selectbooleancheckbox/selectBooleanCheckBox001.xhtml";
        }
    }
}
