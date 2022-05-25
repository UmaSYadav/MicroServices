package com.uma.movies.dtos;

public class CatalogItem {

	private String pName;
	private String pDesc;
	private int iRatings;
	
	public CatalogItem() {
		super();
	}
	public CatalogItem(String pName, String pDesc, int iRatings) {
		super();
		this.pName = pName;
		this.pDesc = pDesc;
		this.iRatings = iRatings;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpDesc() {
		return pDesc;
	}
	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}
	public int getiRatings() {
		return iRatings;
	}
	public void setiRatings(int iRatings) {
		this.iRatings = iRatings;
	}
}
