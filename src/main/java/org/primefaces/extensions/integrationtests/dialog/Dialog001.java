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
package org.primefaces.extensions.integrationtests.dialog;

import lombok.Data;
import org.primefaces.event.CloseEvent;
import org.primefaces.extensions.integrationtests.datatable.ProgrammingLanguage;
import org.primefaces.extensions.integrationtests.datatable.ProgrammingLanguageService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
@Data
public class Dialog001 implements Serializable {

    private static final long serialVersionUID = -7518459955779385834L;

    private String text1;
    private String text2;
    private LocalDate date;

    private DialogData dialogData;

    @PostConstruct
    public void init() {
        date = LocalDate.now();
        dialogData = new DialogData();
    }

    public void submit() {
        FacesMessage msg = new FacesMessage("Submit", "text2: " + this.text2);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void showDialog() {
        dialogData.setText(this.text2);
    }

    public void dialogOk() {
        this.text2 = dialogData.getText();

        FacesMessage msg = new FacesMessage("Dialog - OK", "text2: " + this.text2);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void handleClose(CloseEvent event) {
        FacesMessage msg = new FacesMessage("Dialog - close-event", "text2: " + this.dialogData.getText());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
