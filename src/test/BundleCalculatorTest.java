package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.IllegalFileContentException;
import exception.WrongFilePathException;
import main.BundleCalculator;
import main.interfaces.BundleSource;
import main.interfaces.Order;

class BundleCalculatorTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculateCost_Pass() {
		TestDoubleOrder oTestDoubleOrder = new TestDoubleOrder();
		TestDoubleSource oTestDoubleSource = new TestDoubleSource();

		BundleCalculator oCal = new BundleCalculator(oTestDoubleOrder, oTestDoubleSource);
		Map<String, HashMap<Integer, Integer>> mapResult = oCal.calculateCost();

		HashMap<Integer, Integer> mapBundleAmount = mapResult.get("IMG");
		Assertions.assertEquals(3, mapBundleAmount.size());
		Assertions.assertEquals(1, mapBundleAmount.get(1).intValue());
		Assertions.assertEquals(1, mapBundleAmount.get(3).intValue());
		Assertions.assertEquals(1, mapBundleAmount.get(6).intValue());

		mapBundleAmount = mapResult.get("VID");
		Assertions.assertEquals(3, mapBundleAmount.size());
		Assertions.assertEquals(1, mapBundleAmount.get(1).intValue());
		Assertions.assertEquals(1, mapBundleAmount.get(5).intValue());
		Assertions.assertEquals(2, mapBundleAmount.get(10).intValue());

		mapBundleAmount = mapResult.get("AB");
		Assertions.assertEquals(2, mapBundleAmount.size());
		Assertions.assertEquals(2, mapBundleAmount.get(1).intValue());
		Assertions.assertEquals(1, mapBundleAmount.get(5).intValue());

	}

}

class TestDoubleOrder implements Order {
	@Override
	public Map<String, Integer> extractOrderInfo()
			throws WrongFilePathException, IllegalFileContentException, FileNotFoundException, IOException {
		Map<String, Integer> mapOrder = new HashMap<String, Integer>();
		mapOrder.put("IMG", 10);
		mapOrder.put("VID", 26);
		mapOrder.put("AB", 7);
		mapOrder.put("CD", 13);

		return mapOrder;
	}
}

class TestDoubleSource implements BundleSource {

	@Override
	public Map<String, HashMap<Integer, Float>> extractBundleCostMapping()
			throws WrongFilePathException, IllegalFileContentException, FileNotFoundException, IOException {
		Map<String, HashMap<Integer, Float>> mapBundleCostByType = new HashMap<String, HashMap<Integer, Float>>();

		HashMap<Integer, Float> mapBundleCost = new HashMap<Integer, Float>();
		mapBundleCost.put(1, 2f);
		mapBundleCost.put(3, 5f);
		mapBundleCost.put(6, 10f);
		mapBundleCostByType.put("IMG", mapBundleCost);

		mapBundleCost = new HashMap<Integer, Float>();
		mapBundleCost.put(1, 3f);
		mapBundleCost.put(5, 14f);
		mapBundleCost.put(10, 25f);
		mapBundleCostByType.put("VID", mapBundleCost);

		mapBundleCost = new HashMap<Integer, Float>();
		mapBundleCost.put(1, 3f);
		mapBundleCost.put(5, 14f);
		mapBundleCost.put(10, 25f);
		mapBundleCostByType.put("AB", mapBundleCost);

		mapBundleCost = new HashMap<Integer, Float>();
		mapBundleCost.put(3, 3f);
		mapBundleCost.put(5, 14f);
		mapBundleCost.put(9, 25f);
		mapBundleCostByType.put("CD", mapBundleCost);
		return mapBundleCostByType;
	}

}
