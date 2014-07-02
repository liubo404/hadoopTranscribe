package org.apache.hadoop.yarn.api;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.protocolrecords.CancelDelegationTokenRequest;
import org.apache.hadoop.yarn.api.protocolrecords.CancelDelegationTokenResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptReportRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptReportResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationAttemptsResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationReportRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationReportResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationsResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainerReportRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetContainerReportResponse;
import org.apche.hadoop.yarn.api.protocolrecords.GetContainersRequest;
import org.apche.hadoop.yarn.api.protocolrecords.GetContainersResponse;
import org.apche.hadoop.yarn.api.protocolrecords.GetDelegationTokenRequest;
import org.apche.hadoop.yarn.api.protocolrecords.GetDelegationTokenResponse;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptId;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptReport;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.api.records.ContainerReport;
import org.apache.hadoop.yarn.api.records.Token;
import org.apache.hadoop.yarn.api.exceptions.YarnException;

/**
 *<p>
 * The protocol between clients and the <code>ApplicationHistoryServer</code> to
 * get the information of completed applications etc.
 * </p>
 */
@Public
@Unstable
public interface ApplicationHistoryProtocol {

    /**
     * The interface used by clients to get a report of an Application from ResourceManager
     *
     * The client, via {@link GetApplicationReportRequest} provides the {@link ApplicationId} of the
     * application.
     *
     * In secure mode, the ApplicationHistoryServer verifies access to the application, queue etc.
     * before accepting the request.
     *
     * The ApplicationHistoryServer response with a {@link GetApplicationReportRequest} which includes
     * the {@link ApplicationReport} for the application.
     *
     * If the usr does not have <code>VIEW_APP</code> access then the following fields in the report
     * will be set to stubbed values:
     *<ul>
     *<li>host - set to "N/A"</li>
     *<li>RPC port - set to -1</li>
     *<li>client token - set to "N/A"</li>
     *<li>diagnostic - set to "N/A"</li>
     *<li>tracking URL - set to "N/A"</li>
     *<li>original tracking URL - set to "N/A"</li>
     *<li>resource usage report - all values are -1</li>
     *</ul>
     *
     *@param request
     *         request for an application
     *@return application report
     *@throws YarnException
     *@throws IOException
     */
    @Public
    @Unstable
    public GetApplicationReportResponse getApplicationReport(GetApplicationReportRequest request)
        throws YarnException, IOException;

    @Public
    @Unstable
    public GetApplicationsResponse getApplications(GetApplicationsRequest request)
        throws YarnException, IOException;
    @Public
    @Unstable
    public GetApplicationAttemptReportResponse getApplicationAttemptReport(
                                                   GetApplicationAttemptReportRequest request)
        throws YarnException, IOException;

    @Public
    @Unstable
    public GetApplicationAttemptsResponse getApplicationAttempts(
                                                                 GetApplicationAttemptsRequest request)
        throws YarnException, IOException;
    @Public
    @Unstable
    public GetContainerReportResponse getContainerReport(
                                                         GetContainerReportRequest request)
        throws YarnException, IOException;

    @Public
    @Unstable
    public GetContainersResponse getContainers(GetContainersRequest request)
        throws YarnException, IOException;

    @Public
    @Unstable
    public GetDelegationTokenResponse getDelegationToken(GetDelegationTokenRequest request)
        throws YarnException, IOException;

    /**
     * Renew an existing delegation token.
     */
    @Public
    @Untable
    public RenewDelegationTokenResponse renewDelegationToken(RenewDelegationTokenRequest request)
        throws YarnException, IOException;

    /**
     * Cancel an existing delegation token.
     * @param request
     *          the delegation token to be cancelled.
     * @return an empty response.
     * @throws YarnException
     * @throws IOException
     */
    @Public
    @Unstable
    public CancelDelegationTokenResponse cancelDelegationToken(CancelDelegationTokenRequest request)
        throws YarnException, IOException;
}
