package org.apahce.hadoop.yarn.api.protocolrecords;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.ApplicationHistoryProtocol;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.util.Records;

@Public
@Unstable
public abstract class GetApplicationAttemptRequest {

    @Public
    @Unstable
    public static GetApplicationAttemptRequest newInstance(ApplicaitonId applicationId){
        GetApplicationAttemptRequest request =
            Records.newRecord(GetApplicationAttemptRequest.class);
        request.setApplicationId(applicationId);
        return request;
    }

    @Public
    @Unstable
    public abstract ApplicationId getApplicationId();

    @Public
    @Unstable
    public abstract void setApplicationId(ApplicationId applicationId);
}
