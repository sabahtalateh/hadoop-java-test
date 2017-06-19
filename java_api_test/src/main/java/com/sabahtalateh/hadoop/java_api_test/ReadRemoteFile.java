package com.sabahtalateh.hadoop.java_api_test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.io.InputStream;

public class ReadRemoteFile {
    public static void main(String[] args) throws IOException {
        String hdfsPath = "hdfs://localhost:9000";

        Configuration conf = new Configuration();
        conf.set("fs.default.name", hdfsPath);

        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/user/sabahtalateh/input/1.iml");

        InputStream inputStream = null;

        try {
            inputStream = fs.open(path);
            IOUtils.copyBytes(inputStream, System.out, 4096);
        } finally {
            IOUtils.closeStream(inputStream);
        }

    }
}
