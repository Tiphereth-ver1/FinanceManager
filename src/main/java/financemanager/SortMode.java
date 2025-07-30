package financemanager;
public enum SortMode {
    DATE_ASC, DATE_DESC, VALUE_ASC, VALUE_DESC;

    public static SortMode fromString(String s){
        switch(s.trim()) {
            case "Date (Ascending)":
                return DATE_ASC;
            case "Date (Descending)":
                return DATE_DESC;
            case "Value (Ascending)":
                return VALUE_ASC;
            case "Value (Descending)":
                return VALUE_DESC;

            default:
                throw new IllegalArgumentException();
        }
    }

}
