package org.apache.hadoop.yarn.api.records;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;

/**
 * Application access type.
 */

@Public
@Stable
public enum ApplicationAccessType{

    /**
     * Access-type representing 'viewing' application. ACLs against this type
     * dictate who can 'view' some or all of the application related details.
     */
    VIEW_APP,

    /**
     * Access-type representing 'modifying' application. ACLs against this type
     * dictate who can 'modify' the application for e.g., by killing the
     * applicaiton.
     */
    MODIFY_APP;
}
