package xu.main.java.distribute_crawler_web.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import xu.main.java.distribute_crawler_common.util.StringHandler;


public class DbUtil {
	private static Logger logger = Logger.getLogger(DbUtil.class);
	private String dbStr = "jdbc/";

	public DbUtil() {
	}

	public DbUtil(String dbStr) {
		this.dbStr = dbStr;
	}

	public Connection getConn() {
		return getConn(this.dbStr);
	}

	public Connection getConn(String dbStr) {
		Connection conn = null;
		DataSource source = null;
		InitialContext context = null;
		try {
			context = new InitialContext();
			source = (DataSource) context.lookup("java:comp/env/" + dbStr);
			conn = source.getConnection();
		} catch (Exception e) {
			logger.error("初始化数据库连接时出错：" + e.getMessage(), e);
		} finally {
			if (context != null) {
				try {
					context.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}
				context = null;
			}
		}

		return conn;
	}

	@SuppressWarnings("rawtypes")
	public List<Object> executeSQL(String sqlStr, Class clazz, String... parameters) throws SQLException {
		List<Object> list = new ArrayList<Object>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = getConn();
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sqlStr);
				for (int i = 0; i < parameters.length; i++) {
					psmt.setString(i + 1, parameters[i]);
				}
				logger.debug("sqlStr=" + sqlStr);
				rs = psmt.executeQuery();
				list = resultSet2Object(rs, clazz);
			} catch (InstantiationException e) {
				logger.error("InstantiationException", e);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return list;
	}

	public List<Map<String, Object>> executeSQL(String sqlStr, String... parameters) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = getConn();
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sqlStr);
				for (int i = 0; i < parameters.length; i++) {
					psmt.setString(i + 1, parameters[i]);
				}
				logger.debug("sqlStr=" + sqlStr);
				rs = psmt.executeQuery();
				list = resultSet2List(rs);
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return list;
	}

	public int executeUpdate(String sqlStr, String... parameters) throws SQLException {
		PreparedStatement psmt = null;
		int rs = 0;
		Connection conn = getConn();
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sqlStr);
				for (int i = 0; i < parameters.length; i++) {
					psmt.setString(i + 1, parameters[i]);
				}
				logger.debug("sqlStr=" + sqlStr);
				rs = psmt.executeUpdate();
			} catch (SQLException e) {
				throw e;
			} finally {
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return rs;
	}

	/**
	 * 
	 * @param sqlStr
	 * @param parameters
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> executeSQL(String sqlStr, List<Object> dataList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = getConn();
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sqlStr);
				for (int i = 0; i < dataList.size(); i++) {
					Object obj = dataList.get(i);
					if (obj instanceof String) {
						psmt.setString(i + 1, (String) obj);
					} else {
						psmt.setInt(i + 1, (Integer) obj);
					}
					obj = null;
				}
				logger.debug("sqlStr=" + sqlStr);
				rs = psmt.executeQuery();
				list = resultSet2List(rs);
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return list;
	}

	public Map<String, Map<String, Object>> executeSQL(String sqlStr, String keyColumn, List<String> dataList) throws SQLException {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = getConn();
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sqlStr);
				if (dataList != null && !dataList.isEmpty()) {
					for (int i = 0; i < dataList.size(); i++) {
						psmt.setString(i + 1, dataList.get(i));
					}
				}
				logger.debug("---------executeSQL=" + sqlStr);
				rs = psmt.executeQuery();
				map = ResultSet2Map(rs, keyColumn);
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return map;
	}

	/**
	 * @author Liangjiameng
	 * @param sqlStr
	 *            参数化SQL语句
	 * @param clazz
	 *            对象
	 * @param dataList
	 *            参数列表List（与SQL语句中参数顺序一致）
	 * @param conn
	 *            数据库连接
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List<Object> executeSQLWhitConn(String sqlStr, Class clazz, List<String> dataList, Connection conn) {
		List<Object> list = new ArrayList<Object>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sqlStr);
				for (int i = 0; i < dataList.size(); i++) {
					psmt.setString(i + 1, dataList.get(i));
				}
				logger.debug("---------executeSQL=" + sqlStr);
				rs = psmt.executeQuery();
				list = resultSet2Object(rs, clazz);
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} catch (InstantiationException e) {
				logger.error("InstantiationException", e);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return list;
	}

	/**
	 * @author liangjiameng
	 * @param sqlStr
	 *            参数化SQL语句
	 * @param dataList
	 *            参数列表List（与SQL语句中参数顺序一致）
	 * @return
	 * @throws SQLException
	 */
	public int executeUpdate(String sqlStr, List<String> dataList) {
		logger.debug("________________________________________________________________");
		logger.debug("executeUpdate:" + sqlStr);
		for (String dataItem : dataList) {
			logger.debug(dataItem);
		}
		PreparedStatement psmt = null;
		int rs = 0;
		Connection conn = getConn();
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sqlStr);
				for (int i = 0; i < dataList.size(); i++) {
					psmt.setString(i + 1, dataList.get(i));
				}
				rs = psmt.executeUpdate();
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return rs;
	}

	/**
	 * chau并返回上次插入的ID
	 */
	public int executeInsert(String sqlStr, List<String> dataList) {
		logger.debug("________________________________________________________________");
		logger.debug("executeInsert:" + sqlStr);
		for (String dataItem : dataList) {
			logger.debug(dataItem);
		}

		PreparedStatement psmt = null;
		int insertedId = 0;
		Connection conn = getConn();
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sqlStr);
				for (int i = 0; i < dataList.size(); i++) {
					psmt.setString(i + 1, dataList.get(i));
				}
				psmt.executeUpdate();
				insertedId = getLastInsertedId(conn);
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return insertedId;
	}

	private int getLastInsertedId(Connection conn) {
		int insertedId = 0;
		ResultSet results = null;
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement("select @@IDENTITY");
			results = psmt.executeQuery();
			if (results.next()) {
				insertedId = results.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				psmt = null;
			}
			if (results != null) {
				try {
					results.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				results = null;
			}
		}
		return insertedId;
	}

	/**
	 * @author liangjiameng
	 * @param sqlStr
	 *            参数化的Sql语句
	 * @param dataList
	 *            参数列表List（与SQL语句中参数顺序一致）
	 * @return
	 */
	public boolean queryExist(String sqlStr, List<String> dataList) {
		boolean isExist = false;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Connection conn = getConn();
		if (conn != null) {
			try {
				psmt = conn.prepareStatement(sqlStr);
				for (int i = 0; i < dataList.size(); i++) {
					psmt.setString(i + 1, dataList.get(i));
				}
				rs = psmt.executeQuery();
				isExist = rs.last();
			} catch (SQLException e) {
				logger.error("SQLException", e);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					rs = null;
				}
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					psmt = null;
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("SQLException", e);
					}
					conn = null;
				}
			}
		}
		return isExist;
	}

	/*
	 * 将rs结果转换成对象列表
	 * 
	 * @param rs jdbc结果集
	 * 
	 * @param clazz 对象的映射类 return 封装了对象的结果列表
	 */
	@SuppressWarnings("rawtypes")
	public List<Object> resultSet2Object(ResultSet rs, Class clazz) throws SQLException, InstantiationException, IllegalAccessException {
		// 结果集的元素对象
		// 返回结果的列表集合
		List<Object> list = new ArrayList<Object>();
		if (rs != null) {
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取结果集的元素个数
			int colCount = rsmd.getColumnCount();
			// 业务对象的属性数组
			Field[] fields = clazz.getDeclaredFields();
			while (rs.next()) {// 对每一条记录进行操作
				Object obj = clazz.newInstance();// 构造业务对象实体
				// 将每一个字段取出进行赋值
				for (int i = 1; i <= colCount; i++) {
					Object value = rs.getObject(i);
					// 寻找该列对应的对象属性
					for (int j = 0; j < fields.length; j++) {
						Field f = fields[j];
						// 如果匹配进行赋值
						if (f.getName().equalsIgnoreCase(rsmd.getColumnLabel(i))) {
							boolean flag = f.isAccessible();
							f.setAccessible(true);
							f.set(obj, value);
							f.setAccessible(flag);
						}
					}
				}
				list.add(obj);
			}
		}
		return list;
	}

	public List<Map<String, Object>> resultSet2List(ResultSet rs) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			// 封装resultSet数据 ,利用ResultSetMetaData对象可获得表的结构
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {// 循环表
				// 每行记录放到一个map里！
				Map<String, Object> map = new HashMap<String, Object>();// 每行记录放到一个
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnLabel(i), rs.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
		}
		return list;
	}

	public Map<String, Map<String, Object>> ResultSet2Map(ResultSet rs, String keyColumn) throws SQLException {
		Map<String, Map<String, Object>> rsmap = new HashMap<String, Map<String, Object>>();
		try {
			// 封装resultSet数据 ,利用ResultSetMetaData对象可获得表的结构
			ResultSetMetaData rsmd = rs.getMetaData();
			String mapkey = "";
			while (rs.next()) {// 循环表
				// 每行记录放到一个map里！
				Map<String, Object> map = new HashMap<String, Object>();// 每行记录放到一个
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
					if (keyColumn.equals(rsmd.getColumnName(i))) {
						mapkey = StringHandler.nullToEmpty(rs.getObject(i));
					}
				}
				rsmap.put(mapkey, map);
			}
		} catch (SQLException e) {
			logger.error("SQLException", e);
		}
		return rsmap;
	}

	/**
	 * @author Liangjiameng
	 * @param sqlStr
	 *            参数化SQL语句
	 * @param clazz
	 *            对象
	 * @param dataList
	 *            参数列表List（与SQL语句中参数顺序一致）
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List<Object> executeSQL(String sqlStr, Class clazz, List<String> dataList) {
		return this.executeSQLWhitConn(sqlStr, clazz, dataList, getConn());
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException {
		testInsert();
	}

	private static void testInsert() {
		String sql = "insert into xixihaha_0 (quhao,chengshi,diqu) values (?,?,?)";
		List<String> params = new ArrayList<String>(3);
		params.add("11");
		params.add("北京");
		params.add("海淀区");

		DbUtil dbUtils = new DbUtil();
		int lastInsertedId = dbUtils.executeInsert(sql, params);
		System.out.println(lastInsertedId);
	}
}