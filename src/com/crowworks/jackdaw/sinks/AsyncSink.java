package com.crowworks.jackdaw.sinks;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncSink
{
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private final Thread worker;
    private final FileSink fileSink;

    public AsyncSink(FileSink fileSink)
    {
        this.fileSink = fileSink;
        worker = new Thread(() -> {
            while (true)
            {
                try
                {
                    String logMessage = queue.take();
                    fileSink.log(logMessage);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        worker.start();
    }

    public void log(String message)
    {
        queue.offer(message);
    }
}
