package ru.learnup;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactsBook contactsBook = new ContactsBook()
                .add(new Contact("Vasily Petrovich", "+7-111-1111-111"))
                .add(new Contact("Kalyan", "+2-222-2222-222"))
                .add(new Contact("Olga Vassilena", "+43-333-3333-333"))
                .add(new Contact("Ex", "+7-444-4444-444"))
                .add(new Contact("Stepfather", "+43-555-5555-555"))
                .add(new Contact("Friend's mom", "+2-666-6666-666"))
                .add(new Contact("Mommy", "+2-777-7777-777"));

        for (Contact contact : contactsBook.sortingByName()) {
            System.out.println(contact);
        }

        numberSearch(contactsBook);

        deletingNumber(contactsBook);

        System.out.println("Enter the name/part of the contact name to search for:");
        String contactSearch = scanner.nextLine();
        List<Contact> listContactSearch = contactsBook.searchBy(task -> task.getName().contains(contactSearch));
        System.out.println(listContactSearch.isEmpty() ? "There are no such contacts!" : "We found such a contact/s " + listContactSearch);

        System.out.println("Enter part of the name with *:");
        String partTheName = scanner.nextLine();
        List<Contact> listPartTheName = contactsBook.searchBy(partTheName);
        System.out.println(listPartTheName.isEmpty() ? "There are no such contacts!" : "We found such a contact/s " + listPartTheName);

        System.out.println("We have numbers with country codes " + contactsBook.getCountries());
    }

    public static void numberSearch(ContactsBook contactsBook) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the contact number you want to find:");
        Contact contact = contactsBook.getByPhone(scanner.nextLine());
        System.out.println(contact == null ? "We have no contact with such a number!" : "Contact name: " + contact.getName() + " with a number: " + contact.getPhoneNumber());
    }

    public static void deletingNumber(ContactsBook contactsBook) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the contact you want to delete:");
        contactsBook.removeByPhone(scanner.nextLine());
    }
}
