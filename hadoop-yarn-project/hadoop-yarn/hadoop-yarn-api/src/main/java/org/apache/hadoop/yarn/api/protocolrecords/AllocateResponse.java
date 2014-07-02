package org.apache.hadoop.yarn.api.protocolrecords;

import java.util.List;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apahce.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceStability.Evolving;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.ApplicationMasterProtocol;
import org.apache.hadoop.yarn.api.records.AMCommand;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.ContainerResourceDecrease;
import org.apache.hadoop.yarn.api.records.ContainerResourceIncrease;
import org.apache.hadoop.yarn.api.records.ContainerStatus;
import org.apache.hadoop.yarn.api.records.NMToken;
import org.apache.hadoop.yarn.api.records.NodeReport;
import org.apache.hadoop.yarn.api.records.PreemptionMessage;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.util.Records;

@Public
@Stable
public abstract class AllocateResponse{

    @Public
    @Stable
    public static AllocateResponse newInstance(int responseId,
                                     List<ContainerStatus> completedContainers,
                                               List<Container> allocatedContainers,
                                               List<NodeReport> updatedNodes,
                                               Resource availResources,
                                               AMCommand command,
                                               int numClusterNodes,
                                               PreemptionMessage preempt,
                                               List<NMToken> nmTokens){
        AllocateResponse response = Records.newRecord(AllocateResponse.class);
        response.setNumClusterNodes(numClusterNodes);
        response.setResponseId(responseId);
        response.setCompletedContainerStatues(completedContainers);
        response.setAllocatedContainers(allocatedContainers);
        response.setUpdatedNodes(updatedNodes);
        response.setAvailableResources(availResources);
        response.setAMCommand(command);
        response.setPreemptionMessage(preempt);
        response.setNMTokens(nmTokens);
        return response;
    }

    @Public
    @Stable
    public static AllocateResponse newInstance(int responseId,
                                        List<ContainerStatus> completedConatiners,
                                               List<Container> alloatedContainers,
                                               List<NodeReport> updatedNodes,
                                               Resource availResource,
                                               AMCommand command,
                                               int numClusterNodes,
                                               PreemptiongMessage preempt,
                                               List<NMToken> nmTokens,
                              List<ConatinerResourceIncrease> increasedContainers,
                              List<ContainerResourceDecrease> decreasedContainers){
        AllocateResponse response = newInstance(responseId, completedContainers,
                                                allocatedContainers,updatedNodes,
                                                availResources,command,
                                                numClusterNodes,preempt,nmTokens);
        response.setIncreasedContainers(increasedContainers);
        response.setDecreasedContainers(decreasedContainers);
        return response;
    }

    @Public
    @Stable
    public abstract AMCommand getAMCommand();

    @Private
    @Unstable
    public abstract void setAMCommand(AMCommand command);

    @Public
    @Stable
    public abstract int getResponseId();

    @Private
    @Unstable
    public abstract void setResponseId(int responseId);

    @Public
    @Stable
    public abstract List<Container> getAllocatedContainers();

    @Private
    @Unstable
    public abstract void setAllocatedContainers(List<Container> containers);

    @Public
    @Stable
    public abstract Resource getAvailableResources();

    @Private
    @Unstable
    public abstract void setAvailableResources(Resource limit);

    @Public
    @Stable
    public abstract List<ContainerStatus> getCompletedContainersStatus();

    @Private
    @Unstable
    public abstract void setCompletedContainersStatus(List<ContainerStatus> containers);

    @Public
    @Stable
    public abstract List<NodeReport> getUpdatedNodes();

    @Private
    @Unstable
    public abstract void setUpdatedNodes(final List<NodeReport> updatedNodes);

    @Public
    @Stable
    public abstract int getNumClusterNodes();

    @Private
    @Unstable
    public abstract void setNumClusterNodes(int numNodes);

    /**
     * Get the description of containers owned by the AM, but requested back by
     * the cluster. Note thate the RM may have an inconsistent view of the
     * resouces owned b te AM. These messages are advisory, and them AM may
     * elect to ignore them.
     *
     * The message is a snapshot of the resources the RM wants back from the AM.
     * While demand persists, the RM will repeat its request; applications should
     * not interpret on top of previouse messages. Resources requested consistently
     * over some duration may be forcibily killed by the RM.
     * @return A specification of the resources to reclaim from this AM.
     */
    @Public
    @Evolving
    public abstract PreemptionMessage getPreemptionMessage();

    @Private
    @Unstable
    public abstract void setPreemptionMessage(PreemptionMessage request);

    @Public
    @Stable
    public abstract List<NMToken> getNMTokens();

    @Private
    @Unstable
    public abstract void setNMTokens(List<NMToken> nmTokens);

    @Public
    @Stable
    public abstract List<ContainerResourceIncrease> getIncreasedContainers();

    @Private
    @Unstable
    public abstract void setIncreasedContainers(
                           List<ContainerResourceIncrease> increasedContainers);

    /**
     * Get the list of newly decreased containers by NodeManager
     */
    @Public
    @Stable
    public abstract List<ContainerResourceDecrease> getDecreasedContainers();

    /**
     * Set the list of newly decreased containers by NodeManager
     */
    @Private
    @Unstable
    public abstract void setDecreasedContainers(List<ContainerResourceDecrease> decreasedContainers);
}
