package xu.main.java.distribute_crawler_web.request_processor.template;

import java.util.HashMap;
import java.util.Map;

import xu.main.java.extractor.CssExtractor;
import xu.main.java.extractor.IExtractor;

public class ExtractorFactory {

	private ExtractorFactory() {
		init();
	}

	private static ExtractorFactory instance;

	private Map<String, IExtractor> extractorMap = new HashMap<String, IExtractor>();

	public IExtractor getExtractor(String extractorName) {
		return extractorMap.get(extractorName);
	}

	public static ExtractorFactory getInstance() {
		if (instance != null) {
			return instance;
		}
		instance = new ExtractorFactory();
		return instance;
	}

	private void init() {
		extractorMap.put("cssExtractor", new CssExtractor());
	}

}
