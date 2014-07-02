package org.apache.hadoop.yarn.api.records;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.protocolrecords.AllocateResponse;

/**
 * Command sent by the Resource Manager to the Application Master in the
 * AllocateResponse
 * @see AllocateResponse
 */
@Public
@Unstable
public enum AMCommand{
    /**
     * Sent by Resource Manager when it is out of sync with the AM and wants
     * the AM get back in sync.
     */
    AM_RESYNC,

    /**
     * Sent by Resource Manager when it wants the AM to shutdown. Eg. when the
     * node is going down for maintenance. The AM should save any state and
     * prepare to be restarted at a later time.
     */
    AM_SHUTDOWN
}
