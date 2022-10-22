package org.raiseup.sparkguide.rdd.airports;

import org.apache.ivy.util.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class AirportInUsaProblem {
    public static void main(String[] args) throws Exception{
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf= new SparkConf().setAppName("airportsInUsaProblem").setMaster("local[3]");
        try(JavaSparkContext sc= new JavaSparkContext(conf)) {
            JavaRDD<String> airports=sc.textFile("in/airports.text");
            JavaRDD<String> airportsInUsa=airports.filter(row->row.split(",")[3].equalsIgnoreCase("\"United States\""));
            JavaRDD<String>airportsNameAndCity=airportsInUsa.map(row->{
                String[]splits=row.split(",");
                String returnValue= StringUtils.join(new String[]{splits[1],splits[2]},",");
                return returnValue;
            });
            System.out.println(airportsNameAndCity.first());
        }


    }
}
