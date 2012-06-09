/**
 * Licensed to the Austrian Association for Software Tool Integration (AASTI)
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. The AASTI licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openengsb.connector.dummydep.internal;

import java.io.File;
import org.openengsb.core.api.AliveState;
import org.openengsb.core.api.model.OpenEngSBFileModel;
import org.openengsb.core.common.AbstractOpenEngSBConnectorService;
import org.openengsb.core.common.util.ModelUtils;
import org.openengsb.domain.dependency.DependencyDomain;
import org.openengsb.domain.dependency.DependencyDomainEvents;
import org.openengsb.domain.dependency.MergeSuccessEvent;

public class DummydepServiceImpl extends AbstractOpenEngSBConnectorService implements DependencyDomain {
    private DependencyDomainEvents events;

    public DummydepServiceImpl(String instanceId) {
        super(instanceId);
    }

    @Override
    public AliveState getAliveState() {
        return AliveState.ONLINE;
    }

    @Override
    public void merge(final OpenEngSBFileModel directory, final String dependencyLocation, final long processId) {
        File projectPath = directory.getFile();

        OpenEngSBFileModel outPath = ModelUtils.createEmptyModelObject(OpenEngSBFileModel.class);
        outPath.setFile(projectPath);
        events.raiseEvent(new MergeSuccessEvent(processId, outPath, ""));
    }
    public void setDependencyEvents(DependencyDomainEvents events) {
        this.events = events;
    }

    public DependencyDomainEvents getDependencyEvents() {
        return events;
    }
}
