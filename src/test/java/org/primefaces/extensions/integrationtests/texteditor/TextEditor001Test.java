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
package org.primefaces.extensions.integrationtests.texteditor;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.TextEditor;

public class TextEditor001Test extends AbstractPrimePageTest {

    @Test
    public void testSubmit(Page page) {
        // Arrange
        TextEditor editor = page.textEditor;
        Assertions.assertEquals("", editor.getValue());

        // Act
        editor.setValue("hello!");
        page.button.click();

        // Assert
        Assertions.assertEquals("<p>hello!</p>", editor.getValue());
        assertConfiguration(editor.getWidgetConfiguration());
    }

    @Test
    public void testClear(Page page) {
        // Arrange
        TextEditor editor = page.textEditor;

        // Act
        editor.setValue("Some Text");
        editor.clear();

        // Assert
        Assertions.assertEquals("", editor.getEditorValue());
        assertConfiguration(editor.getWidgetConfiguration());
    }

    private void assertConfiguration(JSONObject cfg) {
        assertNoJavascriptErrors();
        System.out.println("TextEditor Config = " + cfg);
        Assertions.assertTrue(cfg.getBoolean("toolbarVisible"));
        Assertions.assertEquals("snow", cfg.getString("theme"));
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:editor")
        TextEditor textEditor;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "texteditor/textEditor001.xhtml";
        }
    }
}
