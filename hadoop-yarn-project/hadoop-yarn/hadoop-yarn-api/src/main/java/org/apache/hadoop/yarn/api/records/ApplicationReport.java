package org.apache.hadoop.yarn.api.records;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.ApplicationClientProtocol;
import org.apache.hadoop.yarn.util.Records;

import java.util.Set;

@Public
@Stable
public abstract class ApplicationReport {

    @Private
    @Unstable
    public static ApplicationReport newInstance(ApplicationId applicationId,
                                       ApplicationAttemptId applicationAttemptId,
                                                String user,
                                                String queue,
                                                String name,
                                                String host,
                                                int rpcPort,
                                                Token clientToAMToken,
                                                YarnApplicationState state,
                                                String diagnostics,
                                                String url,
                                                long startTime,
                                                long finishTime,
                                                FinalApplicationStatus finalStatus,
                                      ApplicationResourceUsageReport appResources,
                                                String origTrackingUrl,
                                                float progress,
                                                String applicationType,
                                                Token amRmToken) {
        ApplicationReport report = Records.newRecord(ApplicationReport.class);
        report.setApplicationId(applicationId);
        report.setCurrentApplicationAttemptId(applicationAttemptId);
        report.setUser(user);
        report.setQueue(queue);
        report.setName(name);
        report.setHost(host);
        report.setRpcPort(rpcPort);
        report.setClientToAMToken(clientToAMToken);
        report.setYarnApplicationState(state);
        report.setDiagnostics(diagnostics);
        report.setTrackingUrl(url);
        report.setStartTime(startTime);
        report.setFinishTime(finishTime);
        report.setFinalApplicationStatus(finalStatus);
        report.setApplicationResourceUsageReport(appResources);
        report.setOriginalTrackingUrl(origTrackingUrl);
        report.setProgress(progress);
        report.setApplicationType(applicationType);
        report.setAMRMToken(amRmToken);
        return report;
    }
}
