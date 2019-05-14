package accessPoint;

import org.json.simple.*;

import com.mysql.jdbc.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.lang.annotation.Retention;
import java.sql.*;
import java.sql.Driver;
import java.util.*;

public class DataAccess {


 public static String attributeSearch(String databaseURL, String dbUser, String dbPass, Double abv, Integer ibu, String brewery, String type ){
        JSONObject results = new JSONObject();
        JSONObject attributes = new JSONObject();
        JSONArray beers = new JSONArray();
        String atrribQ = null;
        StringBuilder caseControll = new StringBuilder();
        if (abv != null){
        	caseControll.append("a");
        	attributes.put("abv",abv);
        }
        
        if (ibu != null){
        	caseControll.append("i");
        	attributes.put("ibu",ibu);
        }

       if(!brewery.isEmpty()){
    	  caseControll.append("b");
    	  attributes.put("brewery",brewery);
        } 

        if (!type.isEmpty()){
        	caseControll.append("t");
        	attributes.put("type", type);
        }
        
        switch (caseControll.toString()) {
        	case "a":
        	    atrribQ = "CALL get_by_abv(" + abv +")";
        		break;
            case "i":
                atrribQ = "CALL get_by_ibu(" + ibu + ")";
                break;
            case "b":
                atrribQ = "CALL get_by_brewery( \'" + brewery + "\')";
                break;
            case "t":
                atrribQ = "CALL get_by_type(\'"+ type +"\')";
                break;
            case "ai":
                atrribQ = "CALL get_by_abv_ibu("+ abv + ", "+ ibu+")";
                break;
            case "at":
                atrribQ = "CALL get_by_abv_type(" + abv + ", \'"+type+"\')";
                break;
            case "ab":
                atrribQ = "CALL get_by_abv_brewery(" + abv + ", \'"+ brewery +"\')";
                break;
            case "ib":
                atrribQ = "CALL get_by_ibu_brewery(" + ibu + ", \'"+ brewery +"\')";
                break;
            case "it":
                atrribQ = "CALL get_by_ibu_type(" + ibu + ", \'"+ type + "\')";
                break;
            case "bt":
                atrribQ = "CALL get_by_brewery_type(\'" + brewery + "\', \'" + type + "\')";
                break;
            case "aib":
                atrribQ = "CALL get_by_abv_ibu_brewery(" + abv + ", " + ibu + ", \'" + brewery + "\')";
                break;
            case "abt":
                atrribQ = "CALL get_by_abv_brewery_type(" + abv + ", \'" + brewery + "\', \'" + type +"\')";
                break;
            case "ibt":
                atrribQ = "CALL get_by_abv_brewery_type(" + ibu + ", \'" + brewery + "\', \'" + type +"\')";
                break;
            case "aibt":
                atrribQ = "CALL get_by_abv_ibu_brewery_type("+ abv + ", "+ ibu + ", \'" + brewery + "\', \'" + type +"\')";
                break;
        }


        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(databaseURL,dbUser,dbPass);
            Statement sqlStatement = (Statement) conn.createStatement();
            ResultSet resultSet;
            resultSet = sqlStatement.executeQuery(atrribQ);

            while (resultSet.next()){
                beers.add(rowToJson(rowArray(resultSet), databaseURL,dbUser,dbPass));

            }
            conn.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        //Building the output json
        results.put("attriSearchedFor", attributes);
        results.put("numberFound", beers.size());
        results.put("beers", beers);

        return results.toString();
   }

    public static String getDark(String databaseURL, String dbUser, String dbPass){
        JSONObject result = new JSONObject();
        JSONArray beers = new JSONArray();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(databaseURL,dbUser,dbPass);
            Statement sqlStatement = (Statement) conn.createStatement();
            ResultSet resultSet = sqlStatement.executeQuery("CALL get_dark()");
            while (resultSet.next()){
                beers.add((rowToJson(rowArray(resultSet), databaseURL,dbUser,dbPass)));
            }

            result.put("darkBeers", beers);

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result.toString();
    }
    
    public static String getLight(String databaseURL, String dbUser, String dbPass){
        JSONObject result = new JSONObject();
        JSONArray beers = new JSONArray();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(databaseURL,dbUser,dbPass);
            Statement sqlStatement = (Statement) conn.createStatement();
            ResultSet resultSet = sqlStatement.executeQuery("CALL get_light()");
            while (resultSet.next()){
                beers.add((rowToJson(rowArray(resultSet), databaseURL,dbUser,dbPass)));
            }

            result.put("lightBeers", beers);

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result.toString();
    }


