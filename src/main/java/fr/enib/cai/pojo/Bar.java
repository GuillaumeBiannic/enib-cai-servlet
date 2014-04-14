
package fr.enib.cai.pojo;

public class Bar {

  private Beer beer;
  private int stock;

  public Bar(Beer beer, Integer stock) {
    this.beer = beer;
    this.stock = stock;
  }

  public void setBeer(Beer beer) {
    this.beer = beer;
  }

  public Beer getBeer() {
    return beer;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getStock() {
    return stock;
  }

}
