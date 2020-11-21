package pd.utils;

public enum Result {
    success,
    failure;

    private Object value;
    private Error error;

    public static Result withError(Error e) {
        Result result = Result.failure;
        result.setError(e);
        return result;
    }

    public static Result withValue(Object o) {
        Result result = Result.success;
        result.setValue(o);
        return result;
    }

    private void setError(Error e) {
        this.error = e;
    }
    public void setValue(Object value) {
        this.value = value;
    }

    public Error getError() {
        return error;
    }
    public Object getValue() {
        return value;
    }
}
