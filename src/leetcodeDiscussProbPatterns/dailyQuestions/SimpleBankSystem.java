package leetcodeDiscussProbPatterns.dailyQuestions;

public class SimpleBankSystem {
    public static void main(String[] args) {

        long[] balance = {10, 100, 20, 50, 30};
        SimpleBankSystem obj = new SimpleBankSystem(balance);

        System.out.println(obj.withdraw(3, 10));
        System.out.println(obj.transfer(5, 1, 20));
        System.out.println(obj.deposit(5, 20));
        System.out.println(obj.transfer(3, 4, 15));
        System.out.println(obj.withdraw(10, 50));
    }

    private long[] balance;
    private int n;

    public SimpleBankSystem(long[] balance) {
        this.balance = balance;
        this.n = balance.length;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 < 1 || account1 > n)
            return false;

        if (account2 < 1 || account2 > n)
            return false;

        if (balance[account1 - 1] < money)
            return false;

        balance[account1 - 1] -= money;
        balance[account2 - 1] += money;

        return true;
    }

    public boolean deposit(int account, long money) {
        if (account < 1 || account > n)
            return false;
        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account < 1 || account > n)
            return false;
        if (balance[account - 1] < money)
            return false;
        balance[account - 1] -= money;
        return true;
    }

    /**
     * Your Bank object will be instantiated and called as such:
     * Bank obj = new Bank(balance);
     * boolean param_1 = obj.transfer(account1,account2,money);
     * boolean param_2 = obj.deposit(account,money);
     * boolean param_3 = obj.withdraw(account,money);
     */
}
