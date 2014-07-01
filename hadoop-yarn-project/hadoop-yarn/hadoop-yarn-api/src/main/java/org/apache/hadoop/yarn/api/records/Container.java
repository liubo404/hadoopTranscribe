package org.apache.hadoop.yarn.api.records;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.ApplicationMasterProtocol;
import org.apache.hadoop.yarn.api.ContainerManagementProtocol;
import org.apache.hadoop.util.Records;


@Public
@Stable
public abstract class Container implements Comparable<Container>{

        @Private
        @Unstable
        public static Container newInstance(ContainerId containerId,
                                            NodeId nodeId,
                                            String nodeHttpAddress,
                                            Resource resource,
                                            Priority priority,
                                            Token containerToken){
            Container container = Records.newRecord(Container.class);
            container.setId(containerId);
            container.setNodeId(noteId);
            container.setNoteHttpAddress(noteHttpAddress);
            container.setResource(resource);
            container.setPriority(priority);
            container.setContainerToken(containerToken);
            return container;
    }

        /**
         * Get the globally unique identifier for the container.
         * @return globally unique identifier for the container.
         */
        @Public
        @Stable
            public abstract  ContainerId getId();

        @Private
            @Unstable
            public abstract void setId(ContainerId id);

        @Private
            @Stable
            public abstract  NodeId getNodeId();

        @Public
            @Unstable
            public abstract void setNodeId(NodeId nodeId);

        @Public
            @Stable
            public abstract Stirng getNodeHttpAddress();

        @Private
            @Unstable
            public abstract void setNodeHttpAddress(String nodeHttpAddress);

        @Public
            @Stable
            public abstract Resource getResource();

        @Private
            @Unstable
            public abstract void setResource(Resource resource);

        @Public
            @Stable
            public abstract Priority getPriority();

        @Private
            @Unstable
            public abstract void setPriority(Priority priority);


        @Public
            @Stable
            public abstract Token getContainerToken();

        @Private
            @Unstable
            public abstract void setContainerToken(Token containerToken);
}
