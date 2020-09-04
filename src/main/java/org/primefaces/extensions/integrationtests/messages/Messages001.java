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
package org.primefaces.extensions.integrationtests.messages;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Named
@ViewScoped
@Data
public class Messages001 implements Serializable {
    
    private static final long serialVersionUID = -3664548553854145624L;

    private String val1;

    @NotNull
    private String val2;

    @PostConstruct
    public void init() {
    }

    public void action1() {
        FacesMessage msg = new FacesMessage("Action 1", "Action 1 - some info");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void action2() {
        FacesMessage msg = new FacesMessage("Action 2", "Action 2 - val1 invalid due to reason X");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("form:val1", msg);
    }
}
