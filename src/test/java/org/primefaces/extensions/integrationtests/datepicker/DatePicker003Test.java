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
package org.primefaces.extensions.integrationtests.datepicker;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.DatePicker;

public class DatePicker003Test extends AbstractPrimePageTest {

    @Test
    @DisplayName("DatePicker: minDate and maxDate; days outside range should be disabled")
    public void testMinMax(Page page) {
        // Arrange
        DatePicker datePicker = page.datePicker;

        // Act
        datePicker.click(); // focus to bring up panel

        // Assert (all days of august active)
        WebElement panel = datePicker.getPanel();
        Assertions.assertNotNull(panel);
        Assertions.assertEquals(31, panel.findElements(By.cssSelector("a.ui-state-default")).size());

        // Act - previous month
        panel.findElement(By.className("ui-datepicker-prev")).click();

        // Assert
        Assertions.assertEquals(30, panel.findElements(By.cssSelector("td > span.ui-state-disabled")).size()); //includes invisible days of other months
        Assertions.assertEquals(12, panel.findElements(By.cssSelector("td > a.ui-state-default")).size());

        // Act - next month
        panel.findElement(By.className("ui-datepicker-next")).click();
        panel.findElement(By.className("ui-datepicker-next")).click();

        // Assert
        Assertions.assertEquals(22, panel.findElements(By.cssSelector("td > span.ui-state-disabled")).size()); //includes invisible days of other months
        Assertions.assertEquals(20, panel.findElements(By.cssSelector("td > a.ui-state-default")).size());

        assertConfiguration(datePicker.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("DatePicker Config = " + cfg);
        Assertions.assertEquals("mm/dd/yy", cfg.getString("dateFormat"));
        Assertions.assertEquals("single", cfg.getString("selectionMode"));
        Assertions.assertFalse(cfg.getBoolean("inline"));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:datepicker")
        DatePicker datePicker;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "datepicker/datePicker003.xhtml";
        }
    }
}
