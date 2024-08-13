package com.crowworks.jackdaw.sinks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileSink
{
    private PrintWriter writer;

    public FileSink(String filePath)
    {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if(parentDir != null && !parentDir.exists())
        {
            boolean dirsCreated = parentDir.mkdirs();
            if(!dirsCreated)
            {
                System.err.println("Failed to create directory: " + parentDir.getAbsolutePath());
            }
        }

        try
        {
            this.writer = new PrintWriter(new FileWriter(filePath, false));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void log(String message)
    {
        writer.println(message);
        writer.flush();
    }
}
