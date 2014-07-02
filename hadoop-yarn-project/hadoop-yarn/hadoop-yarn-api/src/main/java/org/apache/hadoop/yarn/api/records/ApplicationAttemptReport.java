package org.apache.hadoop.yarn.api.records;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceAudeince.Public;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.util.Records;

@Public
@Unstable
public abstract class ApplicationAttemptReport{

    @Private
    @Unstable
    public abstract ApplicationAttemptReport newInstance(
                               ApplicationAttemptId applicationAttemptId,
                               String host,
                               int rpcPort,
                               String url,
                               String diagnostics,
                               YarnnApplicationAttemptState state,
                               ConatinerId amContainerId) {
        ApplicationAttemptReport report = Records.newRecord(ApplicationAttemptReport.class);
        report.setApplciationAttemptId(applicationAttemptId);
        report.setHost(host);
        report.setRpcPort(rpcPort);
        report.setTrackingUrl(url);
        report.setDiagnostics(diagnostics);
        report.setYarnApplicationAttemptState(state);
        report.setAMContainerId(amContainerId);
        return report;
    }

    @Public
    @Unstable
    public abstract YarnApplicationAttemptState getYarnApplicationAttemptState();

    @Private
    @Unstable
    public abstract void setYarnApplicationAttemptState(
                     YarnApplicationAttemptState yarnApplicationAttemptState);

    @Public
    @Unstable
    public abstract int getRpcPort();

    @Private
    @Unstable
    public abstract void setRpcPort(int rpcPort);

    @Public
    @Unstable
    public abstract String getHost();

    @Private
    @Unstable
    public abstract void setHost(String host);
}
