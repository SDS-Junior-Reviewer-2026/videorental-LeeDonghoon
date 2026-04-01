package com.videorental;

public class RegularMovie extends Movie {
    public RegularMovie( String title) {
        super(title, Movie.REGULAR);
    }

    @Override
    double getChargeFor (int dayRented) {
        double thisAmount = 2;
        if (dayRented > 2) {
            thisAmount += (dayRented - 2) * 1.5;
        }
        return thisAmount;
    }

    @Override
    int getFrequentRenterPointsFor(int daysRented) {
        return 1;
    }
}
