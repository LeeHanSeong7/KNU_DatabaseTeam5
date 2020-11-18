package pd.utils;

public enum Result {
    success,
    failure;

    private Void value;
    private Error error;

    public static Result withError(Error e) {
        Result result = Result.failure;
        result.setError(e);
        return result;
    }

    public static Result withValue(Void o) {
        Result result = Result.success;
        result.setValue(o);
        return result;
    }

    private void setError(Error e) {
        this.error = e;
    }
    public void setValue(Void value) {
        this.value = value;
    }

    public Error getError() {
        return error;
    }
    public Void getValue() {
        return value;
    }
}
