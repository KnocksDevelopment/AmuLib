package me.knocks.generic.registers.impl.generic;

import me.knocks.generic.registers.interfaces.IAccount;
import me.knocks.generic.registers.interfaces.IRegister;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic implementation of a register for objects.
 */
public class GenericRegister<ObjectType> implements IRegister<ObjectType> {
    protected Map<String, IAccount<ObjectType>> registeredAccounts = new HashMap<String, IAccount<ObjectType>>();

    @Override
    public void createAccount(String name) {
        if (registeredAccounts.get(name) == null)
            registeredAccounts.put(name, new GenericAccount<ObjectType>());
    }

    @Override
    public IAccount<ObjectType> getAccount(String name) {
        createAccount(name);
        return registeredAccounts.get(name);
    }

    @Override
    public IAccount<ObjectType>[] getAllAccounts() {
        Collection<IAccount<ObjectType>> accounts = registeredAccounts.values();
        return accounts.toArray((IAccount<ObjectType>[])Array.newInstance(GenericAccount.class, accounts.size()));
    }
}
