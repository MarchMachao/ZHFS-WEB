package com.smates.dbc2.po;
/**
 * 标签数据实体类
 * 
 * @author wrz
 *
 */
public class TagInformation {
	private String tagNum;
	private String name;
	private Integer age;
	private String sex;
	private String maritalStatus;
	private String useFull;
	
	public TagInformation() {
	}
	
	public TagInformation(String tagNum, String name, Integer age, String sex, String maritalStatus, String useFull) {
		this.tagNum = tagNum;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.maritalStatus = maritalStatus;
		this.useFull = useFull;
	}
	public String getTagNum() {
		return tagNum;
	}

	public void setTagNum(String tagNum) {
		this.tagNum = tagNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getUseFull() {
		return useFull;
	}

	public void setUseFull(String useFull) {
		this.useFull = useFull;
	}

	
	


	

	

	
}
