package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.IllegalFileContentException;
import exception.WrongFilePathException;
import main.TxtFileSource;
import main.interfaces.BundleSource;

class TxtFileSourceTest {

	BundleSource oSource;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		this.oSource = new TxtFileSource();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testExtractBundleCostMapping_WithNullFilePath() {
		Assertions.assertThrows(WrongFilePathException.class, () -> {
			this.oSource.extractBundleCostMapping(null);
		});
	}

	@Test
	void testExtractBundleCostMapping_EmptyFile() {
		Path oPath = UTHelper.generateEmptyFile();
		try {
			HashMap<String, HashMap<Integer, Float>> mapBundleCost = (HashMap<String, HashMap<Integer, Float>>) this.oSource
					.extractBundleCostMapping(oPath.toString());
			Assertions.assertEquals(0, mapBundleCost.size());
			Files.delete(oPath);
		} catch (WrongFilePathException e) {
			e.printStackTrace();
		} catch (IllegalFileContentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExtractBundleCostMapping_FileWithOneCorrectLine() {
		List<String> oLines = this.generateFileContent(1);
		Path oPath = UTHelper.generateSampleTxtSourceFile(oLines);
		try {

			HashMap<String, HashMap<Integer, Float>> mapBundleCost = (HashMap<String, HashMap<Integer, Float>>) this.oSource
					.extractBundleCostMapping(oPath.toString());
			Assertions.assertEquals(1, mapBundleCost.keySet().size(),
					"File contains 1 bundle type should have 1 mapping entry");

			HashMap<Integer, Float> mapAmountCostMapping = mapBundleCost.get("IMG");
			Assertions.assertEquals(3, mapAmountCostMapping.keySet().size());

			// validate all bundle amount
			Integer[] aKey = mapAmountCostMapping.keySet().toArray(new Integer[0]);
			Integer[] aExpectedKey = { new Integer(1), new Integer(2), new Integer(5) };
			Assertions.assertArrayEquals(aExpectedKey, aKey);

			// validate all bundle cost
			Float[] aCost = mapAmountCostMapping.values().toArray(new Float[0]);
			Float[] aExpectedCost = { new Float(2), new Float(3), new Float(6) };
			Assertions.assertArrayEquals(aExpectedCost, aCost);

			Files.delete(oPath);
		} catch (WrongFilePathException e) {
			e.printStackTrace();
		} catch (IllegalFileContentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExtractBundleCostMapping_FileWithMultipleCorrectLines() {
		List<String> oLines = this.generateFileContent(3);
		Path oPath = UTHelper.generateSampleTxtSourceFile(oLines);
		try {

			HashMap<String, HashMap<Integer, Float>> mapBundleCost = (HashMap<String, HashMap<Integer, Float>>) this.oSource
					.extractBundleCostMapping(oPath.toString());
			Assertions.assertEquals(3, mapBundleCost.keySet().size());

			HashMap<Integer, Float> mapAmountCostMapping = mapBundleCost.get("IMG");
			Assertions.assertEquals(3, mapAmountCostMapping.keySet().size());
			mapAmountCostMapping = mapBundleCost.get("FLG");
			Assertions.assertEquals(3, mapAmountCostMapping.keySet().size());
			mapAmountCostMapping = mapBundleCost.get("VID");
			Assertions.assertEquals(2, mapAmountCostMapping.keySet().size());

			// validate all bundle amount
			Integer[] aKey = mapAmountCostMapping.keySet().toArray(new Integer[0]);
			Integer[] aExpectedKey = { new Integer(1), new Integer(4) };
			Assertions.assertArrayEquals(aExpectedKey, aKey);

			// validate all bundle cost
			Float[] aCost = mapAmountCostMapping.values().toArray(new Float[0]);
			Float[] aExpectedCost = { new Float(8), new Float(30) };
			Assertions.assertArrayEquals(aExpectedCost, aCost);

			Files.delete(oPath);
		} catch (WrongFilePathException e) {
			e.printStackTrace();
		} catch (IllegalFileContentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testExtractBundleCostMapping_FileWithIllegalContent() {
		try {
			List<String> oLines = this.generateIllegalFileContent();
			Path oPath = UTHelper.generateSampleTxtSourceFile(oLines);
			Assertions.assertThrows(IllegalFileContentException.class, () -> {
				this.oSource.extractBundleCostMapping(oPath.toString());
			});
			Files.delete(oPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<String> generateFileContent(int iLines) {
		List<String> oList = new ArrayList<String>();
		String s;
		switch (iLines) {
		case 1:
			s = "Image | IMG | 1 @ $2 2 @ $3 5 @ $6";
			oList.add(s);
			break;

		case 3:
			s = "Image | IMG | 1 @ $2 2 @ $3 5 @ $6";
			oList.add(s);
			s = "Audio | FLG | 1 @ $5 5 @ $20 10 @ $30";
			oList.add(s);
			s = "Video | VID | 1 @ $8 4 @ $30";
			oList.add(s);
			break;

		default:
			s = "Image | IMG | 1 @ $2 2 @ $3 5 @ $6";
			oList.add(s);
			break;
		}
		return oList;

	}

	private List<String> generateIllegalFileContent() {
		List<String> oList = new ArrayList<String>();
		String s = "Image | IMG | xxx @ $2 2 @ $3 5 @ $6";
		oList.add(s);
		return oList;
	}

}
