package org.apache.hadoop.yarn.api;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.io.retry.AtMostOnce;
import org.apache.hadoop.yarn.api.protocolrecords.AllocateRequest;
import org.apache.hadoop.yarn.api.protocolrecords.AllocateResponse;
import org.apache.hadoop.yarn.api.protocolrecords.FinishApplicationMasterRequest;
import org.apache.hadoop.yarn.api.protocolrecords.RegisterApplicationMasterRequest;
import org.apache.hadoop.yarn.api.protocolrecords.RegisterApplicationMasterResponse;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.recodes.ResourceRequest;
/**
   <p> The protocol between a live instance of <code>ApplicationMaster</code>
   and the <code>ResourceManager</code>.</p>

   <p>This is used by the <code>ApplicationMaster</code> to register/unregister
   and to request and botain resources in the cluster from the
   <code>ResourceManager</code>.</p>
 */
@Public
@Stable
public interface ApplicationMasterProtocol {
    /**
     *<p>
     *The interface used by a new <code>ApplicationMaster</code> to register with
     *the <code>ResourceManager</code>.
     *</p>
     *
     *<p>
     *The <code>ResourceManager</code> responds with critical details such as
     *maximun resource capabilities in the cluster as specified in
     *{@link RegisterApplicationMasterResponse}.
     *</p>
     *@param request
     *       registration request
     *@return registration response
     *@throws YarnException
     *@throws IOException
     *@throws InvalidApplicationMasterRequestException
     * The exception is thrown when a ApplicationMaster tries to
     * register more than once.
     *@see RegisterApplicationMasterRequest
     *@see RegisterApplicationMasterResponse
     */
    @Public
    @Stable
    public RegisterApplicationMasterResponse registerApplicationMaster(
               RegisterApplicationMasterRequest request)
        throws YarnException,IOException;

    /**
     *<p>the interface used by an <code>ApplicationMaster</code> to notify the
     *<code>ResourceManager</code> about its completion (success or failed).</p>
     *
     *<p>The <code>ApplicationMaster</code> has to provice details such as
     * final state, diagnostics(in case of failures) etc. as specified in
     *{@link FinishApplicationMasterRequest}.</p>
     *
     *<p>The <code>ResourceManager</code> responds with
     *{@link FinishApplicationMasterResponse}.</p>
     *
     *@param request completion request
     *@return completion response
     *@throws YarnException
     *@throws IOException
     *@see FinishApplicationMasterRequest
     *@see FinishApplicationMasterResponse
     */
    @Public
    @Stable
    public FinishApplicationMasterRequest finishApplicationMaster(
           FinishApplicationMasterRequest request)
        throws YarnException, IOException;

    /**
     *<p>
     * The main interface between an <code>ApplicationMaster</code> and the
     *<code>ResourceManager</code>.
     *</p>
     *
     *<p>
     *The <code>ApplicationMaster</code> uses the interface to provide a list of
     *{@link ResourceRequest} and returns unused {@link Container} allocated to
     *it via {@link AllocateRequest}. Optionally, the
     *<code>ApplicationMaster</code> can also <em>blacklist</em> resources with
     *it doesn't want to use.
     *</p>
     *This also doubles up as a <em>heartbeat</em> to let the
     *<code>Resourcemanager</code> know thant the <code>ApplicationMaster</code>
     *is alive. Thus, applications should periodically make this call to be kept
     *alive. The frequency depends on
     *{@link YarnConfiguration#RM_AM_EXPIRY_INTERVAL_MS} which defaults to
     *{@link YarnConfiguration#DEFAULT_RM_AM_EXPIRY_INTERVAL_MS}.
     *</p>
     *
     *<p>
     *The <code>ResourceManager</code> responds with list of allocated
     *{@link Container}, status of completed containers and headroom information
     *for the application.
     *</p>
     *
     *@param request
     *       allocation request
     *@return allocation response
     *@throws YarnException
     *@throws IOException
     *@throws InvalidApplicationMasterRequestException
     * This exception is thrown when a ApplicationMaster calls allocate
     * without registering first.
     *@throws InvalidResourcesBlacklistRequestException
     * This exception is thrown when an application provices an invalid
     * specification for blacklist of resources.
     *@throws InvalidResourceRequestException
     *        This exception is thrown when a {@link ResourceRequest} is out of
     *        the range of the configured lower and upper limits on the
     *        resources.
     *@see AllocateRequest.
     *@see AllocateResponse.
     */
    @Public
    @Stable
    @AtMostOnce
    public AllocateRespose allocate(AllocateRequest request)
        throws YarnException, IOException;
}
