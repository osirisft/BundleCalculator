package main;

import java.util.HashMap;
import java.util.Map;

import main.interfaces.CalculationResult;

public class CommandLineOutput implements CalculationResult {

	@Override
	public void displayResult(Map<String, HashMap<Integer, Integer>> mapBundleCompositionByType) {

		if (mapBundleCompositionByType != null && mapBundleCompositionByType.size() > 0) {
			mapBundleCompositionByType.forEach((String sType, HashMap<Integer, Integer> mapComposition) -> {
				System.out.println("Item Type: " + sType);
				mapComposition.forEach((Integer iBundle, Integer iAmount) -> {
					System.out.println("    " + iBundle + " X " + iAmount);
				});
			});
		}

	}

}
