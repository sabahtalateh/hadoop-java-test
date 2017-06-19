package com.sabahtalateh.hadoop.java_api_test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WtiteRemoteFile {
    public static void main(String[] args) throws IOException {
        String hdfsPath = "hdfs://localhost:9000";

        Configuration conf = new Configuration();
        conf.set("fs.default.name", hdfsPath);

        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/user/sabahtalateh/output/out.txt");

        String s = "Hello!! Moufaker!\n";
        InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(s.getBytes()));

        FSDataOutputStream fsDataOutputStream = fs.create(path);
        IOUtils.copyBytes(inputStream, fsDataOutputStream, conf);

    }
}
