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

    /**
     * Θέτει τις τέσσερις παραμέτρους
     * @param uid To μοναδίκο id.
     * @param first string
     * @param second string
     * @param third string
     */
    public Quadruple(int uid, String first, String second, String third)
    {
        this.uid = uid;
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     * Επιστρέφει το μοναδίκο id.
     * @return Το μοναδίκο id
     */
    public int getUID()
    {
        return uid;
    }

    /**
     * Επιστρέφει το string first.
     * @return Το string first
     */
    public String getFirst()
    {
        return first;
    }

    /**
     * Επιστρέφει το string second.
     * @return Το string second
     */
    public String getSecond()
    {
        return second;
    }

    /**
     * Επιστρέφει το string third.
     * @return Το string third
     */
    public String getThird()
    {
        return third;
    }
}