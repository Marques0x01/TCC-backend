package com.tcc.backend.service;

import com.tcc.backend.model.Log;
import com.tcc.backend.model.LogType;
import com.tcc.backend.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void saveLog(LogType logType, Long userId){
        if(logType == null || userId == null) {
            throw new IllegalArgumentException("LogType and User id can't be null");
        }
        Log log = Log.builder()
                .type(logType)
                .date(new Date())
                .userId(userId)
                .build();

        logRepository.save(log);
    }


}
