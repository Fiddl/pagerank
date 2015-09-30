package edu.stevens.cs549.hadoop.pagerank;

import java.io.*;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class DiffRed2 extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		double diff_max = 0.0; // sets diff_max to a default value
		/* 
		 * TODO: Compute and emit the maximum of the differences
		 */
			
		for (Text val : values) {
			double diff = Double.valueOf(val.toString());
			diff_max = (diff > diff_max) ? diff : diff_max; // If diff is greater than max_diff, make diff new max
		}

		context.write(new Text(String.valueOf(diff_max)), new Text("\nDifference"));
	}
}
