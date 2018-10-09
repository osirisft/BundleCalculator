package main.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import exception.IllegalFileContentException;
import exception.WrongFilePathException;

public interface BundleSource {

	Map<String, HashMap<Integer, Float>> extractBundleCostMapping(String sFilePath)
			throws WrongFilePathException, IllegalFileContentException, FileNotFoundException, IOException;

}
