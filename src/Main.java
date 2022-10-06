import java.util.Scanner;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		StepTracker stepTracker = new StepTracker();
		runApp(stepTracker);
	}
	// Looped main menu of application. Scans main menu commands.
	private static void runApp(StepTracker stepTracker) {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("\n____________MAIN MENU____________");
			System.out.println("Commands: add, goal, stats, exit");
			System.out.print("Enter command: ");
			String input = scanner.nextLine().toLowerCase();
			switch(input) {
				case "add":
					String[] steps = inputSteps(scanner);
					stepTracker.setStepsStat(steps);
					break;
				case "goal":
					int goal = inputGoal(scanner);
					stepTracker.setGoal(goal);
					break;
				case "stats":
					showStats(scanner, stepTracker);
					break;
				case "exit": 
					System.out.println("\nYou've left this app.");
					scanner.close();
					System.exit(0);
				default: System.out.print("Incorrect command! ");
			}
		}
	}
	// Scans input for new daily goal. Checks for number > 0.
	private static int inputGoal(Scanner scanner) {
		int newGoal;
		System.out.print("Enter your new goal: ");
		while (!scanner.hasNextInt()) {
			System.out.print("This is not a number. Try again: ");
			scanner.nextLine();
		}
		newGoal = scanner.nextInt();
		if (newGoal > 0) {
			System.out.println("Your new goal is " + newGoal + " steps a day.");
			return newGoal;
		} else {
			System.out.print("Your goal can't be less than 1. ");
			return inputGoal(scanner);
		}
	}
	// Scans input for count of steps for certain day
	private static String[] inputSteps(Scanner scanner) {
		String steps;
		String pattern = "^(30|[1-2][0-9]|0?[1-9]) (1[0-2]|0?[1-9]) [0-9]{1,5}$";
		System.out.print("Enter your steps in number format 'day month steps': ");
		steps = scanner.nextLine();
		if (steps.matches(pattern)) {
			String[] array = steps.split(" ");
			System.out.println("Adding " + array[2] + " steps for " + array[0] + " day of month " + array[1] + ".");
			return array;
		}
		System.out.println("Incorrect input. Try again.");
		return inputSteps(scanner);
	}
	// Scans for input specific month for statistic. Checks for number from 1 to 12.
	private static int inputMonth(Scanner scanner) {
		int month;
		System.out.print("Enter month number: ");
		while (!scanner.hasNextInt()) {
			System.out.print("This is not a number. Try again: ");
			scanner.nextLine();
		}
		month = scanner.nextInt();
		if (month >= 1 && month <=12) {
			return month;
		} else {
			System.out.println("There are only 12 months in the year.");
			return inputMonth(scanner);
		}
	}
	// Looped statistic menu of application. Scans for input certain statistic type.
	private static void showStats(Scanner scanner, StepTracker stepTracker) {
		System.out.println("\n____________STATS MENU____________");
		System.out.println("List of commands:");
		System.out.println("month - Stats for each day in month");
		System.out.println("total - Total number of steps per month");
		System.out.println("max - Maximum number of steps per month");
		System.out.println("average - Average number of steps per month");
		System.out.println("distance - Distance in km per month");
		System.out.println("calories - Number of calories burned per month");
		System.out.println("best - Maximum sequence of days goal was completed per month");
		System.out.print("\nChoose yout statistics: ");
		String input = scanner.nextLine().toLowerCase();
		switch(input) {
			case "month": {
				int month = inputMonth(scanner);
				List<String> monthStat = stepTracker.getMonthStat(month);
				System.out.println(monthStat);
				break;
			}
			case "total": {
				int month = inputMonth(scanner);
				int totalStat = stepTracker.getTotalStat(month);
				System.out.println("You walked " + totalStat + " steps this month.");
				break;
			}
			case "max": {
				int month = inputMonth(scanner);
				int maxStat = stepTracker.getMaxStat(month);
				System.out.println("This month your record for steps was " + maxStat + " steps.");
				break;
			}
			case "average": {
				int month = inputMonth(scanner);
				int maxStat = stepTracker.getAverageStat(month);
				System.out.println("This month your average result was " + maxStat + " steps per day.");
				break;
			}
			case "distance": {
				int month = inputMonth(scanner);
				double distanceStat = stepTracker.getDistanceStat(month);
				String formattedDistance = String.format("%.2f", distanceStat);
				System.out.println("This month you walked " + formattedDistance + " km.");
				break;
			}
			case "calories": {
				int month = inputMonth(scanner);
				double caloriesStat = stepTracker.getCaloriesStat(month);
				System.out.println("This month you lost " + caloriesStat + " calories.");
				break;
			}
			case "best": {
				int month = inputMonth(scanner);
				int bestSeries = stepTracker.getBestSeries(month);
				if (bestSeries == 0 || bestSeries > 1) {
					System.out.println("You best series of completed days is " + bestSeries + " days.");
				} else {
					System.out.println("You best series of completed days is " + bestSeries + " day.");
				}
				break;
			}
			default: System.out.println("Incorrect command! Try again."); showStats(scanner, stepTracker);
		}
	}

}
