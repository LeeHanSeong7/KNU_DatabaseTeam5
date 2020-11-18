package utils;

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

    private void setError(Error e) {
        this.error = e;
    }

    public Error getError() {
        return error;
    }
    public Void getValue() {
        return value;
    }
}
