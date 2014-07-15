package org.apache.hadoop.yarn.api.protocolrecords;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classificaion.InterfaceStability.Stable;
import org.apache.hadoop.yarn.api.ApplicationMasterProtocol;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.util.Records;

@Public
@Stable
public abstract class FinishApplicationMasterRequest {
    @Public
    @Stable
    public static FinishApplicationMasterRequest newInstance(
       FinalApplicationStatus fianlAppStatus, String diagnostics, String url){
        FinalApplicationMasterRequest request =
            Records.newRecord(FinishApplicationMasterRequest.class);
        request.setFinalApplicationStatus(finalAppStatus);
        request.setDiagnostics(diagnostics);
        request.setTrackingUrl(url);
        return request;
    }

    @Public
    @Stable
    public abstract FinalApplicationStatus getFinalApplicationStatus();

    @Public
    @Stable
    public abstract void setFinalApplicationStatus(FinalApplicationStatus finalState);

    @Public
    @Stable
    public abstract String getDiagnostics();

    @Public
    @Stable
    public abstract void setDiagnostics(String diagnostics);

    @Public
    @Stable
    public abstract String getTrackingUrl();

    @Public
    @Stable
    public abstract void setTrackingUrl(String url);
}
