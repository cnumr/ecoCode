package fr.cnumr.java.checks;

import java.util.*;

/**
 * Not compliant
 */
public class AvoidUsageOfStaticCollections {
    public static final List<String> LIST = new ArrayList<String>();
    public static final Set<String> SET = new HashSet<String>();
    public static final Map<String, String> MAP = new HashMap<String, String>();
}
