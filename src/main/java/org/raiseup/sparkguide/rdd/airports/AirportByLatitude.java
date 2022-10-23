package org.raiseup.sparkguide.rdd.airports;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.raiseup.sparkguide.rdd.commons.Utils;


public class AirportByLatitude {
    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf= new SparkConf().setAppName("airportByLatitude").setMaster("local[3]");
        try (JavaSparkContext sc= new JavaSparkContext(conf)){
            JavaRDD<String>airports=sc.textFile("in/airports.text");
            JavaRDD<String>airportsInUsa=airports.filter(line->Float.parseFloat(line.split(Utils.COMMA_DELIMITER)[6])>40);
            airportsInUsa.saveAsTextFile("out/airports_by_latitude");
        }
    }

}
