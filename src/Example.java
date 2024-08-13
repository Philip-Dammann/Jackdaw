import com.crowworks.jackdaw.core.LogLevel;
import com.crowworks.jackdaw.core.Logger;
import com.crowworks.jackdaw.core.LoggerBuilder;

public class Example
{
    public static void main(String[] args)
    {
        Logger logger = new LoggerBuilder("Main")
                .setLogLevel(LogLevel.DEBUG)
                .setFilePath("app.log")
                .setLogPattern("[%T] %n: %v")
                .setEnableFileLogging(true)
                .setEnableConsoleLogging(true)
                .build();

        logger.trace("Trace test");
        logger.debug("Debug Test");
        logger.info("Info test");
        logger.warn("Warn test");
        logger.error("Error test");
        logger.fatal("Fatal test");
    }
}
