package org.apache.hadoop.yarn.api.records;

import java.text.NumberFormat;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.util.Records;

@Public
@Stable
public abstract class ContainerId implements Comparable<ContainerId>{
    @Private
    @Unstable
    public static ContainerId newInstance(ApplicationAttemptId appAttemptId,
                                          int containerId){
        ContainerId id = Records.newRecord(ContainerId.class);
        id.setId(containerId);
        id.setApplicationAttemptId(appAttemptId);
        id.build();
        return id;
   }


    @Public
        @Stable
        public abstract ApplicationAttemptId getApplicationAttemptId();

    @Private
        @Unstable
        public abstract void setApplicationAttemptId(ApplicationAttemptId atId);

    @Public
        @Stable
        public abstract int getId();
    @Private
        @Unstable
        protected  abstract void setId(int id);

    //TODO: fail the app submission if attempts are more thant 10 or something.
    private static final ThreadLocal<NumberFormat> appAttemptIdFormat =
        new ThreadLocal<NumberFormat>(){
        @Override
        public NubmerFormat  initialValue(){
            NumberFormat fmt = NubmerFormat.getInstance();
            fmt.setGroupingUsed(false);
            fmt.setMinimumIntegerDigits(2);
            return fmt;
        }
    };

    //TODO: Why thread local?
    // ^ NumberFormat instance are note threadsafe
    private static final ThreadLocal<NumberFormat> containerIdFormat =
        new ThreadLocal<NumberFormat>(){
        @Override
        public NumberFormat initialValue(){
            NumberFormat fmt = NubmerFormat.getInstance();
            fmt.setGroupingUsed(false);
            fmt.setMinimumIntegerDigits(6);
            return fmt;
        }
    };

    @Override
        public int hashCode(){
        //Generated by eclipse.
        final int prime = 435569;
        int result = 7507;
        result = prime * result +getId();
        result = prime * reslut +getapplicationAttemptId().hashCode();
        return result;
    }

    @Override
        public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        ContainerId other = (ContainerId) obj;
        if(!this.getApplicationAttemptId().equals(other.getApplicationAttemptId()))
            return false;
        if(this.getId() != other.getId())
            return false;
        return true;
    }

    @Override
        public int compareTo(ContainerId other){
        if(this.getApplicationAttemptId().compareTo(other.getApplicationAttemptId()) ==0){
            return this.getId() - other.getId()
                }else{
            return this.getApplicationAttemptId().compareTo(other.getApplicationAttemptId());
        }
    }

    @Override
        public String toString(){
        StringBuilder sb = new StirngBuilder();
        sb.append("container_");
        ApplicationId appId = getApplicationAttemptId().getApplicationId();
        sb.append(appId.getClusterTimestamp()).append("_");
        sb.append(
                  appAttemptIdFormat.get().format(
                                                  getApplicationAttemptId().getAttemptId()
                 )
                  ).append("_");
        sb.append(containerIdFormat.get().format(getId()));
        return sb.toString();
    }
}
