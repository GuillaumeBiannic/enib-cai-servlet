
package fr.enib.cai.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import fr.enib.cai.pojo.Bar;
import fr.enib.cai.pojo.Beer;

public class BarBusiness {

  private static ConcurrentMap<Integer, Beer> myFavoriteBeers = new ConcurrentHashMap<Integer, Beer>();

  private static ConcurrentMap<Integer, Integer> myBar = new ConcurrentHashMap<Integer, Integer>();

  private static AtomicInteger idSequence = new AtomicInteger(1);

  public BarBusiness() {
    // Default beer
    Beer beer = new Beer("Tripel Karmeliet", "Bosteels", "Belgique", 8.4);
    addBeer(beer);
  }

  public Collection<Beer> getBeers() {
    return myFavoriteBeers.values();
  }

  public Collection<Bar> getBar() {
    Collection<Bar> bar = new ArrayList<Bar>();

    for (Entry<Integer, Integer> entry : myBar.entrySet()) {
      Integer beerId    = entry.getKey();
      Integer stock = entry.getValue();
      
      bar.add(new Bar( myFavoriteBeers.get(beerId) , stock));
    }
    return bar;
  }

  public Beer addBeer(final Beer beer) {
    if (!myFavoriteBeers.containsValue(beer)) {
      int beerId = idSequence.incrementAndGet();
      beer.setId(beerId);
      myBar.put(beerId, 0);
      myFavoriteBeers.put(beerId, beer);
      return beer;
    }
    return null;
  }

  public Bar checkinBeer(int id, int number) {
    Integer currentStock = myBar.get(id);
    currentStock = currentStock + number;
    myBar.put(id, currentStock);
    
    return new  Bar( myFavoriteBeers.get(id) , currentStock);
  }

  public Bar checkoutBeer(int id, int number) {
    Integer currentStock = myBar.get(id);
    if (number > currentStock) {
      currentStock = 0;
    } else {
      currentStock = currentStock - number;
    }
    myBar.put(id, currentStock);
    return new  Bar( myFavoriteBeers.get(id) , currentStock);
  }

  public Beer removeBeer(int id) {
    myBar.remove(id);
    return myFavoriteBeers.remove(id);
  }

}
