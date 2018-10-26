#!/usr/bin/env bash
if [ $# -lt 1 ]
then
    echo "Usage $0 some-class [some-params]"
    exit 1
fi
MZ_JARS_PATH=/Users/idey/mzmap
HADOOP_JAR_LOCATION=/opt/cloudera/parcels/CDH/lib/hadoop-mapreduce/
HADOOP_CLASSPATH=""

for f in ${MZ_JARS_PATH}/*.jar; do

    if (echo ${f} | grep "parquet")
    then
       HADOOP_CLASSPATH=${HADOOP_CLASSPATH}:${f}
    elif (echo ${f} | grep "avro")
    then

       if !(echo ${f} | grep "parquet")
       then
              HADOOP_CLASSPATH=${HADOOP_CLASSPATH}:${f}
       fi
    elif (echo ${f} | grep "kafka")
    then
    	 HADOOP_CLASSPATH=${HADOOP_CLASSPATH}:${f}
    elif (echo ${f} | grep "dataingest")
    then
    	 HADOOP_CLASSPATH=${HADOOP_CLASSPATH}:${f}
    fi
done

for f in ${HADOOP_JAR_LOCATION}/*.jar; do

    if (echo ${f} | grep "hadoop")
    then
    	 HADOOP_CLASSPATH=${HADOOP_CLASSPATH}:${f}
    fi
done

PARQUET_JARS_LOCATION=/opt/cloudera/parcels/CDH/lib/parquet/lib/
for f in ${PARQUET_JARS_LOCATION}/*.jar; do

 if (echo ${f} | grep "parquet")
 then
   #echo "skipping cluster jar"
   HADOOP_CLASSPATH=${HADOOP_CLASSPATH}:${f}
 fi
done

HADOOP_CONF=/etc/hadoop/conf/hdfs-site.xml:/etc/hadoop/conf/mapred-site.xml:/etc/hadoop/conf/core-site.xml:/etc/hive/conf/hive-site.xml
HADOOP_CLASSPATH=${HADOOP_CLASSPATH}:${HADOOP_CONF}
echo ${HADOOP_CLASSPATH}
export HADOOP_CLASSPATH
