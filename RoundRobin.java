import java.util.*;

class RoundRobin {
    static class Process {
        int id;         // Process ID
        int bt;         // Burst Time
        int at;         // Arrival Time
        int rt;         // Remaining Time (initialized with burst time)
        int wt = 0;     // Waiting Time
        int tat = 0;    // Turnaround Time
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Number of processes
        System.out.print("Enter the number of processes: ");
        int n = in.nextInt();

        // Time quantum
        System.out.print("Enter the time quantum: ");
        int quantum = in.nextInt();

        Process[] processes = new Process[n];
        int totalBurstTime = 0;

        // Input burst time and arrival time for each process
        System.out.println("Enter burst time and arrival time for each process:");
        for (int i = 0; i < n; i++) {
            processes[i] = new Process();
            processes[i].id = i + 1;
            System.out.print("Burst time for P" + (i + 1) + ": ");
            processes[i].bt = in.nextInt();
            System.out.print("Arrival time for P" + (i + 1) + ": ");
            processes[i].at = in.nextInt();
            processes[i].rt = processes[i].bt; // Remaining time starts as full burst time
            totalBurstTime += processes[i].bt;
        }

        // Sorting processes based on arrival time
        Arrays.sort(processes, Comparator.comparingInt(p -> p.at));

        int time = 0; // Current time
        int completed = 0;
        Queue<Process> readyQueue = new LinkedList<>();
        ArrayList<Process> arrivedProcesses = new ArrayList<>();

        // Main Round Robin scheduling loop
        while (completed < n) {
            // Add processes that have arrived by the current time
            for (Process p : processes) {
                if (p.at <= time && !arrivedProcesses.contains(p)) {
                    readyQueue.add(p);
                    arrivedProcesses.add(p);
                }
            }

            if (readyQueue.isEmpty()) {
                time++; // If no process is ready, increment time
                continue;
            }

            Process current = readyQueue.poll(); // Get the next process in queue
            int executeTime = Math.min(current.rt, quantum); // Time slice for execution

            current.rt -= executeTime;
            time += executeTime;

            // Add processes that have arrived during the current time slice
            for (Process p : processes) {
                if (p.at <= time && !arrivedProcesses.contains(p)) {
                    readyQueue.add(p);
                    arrivedProcesses.add(p);
                }
            }

            if (current.rt > 0) {
                readyQueue.add(current); // If the process is not complete, re-add it to the queue
            } else {
                // Calculate completion, turnaround, and waiting times
                current.tat = time - current.at;
                current.wt = current.tat - current.bt;
                completed++;
            }
        }

        // Calculate average waiting time and turnaround time
        float totalWt = 0, totalTat = 0;
        System.out.println("Process\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (Process p : processes) {
            totalWt += p.wt;
            totalTat += p.tat;
            System.out.println("P" + p.id + "\t\t" + p.at + "\t\t" + p.bt + "\t\t" + p.wt + "\t\t" + p.tat);
        }

        System.out.println("Average Waiting Time = " + (totalWt / n));
        System.out.println("Average Turnaround Time = " + (totalTat / n));
    }
}
