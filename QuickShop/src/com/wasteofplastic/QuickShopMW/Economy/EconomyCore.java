package com.wasteofplastic.QuickShopMW.Economy;

/**
 * @author netherfoam
 * Represents an economy.
 */
public interface EconomyCore{
	/**
	 * Checks that this economy is valid. Returns false if it is not valid.
	 * @return True if this economy will work, false if it will not.
	 */
	public boolean isValid();
	/**
	 * Deposits a given amount of money from thin air to the given username.
	 * @param name The exact (case insensitive) username to give money to
	 * @param amount The amount to give them
	 * @return True if success (Should be almost always)
	 */
	public boolean deposit(String name, double amount);
	public boolean deposit(String name, double amount, String world);
	/**
	 * Withdraws a given amount of money from the given username and turns it to thin air.
	 * @param name The exact (case insensitive) username to take money from
	 * @param amount The amount to take from them
	 * @return True if success, false if they didn't have enough cash
	 */
	public boolean withdraw(String name, double amount);
	public boolean withdraw(String name, double amount, String world);
	/**
	 * Transfers the given amount of money from Player1 to Player2
	 * @param from The player who is paying money
	 * @param to The player who is receiving money
	 * @param amount The amount to transfer
	 * @return true if success (Payer had enough cash, receiver was able to receive the funds)
	 */
	public boolean transfer(String from, String to, double amount);
	public boolean transfer(String from, String to, double amount, String world);
	/**
	 * Fetches the balance of the given account name
	 * @param name The name of the account
	 * @return Their current balance.
	 */
	public double getBalance(String name);
	/**
	 * Fetches the balance of the given account name
	 * @param name The name of the account
	 * @param world The name of the world where the balance is
	 * @return Their current balance.
	 */
	public double getBalance(String name, String world);
	/**
	 * Formats the given number... E.g. 50.5 becomes $50.5 Dollars, or 50 Dollars 5 Cents
	 * @param balance The given number
	 * @return The balance in human readable text.
	 */
	public String format(double balance);
}
