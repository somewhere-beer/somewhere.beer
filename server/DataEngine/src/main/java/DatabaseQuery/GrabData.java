package DatabaseQuery;

import org.json.simple.JSONObject;

import java.util.Scanner;

public class GrabData {

    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        String dbUser = "beerUser";
        String dbpass = "drinkUp";
        String dburl = "jdbc:mysql://localhost/beer_data?autoReconnect=true&useSSL=false";
        boolean done = false;
        JSONObject returnValue = null;

        while (!done){
            System.out.println("give action");
            String action = userInput.nextLine();

            switch (action){
                case "quit":
                    done = true;
                    break;
                case "beerId":
                    int id = Integer.parseInt(userInput.nextLine());
                    returnValue = DataAccess.getBeerById(dburl,dbUser,dbpass,id);
                    break;
                case "textSearch":
                    String target = userInput.nextLine();
                    returnValue = DataAccess.textSearch(dburl,dbUser,dbpass,target);
                    break;

                case"featured":
                    returnValue = DataAccess.getFeatured(dburl,dbUser,dbpass);
                    break;
                case "category":
                    String category = userInput.nextLine();
                    returnValue = DataAccess.getByCategory(dburl,dbUser,dbpass,category);
                    break;
                case "attribute":
                    Double abv = null;
                    Integer ibu = null;
                    try {
                        System.out.println("Pleae give abv");
                        abv = Double.parseDouble( userInput.nextLine());

                    } catch (NumberFormatException e){
                        abv = null;
                    }
                    try {
                        System.out.println("Please git ibu");
                        ibu = Integer.parseInt( userInput.nextLine());
                    } catch (NumberFormatException e){
                        ibu = null;
                    }

                    System.out.println("Please give brewery");
                    String brewery = userInput.nextLine();
                    System.out.println("Please give the type");
                    String type = userInput.nextLine();
                    returnValue = DataAccess.attributeSearch(dburl,dbUser,dbpass,abv,ibu,brewery,type);
                    break;
            }
            if(!done){
                System.out.println(returnValue);
            }
        }

    }


}//GrabData
