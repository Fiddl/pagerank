package edu.stevens.cs549.hadoop.pagerank;

import java.io.*;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class DiffRed1 extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		double[] ranks = new double[2];
		int index = 0;
		/* 
		 * The list of values should contain two ranks.  Compute and output their difference.
		 */
		
		for (Text val: values) {
			ranks[index] = Double.valueOf(val.toString());
			index++;
		}
		
		double difference = Math.abs(ranks[0]-ranks[1]);
		
		context.write(new Text(key.toString()), new Text(String.valueOf(difference)));

	}
}
