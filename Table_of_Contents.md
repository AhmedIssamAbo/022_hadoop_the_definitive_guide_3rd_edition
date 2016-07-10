#Hadoop : The Definitive Guide 3rd Edition#

##Table of Contents##
```
Foreword . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . xv

Preface . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . xvii

1. Meet Hadoop . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 1
Data! 1
Data Storage and Analysis 3
Comparison with Other Systems 4
Rational Database Management System 4
Grid Computing 6
Volunteer Computing 8
A Brief History of Hadoop 9
Apache Hadoop and the Hadoop Ecosystem 12
Hadoop Releases 13
What’s Covered in This Book 15
Compatibility 15

2. MapReduce . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 17
A Weather Dataset 17
Data Format 17
Analyzing the Data with Unix Tools 19
Analyzing the Data with Hadoop 20
Map and Reduce 20
Java MapReduce 22
Scaling Out 30
Data Flow 30
Combiner Functions 33
Running a Distributed MapReduce Job 36
Hadoop Streaming 36
Ruby 36
Python 39
Hadoop Pipes 40
Compiling and Running 41

3. The Hadoop Distributed Filesystem . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 43
The Design of HDFS 43
HDFS Concepts 45
Blocks 45
Namenodes and Datanodes 46
HDFS Federation 47
HDFS High-Availability 48
The Command-Line Interface 49
Basic Filesystem Operations 50
Hadoop Filesystems 52
Interfaces 53
The Java Interface 55
Reading Data from a Hadoop URL 55
Reading Data Using the FileSystem API 57
Writing Data 60
Directories 62
Querying the Filesystem 62
Deleting Data 67
Data Flow 67
Anatomy of a File Read 67
Anatomy of a File Write 70
Coherency Model 72
Data Ingest with Flume and Sqoop 74
Parallel Copying with distcp 75
Keeping an HDFS Cluster Balanced 76
Hadoop Archives 77
Using Hadoop Archives 77
Limitations 79

4. Hadoop I/O . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 81
Data Integrity 81
Data Integrity in HDFS 81
LocalFileSystem 82
ChecksumFileSystem 83
Compression 83
Codecs 85
Compression and Input Splits 89
Using Compression in MapReduce 90
Serialization 93
The Writable Interface 94
Writable Classes 96
Implementing a Custom Writable 103
Serialization Frameworks 108
Avro 110
Avro Data Types and Schemas 111
In-Memory Serialization and Deserialization 114
Avro Datafiles 117
Interoperability 118
Schema Resolution 121
Sort Order 123
Avro MapReduce 124
Sorting Using Avro MapReduce 128
Avro MapReduce in Other Languages 130
File-Based Data Structures 130
SequenceFile 130
MapFile 137

5. Developing a MapReduce Application . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 143
The Configuration API 144
Combining Resources 145
Variable Expansion 146
Setting Up the Development Environment 146
Managing Configuration 148
GenericOptionsParser, Tool, and ToolRunner 150
Writing a Unit Test with MRUnit 154
Mapper 154
Reducer 156
Running Locally on Test Data 157
Running a Job in a Local Job Runner 157
Testing the Driver 160
Running on a Cluster 161
Packaging a Job 162
Launching a Job 163
The MapReduce Web UI 165
Retrieving the Results 168
Debugging a Job 170
Hadoop Logs 175
Remote Debugging 177
Tuning a Job 178
Profiling Tasks 179
MapReduce Workflows 181
Decomposing a Problem into MapReduce Jobs 181
JobControl 183
Apache Oozie 183

6. How MapReduce Works . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 189
Anatomy of a MapReduce Job Run 189
Classic MapReduce (MapReduce 1) 190
YARN (MapReduce 2) 196
Failures 202
Failures in Classic MapReduce 202
Failures in YARN 204
Job Scheduling 206
The Fair Scheduler 207
The Capacity Scheduler 207
Shuffle and Sort 208
The Map Side 208
The Reduce Side 210
Configuration Tuning 211
Task Execution 214
The Task Execution Environment 215
Speculative Execution 215
Output Committers 217
Task JVM Reuse 219
Skipping Bad Records 220

7. MapReduce Types and Formats . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 223
MapReduce Types 223
The Default MapReduce Job 227
Input Formats 234
Input Splits and Records 234
Text Input 245
Binary Input 249
Multiple Inputs 250
Database Input (and Output) 251
Output Formats 251
Text Output 252
Binary Output 252
Multiple Outputs 253
Lazy Output 257
Database Output 258

8. MapReduce Features . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 259
Counters 259
Built-in Counters 259
User-Defined Java Counters 264
User-Defined Streaming Counters 268
Sorting 268
Preparation 269
Partial Sort 270
Total Sort 274
Secondary Sort 277
Joins 283
Map-Side Joins 284
Reduce-Side Joins 285
Side Data Distribution 288
Using the Job Configuration 288
Distributed Cache 289
MapReduce Library Classes 295

9. Setting Up a Hadoop Cluster . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 297
Cluster Specification 297
Network Topology 299
Cluster Setup and Installation 301
Installing Java 302
Creating a Hadoop User 302
Installing Hadoop 302
Testing the Installation 303
SSH Configuration 303
Hadoop Configuration 304
Configuration Management 305
Environment Settings 307
Important Hadoop Daemon Properties 311
Hadoop Daemon Addresses and Ports 316
Other Hadoop Properties 317
User Account Creation 320
YARN Configuration 320
Important YARN Daemon Properties 321
YARN Daemon Addresses and Ports 324
Security 325
Kerberos and Hadoop 326
Delegation Tokens 328
Other Security Enhancements 329
Benchmarking a Hadoop Cluster 331
Hadoop Benchmarks 331
User Jobs 333
Hadoop in the Cloud 334
Apache Whirr 334

10. Administering Hadoop . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 339
HDFS 339
Persistent Data Structures 339
Safe Mode 344
Audit Logging 346
Tools 347
Monitoring 351
Logging 352
Metrics 352
Java Management Extensions 355
Maintenance 358
Routine Administration Procedures 358
Commissioning and Decommissioning Nodes 359
Upgrades 362

11. Pig . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 367
Installing and Running Pig 368
Execution Types 368
Running Pig Programs 370
Grunt 370
Pig Latin Editors 371
An Example 371
Generating Examples 373
Comparison with Databases 374
Pig Latin 375
Structure 376
Statements 377
Expressions 381
Types 382
Schemas 384
Functions 388
Macros 390
User-Defined Functions 391
A Filter UDF 391
An Eval UDF 394
A Load UDF 396
Data Processing Operators 399
Loading and Storing Data 399
Filtering Data 400
Grouping and Joining Data 402
Sorting Data 407
Combining and Splitting Data 408
Pig in Practice 409
Parallelism 409
Parameter Substitution 410

12. Hive . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 413
Installing Hive 414
The Hive Shell 415
An Example 416
Running Hive 417
Configuring Hive 417
Hive Services 419
The Metastore 421
Comparison with Traditional Databases 423
Schema on Read Versus Schema on Write 423
Updates, Transactions, and Indexes 424
HiveQL 425
Data Types 426
Operators and Functions 428
Tables 429
Managed Tables and External Tables 429
Partitions and Buckets 431
Storage Formats 435
Importing Data 441
Altering Tables 443
Dropping Tables 443
Querying Data 444
Sorting and Aggregating 444
MapReduce Scripts 445
Joins 446
Subqueries 449
Views 450
User-Defined Functions 451
Writing a UDF 452
Writing a UDAF 454

13. HBase . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 459
HBasics 459
Backdrop 460
Concepts 460
Whirlwind Tour of the Data Model 460
Implementation 461
Installation 464
Test Drive 465
Clients 467
Java 467
Avro, REST, and Thrift 470
Example 472
Schemas 472
Loading Data 473
Web Queries 476
HBase Versus RDBMS 479
Successful Service 480
HBase 481
Use Case: HBase at Streamy.com 481
Praxis 483
Versions 483
HDFS 484
UI 485
Metrics 485
Schema Design 485
Counters 486
Bulk Load 486

14. ZooKeeper . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 489
Installing and Running ZooKeeper 490
An Example 492
Group Membership in ZooKeeper 492
Creating the Group 493
Joining a Group 495
Listing Members in a Group 496
Deleting a Group 498
The ZooKeeper Service 499
Data Model 499
Operations 501
Implementation 506
Consistency 507
Sessions 509
States 511
Building Applications with ZooKeeper 512
A Configuration Service 512
The Resilient ZooKeeper Application 515
A Lock Service 519
More Distributed Data Structures and Protocols 521
ZooKeeper in Production 522
Resilience and Performance 523
Configuration 524

15. Sqoop . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 527
Getting Sqoop 527
Sqoop Connectors 529
A Sample Import 529
Text and Binary File Formats 532
Generated Code 532
Additional Serialization Systems 533
Imports: A Deeper Look 533
Controlling the Import 535
Imports and Consistency 536
Direct-mode Imports 536
Working with Imported Data 536
Imported Data and Hive 537
Importing Large Objects 540
Performing an Export 542
Exports: A Deeper Look 543
Exports and Transactionality 545
Exports and SequenceFiles 545

16. Case Studies . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 547
Hadoop Usage at Last.fm 547
Last.fm: The Social Music Revolution 547
Hadoop at Last.fm 547
Generating Charts with Hadoop 548
The Track Statistics Program 549
Summary 556
Hadoop and Hive at Facebook 556
Hadoop at Facebook 556
Hypothetical Use Case Studies 559
Hive 562
Problems and Future Work 566
Nutch Search Engine 567
Data Structures 568
Selected Examples of Hadoop Data Processing in Nutch 571
Summary 580
Log Processing at Rackspace 581
Requirements/The Problem 581
Brief History 582
Choosing Hadoop 582
Collection and Storage 582
MapReduce for Logs 583
Cascading 589
Fields, Tuples, and Pipes 590
Operations 593
Taps, Schemes, and Flows 594
Cascading in Practice 595
Flexibility 598
Hadoop and Cascading at ShareThis 599
Summary 603
TeraByte Sort on Apache Hadoop 603
Using Pig and Wukong to Explore Billion-edge Network Graphs 607
Measuring Community 609
Everybody’s Talkin’ at Me: The Twitter Reply Graph 609
Symmetric Links 612
Community Extraction 613

A. Installing Apache Hadoop . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 617

B. Cloudera’s Distribution Including Apache Hadoop . . . . . . . . . . . . . . . . . . . . . . . . . . 623

C. Preparing the NCDC Weather Data . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 625

Index . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 62

```

