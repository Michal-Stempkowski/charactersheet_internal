package com.github.michal_stempkowski.charactersheet.internal;

/**
 * Enum used in order to differentiate between different targets (mainly used for separating events' enumeration space).
 */
public enum Target {
    UNDEFINED(-1),
    INTERNAL(0),
    DESKTOP(1);

    private final int id;

    Target(int id) {
        this.id = id;
    }

    public static class Consts {
        public static final int DOMAIN_BITS = 10;
        public static final int EVENT_BITS = 10;
        public static final int DOMAINS_ALLOWED = (int)Math.pow(2, DOMAIN_BITS);
        public static final int EVENTS_IN_DOMAIN_ALLOWED = (int)Math.pow(2, EVENT_BITS);
    }

    public int id() {
        return id;
    }
}
