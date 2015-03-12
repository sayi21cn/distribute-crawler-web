1. 预处理程序分为2部分: term-preprocessor-web and term-preprocessor-service 两部分通过 MySQL 数据库关联
2. term-preprocessor-web 负责处理模板添加、任务添加、文件上传、结果文件下载、任务进度查看、模板查看功能
   term-preprocessor-service 负责文本处理

3. term-preprocessor-web 部署前配置项:
	1) 需要在 Tomcat Context.xml文件中添加数据库连接，需要与 term-preprocessor-service 数据库连接一致，添加示例：
		<Resource auth="Container" driverClassName="com.mysql.jdbc.Driver"
		maxActive="1000" maxIdle="50" maxWait="5000"
		minEvictableIdleTimeMillis="1000" name="jdbc/term_preprocessor"
		numTestsPerEvictionRun="3" password="kaimen" testOnBorrow="true"
		testOnReturn="true" testWhileIdle="true"
		timeBetweenEvictionRunsMillis="300000" type="javax.sql.DataSource"
		url="jdbc:mysql://10.10.10.123:3306/term_preprocessor?useUnicode=true&amp;characterEncoding=UTF-8"
		username="dev" validationQuery="SELECT 1" />
		
	2) 配置WEB-INF/etc/term-preprocessor-web.properties 文件
		fileUploadSaveDir //上传文件保存路径
		processedFileDir	//处理结果文件保存路径
	
4. term-preprocessor-service 部署前配置:
	1) 配置 etc/term-preprocessor-service.properties 
		jobTrackerQueryTaskIntervalMillions	查询任务间隔毫秒数
		taskBatchLineNum					每次处理文本行数
		taskTrackerThreadNum				任务最大并行数
		dbUrl								数据库连接URL(dbc:mysql://10.10.10.123:3306/term_preprocessor?useUnicode=true&amp;characterEncoding=UTF-8)
		dbUserName							数据库连接用户名
		dbPassword							数据库连接密码
	
	2) 依赖文件
		etc/defaultRulesPattern.properties
		etc/identifyCharactersRuleMap.properties
		etc/ruleNameAndConverterMap.properties

5. term-preprocessor-service 默认规则修改
	通过 etc/defaultRulesPattern.properties 文件修改.key=value 形式
	key		:	模板名称
	value	:	具体规则，使用"-"分隔 
	
	
6. 数据库 
	数据库名称: term_preprocessor 建库语句: CREATE DATABASE IF NOT EXISTS term_preprocessor DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
	
	数据表:		rule_pattern and task_list 两张表,程序启动自动创建
 	rule_pattern	规则模板表	建表语句: CREATE TABLE IF NOT EXISTS `rule_pattern` (`id` int(11) NOT NULL AUTO_INCREMENT,`identifyCharacters` varchar(500) DEFAULT NULL,`maxParseLength` int(10) DEFAULT NULL,`patternName` varchar(500) DEFAULT NULL,`numberToTel` varchar(5000) DEFAULT NULL,`numberToNatrue` varchar(5000) DEFAULT NULL,`numberToCommon` varchar(5000) DEFAULT NULL,`is_delete` int(10) DEFAULT '0',PRIMARY KEY (`id`)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='规则模板表';
 	task_list		任务列表	建表语句: CREATE TABLE IF NOT EXISTS `task_list` (`id` int(11) NOT NULL AUTO_INCREMENT,`task_name` varchar(500) DEFAULT NULL,`insert_date` varchar(255) DEFAULT '',`rule_pattern_id` int(10) DEFAULT NULL,`status` varchar(255) DEFAULT '0' COMMENT '状态',`remarks` varchar(255) DEFAULT '' COMMENT '备注',`is_delete` int(10) DEFAULT '0',`source_file_name` varchar(500) DEFAULT NULL,`local_save_path` varchar(500) DEFAULT NULL,`local_save_path_processed` varchar(500) DEFAULT NULL,PRIMARY KEY (`id`)) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='任务表';