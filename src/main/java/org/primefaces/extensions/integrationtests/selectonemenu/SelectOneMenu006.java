/**
 * Copyright 2011-2020 PrimeFaces Extensions
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.extensions.integrationtests.selectonemenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.util.LangUtils;

import lombok.Data;

@Named
@ViewScoped
@Data
public class SelectOneMenu006 implements Serializable {

    private static final long serialVersionUID = -7798312444085660208L;

    private String console;
    private List<SelectItem> availableConsoles;

    @PostConstruct
    public void init() {
        availableConsoles = new ArrayList<>();
        SelectItemGroup group1 = new SelectItemGroup("Older");
        group1.setSelectItems(new SelectItem[] {new SelectItem("XBOXONE", "Xbox One"), new SelectItem("PS3", "PlayStation 3"),
                    new SelectItem("WII", "Wii U")});
        availableConsoles.add(group1);

        SelectItemGroup group2 = new SelectItemGroup("Newer");
        group2.setSelectItems(new SelectItem[] {new SelectItem("XBOXX", "Xbox X"), new SelectItem("PS5", "PlayStation 5"),
                    new SelectItem("SWITCH", "Nintendo Switch")});
        availableConsoles.add(group2);
    }

    public void submit() {
        if (!LangUtils.isValueBlank(console)) {
            FacesMessage msg = new FacesMessage("Console", console);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
