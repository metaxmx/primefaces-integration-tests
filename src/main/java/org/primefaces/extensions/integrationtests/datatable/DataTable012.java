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
package org.primefaces.extensions.integrationtests.datatable;

import lombok.Data;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Data
public class DataTable012 implements Serializable {

    private static final long serialVersionUID = -6124655299776064944L;

    private List<ProgrammingLanguage> progLanguages1;
    private List<ProgrammingLanguage> progLanguages2;

    private SortMeta sortBy;

    @Inject
    private ProgrammingLanguageService service;

    @PostConstruct
    public void init() {
        progLanguages1 = service.getLangs();
        progLanguages2 = service.getLangs();

        sortBy = SortMeta.builder().field("name").order(SortOrder.ASCENDING).build();
    }

    public void resetTable1() {
        DataTable dataTable = (DataTable)FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:datatable1");
        dataTable.reset();

        progLanguages1 = service.getLangs(); //progLanguages may have been sorted from DataTable
    }

    public void resetTable2() {
        DataTable dataTable = (DataTable)FacesContext.getCurrentInstance().getViewRoot().findComponent("form2:datatable2");
        dataTable.reset();

        progLanguages2 = service.getLangs(); //progLanguages may have been sorted from DataTable
    }

}