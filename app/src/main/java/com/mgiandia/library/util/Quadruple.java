package com.mgiandia.library.util;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class Quadruple
{
    private int uid;
    private String first, second, third;

    public Quadruple(int uid, String first, String second, String third)
    {
        this.uid = uid;
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getUID()
    {
        return uid;
    }

    public String getFirst()
    {
        return first;
    }

    public String getSecond()
    {
        return second;
    }

    public String getThird()
    {
        return third;
    }
}