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
import main.TxtFileOrder;
import main.interfaces.Order;

class TxtFileOrderTest {
	Order oOrder;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		this.oOrder = new TxtFileOrder();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testExtractOrderInfo_EmptyOrderContent() {
		Path oPath = UTHelper.generateEmptyFile();
		try {
			HashMap<String, Integer> mapOrder = (HashMap<String, Integer>) this.oOrder
					.extractOrderInfo(oPath.toString());
			Assertions.assertEquals(0, mapOrder.size());
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
	void testExtractOrderInfo_WrongFOrderFilePath() {
		Assertions.assertThrows(WrongFilePathException.class, () -> {
			this.oOrder.extractOrderInfo(null);
		});
	}

	@Test
	void testExtractOrderInfo_CorrectOrderContentOneLine() {
		List<String> oLines = this.generateFileContent(1);
		Path oPath = UTHelper.generateSampleTxtSourceFile(oLines);
		try {

			HashMap<String, Integer> mapOrder = (HashMap<String, Integer>) this.oOrder
					.extractOrderInfo(oPath.toString());
			Assertions.assertEquals(1, mapOrder.size(), "File contains 1 bundle type should have 1 mapping entry");

			Integer oAmount = mapOrder.get("IMG");
			Assertions.assertEquals(23, oAmount.intValue());

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
	void testExtractOrderInfo_CorrectOrderContentMultipleLine() {
		List<String> oLines = this.generateFileContent(3);
		Path oPath = UTHelper.generateSampleTxtSourceFile(oLines);
		try {

			HashMap<String, Integer> mapOrder = (HashMap<String, Integer>) this.oOrder
					.extractOrderInfo(oPath.toString());
			Assertions.assertEquals(3, mapOrder.size());

			Integer oAmount = mapOrder.get("IMG");
			Assertions.assertEquals(18, oAmount.intValue());

			oAmount = mapOrder.get("FLG");
			Assertions.assertEquals(27, oAmount.intValue());

			oAmount = mapOrder.get("VID");
			Assertions.assertEquals(35, oAmount.intValue());

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
	void testExtractOrderInfo_IllegalOrderContent() {
		try {
			List<String> oLines = this.generateIllegalFileContent();
			Path oPath = UTHelper.generateSampleTxtSourceFile(oLines);
			Assertions.assertThrows(IllegalFileContentException.class, () -> {
				this.oOrder.extractOrderInfo(oPath.toString());
			});
			Files.delete(oPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> generateFileContent(int iLines) {
		List<String> oList = new ArrayList<String>();
		String s;
		switch (iLines) {
		case 1:
			s = "Image | IMG | 23";
			oList.add(s);
			break;

		case 3:
			s = "Image | IMG | 18";
			oList.add(s);
			s = "Audio | FLG | 27";
			oList.add(s);
			s = "Video | VID | 35";
			oList.add(s);
			break;

		default:
			s = "Image | IMG | 23";
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
