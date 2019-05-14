package accessPoint;

import accessPoint.DataAccess;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path(value="/api")
public class AccessPoint {
    String dbUser = "beerUser";
    String dbPass = "drinkUp";
    String dburl = "jdbc:mysql://localhost/beer_data?autoReconnect=true&useSSL=false";

    @GET
    @Path(value="/beer")
    @Produces(value={"appilcation/json"})
    public String getBeer(@QueryParam(value="id") int id) {
        return DataAccess.getBeerById(this.dburl, this.dbUser, this.dbPass, id);
    }

    @GET
    @Path(value="/textSearch")
    @Produces(value={"appilcation/json"})
    public String textSearch(@QueryParam(value="target") String target) {
        return DataAccess.textSearch(this.dburl, this.dbUser, this.dbPass, target);
    }

    @GET
    @Path(value="/category")
    @Produces(value={"appilcation/json"})
    public String categorySearch(@QueryParam(value="category") String category) {
        return DataAccess.getByCategory(this.dburl, this.dbUser, this.dbPass, category);
    }

    @GET
    @Path(value="/featured")
    @Produces(value={"appilcation/json"})
    public String getFeatured() {
        return DataAccess.getFeatured(this.dburl, this.dbUser, this.dbPass);
    }
    
    @GET
    @Path(value="/light")
    @Produces(value={"appilcation/json"})
    public String getlight() {
        return DataAccess.getLight(this.dburl, this.dbUser, this.dbPass);
    }

    @GET
    @Path(value="/dark")
    @Produces(value={"appilcation/json"})
    public String getDark() {
        return DataAccess.getDark(this.dburl, this.dbUser, this.dbPass);
    }
    
    @GET
    @Path(value="/attribueSearch")
    @Produces(value={"appilcation/json"})
    public String attribueSearch(@QueryParam(value="abv") String abvIN, @QueryParam(value="ibu") String ibuIN, @QueryParam(value="brewery") String brewery, @QueryParam(value="type") String type) {
        Double abv = null;
        Integer ibu = null;
        try {
            abv = Double.parseDouble(abvIN);
        }
        catch (NumberFormatException e) {
            abv = null;
        }
        try {
            ibu = Integer.parseInt(ibuIN);
        }
        catch (NumberFormatException e) {
            ibu = null;
        }
        return DataAccess.attributeSearch(this.dburl, this.dbUser, this.dbPass, abv, ibu, brewery, type);
    }
}
