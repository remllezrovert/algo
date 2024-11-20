package wk7;

import java.util.*;

import java.util.ArrayList;

public class GreedyIntervals {

	private static void sortIntervalsByStartTime(List<Interval> intervals) {
		Collections.sort(intervals, (arg1, arg2) -> arg1.start - arg2.start);
	}

	private static void sortIntervalsByEndTime(List<Interval> intervals) {
		Collections.sort(intervals, (arg1, arg2) -> arg1.end - arg2.end);
	}


	
	public static ArrayList<Interval> schedule(List<Interval> intervals) { // complete this method
			sortIntervalsByEndTime(intervals);
			List<Interval> sorted = intervals;
			ArrayList<Interval> optimal = new ArrayList<>();
			optimal.add(sorted.get(0));
			Interval previous = sorted.get(0);
		for (int i = 1; i < sorted.size(); i++){
			Interval current = sorted.get(i);
			if (current.start >= previous.end){
				optimal.add(current);
				previous = current;
			}
		}
		return optimal;
	}

	public static int color(List<Interval> intervals) { // complete this method
		sortIntervalsByStartTime(intervals);
		PriorityQueue<Integer> pq = new PriorityQueue<>(new IntegerComparator());	
		pq.add(intervals.get(0).end);

		for (int i = 1; i < intervals.size(); i++) {
			Interval current = intervals.get(i);

			if (current.start >= pq.peek()) {
				pq.poll();
			}

			pq.add(current.end);
    	}

    return pq.size();

	}
}
