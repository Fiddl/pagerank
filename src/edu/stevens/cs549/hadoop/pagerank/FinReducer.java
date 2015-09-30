package edu.stevens.cs549.hadoop.pagerank;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FinReducer extends Reducer<DoubleWritable, Text, Text, Text> {

	public void reduce(DoubleWritable key, Iterable<Text> values, Context context) throws IOException,
			InterruptedException {
		String outVal = key.toString();
		/* 
		 * TODO: For each value, emit: key:value, value:-rank
		 */
		for (Text val : values) {
			String outKey = val.toString();
			context.write(new Text(outKey), new Text(outVal));
		}
	}
}
