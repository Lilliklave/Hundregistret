import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Arrays;
import java.util.stream.*;

import org.junit.jupiter.api.*;

public class HR4_2_1NewScannersInLoopDemoTest {

	public static final String VERSION = "2024-01-04 13:16";

	private ByteArrayOutputStream systemOut = new ByteArrayOutputStream();

	private InputStream originalSystemIn = System.in;
	private PrintStream originalSystemOut = System.out;

	@BeforeEach
	public void setupSystemOutputStreams() {
		System.setOut(new PrintStream(systemOut));
	}

	@AfterEach
	public void restoreSystemStreams() {
		System.setIn(originalSystemIn);
		System.setOut(originalSystemOut);
	}

	private void setSystemIn(String... userInputLines) {
		System.setIn(createUserInputStream(userInputLines));
	}

	private InputStream createUserInputStream(String... userInputLines) {
		var input = Arrays.stream(userInputLines).collect(Collectors.joining("\n")) + "\n";
		return new ByteArrayInputStream(input.getBytes());
	}

	private String systemOut() {
		return systemOut.toString();
	}

	private String lastSystemOut() {
		String[] content = systemOut().split("\\R");
		return content[content.length - 1].trim();
	}

	private void executeTest(String expected, String... userInputLines) {
		setSystemIn(userInputLines);
		try {
			HR4_2_1NewScannersInLoopDemo.main(null);
			assertEquals(expected, lastSystemOut());
		} catch (Exception e) {
			fail(systemOut(), e);
		}
	}

	@Test
	void eachInputOnItsOwnLine() {
		executeTest("[1, 22, 333, 4444, 55555, 666666]", "1", "22", "333", "4444", "55555", "666666");
	}

	@Test
	void allInputOnOneLine() {
		executeTest("[1, 22, 333, 4444, 55555, 666666]", "1 22", "333", "4444", "55555", "666666");
	}

}
