/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.guvnor.server;

import org.drools.guvnor.server.security.PackageUUIDType;
import org.drools.repository.AssetItem;
import org.drools.repository.RepositoryFilter;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.security.Identity;

public class AssetItemFilter implements RepositoryFilter {

    public boolean accept(Object artifact, String action) {
        if (!(artifact instanceof AssetItem))
            return false;

        // for GWT hosted mode - debug only
        if (!Contexts.isSessionContextActive()) {
            return true;
        } else {
            return Identity.instance().hasPermission(new PackageUUIDType(((AssetItem) artifact).getPackage().getUUID()), action);
        }
    }

}
