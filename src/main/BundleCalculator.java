package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import exception.IllegalFileContentException;
import exception.WrongFilePathException;
import main.interfaces.BundleSource;
import main.interfaces.Order;

public class BundleCalculator {

	private Order oOrder;
	private BundleSource oBundleSource;

	public BundleCalculator(Order oOrder, BundleSource oBundleSource) {
		super();
		this.oOrder = oOrder;
		this.oBundleSource = oBundleSource;
	}

	public Map<String, HashMap<Integer, Integer>> calculateCost() {

		Map<String, HashMap<Integer, Integer>> mapResult = new HashMap<String, HashMap<Integer, Integer>>();

		try {
			HashMap<String, Integer> mapOrder = (HashMap<String, Integer>) this.oOrder.extractOrderInfo();
			HashMap<String, HashMap<Integer, Float>> mapBudleCostByType = (HashMap<String, HashMap<Integer, Float>>) this.oBundleSource
					.extractBundleCostMapping();

			mapOrder.forEach((String sType, Integer iOrderAmount) -> {
				HashMap<Integer, Float> mapBundleCost = mapBudleCostByType.get(sType);
				HashMap<Integer, Integer> mapBundleComposition = this.getBundleCompostitionMapping(iOrderAmount,
						mapBundleCost);
				mapResult.put(sType, mapBundleComposition);
			});
		} catch (WrongFilePathException e) {
			e.printStackTrace();
		} catch (IllegalFileContentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapResult;
	}

	private HashMap<Integer, Integer> getBundleCompostitionMapping(int iOrderAmount,
			Map<Integer, Float> mapBundleCostMapping) {

		// sort the bundle key by descending
		Set<Integer> oBundleAmountSet = mapBundleCostMapping.keySet();
		LinkedList<Integer> listBundleAmount = new LinkedList<Integer>(oBundleAmountSet);
		Comparator<Integer> oDescending = (Integer i, Integer j) -> {
			return Integer.compare(j, i);
		};
		listBundleAmount.sort(oDescending);

		return this.findBundleCompostion(iOrderAmount, listBundleAmount);
	}

	private HashMap<Integer, Integer> findBundleCompostion(int iOrderAmount, LinkedList<Integer> listBundleAmount) {

		HashMap<Integer, Integer> mapComposition = new HashMap<Integer, Integer>();
		int iAmount = iOrderAmount;
		int iRemainder = 0;
		int iAnswer = 0;
		boolean bIsRemainderEQZero = false;

		for (int i : listBundleAmount) {
			if (iAmount >= i) {
				iRemainder = iAmount % i;
				iAnswer = iAmount / i;
				mapComposition.put(i, iAnswer);
				if (iRemainder == 0) {
					bIsRemainderEQZero = true;
					break;
				} else {
					iAmount = iRemainder;
				}
			}
		}
		if (bIsRemainderEQZero) {
		} else {
			listBundleAmount.pop();
			mapComposition = this.findBundleCompostion(iOrderAmount, listBundleAmount);
		}
		return mapComposition;
	}

	public static void main(String[] args) {

	}

}
