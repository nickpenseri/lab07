package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final int JANUARY_DAYS = 31;
    private static final int FEBRUARY_DAYS = 28;
    private static final int MARCH_DAYS = 31;
    private static final int APRIL_DAYS = 30;
    private static final int MAY_DAYS = 31;
    private static final int JUNE_DAYS = 30;
    private static final int JULY_DAYS = 31;
    private static final int AUGUST_DAYS = 31;
    private static final int SEPTEMBER_DAYS = 30;
    private static final int OCTOBER_DAYS = 31;
    private static final int NOVEMBER_DAYS = 30;
    private static final int DECEMBER_DAYS = 31;

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    public static class SortByMonthOrder implements Comparator<String> {

        public int compare(String o1, String o2) {
            return Month.fromString(o1).ordinal() - Month.fromString(o2).ordinal();
        }

    }

    public static class SortByDate implements Comparator<String> {
        public int compare(String o1, String o2) {
            return Month.fromString(o1).daysCount - Month.fromString(o2).daysCount;
        }
    }

    public static enum Month {
        JANUARY(JANUARY_DAYS),
        FEBRUARY(FEBRUARY_DAYS),
        MARCH(MARCH_DAYS),
        APRIL(APRIL_DAYS),
        MAY(MAY_DAYS),
        JUNE(JUNE_DAYS),
        JULY(JULY_DAYS),
        AUGUST(AUGUST_DAYS),
        SEPTEMBER(SEPTEMBER_DAYS),
        OCTOBER(OCTOBER_DAYS),
        NOVEMBER(NOVEMBER_DAYS),
        DECEMBER(DECEMBER_DAYS);

        private final int daysCount;

        private Month(int daysCount) {
            this.daysCount = daysCount;
        }

        static Month fromString(String name) {
            String lowerName = name.toLowerCase();
            if (isAccepted(lowerName, "january", "ja")) {
                return JANUARY;
            } else if (isAccepted(lowerName, "february", "f")) {
                return FEBRUARY;
            } else if (isAccepted(lowerName, "march", "mar")) {
                return MARCH;
            } else if (isAccepted(lowerName, "april", "ap")) {
                return APRIL;
            } else if (isAccepted(lowerName, "may", "may")) {
                return MAY;
            } else if (isAccepted(lowerName, "june", "jun")) {
                return JUNE;
            } else if (isAccepted(lowerName, "july", "jul")) {
                return JULY;
            } else if (isAccepted(lowerName, "august", "au")) {
                return AUGUST;
            } else if (isAccepted(lowerName, "september", "s")) {
                return SEPTEMBER;
            } else if (isAccepted(lowerName, "october", "o")) {
                return OCTOBER;
            } else if (isAccepted(lowerName, "november", "n")) {
                return NOVEMBER;
            } else if (isAccepted(lowerName, "december", "d")) {
                return DECEMBER;
            } else {
                throw new IllegalArgumentException("Ambiguous or non existant month");
            }
        }

        static private boolean isAccepted(String name,
                String completeName,
                String prefix) {
            return completeName.contains(name) && name.startsWith(prefix);
        }
    }
}
