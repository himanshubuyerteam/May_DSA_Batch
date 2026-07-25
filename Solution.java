import java.util.*;

public class Solution {
    static class Result {
        public static List<Integer> getUnfulfilledCustomers(List<List<Integer>> requests, int totalInventory) {
            // bid -> list of {customerId, quantity, timestamp}
            TreeMap<Integer, List<int[]>> bidGroups = new TreeMap<>(Collections.reverseOrder());
            Map<Integer, Long> allocated = new LinkedHashMap<>();

            for (List<Integer> r : requests) {
                int customerId = r.get(0);
                int quantity   = r.get(1);
                int bid        = r.get(2);
                int ts         = r.get(3);
                bidGroups.computeIfAbsent(bid, k -> new ArrayList<>())
                         .add(new int[]{customerId, quantity, ts});
                allocated.put(customerId, 0L);
            }

            long inventory = totalInventory;

            for (Map.Entry<Integer, List<int[]>> entry : bidGroups.entrySet()) {
                if (inventory <= 0) break;

                List<int[]> group = entry.getValue();
                group.sort((a, b) -> Integer.compare(a[2], b[2])); // sort by timestamp

                int m = group.size();
                long totalDemand = 0;
                for (int[] c : group) totalDemand += c[1];

                if (totalDemand <= inventory) {
                    // everyone in this bid group gets fully served
                    for (int[] c : group) {
                        allocated.put(c[0], allocated.get(c[0]) + c[1]);
                    }
                    inventory -= totalDemand;
                    continue;
                }

                // Not enough for everyone: binary search max full rounds k
                long lo = 0, hi = 0;
                for (int[] c : group) hi = Math.max(hi, c[1]);

                while (lo < hi) {
                    long mid = lo + (hi - lo + 1) / 2;
                    long sum = 0;
                    for (int[] c : group) sum += Math.min((long) c[1], mid);
                    if (sum <= inventory) {
                        lo = mid; // feasible, try more rounds
                    } else {
                        hi = mid - 1;
                    }
                }
                long k = lo;

                long usedInFullRounds = 0;
                for (int[] c : group) {
                    long give = Math.min((long) c[1], k);
                    allocated.put(c[0], allocated.get(c[0]) + give);
                    usedInFullRounds += give;
                }

                long rem = inventory - usedInFullRounds;

                // distribute leftover single items, in timestamp order,
                // to whoever still needs more than k
                for (int[] c : group) {
                    if (rem == 0) break;
                    if (c[1] > k) {
                        allocated.put(c[0], allocated.get(c[0]) + 1);
                        rem--;
                    }
                }

                inventory = 0; // inventory fully exhausted in this group
            }

            List<Integer> result = new ArrayList<>();
            for (Map.Entry<Integer, Long> e : allocated.entrySet()) {
                if (e.getValue() == 0) result.add(e.getKey());
            }
            Collections.sort(result);
            return result;
        }
    }

    public static void main(String[] args) {
        // Quick sanity checks against the examples given
        List<List<Integer>> req1 = Arrays.asList(
            Arrays.asList(1, 5, 5, 0),
            Arrays.asList(2, 7, 8, 1),
            Arrays.asList(3, 7, 5, 1),
            Arrays.asList(4, 10, 3, 3)
        );
        System.out.println(Result.getUnfulfilledCustomers(req1, 18)); // expect [4]

        List<List<Integer>> req2 = Arrays.asList(
            Arrays.asList(1, 2, 5, 0),
            Arrays.asList(2, 1, 4, 2),
            Arrays.asList(3, 5, 4, 6)
        );
        System.out.println(Result.getUnfulfilledCustomers(req2, 3)); // expect [3]

        List<List<Integer>> req3 = Arrays.asList(
            Arrays.asList(101, 3, 10, 15),
            Arrays.asList(102, 2, 8, 20),
            Arrays.asList(103, 5, 8, 25),
            Arrays.asList(104, 4, 5, 30)
        );
        System.out.println(Result.getUnfulfilledCustomers(req3, 8)); // expect [104]
    }
}


// Not enough for everyone: need to do round-robin allocation

// First, find the maximum number of complete rounds we can do
long lo = 0;
long hi = 0;
for (int[] c : group) hi = Math.max(hi, c[1]);

// Binary search for the maximum k that fits in inventory
while (lo < hi) {
    long mid = lo + (hi - lo + 1) / 2;
    long sum = 0;
    for (int[] c : group) {
        sum += Math.min((long) c[1], mid);
        if (sum > inventory) break; // ✅ Early exit to avoid overflow
    }
    if (sum <= inventory) {
        lo = mid;
    } else {
        hi = mid - 1;
    }
}
long k = lo;

