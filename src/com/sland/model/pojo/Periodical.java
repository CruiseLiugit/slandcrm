package com.sland.model.pojo;


public class Periodical implements java.io.Serializable,MyEntity {

	// Fields
	private Integer id;
	private String coverpageName;
	private String coverpageThunmbname;
	private String coverPath;
	private String catalogPagename;
	private String catalogThumbname;
	private String cataPath;
	private String column1;
	private String column2;
	private String column3;
	private Integer column4;

	//期刊与杂志 1:n
	private Magazine magazine;
	
	// Constructors

	/** default constructor */
	public Periodical() {
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoverpageName() {
		return this.coverpageName;
	}

	public void setCoverpageName(String coverpageName) {
		this.coverpageName = coverpageName;
	}

	public String getCoverpageThunmbname() {
		return this.coverpageThunmbname;
	}

	public void setCoverpageThunmbname(String coverpageThunmbname) {
		this.coverpageThunmbname = coverpageThunmbname;
	}


	public String getCoverPath() {
		return this.coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}


	public String getCatalogPagename() {
		return this.catalogPagename;
	}

	public void setCatalogPagename(String catalogPagename) {
		this.catalogPagename = catalogPagename;
	}

	public String getCatalogThumbname() {
		return this.catalogThumbname;
	}

	public void setCatalogThumbname(String catalogThumbname) {
		this.catalogThumbname = catalogThumbname;
	}


	public String getCataPath() {
		return this.cataPath;
	}

	public void setCataPath(String cataPath) {
		this.cataPath = cataPath;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public String getColumn1() {
		return this.column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return this.column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return this.column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public Integer getColumn4() {
		return this.column4;
	}

	public void setColumn4(Integer column4) {
		this.column4 = column4;
	}

}