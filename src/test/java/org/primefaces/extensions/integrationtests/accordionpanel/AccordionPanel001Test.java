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
package org.primefaces.extensions.integrationtests.accordionpanel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.component.AccordionPanel;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.TabView;
import org.primefaces.extensions.selenium.component.model.Tab;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AccordionPanel001Test extends AbstractPrimePageTest {

    @Test
    public void test(Page page) {
        // Arrange
        AccordionPanel accordionPanel = page.accordionPanel;

        // Assert - part 1
        List<Tab> tabs = accordionPanel.getTabs();
        Assertions.assertNotNull(tabs);
        Assertions.assertEquals(3, tabs.size());
        AtomicInteger cnt = new AtomicInteger(0);
        tabs.forEach(tab -> {
                    Assertions.assertNotNull(tab.getHeader());
                    Assertions.assertNotNull(tab.getContent());
                    Assertions.assertEquals(cnt.getAndIncrement(), tab.getIndex());
                });
        Assertions.assertEquals("Panel1", tabs.get(0).getTitle());
        Assertions.assertEquals("Panel2", tabs.get(1).getTitle());

        Assertions.assertNotNull(accordionPanel.getSelectedTabs());
        Assertions.assertEquals(1, accordionPanel.getSelectedTabs().size());
        Assertions.assertEquals(0, accordionPanel.getSelectedTabs().get(0).getIndex());
        Assertions.assertEquals("Panel1", accordionPanel.getSelectedTabs().get(0).getTitle());

        // Act
        accordionPanel.toggleTab(2);

        // Assert - part 2
        assertNoJavascriptErrors();
        Assertions.assertNotNull(accordionPanel.getSelectedTabs());
        Assertions.assertEquals(1, accordionPanel.getSelectedTabs().size());
        Assertions.assertEquals(2, accordionPanel.getSelectedTabs().get(0).getIndex());
        Assertions.assertEquals("Panel3", accordionPanel.getSelectedTabs().get(0).getTitle());
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:accordionpanel")
        AccordionPanel accordionPanel;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "accordionpanel/accordionPanel001.xhtml";
        }
    }
}
