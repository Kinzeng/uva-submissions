import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class uva10440 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		int c = console.nextInt();
		while (c-- > 0) {
			int n = console.nextInt();
			int t = console.nextInt();
			int m = console.nextInt();
			
			int[] cars = new int[m];
			for (int i = 0; i < m; i++) {
				cars[i] = console.nextInt();
			}
			
			int firstLoad = ((m % n) - 1 + n) % n;
			int currentCar = firstLoad + 1;
			int currentTime = cars[firstLoad] + 2 * t;
			int numTrips = 1;
			
			int numCars = 0;
			for (int i = currentCar; i < m; i++) {
				if (cars[i] > currentTime) {
					currentTime = cars[i];
				}
				
				numCars++;
				if (numCars >= n) {
					currentTime += 2 * t;
					numCars = 0;
					numTrips++;
				}
			}
			
			currentTime -= t;
			
			System.out.println(currentTime + " " + numTrips);
		}
		console.close();
	}
}
