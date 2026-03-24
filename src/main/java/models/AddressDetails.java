package models;

public class AddressDetails {
	
	private String actualName;
	private String actualCompany;
	private String actualAddress1;
	private String actualAddress2;
	private String actualCityStateZip;
	private String actualCountry;
	private String actualMobile;
	
	
	public AddressDetails(String actualName, String actualCompany, String actualAddress1, String actualAddress2,String actualCityStateZip,String actualCountry,  String actualMobile )
	{
		this.actualName = actualName;
		this.actualCompany = actualCompany;
		this.actualAddress1 = actualAddress1;
		this.actualAddress2 = actualAddress2;
		this.actualCityStateZip= actualCityStateZip;
		this.actualCountry = actualCountry;
		this.actualMobile = actualMobile;
		
	}
	public String getActualName() {
		return actualName;
	}
	public String getActualCompany() {
		return actualCompany;
	}
	public String getActualAddress1() {
		return actualAddress1;
	}
	public String getActualAddress2() {
		return actualAddress2;
	}
	public String getActualCityStateZip() {
		return actualCityStateZip;
	}
	public String getActualCountry() {
		return actualCountry;
	}
	public String getActualMobile() {
		return actualMobile;
	}
	
}
