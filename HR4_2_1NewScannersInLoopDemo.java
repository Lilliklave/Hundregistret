import java.util.*;

public class HR4_2_1NewScannersInLoopDemo {

	public static void main(String[] args) {

		ArrayList<Integer> data = new ArrayList<>();
		Scanner input = new Scanner(System.in);

		System.out.printf("Skriv tre heltal: ");
		for (int i = 1; i <= 3; i++) {
			data.add(input.nextInt());
		}

		for (int i = 4; i <= 6; i++) {
			
			System.out.printf("Skriv heltal nr %d: ", i);
			data.add(input.nextInt());
		}

		System.out.println("Du skrev in: ");
		System.out.println(data);

		input.close();
	}

}
