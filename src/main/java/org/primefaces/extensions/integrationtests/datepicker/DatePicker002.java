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
package org.primefaces.extensions.integrationtests.datepicker;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;

@Named
@ViewScoped
@Data
public class DatePicker002 implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private LocalDate localDate1;
    private LocalDate localDate2;

    @PostConstruct
    public void init() {
        localDate1 = LocalDate.of(1978, 2, 19);
        localDate2 = LocalDate.of(1978, 2, 19);
    }
}
