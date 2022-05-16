package ru.pelmeshker.phonebook.service;

import ru.pelmeshker.phonebook.entity.PhoneBookEntry;

import java.util.List;

public interface PhoneBookEntryService {
    public List<PhoneBookEntry> getAllPhoneBookEntries();
    public List<PhoneBookEntry> getUserPhoneBookEntries(String authorId);

    public void savePhoneBookEntry(PhoneBookEntry phoneBookEntry);

    public PhoneBookEntry getPhoneBookEntry(int id);

    public void deletePhoneBookEntry(int id);
}
