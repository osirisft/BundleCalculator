package main.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import exception.IllegalFileContentException;
import exception.WrongFilePathException;

public interface Order {
	public Map<String, Integer> extractOrderInfo()
			throws WrongFilePathException, IllegalFileContentException, FileNotFoundException, IOException;
}