// Allocate k full rounds to everyone
long usedInFullRounds = 0;
long[] remainingNeeds = new long[m];  // ✅ Track remaining needs
for (int i = 0; i < m; i++) {
    int[] c = group[i];
    long give = Math.min((long) c[1], k);
    allocated.put(c[0], allocated.get(c[0]) + give);
    usedInFullRounds += give;
    remainingNeeds[i] = c[1] - give;  // ✅ Store remaining needs
}

long rem = inventory - usedInFullRounds;

// ✅ CORRECT: Distribute remaining items one by one in round-robin fashion
int idx = 0;
while (rem > 0 && idx < m) {
    for (int i = idx; i < m && rem > 0; i++) {
        if (remainingNeeds[i] > 0) {
            int[] c = group[i];
            allocated.put(c[0], allocated.get(c[0]) + 1);
            remainingNeeds[i]--;
            rem--;
        }
    }
    // Continue cycling through all customers until inventory exhausted
}




import java.util.*;

public class Solution {
    static class Result {
        public static List<Integer> getUnfulfilledCustomers(List<List<Integer>> requests, int totalInventory) {
            // Group requests by bid amount (higher bid gets priority)
            TreeMap<Integer, List<int[]>> bidGroups = new TreeMap<>(Collections.reverseOrder());
            Map<Integer, Long> allocated = new LinkedHashMap<>();

            // Process all requests and group by bid
            for (List<Integer> r : requests) {
                int customerId = r.get(0);
                int quantity = r.get(1);
                int bid = r.get(2);
                int timestamp = r.get(3);
                
                bidGroups.computeIfAbsent(bid, k -> new ArrayList<>())
                         .add(new int[]{customerId, quantity, timestamp});
                allocated.put(customerId, 0L);
            }

            long inventory = totalInventory;

            // Process each bid group from highest to lowest
            for (Map.Entry<Integer, List<int[]>> entry : bidGroups.entrySet()) {
                if (inventory <= 0) break;

                List<int[]> group = entry.getValue();
                
                // Sort by timestamp for round-robin among same bid
                group.sort((a, b) -> Integer.compare(a[2], b[2]));

                int m = group.size();
                long totalDemand = 0;
                for (int[] c : group) {
                    totalDemand += c[1];
                }

                // If inventory can fulfill all requests in this bid group
                if (totalDemand <= inventory) {
                    for (int[] c : group) {
                        allocated.put(c[0], allocated.get(c[0]) + c[1]);
                    }
                    inventory -= totalDemand;
                    continue;
                }

                // Not enough inventory for everyone in this group
                // Need round-robin allocation

                // Binary search to find maximum full rounds possible
                long lo = 0;
                long hi = 0;
                for (int[] c : group) {
                    hi = Math.max(hi, c[1]);
                }

                while (lo < hi) {
                    long mid = lo + (hi - lo + 1) / 2;
                    long sum = 0;
                    for (int[] c : group) {
                        sum += Math.min((long) c[1], mid);
                        if (sum > inventory) break; // Early exit optimization
                    }
                    if (sum <= inventory) {
                        lo = mid;
                    } else {
                        hi = mid - 1;
                    }
                }
                long fullRounds = lo;

                // Allocate full rounds to everyone
                long usedInFullRounds = 0;
                long[] remainingNeeds = new long[m];
                
                for (int i = 0; i < m; i++) {
                    int[] c = group[i];
                    long give = Math.min((long) c[1], fullRounds);
                    allocated.put(c[0], allocated.get(c[0]) + give);
                    usedInFullRounds += give;
                    remainingNeeds[i] = c[1] - give;
                }

                long remainingInventory = inventory - usedInFullRounds;

                // Distribute remaining items one by one in round-robin fashion
                int idx = 0;
                while (remainingInventory > 0 && idx < m) {
                    for (int i = idx; i < m && remainingInventory > 0; i++) {
                        if (remainingNeeds[i] > 0) {
                            int[] c = group[i];
                            allocated.put(c[0], allocated.get(c[0]) + 1);
                            remainingNeeds[i]--;
                            remainingInventory--;
                        }
                    }
                    // Continue cycling through all customers
                }

                inventory = 0; // All inventory used in this group
            }

            // Collect customers who received no items
            List<Integer> result = new ArrayList<>();
            for (Map.Entry<Integer, Long> e : allocated.entrySet()) {
                if (e.getValue() == 0) {
                    result.add(e.getKey());
                }
            }
            Collections.sort(result);
            return result;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Sample from problem
        List<List<Integer>> req1 = Arrays.asList(
            Arrays.asList(1, 5, 5, 0),
            Arrays.asList(2, 7, 8, 1),
            Arrays.asList(3, 7, 5, 1),
            Arrays.asList(4, 10, 3, 3)
        );
        System.out.println("Test 1: " + Result.getUnfulfilledCustomers(req1, 18)); 
        // Expected: [4]

        // Test Case 2: Sample Input 0
        List<List<Integer>> req2 = Arrays.asList(
            Arrays.asList(1, 2, 5, 0),
            Arrays.asList(2, 1, 4, 2),
            Arrays.asList(3, 5, 4, 6)
        );
        System.out.println("Test 2: " + Result.getUnfulfilledCustomers(req2, 3)); 
        // Expected: [3]

        // Test Case 3: Sample Input 1
        List<List<Integer>> req3 = Arrays.asList(
            Arrays.asList(101, 3, 10, 15),
            Arrays.asList(102, 2, 8, 20),
            Arrays.asList(103, 5, 8, 25),
            Arrays.asList(104, 4, 5, 30)
        );
        System.out.println("Test 3: " + Result.getUnfulfilledCustomers(req3, 8)); 
        // Expected: [104]

        // Test Case 4: All same bid with round-robin
        List<List<Integer>> req4 = Arrays.asList(
            Arrays.asList(1, 5, 5, 1),
            Arrays.asList(2, 3, 5, 2),
            Arrays.asList(3, 2, 5, 3)
        );
        System.out.println("Test 4: " + Result.getUnfulfilledCustomers(req4, 8)); 
        // Expected: [] (all get items)
        // Allocation: A gets 3, B gets 3, C gets 2

        // Test Case 5: Not enough inventory for anyone
        List<List<Integer>> req5 = Arrays.asList(
            Arrays.asList(1, 5, 5, 1),
            Arrays.asList(2, 3, 5, 2)
        );
        System.out.println("Test 5: " + Result.getUnfulfilledCustomers(req5, 0)); 
        // Expected: [1, 2] (no inventory, all unfulfilled)

        // Test Case 6: Edge case with single request
        List<List<Integer>> req6 = Arrays.asList(
            Arrays.asList(1, 10, 5, 1)
        );
        System.out.println("Test 6: " + Result.getUnfulfilledCustomers(req6, 7)); 
        // Expected: [] (gets 7 items, but not fully fulfilled - still gets some)
        // Wait, the requirement says "customers who received no items"
        // So even if partially fulfilled, they received items

        // Test Case 7: Partial fulfillment with same bid group
        List<List<Integer>> req7 = Arrays.asList(
            Arrays.asList(1, 5, 5, 1),
            Arrays.asList(2, 5, 5, 2),
            Arrays.asList(3, 5, 5, 3)
        );
        System.out.println("Test 7: " + Result.getUnfulfilledCustomers(req7, 7)); 
        // Expected: [] (all get at least 1 item)
        // Allocation: 3,2,2 in round-robin
    }
}



AND (
    :textQuery IS NULL OR
    (
        :type = 'title' AND
        LOWER(m.title) LIKE LOWER(CONCAT('%', :textQuery, '%'))
    ) OR
    (
        :type = 'director' AND
        LOWER(m.director) LIKE LOWER(CONCAT('%', :textQuery, '%'))
    ) OR
    (
        :type = 'writer' AND EXISTS (
            SELECT 1 FROM m.writers w
            WHERE LOWER(w) LIKE LOWER(CONCAT('%', :textQuery, '%'))
        )
    ) OR
    (
        :type = 'star' AND EXISTS (
            SELECT 1 FROM m.stars s
            WHERE LOWER(s) LIKE LOWER(CONCAT('%', :textQuery, '%'))
        )
    ) OR
    (
        :type = 'all' AND (
            LOWER(m.title) LIKE LOWER(CONCAT('%', :textQuery, '%')) OR
            LOWER(m.director) LIKE LOWER(CONCAT('%', :textQuery, '%')) OR
            LOWER(m.description) LIKE LOWER(CONCAT('%', :textQuery, '%')) OR
            EXISTS (
                SELECT 1 FROM m.writers w
                WHERE LOWER(w) LIKE LOWER(CONCAT('%', :textQuery, '%'))
            ) OR
            EXISTS (
                SELECT 1 FROM m.stars s
                WHERE LOWER(s) LIKE LOWER(CONCAT('%', :textQuery, '%'))
            )
        )
    )
)