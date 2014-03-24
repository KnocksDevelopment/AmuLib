package me.knocks.generic.registers.interfaces;

public interface IRegister<ObjectType> {
    void createAccount(String name);
    IAccount<ObjectType> getAccount(String name);
    IAccount<ObjectType>[] getAllAccounts();
}
