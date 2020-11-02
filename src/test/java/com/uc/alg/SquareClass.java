package com.uc.alg;

public class SquareClass {

  public double square(double x) {


    double low = 0;
    double high = x;
    if(x<1) {
      low=x;
      high=1;
    }
    double middle = x/2;


      while(true) {
      middle = low + (high-middle)/2;
      System.out.println(middle);
      double a = middle*middle - x;
      if (Math.abs(a)<=0.000001) {
        return middle;
      } else if(a>0.000001) {
        high=middle;
      } else{
        low=middle;
      }
    }
  }

  public static void main(String[] args) {
    SquareClass square = new SquareClass();
    double d = square.square(5);
    System.out.println(d);

    double d1 = square.square(0.09);
    System.out.println(d1);
  }

}
