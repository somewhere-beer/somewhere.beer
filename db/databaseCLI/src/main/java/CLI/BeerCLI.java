package CLI;

import java.io.*;
import java.sql.*;
import java.util.*;

public class BeerCLI {

    //This is static to have only one keyboard reader
    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        String dbuser;
        String dbpass;
        String breaweryCSV;
        String beerCSV;

        boolean done = false;

        System.out.println("The Somewhere CLI is up and running");



            System.out.println("Please give the database application login username\n:");

            dbuser = userInput.nextLine();

            System.out.println("Please give database application login password");

            dbpass = userInput.nextLine();

            while (!done){
                System.out.println("Pleas give action to take");
                String loopControl  = userInput.nextLine();

                switch (loopControl){
                    case "fillBrew":

                        System.out.println("Please give the file location of the brewery.csv file");
                        breaweryCSV = userInput.nextLine();
                        fillBrewery(breaweryCSV,"jdbc:mysql://localhost/beer_data", dbuser,dbpass);
                        break;

                    case "fillBeer":
                        System.out.println("Please give beer.csv location");
                        beerCSV = userInput.next();
                         System.out.println("Filling beer table");
                        fillBeer(beerCSV, "jdbc:mysql://localhost/beer_data", dbuser, dbpass);
                        break;

                    case "fillStyle":
                        System.out.println("Please give style.csv location");
                        beerCSV = userInput.next();
                        System.out.println("Filling style table");
                        fillStyle(beerCSV, "jdbc:mysql://localhost/beer_data", dbuser, dbpass);
                        break;

                    case "dellNoBeerBrew":
                        System.out.println("Removing breweries without a beer");
                        dellNoMatch("jdbc:mysql://localhost/beer_data", dbuser, dbpass);
                        break;

                    case "addReview":
                        addReview("jdbc:mysql://localhost/beer_data", dbuser, dbpass);
                        break;

                    case "addDescription":
                        addDescription("jdbc:mysql://localhost/beer_data", dbuser, dbpass);
                        break;

                    case "quit":
                        done = true;
                }

            }


    }

    private static void fillStyle(String stylecsv, String databaseURL ,String user, String password){

        try (FileReader fileReader = new FileReader(
                new File(stylecsv))
        ){

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(databaseURL,  user, password);

            Statement sqlStatement = conn.createStatement();
            Scanner fileScan = new Scanner(fileReader);

            while (fileScan.hasNext()) {
                String line = fileScan.nextLine();
                Scanner lineScaner = new Scanner(line);
                lineScaner.useDelimiter(",");
                String beerStyle = lineScaner.next();
                sqlStatement.execute("INSERT INTO styles(style_id, style_name) VALUES (DEFAULT, \'"+ beerStyle + " \')");
            }

        } catch (FileNotFoundException e){
            e.getStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    private static void addDescription(String databaseURL ,String user, String password){
        int beerId;
        String description;

        try {
            System.out.println("Please the id of the beer");
            beerId = Integer.parseInt(userInput.nextLine());

            System.out.println("Please give the description");
            description = userInput.nextLine();

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(databaseURL,user,password);

            Statement sqlStatement = conn.createStatement();

            String descriptionQ = "INSERT INTO discriptions(beer_id, description) VALUES ("+beerId+", '"+ description +"')";

            sqlStatement.execute(descriptionQ);

        } catch (NumberFormatException e){
            System.out.println("Beer id given was not a integer");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }
    }

    private static void addReview(String databaseURL ,String user, String password){
        int beerId;
        String staffMember;
        String review;
        try {
            System.out.println("Please give the id of the reviewed beer");
            beerId = Integer.parseInt(userInput.nextLine());

            System.out.println("Please give staff member name");
            staffMember = userInput.nextLine();

            System.out.println("Please give review");
            review = userInput.nextLine();

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(databaseURL, user, password);

            Statement sqlStatement = conn.createStatement();

            String reviewQ = "INSERT INTO staff_reviews (beer_id, staff_member, review)\n" +
                    "VALUES (" + beerId +", '"+ staffMember + "', '"+ review+ "' )";

            sqlStatement.execute(reviewQ);

        } catch (NumberFormatException e){
            System.out.println("id given was not an integer");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            System.out.println("database error");
            e.printStackTrace();
        }



    }

    private static void dellNoMatch(String databaseURL ,String user, String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(databaseURL, user, password);

            Statement sqlStatement = conn.createStatement();

            String dellNoMatchQ = "DELETE FROM brewery WHERE brewery.brewery_id NOT IN (SELECT beers.brewery_id FROM beers)";

            sqlStatement.execute(dellNoMatchQ);

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            System.out.println("Database error");
            e.printStackTrace();
        }
    }

    private static void fillBeer(String filePath, String databaseURL, String user, String password) {


        List<Beer> beers = new LinkedList<>();

        try (FileReader fileReader = new FileReader(
                new File(filePath)
        )) {
            Scanner fileScan = new Scanner(fileReader);


            while (fileScan.hasNext()) {
                String line = fileScan.nextLine();
                line.replaceAll("\"", "");
                Scanner lineScaner = new Scanner(line);
                lineScaner.useDelimiter(",");


                String beerName = lineScaner.next();
                String beerStyle = lineScaner.next();
                String abv = lineScaner.next();
                String ibu = lineScaner.next();
                String brewery = lineScaner.next();

                if (!ibu.isEmpty() && !abv.isEmpty()) {
                    beers.add(new Beer(beerName, beerStyle, brewery, Double.valueOf(abv), Integer.valueOf(ibu)));
                } else if (!abv.isEmpty()) {
                    beers.add(new Beer(beerName, beerStyle, brewery, Double.valueOf(abv)));
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("CVS file not found");
        } catch (NoSuchElementException e) {
            System.out.println("Missing field");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Number format error");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Inserting into the database
        System.out.println("\n\nGet a cup of coffee this is going to take awhile\n\n");


        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(databaseURL, user, password);

            Statement sqlStatement = conn.createStatement();

            for (int i = 0; i < beers.size() - 1; i++) {

                //Getting the brewery id value
                String getBreweryId = "SELECT brewery_id FROM brewery WHERE brewery_name = " + "'" + beers.get(i).getBrewery() + "'";
                ResultSet breweryIdQ = sqlStatement.executeQuery(getBreweryId);

                String breweryID = null;
                if (breweryIdQ.next()) {
                    breweryID = breweryIdQ.getString(1);
                }

                //Getting the style id value
                String getStyleId = "SELECT style_id FROM styles WHERE style_name = " + "'" + beers.get(i).getStyle() + "'";
                ResultSet styleIdQ = sqlStatement.executeQuery(getStyleId);

                String styleID = null;
                if (styleIdQ.next()){
                    styleID = styleIdQ.getString(1);
                }
                String create = "INSERT INTO `beers`(`beer_id`, `brewery_id`, `beer_name`, `style_id`, `beer_abv`, `is_featured`, `beer_ibu`) VALUES ( " + beers.get(i).sqlValues(breweryID, styleID) + ")";
                sqlStatement.execute(create);
            }

        } catch (SQLException e) {
            System.out.println("Database error");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unforeseen type of error");
            e.printStackTrace();
        }

    }

    private static void fillBrewery(String filePath, String databaseURL, String user, String password) {
        List<Brewery> breweries = new ArrayList<>();

        try (FileReader fileReader = new FileReader(
                new File(filePath)
        )
        ){
            Scanner fileScan = new Scanner(fileReader);

            while (fileScan.hasNext()){
                //Taking off the trailing comma
                String line = fileScan.nextLine().replace(',' , ' ');
                String brewery =line;

                breweries.add(new Brewery(brewery));
            }

        } catch (FileNotFoundException e){
            System.out.println("File was not found");
        } catch (NoSuchElementException e){
            System.out.println("Missing field");
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        //Inserting into the database
        System.out.println("\n\nGet a cup of coffee this is going to take awhile\n\n");

        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(databaseURL,  user, password);

            Statement sqlStatement = conn.createStatement();

            for (int i = 0; i <  breweries.size() -1; i++) {
                //The ignore flag causes the repeats to be silently skipped.
                // This is needed because otherwise the unique constraint would trigger an sql error
                String createBrewery = "INSERT IGNORE INTO brewery SET brewery_id = NULL, brewery_name = " + breweries.get(i).getBrewery_name() ;
                sqlStatement.execute(createBrewery);
            }

        } catch (SQLException e){
            System.out.println("eee");
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch ( Exception e){
            e.printStackTrace();
        }

    }

}//BeerCLI
