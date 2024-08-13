package com.crowworks.jackdaw.core;

import com.crowworks.jackdaw.config.LoggerConfig;

public class LoggerBuilder
{
    private String name;
    private LoggerConfig config;

    public LoggerBuilder(String name)
    {
        this.name = name;
        this.config = new LoggerConfig();
    }

    public LoggerBuilder setLogLevel(LogLevel level)
    {
        config.setLogLevel(level);
        return this;
    }

    public LoggerBuilder setFilePath(String filePath)
    {
        config.setLogFilePath(filePath);
        return this;
    }

    public LoggerBuilder setLogPattern(String pattern)
    {
        config.setLogPattern(pattern);
        return this;
    }

    public LoggerBuilder setEnableFileLogging(boolean shouldLogFile)
    {
        config.setEnableFileLogging(shouldLogFile);
        return this;
    }

    public LoggerBuilder setEnableConsoleLogging(boolean shouldLogConsole)
    {
        config.setEnableConsoleLogging(shouldLogConsole);
        return this;
    }

    public LoggerBuilder setFileEncoding(String fileEncoding)
    {
        config.setFileEncoding(fileEncoding);
        return this;
    }

    public LoggerBuilder setMaxFileSizeMB(int size)
    {
        config.setMaxFileSizeMB(size);
        return this;
    }

    public LoggerBuilder setMaxBackupFilesAmount(int amount)
    {
        config.setMaxBackupFiles(amount);
        return this;
    }

    public Logger build()
    {
        Jackdaw logger = new Jackdaw(name);
        logger.setConfig(config);
        return logger;
    }
}