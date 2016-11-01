import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class uva11701 {
	private static class Range {
		double start;
		double end;
		
		public Range(double start, double end) {
			this.start = start;
			this.end = end;
		}
		
		public boolean contains(double n) {
			return n >= start && n <= end;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		ArrayList<Range> cantorSet = findRange(0, 1);
		
		while (console.hasNextDouble()) {
			double n = console.nextDouble();
			boolean member = false;
			for (Range range : cantorSet) {
				if (range.contains(n)) {
					member = true;
					break;
				}
			}
			
			System.out.println(member ? "MEMBER" : "NON-MEMBER");
		}
		console.close();
	}
	
	public static ArrayList<Range> findRange(double start, double end) {
		ArrayList<Range> ret = new ArrayList<Range>();
		if (end - start < 0.00001) {
			ret.add(new Range(start, end));
			return ret;
		}
		
		ret.addAll(findRange(start, ((end - start) / 3) + start));
		ret.addAll(findRange(end - ((end - start) / 3), end));
		return ret;
	}
}
