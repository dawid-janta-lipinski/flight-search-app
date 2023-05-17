import java.util.ArrayList;

class Main {
  public static void main(String[] args) {
    FlightDatabase database = new FlightDatabase();
    /*
    // START - checking if the flight exists
     
    boolean yesOrNo = database.checkIfFlightExists("Los Angeles", "Shanghai");
    // END - checking if the flight exists

    
    // START - getting flights from a city
    ArrayList<Flight> fromCity = database.getFlightsFromCity("Paris");
    database.displayFlights(fromCity);
    // END - getting flights from a city

    // START - getting flights to a city
    ArrayList<Flight> toCity = database.getFlightsToCity("Bangkok");
    database.displayFlights(toCity);
    // END - getting flights to a city

    // START - getting a list of all cities in the database
    ArrayList<String> cities = database.getCities();
    System.out.println(cities);
    // END - getting a list of all cities in the database

    // START - getting cheapest flight
    Flight theCheapest = database.getCheapestFlight();
    System.out.println("this is the cheapest flight:");
    theCheapest.info();
    // END - getting  cheapest flight

    // START - getting cheapest flight from city
    Flight theCheapestFromCity = database.getCheapestFlightFromCity("Paris");
    System.out.println("this is the cheapest flight from this city:");
    theCheapestFromCity.info();
    // END - getting  cheapest flight from city
    */

    // START - connecting flight
    ArrayList<Journey> connectingFlight = database.getFlights("Warsaw", "Bangkok");
    if(connectingFlight.isEmpty()){
      System.out.println("This flight doesn't exist!");
    }
    else{
      for(Journey j:connectingFlight){
      j.info();
      }
    }
    // END - connecting flight
  }
}

class Flight {
  String departure;
  String arrival;
  double price;

  public Flight(String departure, String arrival, double price) {
    this.departure = departure;
    this.arrival = arrival;
    this.price = price;
  }

  public void info() {
    System.out.println("Flight from " + this.departure + " to " + this.arrival + " costs " + this.price);
  }
}

class FlightDatabase {
  ArrayList<Flight> flights = new ArrayList<Flight>();

  // Helper method to generate a random city
    public String generateRandomCity() {
        String[] cities = {"Amsterdam", "Paris", "Rome", "Tokyo", "Sydney", "New York", "London", "Berlin", "Bangkok", "Moscow", "Las Vegas", "Barcelona", "Dubai", "Cairo", "Warsaw"};
        int randomIndex = (int) (Math.random() * cities.length);
        return cities[randomIndex];
    }

    // Helper method to generate a random price between 1000 and 5000
    public double generateRandomPrice() {
        return 1000 + ((int) (Math.random() * (500000 - 100000))/100);
    }

  public FlightDatabase() {

    for (int i = 0; i < 50; i++) {
      // Generate random cities and prices
      String origin = generateRandomCity();
      String destination = generateRandomCity();
      double price = generateRandomPrice();
      flights.add(new Flight(origin, destination, price));
    }
  }
    
  public boolean checkIfFlightExists(String departure, String arrival){
    
    boolean flightExistance = false;
    
    for(Flight f:this.flights){
      
      if(!departure.equals(f.departure) && !arrival.equals(f.arrival)){
        flightExistance = false;
      }
      else{
        flightExistance = true;
        System.out.println("This flight exists!");
        break;
      }
    }
    if(flightExistance = false){
      System.out.println("This flight doesn't exist!");
    }
    return flightExistance;
  }
  public ArrayList<Flight> getFlightsFromCity(String city){
    
    ArrayList<Flight> arrivalsFromCity = new ArrayList<Flight>();
    
    for (Flight f:this.flights){
      if (city.equals(f.departure)){
       arrivalsFromCity.add(f); 
      }
    }
    return arrivalsFromCity;
  }
  
  public ArrayList<Flight> getFlightsToCity(String city){
    
    ArrayList<Flight> departuresFromCity = new ArrayList<Flight>();
    
    for (Flight f:this.flights){
      if (city.equals(f.arrival)){
       departuresFromCity.add(f); 
      }
    }
    return departuresFromCity;
  }
  public void displayFlights(ArrayList<Flight> results){
    if (results.isEmpty()){
      System.out.println("Flight not found!");
    }
    else{
      for(Flight f:results){
        f.info();
      }
    }
  }
  public ArrayList<String> getCities(){
    ArrayList<String> Cities = new ArrayList<String>();
    for (Flight f:this.flights){
      if(!Cities.contains(f.departure)){
        Cities.add(f.departure);
      }
      else if(!Cities.contains(f.arrival)){
        Cities.add(f.arrival);
      }
    }
    return Cities;
  }
  public Flight getCheapestFlight(){
    Flight cheapestFlight = null;
    for (Flight f:this.flights){
      if(cheapestFlight == null || f.price<cheapestFlight.price){
        cheapestFlight = f;
      }
    }
    return cheapestFlight;
  }
  public Flight getCheapestFlightFromCity(String city){

    ArrayList<Flight> flightsFromCity = getFlightsFromCity(city);
    Flight cheapestFlight = null;
    for (Flight f:flightsFromCity){
      if(cheapestFlight == null || f.price<cheapestFlight.price){
        cheapestFlight = f;
      }
    }
    return cheapestFlight;
  }
  public ArrayList<Journey> getFlights(String start, String end){
    
    ArrayList<Flight> starting = getFlightsFromCity(start);
    ArrayList<Flight> ending = getFlightsToCity(end);
    ArrayList<Journey> results = new ArrayList<Journey>();
    for (Flight first:starting){
      for (Flight second:ending){
        if (first.arrival.equals(second.departure)){
          results.add(new Journey(first,second));
        }
      }
    }
    return results;
  }
}
class Journey {
  Flight firstFlight;
  Flight secondFlight;

  public Journey (Flight firstFlight, Flight secondFlight){
    this.firstFlight = firstFlight;
    this.secondFlight = secondFlight;
  }
  public void info() {
    System.out.println("Flight from " + firstFlight.departure + " to " + firstFlight.arrival + " costs " + firstFlight.price);
    System.out.println("Flight from " + secondFlight.departure + " to " + secondFlight.arrival + " costs " + secondFlight.price);
        System.out.println("The whole journey will cost you " + (firstFlight.price + secondFlight.price));
  }
}
