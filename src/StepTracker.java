import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class StepTracker {
	private int goal;
	private HashMap<Integer, HashMap<Integer, Integer>> stepsStat; // {month:{day:steps, day:steps, ...}, month:{day:steps, ...}}
	
	public StepTracker() {
		this.goal = 10000;
		this.stepsStat = new HashMap<>();
		
		HashMap<Integer, Integer> month = new HashMap<>();
		for (int i = 1; i <= 30; i++) {
			month.put(i, 0);
		}
		for (int i = 1; i <= 12; i++) {
			stepsStat.put(i, month);
		}
	}
	
	public void setGoal(int newGoal) {
		this.goal = newGoal;
	}
	
	public void setStepsStat(String[] newStat) {
		int day = Integer.valueOf(newStat[0]);
		int month = Integer.valueOf(newStat[1]);
		int steps = Integer.valueOf(newStat[2]);
		stepsStat.get(month).put(day, steps);
	}
	
	public List<String> getMonthStat(int numOfMonth) {
		List<String> result = new ArrayList<>();
		HashMap<Integer, Integer> monthStat = stepsStat.get(numOfMonth);
		monthStat.forEach((day, steps) -> result.add(day + " day: " + steps));
		return result;
		
	}
	
	public int getTotalStat(int numOfMonth) {
		int result = 0;
		HashMap<Integer, Integer> monthStat = stepsStat.get(numOfMonth);
		for (int steps : monthStat.values()) {
			result += steps;
		}
		return result;
	}
	
	public int getMaxStat(int numOfMonth) {
		int result = 0;
		HashMap<Integer, Integer> monthStat = stepsStat.get(numOfMonth);
		for (int steps : monthStat.values()) {
			if (steps > result) {
				result = steps;
			}
		}
		return result;
	}
	
	public int getAverageStat(int numOfMonth) {
		int total = getTotalStat(numOfMonth);
		return total / 30;
	}
	
	public double getDistanceStat(int numOfMonth) {
		double lengthOfStep = 0.7;
		int total = getTotalStat(numOfMonth);
		return total / lengthOfStep / 1000;
	}
	
	public int getCaloriesStat(int numOfMonth) {
		double calloriesForStep = 0.05;
		int total = getTotalStat(numOfMonth);
		double result = total * calloriesForStep;
		return (int) result;
	}
	
	public int getBestSeries(int numOfMonth) {
		int record = 0;
		int current = 0;
		HashMap<Integer, Integer> monthStat = stepsStat.get(numOfMonth);
		for (int steps : monthStat.values()) {
			if (steps >= goal) {
				current++;
			} else if (current > record){
				record = current;
			} else {
				current = 0;
			}
		}
		return record;
	}

}
