package main;

import main.interfaces.BundleSource;

public class BundleCostConfig {

	public BundleSource getBundleSource(String sSourceType) {
		BundleSource oBundleSource;
		switch (sSourceType) {
		case "TXT":
			oBundleSource = new TxtFileSource();
			break;
//		case "XLS":
//			
//			break;
		default:
			oBundleSource = new TxtFileSource();
			break;
		}
		return oBundleSource;
	}

}
