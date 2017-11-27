/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wangzhongqiu.spring.core.exception;


public class BidAmountOverLoadException extends ServiceException {

    private static final long serialVersionUID = 7183929992383982863L;

    public BidAmountOverLoadException(double bidAmount, double restAmount, int loanid, String user) {
        super(user + " Bid amount <" + bidAmount + "> to " + loanid + " should be less than <" + restAmount + ">");
    }
    
    public BidAmountOverLoadException(String msg) {
        super(msg);
    }
}
