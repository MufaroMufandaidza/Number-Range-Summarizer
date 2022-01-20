import java.util.StringTokenizer;
import java.util.Collection;
import static org.junit.Assert.*;

import static java.util.Collections.sort;
import java.util.Arrays;

import java.util.ArrayList;

import numberrangesummarizer.NumberRangeSummarizer;

public class SummarizerImplementation implements NumberRangeSummarizer{
    
    public Collection<Integer> collect(String input){
        Collection collection = new ArrayList<Integer>();
        StringTokenizer st = new StringTokenizer(input, ",");

        while(st.hasMoreTokens()){
            collection.add(Integer.valueOf(st.nextToken()));
        }
        return collection;
    }

    public String summarizeCollection(Collection<Integer> input){
        //Declare all variables required.
        StringBuilder summary = new StringBuilder();
        StringBuilder summary_ = new StringBuilder();
        String subString = new String();
        String finalString = new String();
        int counter, tracker;
        //Initialize.
        counter = tracker = 0;
        /*
        * Check for invalid input.
        * ASSUMPTION: The input should contain at least 2 elements
        */ 
        if(input.size() < 2){
            return "Invalid input provided!";
        }
        
        //Convert the input into a string array for processing.
        for(Integer i: input){
            summary.append(Integer.toString(i) + ",");
        }
        subString = summary.toString();
        subString = subString.substring(0,subString.length()-1);
        String[] arr = subString.split(","); 

        //Sort given input before processing
        //Arrays.sort(arr);
        
        //Split String aarray into ranges.
        while(counter < arr.length - 1){
            while(tracker < arr.length - 1){
                if(Integer.valueOf(arr[counter+tracker+1]) == (Integer.valueOf(arr[counter+tracker]) + 1)){
                    tracker++;
                }
                else{
                    break;
                }
            }
            if(tracker == 0){
                summary_.append(arr[counter] + ",");
                counter++;
            }
            else{
                summary_.append(arr[counter] + "-" +(Integer.valueOf(arr[counter])+tracker) + ",");
                counter += tracker+1;
                tracker = 0;
            }
        }
        summary_.append(arr[counter]);
        finalString = summary_.toString();
        return finalString;
    }
    public static void main(String[] args) {
        /* ASSUMPTIONS 
        * 1. input will be naturally sorted.
        * 2. Only string inputs will be provided.
        */
        SummarizerImplementation si = new SummarizerImplementation();
        String input1 = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        String output1 = "1,3,6-8,12-15,21-24,31";
        System.out.println(si.summarizeCollection(si.collect(input1)));
    }
}
