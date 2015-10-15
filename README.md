# PageRank

## Description
A simple implementation of the iterative PageRank algorithm to find the most popular pages in a dump of
Wikipedia pages.

Input is provided as follows:
node-id: to-node1 to-node2 ...



## Motivation
Implementation of a distributed systems project to explore the Apache Hadoop framework.

## Installation
compile the source in an executable jar file through your method of choice
/bin/hdfs dfs -put <location of input direcotry> input
bin/hadoop jar <location of compile jar> <command operation>

## Usage
```
init      <input directory>
          <output directory>
          <#reducers>
          
diff      <input directory>
          <output directory>
          <#reducers>
          
finish    <input directory
          <output directory>
          <#reducers>
          
composite <input directory>
          <output directory>
          <interim directory 1>
          <interim directory 2> 
          <difference directory>
          <#reducers>
          
compositeWithNames  <input directory>
                    <output directory>
                    <interim directory 1>
                    <interim directory 2>
                    <difference directory>
                    <names directory>
                    <#reducers>
```
