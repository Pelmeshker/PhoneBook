package ru.pelmeshker.phonebook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pelmeshker.phonebook.dao.PhoneBookEntryDAO;
import ru.pelmeshker.phonebook.entity.PhoneBookEntry;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PhoneBookEntryServiceImpl implements PhoneBookEntryService {

    @Autowired
    private PhoneBookEntryDAO phoneBookEntryDAO;

    @Override
    @Transactional
    public List<PhoneBookEntry> getAllPhoneBookEntries() {
        return phoneBookEntryDAO.getAllPhoneBookEntries();
    }

    @Override
    public List<PhoneBookEntry> getUserPhoneBookEntries(String authorId) {
        return phoneBookEntryDAO.getUserPhoneBookEntries(authorId);
    }

    @Override
    @Transactional
    public void savePhoneBookEntry(PhoneBookEntry employee) {
        phoneBookEntryDAO.savePhoneBookEntry(employee);
    }

    @Override
    @Transactional
    public PhoneBookEntry getPhoneBookEntry(int id) {
        return phoneBookEntryDAO.getPhoneBookEntry(id);
    }

    @Override
    @Transactional
    public void deletePhoneBookEntry(int id) {
        phoneBookEntryDAO.deletePhoneBookEntry(id);
    }
}
