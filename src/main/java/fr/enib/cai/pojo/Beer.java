package fr.enib.cai.pojo;

// Beer POJO
public class Beer {

	private String name; 
	private String brewery;
	private String country;
	private double alcohol;
	private int    id; 
	
	public Beer(String name, String brewery, String country, double alcohol) {
		super();
		this.name = name;
		this.brewery = brewery;
		this.country = country;
		this.alcohol = alcohol;
	}

	public int getId() {
		return id;
	}
	public void setId( int id) {
		this.id= id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrewery() {
		return brewery;
	}
	public void setBrewery(String brewery) {
		this.brewery = brewery;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}
	
	public boolean equals(Object otherBeer){		
	   return (this.name.toUpperCase().trim().equals( ((Beer)otherBeer).getName().toUpperCase().trim()) && 
			   this.brewery.toUpperCase().trim().equals( ((Beer)otherBeer).getBrewery().toUpperCase().trim()) &&
			   this.country.toUpperCase().trim().equals( ((Beer)otherBeer).getCountry().toUpperCase().trim()) &&
			   (this.alcohol == ((Beer)otherBeer).getAlcohol())  );
	}
}
