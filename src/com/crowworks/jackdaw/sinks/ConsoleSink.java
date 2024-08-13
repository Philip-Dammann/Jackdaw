package com.crowworks.jackdaw.sinks;

import java.io.PrintStream;

public class ConsoleSink
{
    private PrintStream writer;

    public ConsoleSink()
    {
        this(System.out);
    }

    public ConsoleSink(PrintStream out)
    {
        this.writer = out;
    }

    public void log(String message)
    {
        writer.println(message);
    }
}
