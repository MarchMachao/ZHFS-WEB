package com.smates.dbc2.po;

/**
 * 标签数据实体类
 * 
 * @author 刘晓庆
 *
 */
public class TagInformation {
	private String tagNum;
	private String name;
	private Integer age;
	private String sex;
	private String maritalStatus;
	private String useFull;
	private String image;
	private String health;

	public TagInformation() {
	}

	public TagInformation(String tagNum, String name, Integer age, String sex, String maritalStatus, String useFull,
			String health) {
		this.tagNum = tagNum;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.maritalStatus = maritalStatus;
		this.useFull = useFull;
		this.health = health;
		this.image = "000.png";
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

}
