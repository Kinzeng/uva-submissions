import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Template {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner console = new Scanner(System.in);
		console.close();
	}
}
