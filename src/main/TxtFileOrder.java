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
import main.interfaces.Order;

public class TxtFileOrder implements Order {

	@Override
	public Map<String, Integer> extractOrderInfo(String sPath)
			throws WrongFilePathException, IllegalFileContentException, FileNotFoundException, IOException {

		Map<String, Integer> mapOrder = new HashMap<String, Integer>();
		Path oPath;
		ArrayList<String> aLines;

		if (sPath == null) {
			throw new WrongFilePathException();
		} else {
			oPath = Paths.get(sPath);
			try (Stream<String> oFileStream = Files.lines(oPath)) {
				aLines = (ArrayList<String>) oFileStream.collect(Collectors.toList());
				for (String s : aLines) {
					String sType = this.getBundleType(s);
					int iBundleAmount = this.getBundleAmount(s);
					mapOrder.put(sType, iBundleAmount);
				}
			}
		}
		return mapOrder;
	}

	private String getBundleType(String sLine) {
		String[] aSnippets = sLine.split("\\|");
		return aSnippets[1].trim();

	}

	private int getBundleAmount(String sLine) throws IllegalFileContentException {
		String[] aSnippets = sLine.split("\\|");
		int iAmount;
		try {
			iAmount = Integer.valueOf(aSnippets[2].trim());
		} catch (NumberFormatException e) {
			throw new IllegalFileContentException();
		}
		return iAmount;
	}

}
