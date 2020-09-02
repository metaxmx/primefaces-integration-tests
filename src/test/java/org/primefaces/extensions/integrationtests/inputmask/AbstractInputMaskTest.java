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
package org.primefaces.extensions.integrationtests.inputmask;

import org.primefaces.extensions.selenium.AbstractPrimePageTest;

public abstract class AbstractInputMaskTest extends AbstractPrimePageTest {

    public static final String AUTO_CLEAR = "clearMaskOnLostFocus";
    public static final String OPTIONAL_MASK = "(999) 999-9999[ x99999]";

}
