package financemanager;
public enum TransactionType {

    EXPENDITURE {
        public float applyTo(float value) {
            return -Math.abs(value);
        }
        public String returnString() {
            return "Expenditure";
        }
    }, DEPOSIT {
        public float applyTo(float value) {
            return Math.abs(value);
        }
        public String returnString() {
            return "Deposit";
        }
    };

    public static TransactionType fromString(String s){
        switch(s.trim().toUpperCase()) {
            case "EXPENDITURE":
                return EXPENDITURE;
            case "DEPOSIT":
                return DEPOSIT;
            default:
                throw new IllegalArgumentException();
        }
    }

    public abstract float applyTo(float value);
    public abstract String returnString();
}
