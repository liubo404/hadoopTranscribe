package org.apache.hadoop.yarn.api.protocolrecords;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.ApplicationHistoryProtocol;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptId;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptReport;
import org.apache.hadoop.yarn.util.Records;


@Public
@Static
public abstract class GetApplicationAttemptReportRequest {

    @Public
    @Unstable
    public static GetApplicationAttemptReportRequest newInstance(
                           ApplicationAttemptId applicationAttemptId) {
        GetApplicationAttemptReportRequest request =
            Records.newRecord(GetApplicationAttemptReportRequest.class);
        request.setApplicationAttemptId(applicationAttemptId);
        return request;
    }

    @Public
    @Unstable
    public abstract ApplicationAttemptId getApplicationAttemptId();

    @Public
    @Unstable
    public abstract void setApplicationAttemptId(ApplicationAttemptId applictionAttemptId);
}
