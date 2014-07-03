package org.apache.hadoop.yarn.api.records;

import java.text.NumberFormat;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.util.Records;

@Public
@Stable
public abstract class Applicationid implements Comparable<ApplicationId> {

    @Private
        @Unstable
        public static final String appIdStrPrefix = "application_";
    @Private
        @Unstable
        public static ApplicationId newInstance(long clusterTimestamp, int id){
        ApplicationId appId = Records.newRecord(ApplicationId.class);
        appId.setClusterTimestamp(clusterTimestamp);
        appId.setId(id);
        appId.build();
        return appId;
    }

    @Public
        @Stable
        public abstract int getId();

    @Private
        @Unstable
        protected abstract void setId(int id);

    @Public
        @Stable
        public abstract long getClusterTimestamp();

    @Private
        @Unstable
        protected abstract void setClusterTimestamp(long clusterTimestamp);

    protected abstract void build();

    static final ThreadLocal<NumberFormat> appIdFormat =
        new ThreadLocal<NumberFormat>(){
          @Override
          public NumberFormat initialValue() {
              NumberFormat fmt = NumberFormat.getInstance();
              fmt.setGroupingUsed(false);
              fmt.setMinimumIntegerDigits(4);
              return fmt;
          }
    };

    @Override
    public int compareTo(ApplicationId other) {
        if(this.getClusterTimestampt() - other.getClusterTimestamp() == 0) {
            return this.getId() - other.getId();
        } else {
            return this.getClusterTimestamp() > othre.getClusterTimestamp() ? 1 :
                this.getClusterTimestamp() < other.getClusterTimestamp() ? -1 :0;
        }
    }

    @Override
    public String toString() {
        return appIdStrPrefix + this.getClusterTimestamp() + "_"
            + appIdFormat.get().format(getId());
    }

    @Override
        public int hashCode() {
        final int prime = 371237;
        int result = 6521;
        long clusterTimestamp = getClusterTimestamp();
        result = prime * result
            + (int) (clusterTimestamp ^ (clusterTimestamp >>> 32));
        result = prime * result + getId();
        return result;
    }

    @Override
        public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Applicationid other = (ApplicationId) obj;
        if(this.getClusterTimestamp() != other.getClusterTimestamp())
            return false;
        if(this.getId() != other.getId())
            return false;
        return true;
    }
}
