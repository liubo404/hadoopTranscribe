package org.apache.hadoop.yarn.api.protocolrecords;

import org.apache.hadoop.classification.IntefaceAudience.Private;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.util.Records;

@Private
@Unstable
public abstract class CancelDelegationTokenResponse {
    @Private
    @Unstable
    public static CancelDelegationTokenResponse response =
        Records.newRecord(CancelDelegationTokenResponse.class);
    return response;
}
