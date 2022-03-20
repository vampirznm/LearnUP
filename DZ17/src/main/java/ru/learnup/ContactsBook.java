package ru.learnup;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ContactsBook {
    private final HashMap<String, Contact> contacts = new HashMap<>();

    public void removeByPhone(String phone) {
        if (contacts.containsKey(phone)) {
            contacts.remove(phone);
            System.out.println("Contact number " + phone + " deleted.");
        } else throw new RuntimeException("We have no contact with such a number!");
    }

    public ContactsBook add(Contact contact) {
        contacts.put(contact.getPhoneNumber(), contact);
        return this;
    }

    public Contact getByPhone(String phone) {
        return contacts.get(phone);
    }

    public Collection<Contact> sortingByName() {
        List<Contact> list = new ArrayList<>(contacts.values());
        Collections.sort(list);
        return list;
    }

    public List<Contact> searchBy(Predicate<Contact> predicate) {
        return contacts.values().stream().filter(predicate).sorted().collect(Collectors.toList());
    }

    public List<Contact> searchBy(String str) {
        int partTheName = str.indexOf("*");
        if (partTheName == -1) return searchBy(c -> c.getName().contains(str));
        return searchBy(task -> task.getName().startsWith(str.substring(0, partTheName)) && task.getName().endsWith((partTheName < str.length()) ? str.substring(partTheName + 1) : ""));
    }

    public Set<Integer> getCountries() {
        return contacts.values().stream().map(contact -> Integer.valueOf(contact.getPhoneNumber().substring(0, contact.getPhoneNumber().indexOf("-")))).collect(Collectors.toSet());
    }
}