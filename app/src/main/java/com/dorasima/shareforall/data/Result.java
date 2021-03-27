package com.dorasima.shareforall.data;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result<T> {
    // 서브 클래스 타입을 제한(?)하기 위해 private 생성자
    private Result() {
    }

    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        }
        else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
    }

    // 서브 클래스인 내포 클래스
    public final static class Success<T> extends Result {
        // 성공하면 LoggedInUser 개체
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // 서브 클래스인 내포 클래스
    public final static class Error extends Result {
        // 실패하면 Exception 개체
        private Exception error;

        public Error(Exception error) {
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }
    }
}