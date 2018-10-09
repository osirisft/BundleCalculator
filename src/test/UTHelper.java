package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UTHelper {

	public static Path oDefaultPath = Paths.get(".");

	public static Path generateSampleTxtSourceFile(List<String> oLines) {
		try {
			Path oPath = Files.createTempFile(UTHelper.oDefaultPath, "sampleTxtSource", "txt");
			oPath = Files.write(oPath, oLines);
			return oPath;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Path generateEmptyFile() {

		try {
			Path oPath = Files.createTempFile(UTHelper.oDefaultPath, "sampleTxtSource", "txt");
			return oPath;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
