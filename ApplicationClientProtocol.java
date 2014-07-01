package org.apache.hadoop.yarn.api;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.io.retry.Idempotent;
import org.apache.hadoop.yarn.api.protocolrecords.CancelDelegationTokenRequest;
import org.apache.hadoop.yarn.api.protocolrecords.CancelDelegationTokenResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptReportRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptReportResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptsResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationReportRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationReportResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetClusterMetricsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetClusterMetricsResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetClusterNodesRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetClusterNodesResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainerRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainerResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainersRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainersResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetDelegationTokenRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetDelegationTokenResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetQueueInfoRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetQueueInfoResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetQueueUserAclsInfoRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetQueueUserAclsInfoResponse;
import org.apache.hadoop.yarn.api.protocolrecords.KillApplicationRequest;
import org.apache.hadoop.yarn.api.protocolrecords.KillApplicationResponse;
import org.apache.hadoop.yarn.api.protocolrecords.MoveApplicationAcrossQueuesRequest;
import org.apache.hadoop.yarn.api.protocolrecords.MoveApplicationAcrossQueuesResponse;
import org.apache.hadoop.yarn.api.protocolrecords.RenewDelegationTokenRequest;
import org.apache.hadoop.yarn.api.protocolrecords.RenewDelegationTokenResponse;
import org.apache.hadoop.yarn.api.protocolrecords.SubmitApplicationRequest;
import org.apache.hadoop.yarn.api.protocolrecords.SubmitApplicationResponse;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptId;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptReport;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.api.records.ContainerReport;
import org.apache.hadoop.yarn.api.records.NodeReport;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.api.records.ResourceRequest;
import org.apache.hadoop.yarn.api.records.Token;
import org.apache.hadoop.yarn.api.records.YarnClusterMetrics;
import org.apache.hadoop.yarn.api.records.ApplicationSubmissionContext;
import org.apache.hadoop.yarn.api.records.YarnException;
import org.apache.hadoop.yarn.api.records.ApplicationNotFoundException;

/**
 *The protocol between clients and the <code>ResourceManager</code>
 *to submit/abort jobs and to get information on applications, cluster metrics,
 *nodes, queues and ACLs.
 */
@Public
@Stable
public interface ApplicationClientProtocol{
    /**
     *The interface used by client to obtain a new {@link Application} for
     *submitting new applications.
     *
     *The <code>ResourceManager</code> responds with a new, monotonically
     *increasing,{@link ApplicationId} which is used by the client to submit
     *a new application.
     *
     *The<code>ResourceManager</code> also responds with details such as maximum
     *resource capabilities in the cluster as specified in
     *{@link GetNewApplicationResponse}.
     */
    @Public
    @Stable
    @Idempotent
    public GetNewApplicationResponse getNewApplication(
                     GetNewApplicationRequest request)
    throws YarnException, IOException;

    /**
       The interface used by clients to submit a new application to the
       RescourceManager.

       The client is required to provide details such as queue,
       {@link Resouces} required to run the ApplicationMaster,
       the equivalent of ContainerLaunchContext for launching the
       ApplicationMaster etc. via the SubmitApplicationRequest.

       Currently the ResourceManager sends an immediate (empty)
       SubmitApplicationResponse on aacepting the submission and throws
       an exception if it rejects the submission. However, this call needs to be
       followed by {@link #getApplicationReport(GetApplicationReposrtRequest)}
       to make sure that the application gets properly submitted - obtaining a
       {@link SubmitApplicationResponse} from ResourceManager doesn't guarantee
       that RM 'remembers' this application beyond failover or restartt. If RM
       failover or RM restart happens before ResourceManager saves the
       application's state successfully, the subsequent
       {@link #getApplicationReport(GetApplicationReportRequest)} will throw
       a {@link ApplicationNotFoundException}. The Clients need to be re-submit
       the application with the same {@link ApplicationSubmissionContext} when
       it encounters the {@link ApplicationNotFoundException} on the
       {@link #getApplicationReport(GetApplicationReportRequest)} call.

       During the submission process, it checkes whether the application already
       exists. If the application exists, it will simply return
       SubmissionApplicationResponse.

       In secure mode, the ResourceManager verifies access to queues etc. before
       accepting the application submission.

       @param request request to submit a new application
       @return (empty) response on accepting the submission
       @throws YarnException
       @throws IOException
       @throws InvalidResourceRequestException
           The exception is thrown when a {@link ResourceRequest} is out of
           the range of the configured lower and upper resource boundaries.
       @see #getNewApplication(GetNewApplicationRequest)
     */
    @Public
    @Stable
    @Idempotent
    public SubmitApplicationResponse submitApplication(
                     SubmitApplicationRequest request)
        throws YarnException, IOException;

    /**
     * <p> The interface used by clients to request the
     * <code>ResourceManager</code> to abort submitted application.</p>
     */
    @Public
    @Stable
    @Idempotent
    public KillApplicationResponse forceKillApplication(
                                    KillApplicationRequest request)
        throws YarnException, IOException;

    /**
     * <p> The interface used by clients to get a report of an Application
     * from the <code>ResourceManager</code>.</p>
     */

    @Public
    @Stable
    @Idempotent
    public GetApplicationReportResponse getApplicationReport(
                                     GetApplicationReportRequest request)
        throws YarnException, IOException;


    @Public
    @Stable
    @Idempotent
    public GetClusterMetricsResponse getClusterMetrics(
                                       GetClusterMetricsRequest request)
        throws YarnException, IOException;


    @Public
    @Stable
    @Idempotent
    public GetApplicationResponse getApplications(
                                                  GetApplicationRequest request)
        throws YarnException, IOException;

}
