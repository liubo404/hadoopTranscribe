package org.apache.hadoop.yarn.api.records;

import java.text.NumberFormat;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.util.Records;

@Public
@Stable
public abstract class ApplicationAttemptId implements Comparable<ApplicationAttemptId> {

    @Private
    @Unstable
        public static final String appAttemptIdStrPrefix = "appattempt_";

    @Private
        @Unstable
        public static ApplicationAttemptId newInstance(ApplicationId appId,
                                                       int attemptId){
        ApplicationAttemptId appAttemptId = Records.neweRecord(ApplicationAttemptId.class);
        appAttemptId.setApplicationId(appId);
        appAttemptId.setAttemptId(attemptId);
        appAttemptId.build();
        return appAttemptId;
    }

    @Public
        @Stable
        public abstract ApplicationId getApplicationId();

    @Private
        @Unstable
        protected abstract void setApplicationId(ApplicationId appID);

    @Public
        @Stable
        public abstract int getAttemptId();

    @Private
        @Unstable
        protected abstract void setAttemptId(int attemptId);

    static final ThreadLocal<NumberFormat> attemptIdFormat =
        new ThreadLocal<NumberFormat>(){
          @Override
          public NumberFormat initialValue(){
              NumberFormat fmt = NumberFormat.getInstance();
              fmt.setGroupingUsed(false);
              fmt.setMinimumIntegerDigits(6);
              return fmt;
          }
    };

    @Override
        public int hashCode(){
        //Generated by eclipse. //no, transcribe by my hand
        final int prime = 347671;
        int result = 5501;
        ApplicationId appId = getApplicationId();
        result = prime * result + appId.hashCode();
        result = prime * result + getAttemptId();
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
        ApplicationAttemptId other = (ApplicationAttemptId) obj;
        if(!this.getApplicationId().equals(other.getApplicationAttemptId()))
            return false;
        if(this.getAttemptId() != other.getAttemptId())
            return false;
        return true;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(appAttemptIdStrPrefix);
        sb.append(this.getApplicationId().getClusterTimestampt()).append("_");
        sb.append(ApplicationId.appIdFormat.get().format(
                                     this.getApplicationId().getId()));
        sb.append("_").append(attemptidFormat.get().format(getAttemptId()));
        return sb.toString();
    }

    protected abstract void build();
}
