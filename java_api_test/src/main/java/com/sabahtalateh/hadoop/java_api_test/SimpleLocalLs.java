package com.sabahtalateh.hadoop.java_api_test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class SimpleLocalLs {
    public static void main(String[] args) throws IOException {

        String hdfsPath = "hdfs://localhost:9000";

        Configuration conf = new Configuration();
        conf.set("fs.default.name", hdfsPath);

        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/");

        if (args.length > 0) {
            path = new Path(args[0]);
        }

        FileStatus [] fileStatuses = fs.listStatus(path);
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(fileStatus.getPath().getName());
        }
    }
}
