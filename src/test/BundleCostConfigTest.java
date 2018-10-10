package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.BundleCostConfig;
import main.TxtFileSource;
import main.interfaces.BundleSource;

class BundleCostConfigTest {

	@Test
	void testgetBouldeSource_TxtFileSource() {
		BundleCostConfig oConfig = new BundleCostConfig();
		BundleSource oSource = oConfig.getBundleSource("TXT", UTHelper.oDefaultPath.toString());
		Assertions.assertTrue(oSource instanceof TxtFileSource);

		oSource = oConfig.getBundleSource("XXX", UTHelper.oDefaultPath.toString());
		Assertions.assertTrue(oSource instanceof TxtFileSource);
	}

}
