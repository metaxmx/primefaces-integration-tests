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
package org.primefaces.extensions.integrationtests.slider;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.component.Chips;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.Messages;
import org.primefaces.extensions.selenium.component.Slider;

import java.util.List;

public class Slider001Test extends AbstractPrimePageTest {

    @Test
    @Order(1)
    @DisplayName("Slider: int-value")
    public void testIntValue(Page page) {
        // Arrange
        Slider slider = page.sliderInt;

        // Assert initial state
        Assertions.assertEquals(5, slider.getValue().intValue());

        // Act - add value
        slider.setValue(8);

        // Act - submit
        page.button.click();

        // Assert
        //Assertions.assertEquals("Defect, Feature, Question", page.messages.getMessage(0).getSummary());
        Assertions.assertEquals(8, slider.getValue().intValue());

        assertConfiguration(slider.getWidgetConfiguration());
    }

    @Test
    @Order(2)
    @DisplayName("Slider: float-value")
    public void testFloatValue(Page page) {
        // Arrange
        Slider slider = page.sliderfloat;

        // Assert initial state
        Assertions.assertEquals(3.14f, slider.getValue().floatValue());

        // Act - add value
        slider.setValue(9.9f);

        // Act - submit
        page.button.click();

        // Assert
        //Assertions.assertEquals("Defect, Feature, Question", page.messages.getMessage(0).getSummary());
        Assertions.assertEquals(9.9f, slider.getValue().floatValue());

        assertConfiguration(slider.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("Slider Config = " + cfg);
        Assertions.assertTrue(cfg.has("id"));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:msgs")
        Messages messages;

        @FindBy(id = "form:sliderInt")
        Slider sliderInt;

        @FindBy(id = "form:sliderFloat")
        Slider sliderfloat;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "slider/slider001.xhtml";
        }
    }
}
