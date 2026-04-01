package com.videorental;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    @Test
    void sample() {
        Customer customer = new Customer("Bob");

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
        Customer customer = new Customer("NAME_NOT_IMPORTANT");

        assertNotNull(customer.getName());
    }

    @Test
    void statementForNoRental() {
        // 준비
        Customer customer = new Customer("NAME_NOT_IMPORTANT");

        // 동작
        String statement = customer.statement();

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter pointers", statement);
    }

    @Test
    void statementForRegularMovieRentalForLessThan3Days() {
        // 준비
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
        int dayRented = 2;
        Rental rental = new Rental(movie, dayRented);
        customer.addRental(rental);

        // 동작
        String statement = customer.statement();

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t2.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter pointers", statement);
    }

    @Test
    void statementForRegularMovieRentalForMoreThan2Days() {
        // 준비
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
        int dayRented = 3;
        Rental rental = new Rental(movie, dayRented);
        customer.addRental(rental);

        // 동작
        String statement = customer.statement();

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t3.5(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter pointers", statement);
    }

    @Test
    void statementForNewReleaseMovie() {
        // 준비
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
        int dayRented = 1;
        Rental rental = new Rental(movie, dayRented);
        customer.addRental(rental);

        // 동작
        String statement = customer.statement();

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t3.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers", statement);
    }

    @Test
    void statementForChildrensMovieRentalMoreThan3Days() {
        // 준비
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
        int dayRented = 4;
        Rental rental = new Rental(movie, dayRented);
        customer.addRental(rental);

        // 동작
        String statement = customer.statement();

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t3.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers", statement);
    }

    @Test
    void statementForChildrensMovieRentalLessThan4Days() {
        // 준비
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
        int dayRented = 3;
        Rental rental = new Rental(movie, dayRented);
        customer.addRental(rental);

        // 동작
        String statement = customer.statement();

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t1.5(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter pointers", statement);
    }

    @Test
    void statementForNewReleaseMovieRentalMoreThan1Day() {
        // 준비
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
        int dayRented = 2;
        Rental rental = new Rental(movie, dayRented);
        customer.addRental(rental);

        // 동작
        String statement = customer.statement();

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t6.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter pointers", statement);
    }

    @Test
    void statementForFewMovieRental() {
        // 준비
        Customer customer = new Customer("NAME_NOT_IMPORTANT");
        Movie regularMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
        Movie newReleaseMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
        Movie childrensMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
        customer.addRental(new Rental(regularMovie, 1));
        customer.addRental(new Rental(newReleaseMovie, 4));
        customer.addRental(new Rental(childrensMovie, 4));

        // 동작
        String statement = customer.statement();

        // 검증
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t2.0(TITLE_NOT_IMPORTANT)\n" +
                "\t12.0(TITLE_NOT_IMPORTANT)\n" +
                "\t3.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 17.0\n" +
                "You earned 4 frequent renter pointers", statement);

    }
}
