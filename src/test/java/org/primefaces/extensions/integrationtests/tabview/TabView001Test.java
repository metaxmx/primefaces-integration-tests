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
package org.primefaces.extensions.integrationtests.tabview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.AbstractPrimePage;
import org.primefaces.extensions.selenium.AbstractPrimePageTest;
import org.primefaces.extensions.selenium.component.CommandButton;
import org.primefaces.extensions.selenium.component.TabView;
import org.primefaces.extensions.selenium.component.model.Tab;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TabView001Test extends AbstractPrimePageTest {

    @Test
    public void test(Page page) {
        // Arrange
        TabView tabView = page.tabView;

        // Assert - part 1
        List<Tab> tabs = tabView.getTabs();
        Assertions.assertNotNull(tabs);
        Assertions.assertEquals(tabs.size(), 3);
        AtomicInteger cnt = new AtomicInteger(0);
        tabs.forEach(tab -> {
                    Assertions.assertNotNull(tab.getHeader());
                    Assertions.assertNotNull(tab.getContent());
                    Assertions.assertEquals(tab.getIndex(), cnt.getAndIncrement());
                });
        Assertions.assertEquals(tabView.getTabs().get(0).getTitle(), "Tab1");
        Assertions.assertEquals(tabView.getTabs().get(1).getTitle(), "Tab2");

        Assertions.assertEquals(tabView.getActiveTab().getIndex(), 0);
        Assertions.assertEquals(tabView.getActiveTab().getTitle(), "Tab1");

        // Act
        tabView.toggleTab(2);

        // Assert - part 2
        assertNoJavascriptErrors();
        Assertions.assertEquals(tabView.getActiveTab().getIndex(), 2);
        Assertions.assertEquals(tabView.getActiveTab().getTitle(), "Tab3");
    }

    public static class Page extends AbstractPrimePage {
        @FindBy(id = "form:tabview")
        TabView tabView;

        @FindBy(id = "form:button")
        CommandButton button;

        @Override
        public String getLocation() {
            return "tabview/tabView001.xhtml";
        }
    }
}
