package com.crowworks.jackdaw.utils;

import com.crowworks.jackdaw.core.LogLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LogFormatter
{
    private String pattern;

    public LogFormatter()
    {
        this("[%T] %n: %v");
    }

    public LogFormatter(String pattern)
    {
        this.pattern = pattern;
    }

    public String format(LogLevel level, String loggerName, String message, Object... args)
    {
        String formattedMessage = formatMessage(message, args);
        Map<String, String> placeholders = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        placeholders.put("v", formattedMessage);
        placeholders.put("n", loggerName);
        placeholders.put("l", level.toString());
        placeholders.put("L", level.toString().substring(0, 1));
        placeholders.put("P", Long.toString(ProcessHandle.current().pid()));
        placeholders.put("t", Long.toString(Thread.currentThread().getId()));
        placeholders.put("a", now.getDayOfWeek().toString().substring(0, 3));
        placeholders.put("A", now.getDayOfWeek().toString());
        placeholders.put("b", now.getMonth().toString().substring(0, 3));
        placeholders.put("B", now.getMonth().toString());
        placeholders.put("c", now.format(DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH)));
        placeholders.put("C", now.format(DateTimeFormatter.ofPattern("yy", Locale.ENGLISH)));
        placeholders.put("Y", Integer.toString(now.getYear()));
        placeholders.put("D", now.format(DateTimeFormatter.ofPattern("DD/MM/YY", Locale.ENGLISH)));
        placeholders.put("m", Integer.toString(now.getMonthValue()));
        placeholders.put("d", Integer.toString(now.getDayOfMonth()));
        placeholders.put("H", Integer.toString(now.getHour()));
        placeholders.put("M", Integer.toString(now.getMinute()));
        placeholders.put("S", Integer.toString(now.getSecond()));
        placeholders.put("R", now.format(DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)));
        placeholders.put("T", now.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH)));
        placeholders.put("%", "%");

        return replacePattern(pattern, placeholders);
    }

    private String formatMessage(String message, Object... params)
    {
        StringBuilder formattedMessage = new StringBuilder();
        int paramIndex = 0;
        for(int i = 0; i < message.length(); i++)
        {
            if(i < message.length() - 1 && message.charAt(i) == '{' && message.charAt(i +1) == '}')
            {
                if(paramIndex < params.length)
                {
                    formattedMessage.append(params[paramIndex++]);
                }
                i++; // Skip the next '}'
            }
            else
            {
                formattedMessage.append(message.charAt(i));
            }
        }

        return formattedMessage.toString();
    }

    private String replacePattern(String format, Map<String, String> placeholders)
    {
        StringBuilder stringBuilder = new StringBuilder();
        boolean inSpecifier = false;

        for(char c : format.toCharArray())
        {
            if(c == '%')
            {
                inSpecifier = true;
            }
            else if(inSpecifier)
            {
                String replacement = placeholders.getOrDefault(String.valueOf(c), "%" + c);
                stringBuilder.append(replacement);
                inSpecifier = false;
            }
            else
            {
                stringBuilder.append(c);
            }
        }

        if(inSpecifier)
        {
            stringBuilder.append('%');
        }

        return stringBuilder.toString();
    }
}
