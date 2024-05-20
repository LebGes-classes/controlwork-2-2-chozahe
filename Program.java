package org.example;

public class Program {private final String channel;  //название канала
    private final BroadcastsTime time; // время
    private final String title; //название программы

    public Program(String channel, BroadcastsTime time, String title) {
        this.channel = channel;
        this.time = time;
        this.title = title;
    }

    public String getChannel() {
        return channel;
    }

    public BroadcastsTime getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return channel + " - " + time + ": " + title;
    }
}
