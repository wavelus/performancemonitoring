package com.wavelus.servicedao;

import com.wavelus.dao.SingleLogDao;
import com.wavelus.jappo.model.SingleLog;

import java.util.List;

public class SingleLogService {
    private static SingleLogDao singleLogDao;

    public SingleLogService() {
        singleLogDao = new SingleLogDao();
    }

    public void persist(SingleLog entity) {
        singleLogDao.openCurrentSessionwithTransaction();
        singleLogDao.persist(entity);
        singleLogDao.closeCurrentSessionwithTransaction();
    }

    public void update(SingleLog entity) {
        singleLogDao.openCurrentSessionwithTransaction();
        singleLogDao.update(entity);
        singleLogDao.closeCurrentSessionwithTransaction();
    }

    public SingleLog findById(Long id) {
        singleLogDao.openCurrentSession();
        SingleLog singleLog = singleLogDao.findById(id);
        singleLogDao.closeCurrentSession();
        return singleLog;
    }

    public void delete(Long id) {
        singleLogDao.openCurrentSessionwithTransaction();
        SingleLog singleLog = singleLogDao.findById(id);
        singleLogDao.delete(singleLog);
        singleLogDao.closeCurrentSessionwithTransaction();
    }

    public List<SingleLog> findAll() {
        singleLogDao.openCurrentSession();
        List<SingleLog> singleLogs = singleLogDao.findAll();
        singleLogDao.closeCurrentSession();
        return singleLogs;
    }

    public void deleteAll() {
        singleLogDao.openCurrentSessionwithTransaction();
        singleLogDao.deleteAll();
        singleLogDao.closeCurrentSessionwithTransaction();
    }

    public SingleLogDao singleLogDao() {
        return singleLogDao;
    }
}
