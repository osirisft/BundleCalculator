package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exception.IllegalFileContentException;
import exception.WrongFilePathException;
import main.interfaces.BundleSource;

public class TxtFileSource implements BundleSource {

	private Map<String, HashMap<Integer, Float>> mapBundleCost;

	public TxtFileSource() {
		this.mapBundleCost = new HashMap<String, HashMap<Integer, Float>>();
	}

	@Override
	public Map<String, HashMap<Integer, Float>> extractBundleCostMapping(String sFilePath)
			throws WrongFilePathException, IllegalFileContentException, FileNotFoundException, IOException {

		Path oPath;
		ArrayList<String> aLines;

		if (sFilePath == null) {
			throw new WrongFilePathException();
		} else {
			oPath = Paths.get(sFilePath);
			try (Stream<String> oFileStream = Files.lines(oPath)) {
				aLines = (ArrayList<String>) oFileStream.collect(Collectors.toList());
				for (String s : aLines) {
					String sType = this.getBundleType(s);
					HashMap<Integer, Float> mapAmountCost = this.getBundleAmountandCost(s);
					this.mapBundleCost.put(sType, mapAmountCost);
				}
			}
		}
		return this.mapBundleCost;
	}

	public Map<String, HashMap<Integer, Float>> getBuncleCostMapping() {
		return this.mapBundleCost;
	}

	private String getBundleType(String sLine) {
		String[] aLine = sLine.split("\\|");
		return aLine[1].trim();

	}

	private HashMap<Integer, Float> getBundleAmountandCost(String sLine) throws IllegalFileContentException {
		HashMap<Integer, Float> mapAmountCost = new HashMap<Integer, Float>();
		String sBundle = sLine.split("\\|")[2].trim();
		String[] aSnippets = sBundle.split(" ");

		// snippet - "1 @ $2 2 @ $3 5 @ $6" will be converted into map
		// {{1,2},{2,3},{5,6}}

		try {
			int iKey = 0;
			int iCounter = 1;
			for (int i = 0; i < aSnippets.length; i++) {
				switch (iCounter) {
				case 1:
					iKey = Integer.valueOf(aSnippets[i]);
					break;
				case 3:
					mapAmountCost.put(iKey, Float.valueOf(aSnippets[i].replaceAll("\\$", "")));
					break;
				default:
					break;
				}
				if (iCounter == 3) {
					iCounter = 1;
					iKey = 0;
				} else {
					iCounter++;
				}
			}
		} catch (NumberFormatException e) {
			throw new IllegalFileContentException();
		}
		return mapAmountCost;
	}

}
