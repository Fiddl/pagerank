package edu.stevens.cs549.hadoop.pagerank;

import java.io.IOException;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class FinMapper extends Mapper<LongWritable, Text, DoubleWritable, Text> {

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException, IllegalArgumentException {
		String line = value.toString(); // Converts Line to a String
		String[] sections = line.split("\t");
		/*
		 * TODO output key:-rank, value: node
		 * See IterMapper for hints on parsing the output of IterReducer.
		 */
		double outKey = Double.valueOf(sections[0].split("\\+")[1]); // PageRank
		String outVal= sections[0].split("\\+")[0]; // Node
		
		
		context.write(new DoubleWritable(outKey), new Text(outVal));

	}

}
