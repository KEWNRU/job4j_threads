package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        accounts.put(account.id(), account);
        return accounts.containsKey(account.id());
    }

    public synchronized boolean update(Account account) {
        accounts.replace(account.id(), account);
        return accounts.containsKey(account.id());
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        validationTransfer(fromId, toId, amount);
        update(new Account(fromId, getById(fromId).get().amount() - amount));
        update(new Account(toId, getById(toId).get().amount() + amount));
        return true;
    }

    public  void validationTransfer(int fromId, int toId, int amount) {
        if (getById(fromId).isEmpty() || getById(toId).isEmpty()) {
            throw new IllegalArgumentException("Пользователь не найден");
        }
        if (getById(fromId).get().amount() < amount) {
            throw new IllegalArgumentException("На вашем счете недостаточно средств");
        }
    }
}