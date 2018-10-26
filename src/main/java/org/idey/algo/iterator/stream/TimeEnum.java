package org.idey.algo.iterator.stream;


import java.sql.Timestamp;

public enum  TimeEnum {
    SECONDS(1), MINUTES(60), HOUR(60*60), DAY(24*60*60);
    private long converter;

    TimeEnum(long converter) {
        this.converter = 1000*converter;
    }

    public long convert(Timestamp prevTimeStamp,Timestamp nextTimestamp){
        return (nextTimestamp.getTime()-prevTimeStamp.getTime())/converter;
    }
}
