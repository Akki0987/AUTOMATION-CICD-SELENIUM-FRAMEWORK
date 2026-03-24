package pojo;

public class UserData {


	    // User details
	    private String name;
	    private String email;
	    private String password;
	    private String fName;
	    private String lName;
	    private String company;
	    private String address;
	    private String address2;
	    private String city;
	    private String state;
	    private String zipcode;
	    private String country;
	    private String mobNo;

	    // Card details
	    private String nameOnCard;
	    private String cardNumber;
	    private String cvc;
	    private String expMonth;
	    private String expYear;

	    // Constructor
	    public UserData(String name, String email, String password,
	                    String fName, String lName, String company,
	                    String address, String address2, String city,
	                    String state, String zipcode, String country,
	                    String mobNo, String nameOnCard, String cardNumber,
	                    String cvc, String expMonth, String expYear) {
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.fName = fName;
	        this.lName = lName;
	        this.company = company;
	        this.address = address;
	        this.address2 = address2;
	        this.city = city;
	        this.state = state;
	        this.zipcode = zipcode;
	        this.country = country;
	        this.mobNo = mobNo;
	        this.nameOnCard = nameOnCard;
	        this.cardNumber = cardNumber;
	        this.cvc = cvc;
	        this.expMonth = expMonth;
	        this.expYear = expYear;
	    }

	    // Getters (add setters if needed)
	    public String getName() { return name; }
	    public String getEmail() { return email; }
	    public String getPassword() { return password; }
	    public String getfName() { return fName; }
	    public String getlName() { return lName; }
	    public String getCompany() { return company; }
	    public String getAddress() { return address; }
	    public String getAddress2() { return address2; }
	    public String getCity() { return city; }
	    public String getState() { return state; }
	    public String getZipcode() { return zipcode; }
	    public String getCountry() { return country; }
	    public String getMobNo() { return mobNo; }
	    public String getNameOnCard() { return nameOnCard; }
	    public String getCardNumber() { return cardNumber; }
	    public String getCvc() { return cvc; }
	    public String getExpMonth() { return expMonth; }
	    public String getExpYear() { return expYear; }
	}

