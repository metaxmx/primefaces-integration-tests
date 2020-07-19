/**
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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.InputText;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePicker001Test extends AbstractPrimePageTest
{
    @Test
    public void test(Page page) throws InterruptedException
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Assertions.assertEquals(LocalDate.now().format(dateTimeFormatter), page.datePicker.getValue());

        //TODO: we need to move "things" to PF-Selenium
        page.datePicker.setValue("02/19/1978");
        WebElement eltInput = page.getWebDriver().findElement(By.id("form:datepicker1_input"));
        WebElement eltPanel = page.getWebDriver().findElement(By.id("form:datepicker1_panel"));

        //set focus to input
        new Actions(page.getWebDriver()).moveToElement(eltInput).click().perform();
        //check DatePicker-widget
        Assertions.assertNotNull(eltPanel);
        Assertions.assertTrue(eltPanel.getText().contains("1978"));
        Assertions.assertTrue(eltPanel.getText().contains("February"));

        page.button.click();
        Assertions.assertEquals("02/19/1978", page.datePicker.getValue());
    }

    public static class Page extends AbstractPrimePage
    {
        @FindBy(id = "form:datepicker1_input") //TODO: should not require _input
        InputText datePicker; //TODO: enhance PF-Selenium; should be DatePicker

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation()
        {
            return "datepicker/datePicker001.xhtml";
        }
    }
}
