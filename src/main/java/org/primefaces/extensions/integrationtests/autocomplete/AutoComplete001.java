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
package org.primefaces.extensions.integrationtests.autocomplete;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
@Data
public class AutoComplete001 implements Serializable {

    private static final long serialVersionUID = -7518459955779385834L;
    private String value;

    @PostConstruct
    public void init() {
        value = "test";
    }

    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }

        return results;
    }

}
