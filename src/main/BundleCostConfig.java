package main;

import main.interfaces.BundleSource;

public class BundleCostConfig {

	public BundleSource getBundleSource(String sSourceType, String sPath) {
		BundleSource oBundleSource;
		switch (sSourceType) {
		case "TXT":
			oBundleSource = new TxtFileSource(sPath);
			break;
//		case "XLS":
//			
//			break;
		default:
			oBundleSource = new TxtFileSource(sPath);
			break;
		}
		return oBundleSource;
	}

}
