package org.apache.hadoop.yarn.api.protocolrecords;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.ApplicationHistoryProtocol;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptReport;
import org.apache.hadoop.yarn.util.Records;

@Public
@Unstable
public abstract class GetApplicationAttemptReportResponse {

    @Public
    @Unstable
    public static GetApplicationAttemptReportResponse newInstance(
                              ApplicationAttemptReport ApplicationAttemptReport) {
        GetApplicationAttemptReportResponse response =
            Records.newRecord(GetApplicationAttemptReportResponse.class);
        response.setApplicationAttemptReport(ApplicationAttemptReport);
        return response;
    }

    @Public
    @Unstable
    public abstract ApplicationAttemptReport getApplictionAttemptReport();

    @Public
    @Unstable
    public abstract void setApplicationAttemptReport(ApplicationAttemptReport applicationAttemptReport);
}