    public static String getByCategory(String databaseURL, String dbUser, String dbPass, String category){
        JSONObject result = new JSONObject();
        JSONArray beersFound = new JSONArray();
        String categoryQ = null;
        String cat;

        switch (category){
            case "ale":
                cat = "Ale";
                categoryQ = "CALL get_ale()";
                break;
            case "paleale":
                cat = "Pale Ale";
                categoryQ = "CALL get_pale_ale()";
                break;
            case "ipa":
                cat = "IPA";
                categoryQ = "CALL get_ipa();";
                break;
            case "double":
                cat = "Double IPA";
                categoryQ = "CALL get_double_ipa()";
                break;
            case "amerpaleale":
                cat = "American Pale Ale";
                categoryQ ="CALL get_amer_pale_ale()";
                break;
            case "engpaleale":
                cat = "English Pale Ale";
                categoryQ ="CALL get_eng_pale_ale()";
                break;
            case "porter":
                cat = "Porter";
                System.out.println(cat);
                categoryQ ="CALL get_porter()";
                break;
            case "sour":
                cat ="Sour";
                categoryQ ="CALL get_sour()";
                break;
            case "brown":
                cat = "Brown";
                categoryQ ="CALL get_brown()";
                break;
            case "wheat":
                cat = "Wheat";
                categoryQ ="CALL get_wheat()";
                break;
            case "golden":
                cat = "Blond / Golden";
                categoryQ ="CALL get_golden()";
                break;
            case "lager":
                cat = "Lager";
                categoryQ = "CALL get_lager()";
            case "palelager":
                cat = "Pale Lager";
                categoryQ = "CALL get_lager()";
                break;
            case "pilsner":
                cat = "Pilsner";
                categoryQ = "CALL get_pilsner()";
                break;
            case "amerpilsner":
                cat = "American Pilsner";
                categoryQ = "CALL get_amer_pilsner()";
                break;
            case "amerlager":
                cat = "American Lager";
                categoryQ ="CALL get_amer_lager()";
                break;
            case "amber":
                cat = "Amber";
                categoryQ ="CALL get_amber()";
            case "ameramber":
                cat = "American Amber";
                categoryQ ="CALL get_amer_amber()";
                break;
            case "cider":
                cat = "Cider";
                categoryQ ="CALL get_cider()";
                break;
            case "bwine":
                cat = "BarleyWine";
                categoryQ ="CALL get_bwine()";
                break;
            case "amerbwine":
                cat = "American BarleyWine";
                categoryQ = "CALL get_amer_bwine()";
                break;
            case "britbwine":
                cat = "British BarleyWine";
                categoryQ = "CALL get_brit_bwine()";
                break;
            default:
                cat = "All Beers";
                categoryQ = "SELECT * FROM beer_summary";
                
        }

        try (Connection conn = (Connection) DriverManager.getConnection(databaseURL,dbUser,dbPass)) {
            Class.forName("com.mysql.jdbc.Driver");


            Statement sqlStatement = (Statement) conn.createStatement();
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



        return result.toString();
    }

    public static String getFeatured(String databaseURL, String dbUser, String dbPass){
        JSONObject result = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(databaseURL,dbUser,dbPass);

            Statement sqlStatement = (Statement) conn.createStatement();
            String featuredQ = "CALL get_featured()";
            ResultSet featuredSet = sqlStatement.executeQuery(featuredQ);

            while (featuredSet.next()){
                result = rowToJson(rowArray(featuredSet),databaseURL,dbUser,dbPass);
            }

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result.toString();
    }


    public static String textSearch(String databaseURL, String dbUser, String dbPass, String target){
        JSONObject result = new JSONObject();
        JSONArray beersFound = new JSONArray();
      

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(databaseURL,dbUser,dbPass);
            
            Statement sqlStatement = (Statement) conn.createStatement();

            String searchQ = "CALL text_search('"+target+"')";
         
            ResultSet resultSet = sqlStatement.executeQuery(searchQ);
            

            while (resultSet.next()){
                beersFound.add(rowToJson(rowArray(resultSet), databaseURL, dbUser, dbPass));
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


        return result.toString();
    }

    public static String getBeerById(String databaseURL, String dbUser, String dbPass, int beerId){

        JSONObject result = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = (Connection) DriverManager.getConnection(databaseURL,dbUser,dbPass);
        

            Statement sqlStatement = (Statement) conn.createStatement();
            String getBeerQ = "CALL get_by_beer_id(" + beerId + ")";
            
            ResultSet idSet = sqlStatement.executeQuery(getBeerQ);

            if (idSet.next()){
                result = rowToJson(rowArray(idSet), databaseURL, dbUser, dbPass);
            }
            
            

        } catch (ClassNotFoundException e ){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result.toString();
    }

    //Turns the beer rows found into a string array for processing into json
    private static String[] rowArray(ResultSet resultSet){
        String[] beerValues = new String[8];

        try {

            beerValues[0] = resultSet.getString(1);
            beerValues[1] = resultSet.getString(2);
            beerValues[2] = resultSet.getString(3);
            beerValues[3] = resultSet.getString(4);
            beerValues[4] = resultSet.getString(5);
            beerValues[5] = resultSet.getString(6);
            beerValues[6] = resultSet.getString(7);
            beerValues[7] = resultSet.getString(9);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return beerValues;
    }

 

    //Turns the string array and related queries into output json objects
    @SuppressWarnings("unchecked")
	private static JSONObject rowToJson(String[] rowValues, String databaseURL, String dbUser, String dbPass){

        //Method variables
        JSONObject result = null;
        JSONArray pictures = new JSONArray();
        JSONArray reviews = new JSONArray();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection(databaseURL,dbUser,dbPass);

            //Get picture file locations
            Statement pictureStatement = (Statement) conn.createStatement();
            String pictureQ = "CALL  get_pictures(" + rowValues[0] +")";
            ResultSet pictuesSet = pictureStatement.executeQuery(pictureQ);

            //Fill the images json array
            while (pictuesSet.next()){
                pictures.add(pictuesSet.getString(1));
            }

            //Get the staff reviews
            Statement reviewsStatement = (Statement) conn.createStatement();
            String reviewsQ = "CALL get_reviews(" + rowValues[0] +")";
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
            result.put("beerName", rowValues[2]);
            result.put("beerStyle", rowValues[3]);
            result.put("brewery", rowValues[1]);
            result.put("ABV", Double.parseDouble(rowValues[4]));
            result.put("IBU", (rowValues[5] != null) ? Integer.parseInt(rowValues[5]) : null);
            result.put("featured",(rowValues[6].equals("1")) ? true : false);
            result.put("images", (!pictures.isEmpty()) ? pictures : null);
            result.put("description", rowValues[7]);
            result.put("staffReviews", (!reviews.isEmpty()) ? reviews : null);
            
            conn.close();

        } catch (ClassNotFoundException e ){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;

    }//rowToJson

}//DataAccess
