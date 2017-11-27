package wangzhongqiu.spring.core.exception.base;

import wangzhongqiu.spring.core.model.User;

public class InvalidLenderException extends BusinessBaseException {

    private static final long serialVersionUID = 5381818909078435263L;

    public enum Reason {
        NOT_LENDER() {
            @Override
            public String toString() {
                return "is not lender";
            }
        },
        IS_BORROWER() {
            @Override
            public String toString() {
                return "is borrower";
            }
        }
    }

    public InvalidLenderException(User user, Reason reason) {
        super(String.format("Failed to invest since user %d %s", user.getUserId(), reason));
    }

}
