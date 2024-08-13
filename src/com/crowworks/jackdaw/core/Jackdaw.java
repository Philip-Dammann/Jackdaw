package com.crowworks.jackdaw.core;

import com.crowworks.jackdaw.config.LoggerConfig;
import com.crowworks.jackdaw.sinks.AsyncSink;
import com.crowworks.jackdaw.sinks.ConsoleSink;
import com.crowworks.jackdaw.sinks.FileSink;
import com.crowworks.jackdaw.utils.LogFormatter;


public class Jackdaw implements Logger
{
    private String name;
    private LoggerConfig config;
    private ConsoleSink consoleSink;
    private AsyncSink asyncSink;
    private FileSink fileSink;
    private LogFormatter formatter;

    // Color codes:
    private static final String RESET = "\033[0m";
    private static final String DEBUG_COLOR = "\033[34m"; // Blue
    private static final String INFO_COLOR = "\033[32m"; // Green
    private static final String WARN_COLOR = "\033[33m"; // Yellow
    private static final String ERROR_COLOR = "\033[31m"; // Red
    private static final String FATAL_COLOR = "\033[41m"; // Red background

    public Jackdaw(String name)
    {
        this.name = name;
        this.config = new LoggerConfig();
        initializeSinks();
        this.formatter = new LogFormatter(config.getLogPattern());
    }

    private void initializeSinks()
    {
        if(config.isEnableConsoleLogging())
        {
            this.consoleSink = new ConsoleSink();
        }
        if(config.isEnableFileLogging())
        {
            this.fileSink = new FileSink(config.getLogFilePath());
            this.asyncSink = new AsyncSink(fileSink);
        }
    }

    public void setConfig(LoggerConfig config)
    {
        this.config = config;
        initializeSinks();
        this.formatter = new LogFormatter(config.getLogPattern());
    }

    private void log(LogLevel level, String message, Object... args)
    {
        String formattedString = formatter.format(level, name, message, args);
        String coloredString = applyColor(level, formattedString);
        if(config.isEnableConsoleLogging())
        {
            consoleSink.log(coloredString + RESET);
        }

        if(level.ordinal() >= config.getLogLevel().ordinal() && config.isEnableFileLogging())
        {
            asyncSink.log(formattedString);
        }
    }

    private String applyColor(LogLevel level, String message)
    {
        switch (level)
        {
            case DEBUG:
                return DEBUG_COLOR + message;
            case INFO:
                return INFO_COLOR + message;
            case WARN:
                return WARN_COLOR + message;
            case ERROR:
                return ERROR_COLOR + message;
            case FATAL:
                return FATAL_COLOR + message;
            default:
                return message;
        }
    }

    @Override
    public void trace(String message, Object... params)
    {
        log(LogLevel.TRACE, message, params);
    }

    @Override
    public void debug(String message, Object... params)
    {
        log(LogLevel.DEBUG, message, params);
    }

    @Override
    public void info(String message, Object... params)
    {
        log(LogLevel.INFO, message, params);
    }

    @Override
    public void warn(String message, Object... params)
    {
        log(LogLevel.WARN, message, params);
    }

    @Override
    public void error(String message, Object... params)
    {
        log(LogLevel.ERROR, message, params);
    }

    @Override
    public void fatal(String message, Object... params)
    {
        log(LogLevel.FATAL, message, params);
    }
}
