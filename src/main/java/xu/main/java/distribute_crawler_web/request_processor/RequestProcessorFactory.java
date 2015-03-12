package xu.main.java.distribute_crawler_web.request_processor;

import java.util.HashMap;
import java.util.Map;

import xu.main.java.distribute_crawler_web.request_processor.task.TaskAddProcessor;
import xu.main.java.distribute_crawler_web.request_processor.task.TaskListProcessor;
import xu.main.java.distribute_crawler_web.request_processor.template.ExtractorTemplateListProcessor;
import xu.main.java.distribute_crawler_web.request_processor.template.ExtractorTemplateSaveProcessor;
import xu.main.java.distribute_crawler_web.request_processor.template.ExtractorTemplateTestProcessor;


public class RequestProcessorFactory {

	private static Map<String, AbstractRequestProcessor> processorMap = new HashMap<String, AbstractRequestProcessor>();

	private static final EmptyProcessor EMPTY_PROCESSOR = new EmptyProcessor();

	private static RequestProcessorFactory instance = null;

	private RequestProcessorFactory() {
		init();
	}

	public AbstractRequestProcessor createProcessor(String processorName) {
		processorName = processorName.toLowerCase();
		if (processorMap.containsKey(processorName)) {
			return processorMap.get(processorName);
		}
		return EMPTY_PROCESSOR;
	}

	public static RequestProcessorFactory getInstance() {
		if (instance != null) {
			return instance;
		}
		instance = new RequestProcessorFactory();
		return instance;
	}

	private void init() {
		processorMap.put("template_test", new ExtractorTemplateTestProcessor());
		processorMap.put("template_save", new ExtractorTemplateSaveProcessor());
		processorMap.put("template_list", new ExtractorTemplateListProcessor());
		processorMap.put("task_add", new TaskAddProcessor());
		processorMap.put("task_list", new TaskListProcessor());
	}

}
