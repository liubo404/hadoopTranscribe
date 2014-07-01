package org.apache.hadoop.yarn.api;

import org.apahce.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Evolving;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.security.UserGroupInfomation;
import org.apache.hadoop.util.Shell;

@Public
@Evolving
public interface ApplicationConstants{
    /**
     * The environment variable for APP_SUBMIT_TIME. Set in AppMaster environment only
     */
    public static final String APP_SUBMIT_TIME_ENV = "APP_SUBMIT_TIME_ENV";

    /** The cache file into which container token is written
     */
    public static final String CONTAINE_TOKEN_FILE_ENV_NAME=UserGroupInformation.HADOOP_TOKEN_FILE_LOCATION;

    /**
     *Th environmental variable for APPLICATION_WEB_PROXY_BASE. Set in
     *ApplicationMaster's environment only. This states that for all non-relative
     * web URLs in the app masters web UI what base should they have.
     */
    public static final String APPLICATION_WEB_PROXY_BASE_ENV="APPLICATION_WEB_PROXY_BASE";

    /**
     * The temporary environmental variable for container log directory. This
     * should be replaced by real container log directory on container launch.
     */
    public static final String LOG_DIR_EXPANSION_VAR = "<LOG_DIR>";

    /**
     * This constant is used to construct class path and it will ben replaced with
     * real class path separator(':' for linux and ';' for Windows) by NodeManager
     * on container launch. User has to use this constant to construct class path
     * if user wants cross-platform practice i.e. submit an application from a
     * Windows client to a Linux/Unix server or vice versa.
     */
    @Public
    @Unstable
    public static final String CLASS_PATH_SEPARATOR = "<CPS>";

    /**
     * The following two constants are used to expand parameter and it will be replaced with real parameter expansion marker ('%' for Windows and '$' for Linux) by NodeManager on container launch. For example: {{VAR}} will be replaced as $VAR on Linux, and %VAR% on Windows. User has to use this constant to construct class path if user wants cross-platform practice i.e. submit an application from a Windows client to a Linux/Unix server or vice versa.
     */
    @Public
    @Unstable
    public static final String PARAMETER_EXPANSION_LEFT="{{";

    /**
     * User has to use this constant to construct class path if user wants cross-platform practice i.e. submit an application from a Windows client to a Linux/Unix server or vice versa.
     */
    @Public
    @Unstable
    public static final String PARAMETER_EXPANSION_RIGHT="}}";

    public static final String STDERR = "stderr";

    public static final String STDOUT = "stdout";

    /**
     * The environment variable for MAX_APP_ATTEMPTS. Set in AppMaster environment
     * only
     */
    public static final String MAX_APP_ATTEMPTS_ENV = "MAX_APP_ATTEMPTS";

    /**
     * Environment for Application.
     *
     * Some of the environment variables for application are <em> final </em>
     * i.e. they cannot be modified by the application.
     */
    public enum Environment {
        /**
         * $USER
         * Final, non-modifiable.
         */
        USER("USER"),

        /**
         * $LOGNAME
         * Final, non-modifiable.
         */
        LOGNAME("LOGNAME"),

        /**
         * $HOME
         * Final, non-modifiable.
         */
        HOME("HOME"),

        /**
         * $PWD
         * Final, non-modifiable.
         */
        PWD("PWD"),

        /**
         * $PATH
         */
        PATH("PATH"),

        SHELL("SHELL"),

        JAVA_HOME("JAVA_HOME"),

        CLASSPATH("CLASSPATH"),

        APP_CLASSPATH("APP_CLASSPATH"),

        LD_LIBRARY_PATH("LB_LIBRARY_PATH"),

        HADOOP_CONF_DIR("HADOOP_CONF_DIR"),

        HADOOP_COMMON_HOME("HADOOP_COMMON_HOME"),

        HADOOP_HDFS_HOME("HADOOP_HDFS_HOME"),

        MALLOC_ARENA_MAX("MALLOC_ARENA_MAX"),

        HADOOP_YARN_HOME("HADOOP_YARN_HOME"),

        CONTAINER_ID("CONTAINER_ID"),

        NM_HOST("NM_HOST"),

        NM_HTTP_PORT("NM_HTTP_PORT"),

        NM_PORT("NM_PORT"),

        LOCAL_DIRS("LOCAL_DIRS"),

        LOG_DIRS("LOG_DIRS");

        private final String variable;

        private Environment(String variable){
            this.variable = variable;
        }

        public String key(){
            return variable;
        }

        public String toString(){
            return variable;
        }

        /**
         * Expand the Environment variable based on client OS environment variable
         * expansion syntax (e.g. $VAR for Linux and %VAR% for Windows).
         * <p>
         * Note: Use $$() method for cross-platform practice i.e. submit an
         * application from a Windows client to a Linux/Unix server or vice
         * versa.
         * </p>
         */
        public String $(){
            if(Shell.WINDOWS) {
                return "%" + variable + "%";
                    } else {
                return "$" + variable;
            }
        }

        /**
         * Expand the environment variable in platform-agnostic syntax. The
         * the parameter expansion marker "{{VAR}}" will be replaced with real
         * parameter expansion marker by NodeManager on container launch.
         */
        @Public
        @Unstable
        public String $$(){
            return PARAMETER_EXPANSION_LEFT + variable + PARAMETER_EXPANSION_RIGHT;
        }
    }
}
