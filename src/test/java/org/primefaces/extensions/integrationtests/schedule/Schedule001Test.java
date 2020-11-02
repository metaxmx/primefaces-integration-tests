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
package org.primefaces.extensions.integrationtests.schedule;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.PrimeSelenium;
import org.primefaces.extensions.selenium.component.Messages;
import org.primefaces.extensions.selenium.component.base.AbstractComponent;
import org.primefaces.extensions.selenium.component.model.Msg;

public class Schedule001Test extends AbstractPrimePageTest {

    @Test
    @Order(1)
    @DisplayName("Schedule: show and check for JS-errors")
    public void testBasic(Page page) {
        // Arrange

        // Act

        // Assert
        assertConfiguration(page.schedule.getWidgetConfiguration());
    }

    @Test
    @Order(2)
    @DisplayName("Schedule: dateSelect")
    public void testDateSelect(Page page) {
        // Arrange

        // Act
        PrimeSelenium.guardAjax(page.schedule.findElement(By.className("fc-daygrid-day"))).click();

        // Assert
        assertMessage(page, "Date selected");
        assertConfiguration(page.schedule.getWidgetConfiguration());
    }

    @Test
    @Order(3)
    @DisplayName("Schedule: eventSelect")
    public void testEventSelect(Page page) {
        // Arrange

        // Act
        PrimeSelenium.guardAjax(page.schedule.findElement(By.className("fc-daygrid-event"))).click();

        // Assert
        assertMessage(page, "Event selected");
        assertConfiguration(page.schedule.getWidgetConfiguration());
    }

    private void assertMessage(Page page, String message) {
        Msg msg = page.messages.getMessage(0);
        Assertions.assertNotNull(msg, "No messages found!");
        System.out.println("Message = " + msg);
        Assertions.assertTrue(msg.getSummary().contains(message));
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("Schedule Config = " + cfg);
        Assertions.assertEquals("form", cfg.getString("formId"));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:msgs")
        Messages messages;

        @FindBy(id = "form:schedule")
        AbstractComponent schedule;

        @Override
        public String getLocation() {
            return "schedule/schedule001.xhtml";
        }
    }
}
