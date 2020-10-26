package com.mz.common.utils;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtil {

    public static String read(@NotNull InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(inputStream));
        ZipEntry entry = null;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(zipInputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }
        return builder.toString();
    }
}
