package ru.pelmeshker.phonebook.dao;

import org.springframework.stereotype.Repository;
import ru.pelmeshker.phonebook.entity.PhoneBookEntry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PhoneBookEntryDAOImp implements PhoneBookEntryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<PhoneBookEntry> getAllPhoneBookEntries() {
        Query query = entityManager.createQuery("from PhoneBookEntry ");
        List<PhoneBookEntry> allPhoneBookEntries = query.getResultList();
        return allPhoneBookEntries;
    }

    @Override
    @Transactional
    public List<PhoneBookEntry> getUserPhoneBookEntries(String authorId) {
        Query query = entityManager.createQuery("from PhoneBookEntry where authorId=:userId");
        query.setParameter("userId", authorId);
        List<PhoneBookEntry> userPhoneBookEntries = query.getResultList();
        return userPhoneBookEntries;
    }

    @Override
    @Transactional
    public void savePhoneBookEntry(PhoneBookEntry phoneBookEntry) {
        entityManager.merge(phoneBookEntry);
    }

    @Override
    public PhoneBookEntry getPhoneBookEntry(int id) {
        PhoneBookEntry phoneBookEntry = entityManager.find(PhoneBookEntry.class, id);
        return phoneBookEntry;
    }

    @Override
    public void deletePhoneBookEntry(int id) {
        Query query = entityManager.createQuery("delete from PhoneBookEntry where id=:phoneBookEntryId");
        query.setParameter("phoneBookEntryId", id);
        query.executeUpdate();
    }
}
