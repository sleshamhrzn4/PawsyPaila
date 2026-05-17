package com.pawsypaila.utils;
import com.pawsypaila.utils.SessionUtil; 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {

    /**
     * Adds an attribute and sets a custom session timeout.
     * @param seconds Time before session expires due to inactivity.
     */
	// set session attribute
    public static void setAttribute(HttpServletRequest request, String fullName, Object value, int seconds) {
        HttpSession session = request.getSession(true);
        session.setAttribute(fullName, value);
        session.setMaxInactiveInterval(seconds);
    }
    
    // read session attribute
    public static Object getAttribute(HttpServletRequest request, String fullName) {
        HttpSession session = request.getSession(false);
        return (session != null) ? session.getAttribute(fullName) : null;
    }

    // remove session specific attribute
    public static void removeAttribute(HttpServletRequest request, String fullName) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(fullName);
        }
    }

    // remove all session at all
    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
