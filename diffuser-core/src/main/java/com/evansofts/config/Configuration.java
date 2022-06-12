package com.evansofts.config;

import com.electronwill.nightconfig.core.file.FileConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Configuration {
    private File tempFile;

    public FileConfig load(String file) throws IOException {
        String fileContent = Files.readString(Paths.get(file));
        //TODO: replace environment variables
        // ${VAR:default_value}  ${VAR:?error_text}


        this.tempFile = File.createTempFile("prefix", "suffix", null);
        FileOutputStream fos = new FileOutputStream(this.tempFile);
        fos.write(fileContent.getBytes());
        this.tempFile.deleteOnExit();
        FileConfig fc = FileConfig.of(file);
        fc.load();
        return fc;
    }

    public File getFile() {
        return tempFile;
    }

    private String replaceEnvVariables(String content) {
        //TODO: make sure it's no escaped
        Matcher matcher = Pattern.compile("\\$+\\{(.*?)\\}").matcher(content);
        while (matcher.find()) {
            if(matcher.group().startsWith("$$")){
                continue;
            }
            String spec = matcher.group(1);

        }
        return content;
    }
}
