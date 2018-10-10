package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.CommandLineOutput;

class CommandLineOutputTest {

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
	void testDisplayResult_Pass() {
		Map<String, HashMap<Integer, Integer>> mapBundleCostByType = new HashMap<String, HashMap<Integer, Integer>>();
		HashMap<Integer, Integer> mapBundleComposition = new HashMap<Integer, Integer>();
		mapBundleComposition.put(1, 2);
		mapBundleComposition.put(3, 4);
		mapBundleComposition.put(5, 6);
		mapBundleCostByType.put("ABC", mapBundleComposition);

		mapBundleComposition = new HashMap<Integer, Integer>();
		mapBundleComposition.put(1, 2);
		mapBundleComposition.put(3, 4);
		mapBundleComposition.put(5, 6);
		mapBundleCostByType.put("DEF", mapBundleComposition);

		CommandLineOutput oOutPut = new CommandLineOutput();
		oOutPut.displayResult(mapBundleCostByType);

	}

}
