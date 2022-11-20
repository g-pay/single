package single;

import java.util.Arrays;
import java.util.Scanner;

public class PRIORITYNP {

    public static void main(String[] args) {

        System.out.println("*** Priority Scheduling ***");

        System.out.print("Enter Number of Process: ");
        Scanner sc = new Scanner(System.in);
        int numberOfProcess = sc.nextInt();
        int ar[] = new int[numberOfProcess];
        int ct[] = new int[numberOfProcess];
        String process[] = new String[numberOfProcess];

        int p = 1;
        for (int i = 0; i < numberOfProcess; i++) {
            process[i] = "P" + p;
            p++;
        }

        System.out.println(Arrays.toString(process));

        System.out.print("Enter Burst Time for " + numberOfProcess + " process: ");

        int burstTime[] = new int[numberOfProcess];
        for (int i = 0; i< numberOfProcess; i++) {
            burstTime[i] = sc.nextInt();
        }

        System.out.println(Arrays.toString(burstTime));
        System.out.print("Enter Arrival Time for " + numberOfProcess + " process: ");
        for (int i = 0; i< numberOfProcess; i++) {
            ar[i] = sc.nextInt();
        }
        
        System.out.print("Enter Priority for " + numberOfProcess + " process: ");

        int priority[] = new int[numberOfProcess];
        for (int i = 0; i < numberOfProcess; i++) {
            priority[i] = sc.nextInt();
        }

        System.out.println(Arrays.toString(priority));

// Sorting process &amp; burst time by priority
int temp;
String temp2;
for (int i = 0; i < numberOfProcess - 1; i++) {
      for (int j = 0; j < numberOfProcess - 1; j++) {
            if (priority[j] < priority[j + 1]) {
                   temp = priority[j];
                    priority[j] = priority[j + 1];
                    priority[j + 1] = temp;

                    temp = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = temp;

                    temp2 = process[j];
                    process[j] = process[j + 1];
                    process[j + 1] = temp2;
                    
                    temp = ar[j];
                    ar[j] = ar[j + 1];
                    ar[j + 1] = temp;

                }
            }
        }

ct[0] = burstTime[0] + ar[0];
for(int i = 1; i < numberOfProcess; i++) {
    ct[i] = ct[i - 1] + burstTime[i];
}

        int TAT[] = new int[numberOfProcess];
        int waitingTime[] = new int[numberOfProcess];

// Calculating Waiting Time &amp; Turn Around Time
        for (int i = 0; i <numberOfProcess; i++) {
            TAT[i] = ct[i] - ar[i];
            waitingTime[i] = TAT[i] - burstTime[i];
        }

        int totalWT = 0;
        int totalTAT = 0;
        double avgWT;
        double avgTAT;

        System.out.println("Process  AT   BT         PRIORTY      CT       WT        TAT");
        for (int i = 0; i<numberOfProcess; i++) {

            System.out.println(process[i] + "          " +ar[i] +"      "+ burstTime[i] + "       "+priority[i]+"           "+ct[i]+"       " +( waitingTime[i]) + "         " + (TAT[i]));
            totalTAT +=  TAT[i];
            totalWT += waitingTime[i];

        }

        avgWT = totalWT / (double) numberOfProcess;
        avgTAT = totalTAT / (double) numberOfProcess;

        System.out.println("\n Average Wating Time: " + avgWT);
        System.out.println(" Average Turn Around Time: " + avgTAT);

    }

}