package xu.main.java.distribute_crawler_web.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import xu.main.java.distribute_crawler_web.util.DbUtil;
import xu.main.java.distribute_crawler_web.vo.MyRulePattern;


public class RulePatternDao {
	private static DbUtil dbUtils = new DbUtil("jdbc/term_preprocessor");

	private final String SAVE_RULE_PATTERN_SQL = "insert into rule_pattern (identifyCharacters, maxParseLength, patternName, numberToTel, numberToNature, numberToCommon) values (?,?,?,?,?,?)";

	private final String GET_ALL_RULE_PATTERN_SQL = "select id,identifyCharacters, maxParseLength, patternName, numberToTel, numberToNature, numberToCommon from rule_pattern;";

	public int saveRulePattern(String identifyCharacters, String maxParseLength, String patternName, String numberToTel, String numberToNatrue, String numberToCommon) {
		List<String> dataList = Arrays.asList(identifyCharacters, maxParseLength, patternName, numberToTel, numberToNatrue, numberToCommon);
		return dbUtils.executeInsert(SAVE_RULE_PATTERN_SQL, dataList);
	}

	public List<Object> getAllRulePatterns() {
		try {
			return dbUtils.executeSQL(GET_ALL_RULE_PATTERN_SQL, MyRulePattern.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
