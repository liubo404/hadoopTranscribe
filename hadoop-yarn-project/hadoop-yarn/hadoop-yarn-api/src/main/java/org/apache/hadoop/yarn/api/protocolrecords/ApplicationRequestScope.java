package org.apache.hadoop.yarn.api.protocolrecords;

import org.apache.hadoop.yarn.classification.InterfaceAudience;
import org.apache.hadoop.yarn.classification.InterfaceStability;

@InterfaceAudience.Public
@InterfaceAudience.Unstable
public enum ApplicationRequestScope {
    /**All jobs*/
    ALL,
    /** Jobs viewable by current user*/
    VIEWABLE,
    /** Jobs owned by current user */
    OWN
}
