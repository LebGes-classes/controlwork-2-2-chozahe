package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(Paths.get("data.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<BroadcastsTime, List<Program>> schedule = new TreeMap<>();
        String currentChannel = null;
        for (String line : lines) {
            if (line.startsWith("#")) {
                currentChannel = line.substring(1).trim();
            } else if (line.matches("\\d{2}:\\d{2}")) {
                BroadcastsTime time = new BroadcastsTime(line);
                String title = lines.get(lines.indexOf(line) + 1);
                Program program = new Program(currentChannel, time, title);

                schedule.computeIfAbsent(time, k -> new ArrayList<>()).add(program);
            }
        }
        for (Map.Entry<BroadcastsTime, List<Program>> entry : schedule.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        List<Program> allPrograms = new ArrayList<>();
        for (List<Program> programs : schedule.values()) {
            allPrograms.addAll(programs);
        }
        for (Program program : allPrograms) {
            System.out.println(program);
        }
        WorkingWithPrograms programs = new WorkingWithPrograms();
        programs.printAllProgramsSorted(schedule);
        programs.printCurrentPrograms(schedule);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название программы: ");
        String inputNameProgram = scanner.nextLine();
        programs.findProgramsByTitle(schedule, inputNameProgram);
        System.out.print("Введите название канала: ");
        String inputNameChannel = scanner.nextLine();
        programs.findCurrentProgramsByChannel(schedule, inputNameChannel);
        BroadcastsTime startTime = new BroadcastsTime("00:00");
        BroadcastsTime endTime = new BroadcastsTime("12:00");
        programs.findProgramsByChannelAndTimeRange(schedule, inputNameChannel, startTime, endTime);
        List<Program> outputPrograms = new ArrayList<>();
        for (List<Program> currentProgram : schedule.values()) {
            outputPrograms.addAll(currentProgram);
        }
        programs.saveProgramsToExcel(outputPrograms, "programs.xls");
    }
}
