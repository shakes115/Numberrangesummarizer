

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    public Collection<Integer> collect(String input) {
        // Split the input string into an array of strings representing individual numbers
        String[] numberStrings = input.split(",");
        
        // Parse each number string into an integer and add it to a collection
        List<Integer> numbers = new ArrayList<>();
        for (String numberString : numberStrings) {
            numbers.add(Integer.parseInt(numberString.trim()));
        }
        
        return numbers;
    }

    public String summarizeCollection(Collection<Integer> input) {
        // Sort the input collection
        List<Integer> sortedNumbers = new ArrayList<>(input);
        Collections.sort(sortedNumbers);
        
        // Use a StringBuilder to build the summarized string
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < sortedNumbers.size()) {
            // Start a new range with the current number
            int start = sortedNumbers.get(i);
            int end = start;
            
            // Expand the range until the next number is not sequential
            while (i < sortedNumbers.size() - 1 && sortedNumbers.get(i + 1) - sortedNumbers.get(i) == 1) {
                end = sortedNumbers.get(i + 1);
                i++;
            }
            
            // Add the range to the string builder
            if (start == end) {
                builder.append(start);
            } else {
                builder.append(start).append("-").append(end);
            }
            
            // Add a comma if there are more numbers
            if (i < sortedNumbers.size() - 1) {
                builder.append(", ");
            }
            
            i++;
        }
        
        return builder.toString();
    }
    
    // A simple main method to test the implementation
    public static void main(String[] args) {
        NumberRangeSummarizerImpl summarizer = new NumberRangeSummarizerImpl();
        String input = "1,10,24,51,52,53,54";
        Collection<Integer> numbers = summarizer.collect(input);
        String summary = summarizer.summarizeCollection(numbers);
        System.out.println(summary);
    }
    public interface NumberRangeSummarizer {

        //collect the input
        Collection<Integer> collect(String input);
    
        //get the summarized string
        String summarizeCollection(Collection<Integer> input);
    
    }}


