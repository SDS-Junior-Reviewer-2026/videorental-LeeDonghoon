package com.videorental;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    public static final String NAME = "NAME_NOT_IMPORTANT";
    public static final String TITLE = "TITLE_NOT_IMPORTANT";
    Customer customer = new Customer(NAME);


    @Test
    void sample() {
        customer.addRental(new Rental(
                new Movie("title", Movie.REGULAR),
                10));
        customer.addRental(new Rental(
                new Movie("헤일메리", Movie.NEW_RELEASE),
                55));

        String receipt = customer.statement();
        System.out.println(receipt);
    };
    
    @Test
    void returnNewCustomer() {
        assertNotNull(customer.getName());
    }

    @Test
    void statementForNoRental() {
        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter pointers", customer.statement());
    }

    @Test
    void statementForRegularMovieRentalForLessThan3Days() {
        // 준비
        customer.addRental(createRentalFor(2, Movie.REGULAR));

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t2.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    void statementForRegularMovieRentalForMoreThan2Days() {
        // 준비
        customer.addRental(createRentalFor(3, Movie.REGULAR));

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t3.5(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    void setPriceCodeFromRegularToNewRelease() {
        // 준비
        Movie movie = new Movie(TITLE, Movie.REGULAR);
        movie.setPriceCode(Movie.NEW_RELEASE);
        customer.addRental(new Rental(movie, 3));

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t9.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter pointers", customer.statement());
    }

    @Test
    void statementForNewReleaseMovie() {
        // 준비
        customer.addRental(createRentalFor(1, Movie.NEW_RELEASE));

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t3.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    void statementForChildrensMovieRentalMoreThan3Days() {
        // 준비
        customer.addRental(createRentalFor(4, Movie.CHILDRENS));

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t3.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    void statementForChildrensMovieRentalLessThan4Days() {
        // 준비
        customer.addRental(createRentalFor(3, Movie.CHILDRENS));

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t1.5(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter pointers", customer.statement());
    }

    @Test
    void statementForNewReleaseMovieRentalMoreThan1Day() {
        // 준비
        customer.addRental(createRentalFor(2, Movie.NEW_RELEASE));

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t6.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter pointers", customer.statement());
    }

    @Test
    void statementForFewMovieRental() {
        // 준비
        customer.addRental(createRentalFor(1, Movie.REGULAR));
        customer.addRental(createRentalFor(4, Movie.NEW_RELEASE));
        customer.addRental(createRentalFor(4, Movie.CHILDRENS));

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t2.0(TITLE_NOT_IMPORTANT)\n" +
                "\t12.0(TITLE_NOT_IMPORTANT)\n" +
                "\t3.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 17.0\n" +
                "You earned 4 frequent renter pointers", customer.statement());

    }

    private static Rental createRentalFor(int daysRented, int priceCode) {
        Movie movie = new Movie(TITLE, priceCode);
        return new Rental(movie, daysRented);
    }
}
