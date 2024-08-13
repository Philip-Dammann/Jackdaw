package com.crowworks.jackdaw.config;


import com.crowworks.jackdaw.core.LogLevel;

/* Log Pattern flags (stolen right from the spdlog docs. Thanks :D):
flag 	meaning 	                                                        example
%v 	    The actual text to log 	                                            "some user text"
%n 	    Logger's name 	                                                    "some logger name"
%l 	    The log level of the message 	                                    "debug", "info", etc
%L 	    Short log level of the message 	                                    "D", "I", etc
%a 	    Abbreviated weekday name 	                                        "Thu"
%A 	    Full weekday name 	                                                "Thursday"
%b 	    Abbreviated month name 	                                            "Aug"
%B 	    Full month name 	                                                "August"
%c 	    Date and time representation 	                                    "Thu Aug 23 15:35:46 2014"
%C 	    Year in 2 digits 	                                                "14"
%Y 	    Year in 4 digits 	                                                "2014"
%D  	Short MM/DD/YY date 	                                            "08/23/14"
%m  	Month 01-12 	                                                    "11"
%d 	    Day of month 01-31 	                                                "29"
%H 	    Hours in 24 format 00-23 	                                        "23"
%M 	    Minutes 00-59 	                                                    "59"
%S 	    Seconds 00-59 	                                                    "58"
%R 	    24-hour HH:MM time, equivalent to %H:%M                         	"23:55"
%T 	    ISO 8601 time format (HH:MM:SS), equivalent to %H:%M:%S 	        "23:55:59"
%% 	    The % sign 	                                                        "%"
*/

public class LoggerConfig
{
    private LogLevel logLevel;
    private String logFilePath;
    private String logPattern;
    private boolean enableFileLogging;
    private boolean enableConsoleLogging;
    // TODO:
    private String fileEncoding;
    private int maxFileSizeMB;
    private int maxBackupFiles;

    public LoggerConfig()
    {
        this.logLevel = LogLevel.INFO;
        this.logFilePath = "app.log"; // if no dir is specified, app.log will be produced in the working directory of the executable
        this.logPattern = "[%T] %n: %v";
        this.enableFileLogging = false;
        this.enableConsoleLogging = true;
        this.fileEncoding = "UTF-8";
        this.maxFileSizeMB = 10;
        this.maxBackupFiles = 5;
    }

    // Getters and setters
    public LogLevel getLogLevel()
    {
        return logLevel;
    }

    public String getLogFilePath()
    {
        return logFilePath;
    }

    public String getLogPattern()
    {
        return logPattern;
    }

    public boolean isEnableFileLogging()
    {
        return enableFileLogging;
    }

    public boolean isEnableConsoleLogging()
    {
        return enableConsoleLogging;
    }

    public String getFileEncoding()
    {
        return fileEncoding;
    }

    public int getMaxFileSizeMB()
    {
        return maxFileSizeMB;
    }

    public int getMaxBackupFiles()
    {
        return maxBackupFiles;
    }

    public void setLogLevel(LogLevel logLevel)
    {
        this.logLevel = logLevel;
    }

    public void setLogFilePath(String logFilePath)
    {
        this.logFilePath = logFilePath;
    }

    public void setLogPattern(String logPattern)
    {
        this.logPattern = logPattern;
    }

    public void setEnableFileLogging(boolean enableFileLogging)
    {
        this.enableFileLogging = enableFileLogging;
    }

    public void setEnableConsoleLogging(boolean enableConsoleLogging) { this.enableConsoleLogging = enableConsoleLogging; }

    public void setFileEncoding(String fileEncoding)
    {
        this.fileEncoding = fileEncoding;
    }

    public void setMaxFileSizeMB(int maxFileSizeMB)
    {
        this.maxFileSizeMB = maxFileSizeMB;
    }

    public void setMaxBackupFiles(int maxBackupFiles)
    {
        this.maxBackupFiles = maxBackupFiles;
    }
}