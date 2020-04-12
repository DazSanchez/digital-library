/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package me.hsanchez.digital_library.utils;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class Utils {
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
