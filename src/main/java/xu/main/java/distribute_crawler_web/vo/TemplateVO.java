package xu.main.java.distribute_crawler_web.vo;

import java.util.Date;

public class TemplateVO {

	private int id;
	private String template_name;
	private String template_area;
	private String template_test_url;
	private String template_describe;
	private Long template_update_number;
	private Date template_create_time;
	private Date template_update_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTemplate_name() {
		return template_name;
	}

	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	public String getTemplate_area() {
		return template_area;
	}

	public void setTemplate_area(String template_area) {
		this.template_area = template_area;
	}

	public String getTemplate_test_url() {
		return template_test_url;
	}

	public void setTemplate_test_url(String template_test_url) {
		this.template_test_url = template_test_url;
	}

	public String getTemplate_describe() {
		return template_describe;
	}

	public void setTemplate_describe(String template_describe) {
		this.template_describe = template_describe;
	}

	public Long getTemplate_update_number() {
		return template_update_number;
	}

	public void setTemplate_update_number(Long template_update_number) {
		this.template_update_number = template_update_number;
	}

	public Date getTemplate_create_time() {
		return template_create_time;
	}

	public void setTemplate_create_time(Date template_create_time) {
		this.template_create_time = template_create_time;
	}

	public Date getTemplate_update_time() {
		return template_update_time;
	}

	public void setTemplate_update_time(Date template_update_time) {
		this.template_update_time = template_update_time;
	}

}
