package org.example;
import java.time.LocalTime;

class BroadcastsTime implements Comparable<BroadcastsTime> {
    private final LocalTime time; // время

    public BroadcastsTime(String timeString) {
        this.time = LocalTime.parse(timeString);
    }

    public BroadcastsTime(LocalTime time) {
        this.time = time;
    }

    public byte hour() {
        return (byte) time.getHour();
    }

    public byte minutes() {
        return (byte) time.getMinute();
    }

    public boolean after(BroadcastsTime t) { //время после
        return this.time.isAfter(t.time);
    }

    public boolean before(BroadcastsTime t) { //время до
        return this.time.isBefore(t.time);
    }

    public boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return !this.before(t1) && !this.after(t2);  //время между
    }

    @Override
    public int compareTo(BroadcastsTime other) {
        return this.time.compareTo(other.time);
    }

    @Override
    public String toString() {
        return time.toString();
    }
}