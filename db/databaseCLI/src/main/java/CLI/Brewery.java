package CLI;

public class Brewery {

    //Instance data
    private int brewery_id;
    private String brewery_name;

    //Constructor
    public Brewery(String brewery_name){
        this.brewery_name = brewery_name;
    }

    public int getBrewery_id() {
        return brewery_id;
    }

    public void setBrewery_id(int brewery_id) {
        this.brewery_id = brewery_id;
    }

    public String getBrewery_name() {
        return "'" + brewery_name + "'";
    }

    public void setBrewery_name(String brewery_name) {
        this.brewery_name = brewery_name;
    }

    @Override
    public String toString() {
        return "Brewery{" +
                "brewery_name='" + brewery_name + '\'' +
                '}';
    }
}//Brewery
