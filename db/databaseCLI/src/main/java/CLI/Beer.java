package CLI;

public class Beer {

    //Instance data
    private String name, style, brewery;
    private double abv;
    private Integer ibu;
    private boolean featured;

    //Constructors
    public Beer(String name, String style, String brewery, double abv, Integer ibu){
        this.name = name;
        this.style = style;
        this.brewery = brewery;
        this.abv = abv;
        this.ibu = ibu;
        featured = false;
    }

    //Getters and Setters
    public Beer(String name, String style, String brewery,  double abv){
        this(name,style, brewery,abv, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public Integer getIbu() {
        return ibu;
    }

    public void setIbu(Integer ibu) {
        this.ibu = ibu;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", abv=" + abv +
                ", ibu=" + ibu +
                ", featured=" + featured +
                '}';
    }


    public String sqlValues(String breweryID, String style){
        StringBuilder outString = new StringBuilder("NULL, ");
        outString.append(breweryID + ", ");
        outString.append("'" + name + "', ");
        outString.append("'" + style +"', ");
        outString.append(abv + ", ");
        if(featured){
            outString.append(1 + ", ");
        } else {
            outString.append(0 + ", ");
        }
        if (ibu == null){
            outString.append("NULL");
        } else {
            outString.append(ibu);
        }

        return outString.toString();
    }
}//Beer
