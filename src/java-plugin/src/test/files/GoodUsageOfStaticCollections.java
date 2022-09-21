package fr.cnumr.java.checks;

import java.util.*;

/**
 * Compliant
 */
public class GoodUsageOfStaticCollections {
    public static volatile GoodUsageOfStaticCollections INSTANCE = new GoodUsageOfStaticCollections();

    public final List<String> LIST = new ArrayList<String>();
    public final Set<String> SET = new HashSet<String>();
    public final Map<String, String> MAP = new HashMap<String, String>();

    private GoodUsageOfStaticCollections() {
    }
}
