package DatabaseQuery;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {

    public static JSONObject attributeSearch(String databaseURL, String dbUser, String dbPass, Double abv, Integer ibu, String brewery, String type ){
        JSONObject results = new JSONObject();
        JSONObject attributes = new JSONObject();
        JSONArray beers = new JSONArray();
        StringBuilder atrribQ = new StringBuilder("SELECT * FROM `beers` WHERE `beer_abv` LIKE '%");
        String abvQ, typeQ;
        List<Integer> breweryIds = null;



        if (abv != null){
            if (abv % 1 == 0){
                abvQ = abv.intValue() + ".%";
            } else {
                abvQ = Double.toString(abv);
            }
        } else {
            abvQ = "%";
        }


        if (!type.isEmpty()){
            typeQ = type;
        } else {
            typeQ = "%";
        }

        if(!brewery.isEmpty()){
            breweryIds = findBreweryIds(databaseURL, dbUser,dbPass,brewery);
        } else {
        }

        atrribQ.append(abvQ+"' AND beer_style LIKE '%"+typeQ+"'");



        if (ibu != null){
            atrribQ.append("AND beer_ibu = " + ibu);
        }

        try ( Connection conn = DriverManager.getConnection(databaseURL,dbUser,dbPass)){

            Class.forName("com.mysql.jdbc.Driver");

            Statement sqlStatement = conn.createStatement();

            ResultSet resultSet;

            if (breweryIds != null){
                String d = "";
                for (Integer id: breweryIds
                ) {
                    d = " AND brewery_id = " + id;
                }

                resultSet = sqlStatement.executeQuery(atrribQ.toString() + d);

                while (resultSet.next()){
                    beers.add(rowToJson(rowArray(resultSet), databaseURL,dbUser,dbPass));
                }
            }


        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        //Building the output json
        results.put("attriSearchedFor", attributes);
        results.put("numberFound", beers.size());
        results.put("beers", beers);

        return results;
    }

    public static JSONObject getByCategory(String databaseURL, String dbUser, String dbPass, String category){
        JSONObject result = new JSONObject();
        JSONArray beersFound = new JSONArray();
        String categoryQ = "SELECT * FROM Beers";
        String cat;

        switch (category){
            case "ale":
                cat = "Ale";
                categoryQ = "SELECT * FROM beers WHERE LOWER(beer_style) " +
                        "LIKE('%ind%pale%ale%') OR LOWER(beer_style) " +
                        "LIKE('%ipa%') OR LOWER(beer_style) LIKE ('%double%i%')" +
                        " OR LOWER(beer_style) LIKE ('%amer%pale%ale%') OR " +
                        "LOWER(beer_style) LIKE('%eng%pale%ale%')  OR LOWER(beer_style) LIKE('%stout%')";
                break;
            case "paleale":
                cat = "Pale Ale";
                categoryQ = "SELECT * FROM beers WHERE LOWER(beer_style) " +
                        "LIKE('%ind%pale%ale%') OR LOWER(beer_style) " +
                        "LIKE('%ipa%') OR LOWER(beer_style) LIKE ('%double%i%')" +
                        " OR LOWER(beer_style) LIKE ('%amer%pale%ale%') OR " +
                        "LOWER(beer_style) LIKE('%eng%pale%ale%')";
                break;
            case "ipa":
                cat = "IPA";
                categoryQ = "SELECT * FROM beers WHERE LOWER(beer_style) LIKE('%ind%pale%ale%') OR LOWER(beer_style) LIKE('%ipa%')";
                break;
            case "double":
                cat = "Double IPA";
                categoryQ = "SELECT * FROM beers WHERE LOWER(beer_style) LIKE('%double%i%')";
                break;
            case "amerpaleale":
                cat = "American Pale Ale";
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE('%amer%ind%pale%ale%')";
                break;
            case "engpaleale":
                cat = "English Pale Ale";
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE('%eng%ind%pale%ale%')";
                break;
            case "porter":
                cat = "Porter";
                System.out.println(cat);
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE('%porter%')";
                break;
            case "sour":
                cat ="Sour";
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE('%sour%')";
                break;
            case "brown":
                cat = "Brown";
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE('%brown%a%')";
                break;
            case "wheat":
                cat = "Wheat";
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE('%wheat%')";
                break;
            case "golden":
                cat = "Blond / Golden";
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE('%golden%')";
                break;
            case "lager":
                cat = "Lager";
                categoryQ = "SELECT * FROM beers WHERE LOWER(beer_style) LIKE '%pilse%' OR LOWER(beer_style) LIKE '%amer%lager%'";
            case "palelager":
                cat = "Pale Lager";
                categoryQ = "SELECT * FROM beers WHERE LOWER(beer_style) LIKE '%pilse%' OR LOWER(beer_style) LIKE '%amer%lager%'";
                break;
            case "pilsner":
                cat = "Pilsner";
                categoryQ = "SELECT * FROM beers WHERE LOWER(beer_style) LIKE '%pils%'";
                break;
            case "amerpilsner":
                cat = "American Pilsner";
                categoryQ = "SELECT * FROM beers WHERE LOWER(beer_style) LIKE '%amer%pils%'";
                break;
            case "amerlager":
                cat = "American Lager";
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE '%amer%lager%'";
                break;
            case "amber":
                cat = "Amber";
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE '%amber%'";
            case "ameramber":
                cat = "American Amber";
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE '%amer%amber%'";
                break;
            case "cider":
                cat = "Cider";
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE '%cider%'";
                break;
            case "bwine":
                cat = "BarleyWine";
                categoryQ ="SELECT * FROM beers WHERE LOWER(beer_style) LIKE '%barle%wine%'";
                break;
            case "amerbwine":
                cat = "American BarleyWine";
                categoryQ = "SELECT * FROM beers WHERE LOWER(beer_style) LIKE '%amer%barle%wine%'";
                break;
            case "britbwine":
                cat = "British BarleyWine";
                categoryQ = "SELECT * FROM beers WHERE LOWER(beer_style) LIKE '%bri%barle%wine%'";
                break;
            default:
                return null;
        }

        try (Connection conn = DriverManager.getConnection(databaseURL,dbUser,dbPass)) {
            Class.forName("com.mysql.jdbc.Driver");


            Statement sqlStatement = conn.createStatement();
            ResultSet categorySet = sqlStatement.executeQuery(categoryQ);

            //Added the beers found list
            while (categorySet.next()){
                beersFound.add(rowToJson(rowArray(categorySet),databaseURL,dbUser,dbPass));
            }

            //Building the output json
            result.put("category", cat);
            result.put("numberFound", beersFound.size());
            result.put("beers",beersFound);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }



        return result;
    }

    public static JSONObject getFeatured(String databaseURL, String dbUser, String dbPass){
        JSONObject result = null;

        try (Connection conn = DriverManager.getConnection(databaseURL,dbUser,dbPass)){
            Class.forName("com.mysql.jdbc.Driver");

            Statement sqlStatement = conn.createStatement();
            String featuredQ = "SELECT * FROM beers WHERE is_featured = 1";
            ResultSet featuredSet = sqlStatement.executeQuery(featuredQ);

            while (featuredSet.next()){
                result = rowToJson(rowArray(featuredSet),databaseURL,dbUser,dbPass);
            }

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }


    public static JSONObject textSearch(String databaseURL, String dbUser, String dbPass, String target){
        JSONObject result = new JSONObject();
        JSONArray beersFound = new JSONArray();


        try (Connection conn = DriverManager.getConnection(databaseURL,dbUser,dbPass)){
            Class.forName("com.mysql.jdbc.Driver");

            Statement sqlStatement = conn.createStatement();

            //Searching based on description
            String descripQ = "SELECT `beer_id` FROM `discriptions` WHERE LOWER(`description`) LIKE LOWER('%"+target+"%')";
            ResultSet resultSet = sqlStatement.executeQuery(descripQ);

            while (resultSet.next()){
                beersFound.add(getBeerById(databaseURL,dbUser,dbPass,resultSet.getInt(1)));
            }

            //Searching based on staff reviews
            String reviewQ ="SELECT beer_id FROM staff_reviews WHERE LOWER(staff_member) LIKE LOWER('%" + target +"%') ||  LOWER(review) LIKE LOWER('%"+ target +"%')";
            resultSet = sqlStatement.executeQuery(reviewQ);

            while (resultSet.next()){
                beersFound.add(getBeerById(databaseURL,dbUser,dbPass,resultSet.getInt(1)));
            }


            //Searching based on beer name and beer style
            String beerQ = "SELECT beer_id, brewery_id, beer_name, beer_style, beer_abv, is_featured, beer_ibu " +
                    "FROM beers WHERE LOWER(beer_name) LIKE LOWER('%" + target + "%') || LOWER(beer_style) LIKE LOWER('%" + target + "%')";
            resultSet = sqlStatement.executeQuery(beerQ);


            while (resultSet.next()){
                beersFound.add(rowToJson(rowArray(resultSet), databaseURL, dbUser, dbPass));
            }

            //Brewery ids that the name matched the search
            List<Integer> brewerIds = findBreweryIds(databaseURL, dbUser, dbPass, target);

            //Getting the beers that have a brewery id in the match list
            for (Integer id: brewerIds
            ) {
                String byBreweryIdQ = "SELECT beer_id, brewery_id, beer_name, beer_style, beer_abv, is_featured, beer_ibu FROM beers WHERE brewery_id = " + id;
                resultSet = sqlStatement.executeQuery(byBreweryIdQ);

                while (resultSet.next()){
                    beersFound.add(rowToJson(rowArray(resultSet), databaseURL, dbUser, dbPass));
                }

            }
            //Building the return json
            result.put("searchedFor", target);
            result.put("numberFound", beersFound.size());
            result.put("beers", beersFound);

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }


        return result;
    }

    public static JSONObject getBeerById(String databaseURL, String dbUser, String dbPass, int beerId){

        JSONObject result = null;

        try (Connection conn = DriverManager.getConnection(databaseURL,dbUser,dbPass)){
            Class.forName("com.mysql.jdbc.Driver");

            Statement sqlStatement = conn.createStatement();
            String getBeerQ = "SELECT * FROM beers WHERE beer_id = " + beerId;
            ResultSet idSet = sqlStatement.executeQuery(getBeerQ);

            if (idSet.next()){
                result = rowToJson(rowArray(idSet), databaseURL, dbUser, dbPass);
            }

        } catch (ClassNotFoundException e ){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    //Turns the beer rows found into a string array for processing into json
    private static String[] rowArray(ResultSet resultSet){
        String[] beerValues = new String[7];

        try {

            beerValues[0] = resultSet.getString(1);
            beerValues[1] = resultSet.getString(2);
            beerValues[2] = resultSet.getString(3);
            beerValues[3] = resultSet.getString(4);
            beerValues[4] = resultSet.getString(5);
            beerValues[5] = resultSet.getString(6);
            beerValues[6] = resultSet.getString(7);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return beerValues;
    }

    private static List<Integer> findBreweryIds(String databaseURL, String dbUser, String dbPass, String brewery){
        List<Integer> brewerIds = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(databaseURL,dbUser,dbPass)){
            Class.forName("com.mysql.jdbc.Driver");

            //Searching based on brewery name
            Statement breweryStament = conn.createStatement();
            String breweryQ = "SELECT brewery_id FROM brewery WHERE LOWER(brewery_name) LIKE LOWER('%"+brewery+"%')";
            ResultSet brewerySet = breweryStament.executeQuery(breweryQ);

            while (brewerySet.next()){
                brewerIds.add(brewerySet.getInt(1));
            }

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return brewerIds;
    }

    //Turns the string array and related queries into output json objects
    private static JSONObject rowToJson(String[] rowValues, String databaseURL, String dbUser, String dbPass){

        //Method variables
        JSONObject result = null;
        JSONArray pictures = new JSONArray();
        JSONArray reviews = new JSONArray();

        try (Connection conn = DriverManager.getConnection(databaseURL,dbUser,dbPass)){
            Class.forName("com.mysql.jdbc.Driver");

            //Get the brewery name
            Statement breweryStatement = conn.createStatement();
            String breweryNameQ = "SELECT brewery_name FROM brewery WHERE brewery_id = " + rowValues[1];
            ResultSet breweryNameSet = breweryStatement.executeQuery(breweryNameQ);

            //Get the description
            Statement descripStetement = conn.createStatement();
            String descripQ = "SELECT description FROM discriptions WHERE beer_id = " + rowValues[0];
            ResultSet descripSet = descripStetement.executeQuery(descripQ);

            //Get picture file locations
            Statement pictureStatement = conn.createStatement();
            String pictureQ = "SELECT local_disk_location FROM pictures WHERE beer_id = " + rowValues[0];
            ResultSet pictuesSet = pictureStatement.executeQuery(pictureQ);

            //Fill the images json array
            while (pictuesSet.next()){
                pictures.add(pictuesSet.getString(1));
            }

            //Get the staff reviews
            Statement reviewsStatement = conn.createStatement();
            String reviewsQ = "SELECT staff_member, review FROM staff_reviews WHERE beer_id =" + rowValues[0];
            ResultSet reviewSet = reviewsStatement.executeQuery(reviewsQ);

            //Fill the staffReviews json array
            while (reviewSet.next()){
                JSONObject r = new JSONObject();
                r.put("staffMember", reviewSet.getString(1));
                r.put("review", reviewSet.getString(2));
                reviews.add(r);
            }

            //Creating the output
            result = new JSONObject();
            result.put("beerId", Integer.parseInt(rowValues[0]));
            result.put("beerName", rowValues[2].replaceAll("“|”|\"",""));
            result.put("beerStyle", rowValues[3]);
            result.put("brewery", (breweryNameSet.next()) ? breweryNameSet.getString(1).replaceAll("“|”|\"",""): null);
            result.put("ABV", Double.parseDouble(rowValues[4]));
            result.put("IBU", (rowValues[6] != null) ? Integer.parseInt(rowValues[6]) : null);
            result.put("featured",(rowValues[5].equals("1")) ? true : false);
            result.put("images", (!pictures.isEmpty()) ? pictures : null);
            result.put("description", (descripSet.next()) ? descripSet.getString(1) : null);
            result.put("staffReviews", (!reviews.isEmpty()) ? reviews : null);

        } catch (ClassNotFoundException e ){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;

    }//rowToJson

}//DataAccess
