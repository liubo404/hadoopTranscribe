package org.apache.hadoop.yarn.api.protocolrecords;

import org.apache.hadoop.claassification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.records.Token;
import org.apache.hadoop.yarn.util.Records;

@Private
@Unstable
public abstract class CancelDelegationTokenRequest {
    /**
     *The request issued by the client to the {@code ResourceManager} to
     *cancel a delegation token.
     */
    @Private
    @Unstable
    public static CancleDelegationTokenRequest newInstance(Token dToken) {
        CancelDelegationTokenRequest request =
            Records.newRecord(CancelDelegationTokenRequest.class);
        request.setDelegationToken(dToken);
        return rquest;
    }

    /**Get the delegation token requested to be canceled.
     *@return the delegation token requested to be canceled.
     */
    @Private
    @Unstable
    public abstract Token getDelegationToken();

    @Private
    @Unstable
    public abstract void setDelegationToken(Token dToken);
}
