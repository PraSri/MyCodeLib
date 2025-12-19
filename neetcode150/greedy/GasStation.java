package greedy;

public class GasStation {

    //Agar total petrol, total travel cost se kam hai, to kahin se bhi start karo, trip complete hi nahi ho sakti.
    //
    //Aur agar petrol enough hai, to hum greedy tareeke se kaam karte hain —
    // jaise hi beech me petrol negative ho jaata hai,
    // hum start point ko next station pe shift kar dete hain,
    // kyunki usse pehle wale kisi bhi station se start karna already fail ho chuka hota hai

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0;
        int n = gas.length;
        for(int i = 0;i<n;i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }
        if(totalGas < totalCost) {
            return -1;
        }
        int startIndex = 0;
        int currFuel = 0;
        for(int i = 0;i<n;i++) {
            currFuel += gas[i] - cost[i];
            if(currFuel < 0) {
                // reset
                startIndex = i+1;
                currFuel = 0;
            }
        }
        return startIndex;
    }

}
