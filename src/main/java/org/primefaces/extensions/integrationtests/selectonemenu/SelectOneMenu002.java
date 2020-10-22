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

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
@Data
public class SelectOneMenu002 implements Serializable {

    private static final long serialVersionUID = 6109117006170737298L;

    private List<SelectItem> cars;
    private String car;

    @PostConstruct
    public void init() {
        //cars
        SelectItemGroup g1 = new SelectItemGroup("<span style=\"color:darkgreen\">German</span> Cars");
        g1.setEscape(false);
        g1.setDescription("High quality cars from good-old germany.");
        SelectItem mercedes = new SelectItem("Mercedes", "Mer<span style=\"color:red\">cedes</span>");
        mercedes.setEscape(false);
        g1.setSelectItems(new SelectItem[] {new SelectItem("BMW", "BMW"), mercedes, new SelectItem("Volkswagen", "Volkswagen")});

        SelectItemGroup g2 = new SelectItemGroup("American <Cars>");
        g2.setSelectItems(new SelectItem[] {new SelectItem("Chrysler", "Chry<sler"), new SelectItem("blank", "&nbsp;"), new SelectItem("GM", "GM"), new SelectItem("Ford", "Ford")});

        cars = new ArrayList<SelectItem>();
        cars.add(g1);
        cars.add(g2);
    }

}
