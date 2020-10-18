package org.wally.inteview;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wally
 * 主要是用来解析文件
 */
public class FileUtil {
    public static Set<String> readWordList(String url) throws IOException {
        Set<String> set = new HashSet<String>();
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(url), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = "";
        while ((line = bufferedReader.readLine()) != null){
            String[] split = line.split("\t");
            set.add(split[1]);
        }
        bufferedReader.close();
        inputStreamReader.close();
        return set;
    }
}
