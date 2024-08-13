# Jackdaw, a Logger for Java

Jackdaw is a simple, yet fairly powerful logger with support for logging to a file or the console.
You can turn off both features as you wish.

## Features:
- Colored Outputs
- Custom Configuration
- File logging on a separate thread


## How to use
In order to use Jackdaw, you'll first have to initialize and set all the options. Below you'll find a basic
config:

```java
import com.crowworks.jackdaw.core.LogLevel;
import com.crowworks.jackdaw.core.Logger;
import com.crowworks.jackdaw.core.LoggerBuilder;

public class Main
{
    public static void main(String[] args)
    {
        Logger logger = new LoggerBuilder("Main")
                .setLogLevel(LogLevel.DEBUG)
                .setFilePath("app.log")
                .setLogPattern("[%T] %n: %v")
                .setEnableFileLogging(false)
                .setEnableConsoleLogging(true)
                .build();
    }
}
```
The example above show all the available methods for customizing the logger. <br>
The method ``setLogLevel`` takes in a LogLevel as a parameter and is used to indicate to the file writer the minimal log level threshold before writing to the file. 

``setEnableFileLogging`` and ``setEnableConsoleLogging`` are the switches with which you might toggle on or off the 2 major logging sinks.<br><br>
Additionally, you are allowed to customize how the output should look using the ``setLogPattern`` method.
<br> 
The pattern given in the example will print text in the following way: 
``[23:08:82] Main: Test Message``. This is freely customizable. If you didn't want any additional information and just want to print the text, use the following pattern:
``"%v"``. Yup, it's that simple. <br>

After we configured the damn thing, let's print!

````java
logger.trace("hello world");
````

this will print the text `hello world` in the color white to the console. The available printing modes are:
- trace
- debug
- info
- warn
- error
- fatal

## Printing with parameters
Jackdaw also allows for parameters when printing. To insert parameters like variables into the to be printed string add `{}` to where ever the parameter should be inserted into.<br> 
Important: When you have more than 1 parameter and therefore multiple `{}` the parameters, which are comma-separated behind the string are inserted sequentially. <br>
Here is an example for how printing with parameters:
````java
logger.trace("Hello {} world {}", "param1", 123.4);
````

this will print out:
```
Hello param1 world 123.4
```

You may enter any parameter which can be converted into a string.

## Colored Output
The logger will output a different color based on the log level of the called method. Below a screenshot of how that looks like: 
<br> <br>
![img.png](res/img.png)

## Pattern flags

| Flag | Meaning                                      | Example                    |
|------|----------------------------------------------|----------------------------|
| %v   | The actual text to log                       | "some user text"           |
| %n   | Logger's name                                | "some logger name"         |
| %l   | The log level of the message                 | "DEBUG", "INFO", etc       |
| %L   | Short log level of the message               | "D", "I", etc              |
| %a   | Abbreviated weekday name                     | "Thu"                      |
| %A   | Full weekday name                            | "Thursday"                 |
| %b   | Abbreviated month name                       | "Aug"                      |
| %B   | Full month name                              | "August"                   |
| %c   | Date and time representation                 | "Thu Aug 23 15:35:46 2014" |
| %C   | Year in 2 digits                             | "14"                       |
| %Y   | Year in 4 digits                             | "2014"                     |
| %D   | Short MM/DD/YY date                          | "08/23/14"                 |
| %m   | Month 01-12                                  | "11"                       |
| %d   | Day of month 01-31                           | "29"                       |
| %H   | Hours in 24 format 00-23                     | "23"                       |
| %M   | Minutes 00-59                                | "59"                       |
| %S   | Seconds 00-59                                | "58"                       |
| %R   | 24-hour HH:MM time, equivalent to %H:%M      | "23:55"                    |
| %T   | ISO 8601 time format (HH:MM:SS), equivalent to %H:%M:%S | "23:55:59"                 |
| %%   | The % sign                                   | "%"                        |

