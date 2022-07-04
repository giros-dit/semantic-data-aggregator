package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601;
import com.google.common.util.concurrent.ListenableFuture;
import edu.umd.cs.findbugs.annotations.CheckReturnValue;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.RpcService;
import org.opendaylight.yangtools.yang.common.RpcResult;

/**
 * Interface for implementing the following YANG RPCs defined in module <b>ietf-netconf</b>
 * <pre>
 * rpc get-config {
 *   input input {
 *     container source {
 *       choice config-source {
 *         case candidate {
 *           leaf candidate {
 *             if-feature candidate;
 *             type empty;
 *           }
 *         }
 *         case running {
 *           leaf running {
 *             type empty;
 *           }
 *         }
 *         case startup {
 *           leaf startup {
 *             if-feature startup;
 *             type empty;
 *           }
 *         }
 *       }
 *     }
 *     anyxml filter {
 *       nc:get-filter-element-attributes;
 *     }
 *   }
 *   output output {
 *     anyxml data {
 *     }
 *   }
 * }
 * rpc edit-config {
 *   input input {
 *     container target {
 *       choice config-target {
 *         case candidate {
 *           leaf candidate {
 *             if-feature candidate;
 *             type empty;
 *           }
 *         }
 *         case running {
 *           leaf running {
 *             if-feature writable-running;
 *             type empty;
 *           }
 *         }
 *       }
 *     }
 *     leaf default-operation {
 *       type enumeration {
 *         enum merge {
 *         }
 *         enum replace {
 *         }
 *         enum none {
 *         }
 *       }
 *       default merge;
 *     }
 *     leaf test-option {
 *       if-feature validate;
 *       type enumeration {
 *         enum test-then-set {
 *         }
 *         enum set {
 *         }
 *         enum test-only {
 *         }
 *       }
 *       default test-then-set;
 *     }
 *     leaf error-option {
 *       type enumeration {
 *         enum stop-on-error {
 *         }
 *         enum continue-on-error {
 *         }
 *         enum rollback-on-error {
 *         }
 *       }
 *       default stop-on-error;
 *     }
 *     choice edit-content {
 *       case config {
 *         anyxml config {
 *         }
 *       }
 *       case url {
 *         leaf url {
 *           if-feature url;
 *           type inet:uri;
 *         }
 *       }
 *     }
 *   }
 * }
 * rpc copy-config {
 *   input input {
 *     container target {
 *       choice config-target {
 *         case candidate {
 *           leaf candidate {
 *             if-feature candidate;
 *             type empty;
 *           }
 *         }
 *         case running {
 *           leaf running {
 *             if-feature writable-running;
 *             type empty;
 *           }
 *         }
 *         case startup {
 *           leaf startup {
 *             if-feature startup;
 *             type empty;
 *           }
 *         }
 *         case url {
 *           leaf url {
 *             if-feature url;
 *             type inet:uri;
 *           }
 *         }
 *       }
 *     }
 *     container source {
 *       choice config-source {
 *         case candidate {
 *           leaf candidate {
 *             if-feature candidate;
 *             type empty;
 *           }
 *         }
 *         case running {
 *           leaf running {
 *             type empty;
 *           }
 *         }
 *         case startup {
 *           leaf startup {
 *             if-feature startup;
 *             type empty;
 *           }
 *         }
 *         case url {
 *           leaf url {
 *             if-feature url;
 *             type inet:uri;
 *           }
 *         }
 *         case config {
 *           anyxml config {
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 * rpc delete-config {
 *   input input {
 *     container target {
 *       choice config-target {
 *         case startup {
 *           leaf startup {
 *             if-feature startup;
 *             type empty;
 *           }
 *         }
 *         case url {
 *           leaf url {
 *             if-feature url;
 *             type inet:uri;
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 * rpc lock {
 *   input input {
 *     container target {
 *       choice config-target {
 *         case candidate {
 *           leaf candidate {
 *             if-feature candidate;
 *             type empty;
 *           }
 *         }
 *         case running {
 *           leaf running {
 *             type empty;
 *           }
 *         }
 *         case startup {
 *           leaf startup {
 *             if-feature startup;
 *             type empty;
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 * rpc unlock {
 *   input input {
 *     container target {
 *       choice config-target {
 *         case candidate {
 *           leaf candidate {
 *             if-feature candidate;
 *             type empty;
 *           }
 *         }
 *         case running {
 *           leaf running {
 *             type empty;
 *           }
 *         }
 *         case startup {
 *           leaf startup {
 *             if-feature startup;
 *             type empty;
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 * rpc get {
 *   input input {
 *     anyxml filter {
 *       nc:get-filter-element-attributes;
 *     }
 *   }
 *   output output {
 *     anyxml data {
 *     }
 *   }
 * }
 * rpc close-session {
 * }
 * rpc kill-session {
 *   input input {
 *     leaf session-id {
 *       type session-id-type;
 *     }
 *   }
 * }
 * rpc commit {
 *   if-feature candidate;
 *   input input {
 *     leaf confirmed {
 *       if-feature confirmed-commit;
 *       type empty;
 *     }
 *     leaf confirm-timeout {
 *       if-feature confirmed-commit;
 *       type uint32 {
 *         range 1..max;
 *       }
 *       units seconds;
 *       default 600;
 *     }
 *     leaf persist {
 *       if-feature confirmed-commit;
 *       type string;
 *     }
 *     leaf persist-id {
 *       if-feature confirmed-commit;
 *       type string;
 *     }
 *   }
 * }
 * rpc discard-changes {
 *   if-feature candidate;
 * }
 * rpc cancel-commit {
 *   if-feature confirmed-commit;
 *   input input {
 *     leaf persist-id {
 *       type string;
 *     }
 *   }
 * }
 * rpc validate {
 *   if-feature validate;
 *   input input {
 *     container source {
 *       choice config-source {
 *         case candidate {
 *           leaf candidate {
 *             if-feature candidate;
 *             type empty;
 *           }
 *         }
 *         case running {
 *           leaf running {
 *             type empty;
 *           }
 *         }
 *         case startup {
 *           leaf startup {
 *             if-feature startup;
 *             type empty;
 *           }
 *         }
 *         case url {
 *           leaf url {
 *             if-feature url;
 *             type inet:uri;
 *           }
 *         }
 *         case config {
 *           anyxml config {
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface IetfNetconfService
    extends
    RpcService
{




    /**
     * Invoke {@code get-config} RPC.
     *
     * <pre>
     *     <code>
     *         Retrieve all or part of a specified configuration.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code get-config}
     * @return output of {@code get-config}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<GetConfigOutput>> getConfig(GetConfigInput input);
    
    /**
     * Invoke {@code edit-config} RPC.
     *
     * <pre>
     *     <code>
     *         The &amp;lt;edit-config&amp;gt; operation loads all or part of a specified
     *         configuration to the specified target configuration.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code edit-config}
     * @return output of {@code edit-config}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<EditConfigOutput>> editConfig(EditConfigInput input);
    
    /**
     * Invoke {@code copy-config} RPC.
     *
     * <pre>
     *     <code>
     *         Create or replace an entire configuration datastore with the contents of another
     *         complete configuration datastore.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code copy-config}
     * @return output of {@code copy-config}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<CopyConfigOutput>> copyConfig(CopyConfigInput input);
    
    /**
     * Invoke {@code delete-config} RPC.
     *
     * <pre>
     *     <code>
     *         Delete a configuration datastore.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code delete-config}
     * @return output of {@code delete-config}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<DeleteConfigOutput>> deleteConfig(DeleteConfigInput input);
    
    /**
     * Invoke {@code lock} RPC.
     *
     * <pre>
     *     <code>
     *         The lock operation allows the client to lock the configuration system of a
     *         device.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code lock}
     * @return output of {@code lock}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<LockOutput>> lock(LockInput input);
    
    /**
     * Invoke {@code unlock} RPC.
     *
     * <pre>
     *     <code>
     *         The unlock operation is used to release a configuration lock, previously
     *         obtained with the 'lock' operation.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code unlock}
     * @return output of {@code unlock}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<UnlockOutput>> unlock(UnlockInput input);
    
    /**
     * Invoke {@code get} RPC.
     *
     * <pre>
     *     <code>
     *         Retrieve running configuration and device state information.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code get}
     * @return output of {@code get}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<GetOutput>> get(GetInput input);
    
    /**
     * Invoke {@code close-session} RPC.
     *
     * <pre>
     *     <code>
     *         Request graceful termination of a NETCONF session.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code close-session}
     * @return output of {@code close-session}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<CloseSessionOutput>> closeSession(CloseSessionInput input);
    
    /**
     * Invoke {@code kill-session} RPC.
     *
     * <pre>
     *     <code>
     *         Force the termination of a NETCONF session.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code kill-session}
     * @return output of {@code kill-session}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<KillSessionOutput>> killSession(KillSessionInput input);
    
    /**
     * Invoke {@code commit} RPC.
     *
     * <pre>
     *     <code>
     *         Commit the candidate configuration as the device's new current configuration.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code commit}
     * @return output of {@code commit}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<CommitOutput>> commit(CommitInput input);
    
    /**
     * Invoke {@code discard-changes} RPC.
     *
     * <pre>
     *     <code>
     *         Revert the candidate configuration to the current running configuration.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code discard-changes}
     * @return output of {@code discard-changes}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<DiscardChangesOutput>> discardChanges(DiscardChangesInput input);
    
    /**
     * Invoke {@code cancel-commit} RPC.
     *
     * <pre>
     *     <code>
     *         This operation is used to cancel an ongoing confirmed commit. If the confirmed
     *         commit is persistent, the parameter 'persist-id' must be given, and it must
     *         match the value of the 'persist' parameter.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code cancel-commit}
     * @return output of {@code cancel-commit}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<CancelCommitOutput>> cancelCommit(CancelCommitInput input);
    
    /**
     * Invoke {@code validate} RPC.
     *
     * <pre>
     *     <code>
     *         Validates the contents of the specified configuration.
     *     </code>
     * </pre>
     *
     *
     * @param input of {@code validate}
     * @return output of {@code validate}
     *
     */
    @CheckReturnValue
    ListenableFuture<RpcResult<ValidateOutput>> validate(ValidateInput input);

}

