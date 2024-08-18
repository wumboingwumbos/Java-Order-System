import java.util.ArrayList;
public class Customer {
    public String name;
    public String addr;
    public String credCard;
    public String iD;
    public String pass;
    public int securityQ;
    public String ans;
    ArrayList<Order> myOrders= new ArrayList<>();
    /**
     * Customer constructor
     * @param name
     * @param addr
     * @param credCard
     * @param iD
     * @param pass
     * @param securityQ
     * @param ans
     */
    public Customer(String name, String addr, String credCard, String iD, String pass, int securityQ, String ans) {
        this.name = name;
        this.addr = addr;
        this.credCard = credCard;
        this.iD = iD;
        this.pass = pass;
        this.securityQ = securityQ;
        this.ans = ans;
    }
    /**
     * gets name
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * gets address
     * @return
     */
    public String getAddr() {
        return addr;
    }
    /**
     * gets credit card
     * @return
     */
    public String getCredCard() {
        return credCard;
    }
    /**
     * gets ID
     * @return
     */
    public String getiD() {
        return iD;
    }
    /**
     * gets password
     * @return
     */
    public String getPass() {
        return pass;
    }
    /**
     * get security question
     * @return
     */
    public int getSecurityQ() {
        return securityQ;
    }
    /**
     * get answer
     * @return
     */
    public String getAns() {
        return ans;
    }
    /**
     * sets credit card data
     * @param credCard
     */
    public void setCredCard(String credCard) {
        this.credCard = credCard;
    }
    /**
     * sets password
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
    /**
     * sets security question
     * @param securityQ
     */
    public void setSecurityQ(int securityQ) {
        this.securityQ = securityQ;
    }
    /**
     * sets answer
     * @param ans
     */
    public void setAns(String ans) {
        this.ans = ans;
    }
    @Override
    public String toString() {
        return "Customer [name=" + name + ", addr=" + addr + ", credCard=" + credCard + ", iD=" + iD + ", pass=" + pass
                + ", securityQ=" + securityQ + ", ans=" + ans + "]";
    }
    
    
}
