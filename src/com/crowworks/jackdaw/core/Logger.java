package com.crowworks.jackdaw.core;

public interface Logger
{
    void trace(String message, Object... params);
    void debug(String message, Object... params);
    void info(String message, Object... params);
    void warn(String message, Object... params);
    void error(String message, Object... params);
    void fatal(String message, Object... params);
}