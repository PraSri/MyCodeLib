package hash_maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateFileInSystem {

    // Function to find duplicate files based on their content
    public static List<List<String>> findDuplicate(String[] paths) {
        // Dictionary to store the content as key and list of file paths as value
        Map<String, List<String>> fileMap = new HashMap<>();

        // Iterate through each path in the input array
        for (String path : paths) {
            // Split the directory path and files
            String[] values = path.split(" ");

            // Iterate through each file in the current directory path
            for (int i = 1; i < values.length; i++) {
                // Split the file name and content
                String[] nameContent = values[i].split("\\(");
                // Extract content part
                String content = nameContent[1].substring(0, nameContent[1].length() - 1);

                String directory = values[0];
                String fileName = nameContent[0];

                // Construct the full file path
                String filePath = directory + "/" + fileName;

                // Add the file path to the list of the corresponding content
                fileMap.computeIfAbsent(content, k -> new ArrayList<>()).add(filePath);
            }
        }

        // List to store the result groups of duplicate file paths
        List<List<String>> result = new ArrayList<>();

        // Iterate through the dictionary to find duplicates
        for (List<String> pathsWithSameContent : fileMap.values()) {
            // Only add to result if there are more than one file with the same content
            if (pathsWithSameContent.size() > 1) {
                result.add(pathsWithSameContent);
            }
        }

        return result;
    }

    // Driver code
    public static void main(String[] args) {
        List<String[]> inputList = new ArrayList<>();
        inputList.add(new String[]{
                "home/user1 1.txt(hello) 2.txt(world)",
                "home/user2 3.txt(hello)",
                "home/user3/docs 4.txt(world)",
                "home/user3/docs 5.txt(greetings)"
        });
        inputList.add(new String[]{
                "data 1.csv(data1) 2.csv(data2)",
                "data/2021 3.csv(data1) 4.csv(data3)",
                "data/2022 5.csv(data2)",
                "data/2021/backup 6.csv(data3)"
        });
        inputList.add(new String[]{
                "reports/jan 1.doc(report1) 2.doc(report2)",
                "reports/feb 3.doc(report1)",
                "reports/mar 4.doc(report2)",
                "reports/apr 5.doc(report3)"
        });
        inputList.add(new String[]{
                "docs 1.doc(text1) 2.doc(text2)",
                "docs/2020 3.doc(text1)",
                "docs/2021 4.doc(text2)",
                "docs/2021/backup 5.doc(text3)"
        });
        inputList.add(new String[]{
                "csv 1.csv(123) 2.csv(456)",
                "csv/2022 3.csv(123)",
                "csv/2023 4.csv(456) 5.csv(789)",
                "csv/backup 6.csv(789)"
        });

        for (int i = 0; i < inputList.size(); i++) {
            System.out.println((i + 1) + ".\tInput string:");
            for (String path : inputList.get(i)) {
                System.out.println("\t\t" + path);
            }

            List<List<String>> duplicates = findDuplicate(inputList.get(i));

            System.out.println("\n\tDuplicate files: ");
            for (List<String> group : duplicates) {
                System.out.println("\t\t" + String.join(", ", group));
            }
            System.out.println();
        }
    }

    /**
     * FOLLOW UP:
     *
     * DFS or BFS for file search?
     * Both DFS and BFS can work. DFS is often preferred for deep directory structures, while BFS can be used for broader ones.
     *
     * Large file content (GB-level)?
     * For large files, it’s better not to load the entire file into memory. Instead, one can read the file in chunks, compute a hash (like SHA-256) incrementally, and use that hash to identify duplicate content. This minimizes memory usage since only a small hash is stored, not the whole file.
     *
     * Reading file by 1KB at a time?
     * When restricted to reading files in 1KB chunks, a rolling hash algorithm could be used. This allows incremental processing of files, and comparisons can be made based on the hash, with further verification chunk-by-chunk if necessary.
     *
     * Time complexity of the modified solution?
     * The time complexity is typically O(n * m), where n is the number of files and m is the average file size. The most time-consuming part is usually reading and hashing the files. Memory usage would mostly come from storing file paths and hashes. Optimization can be achieved by reading larger chunks when possible.
     *
     * Avoiding false positives for duplicates?
     * To avoid false positives, file sizes can be compared first. If the sizes match, the file hashes are compared. If the hashes are identical, a byte-by-byte comparison can be performed to ensure the files are truly duplicates.
     */
    

}
