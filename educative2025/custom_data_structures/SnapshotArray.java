package custom_data_structures;

import java.util.*;

public class SnapshotArray {

    int snapId = 0;
    TreeMap<Integer, Integer>[] records;

    public SnapshotArray(int length) {
        records = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            records[i] = new TreeMap<>();
            records[i].put(0, 0);
        }
    }

    public void set(int index, int val) {

        records[index].put(snapId, val);

    }

    public int snap() {

        return snapId++;

    }

    public int get(int index, int snap_id) {

        return records[index].floorEntry(snap_id).getValue();

    }

}
