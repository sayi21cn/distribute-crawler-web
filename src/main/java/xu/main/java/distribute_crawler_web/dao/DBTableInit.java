package xu.main.java.distribute_crawler_web.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import xu.main.java.distribute_crawler_web.configure.TermPreprocessorWebConfigure;
import xu.main.java.distribute_crawler_web.util.DbUtil;


public class DBTableInit {

	public static void createTable() {
		Logger logger = Logger.getLogger(DBTableInit.class);

		logger.info("DB init start !");

		DbUtil dbUtils = new DbUtil(TermPreprocessorWebConfigure.JDBC_DB_NAME);

		String createRulePatternTableSql = "CREATE TABLE if not exists `rule_pattern` (`id` int(11) NOT NULL AUTO_INCREMENT,`identifyCharacters` varchar(500) DEFAULT NULL,`maxParseLength` int(10) DEFAULT NULL,`patternName` varchar(500) DEFAULT NULL,`numberToTel` varchar(5000) DEFAULT NULL,`numberToNature` varchar(5000) DEFAULT NULL,`numberToCommon` varchar(5000) DEFAULT NULL,`is_delete` int(10) DEFAULT '0',PRIMARY KEY (`id`)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='规则模板表';";

		String createTaskListTableSql = "CREATE TABLE if not exists `task_list` (`id` int(11) NOT NULL AUTO_INCREMENT,`task_name` varchar(500) DEFAULT NULL,`insert_date` varchar(255) DEFAULT '',`rule_pattern_id` int(10) DEFAULT NULL,`status` varchar(255) DEFAULT '0' COMMENT '状态',`remarks` varchar(255) DEFAULT '' COMMENT '备注',`is_delete` int(10) DEFAULT '0',`source_file_name` varchar(500) DEFAULT NULL,`local_save_path` varchar(500) DEFAULT NULL,`local_save_path_processed` varchar(500) DEFAULT NULL,PRIMARY KEY (`id`)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='任务表';";

		try {
			dbUtils.executeUpdate(createRulePatternTableSql);
			dbUtils.executeUpdate(createTaskListTableSql);
		} catch (SQLException e) {
			logger.error("", e);
		}
		logger.info("DB init  end !");
	}
}
