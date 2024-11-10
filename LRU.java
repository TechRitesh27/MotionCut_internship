import java.io.*;
import java.util.*;

class LRU {
    public static void main(String args[]) throws IOException {
        int n; // Number of page references
        int f; // Number of frames
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of FRAMES:");
        f = Integer.parseInt(br.readLine());
        
        System.out.println("Enter the number of INPUTS:");
        n = Integer.parseInt(br.readLine());
        
        int pages[] = new int[n];
        System.out.println("Enter INPUT (page references):");
        for (int i = 0; i < n; i++) {
            pages[i] = Integer.parseInt(br.readLine());
        }

        // ArrayList to store the current pages in the frames based on LRU order
        ArrayList<Integer> frame = new ArrayList<>(f);
        int hit = 0;    // Count of page hits
        int fault = 0;  // Count of page faults

        for (int i = 0; i < n; i++) {
            int currentPage = pages[i];
            
            // Check if page is already in frame (hit)
            if (frame.contains(currentPage)) {
                // Page is in frame, so it's a hit
                hit++;
                // Move this page to the end of the list (most recently used)
                frame.remove((Integer) currentPage);
                frame.add(currentPage);
            } else {
                // Page is not in frame, so it's a fault
                fault++;
                
                // If frame is full, remove the least recently used page (first in list)
                if (frame.size() == f) {
                    frame.remove(0);
                }
                
                // Add the new page to the end of the list
                frame.add(currentPage);
            }
        }

        // Calculate hit ratio
        float hitRatio = (float) hit / n;

        System.out.println("HIT: " + hit + "  FAULT: " + fault + "   HIT RATIO: " + hitRatio);
    }
}
