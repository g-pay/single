package single;


import java.util.*;
class FIFO
{
    public static void main(String args[]) {
    	int n;
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter Total No. of Pages: ");
    	n= sc.nextInt();
        int pages[] = new int[n];
        int capacity = 3;
      
        Queue<Integer> queue = new LinkedList<>() ;
        HashSet<Integer> set = new HashSet<>(capacity);
        
        System.out.println("Enter pages: ");
        for(int i=0;i<n;i++) {
        	
        	pages[i] =  sc.nextInt();
        }
        int PF = 0;
        for (int i=0; i<n; i++){
            // Check If the memory holds fewer pages than capacity
            if (set.size() < capacity){
                // Push pages in the set until
                //  the size of set reaches its maximum capacity.
                if (!set.contains(pages[i])){
                    set.add(pages[i]);
                    // increment page fault
                    PF++;
                    // Insert the page into the queue
                    queue.add(pages[i]);
                }
            }
            else {
                //  If the current page is not already present in memory
                if (!set.contains(pages[i]))
                {
                    //Pop the topmost page from the queue and,
                    // Replace it with the current page
                    int val = queue.peek();
                    queue.poll();
                    set.remove(val);
                    set.add(pages[i]);
                    queue.add(pages[i]);
                    //Increment the page faults(PF) and return PF
                    PF++;
                }
            }
        }
        // Print the count of page faults
        System.out.println("The total number of page faults are: "+PF);
    }
}
