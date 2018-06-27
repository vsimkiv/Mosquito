package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.entities.User;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
class LogWorkTransformerTest {

    @Test
    void toEntity() {

        LogWorkDto logWorkDto = new LogWorkDto();
        logWorkDto.setDescription("TestDescription");
        logWorkDto.setId(10L);
        logWorkDto.setLogged(100);
        User user = new User();
        logWorkDto.setUserId(user.getId());
        Estimation estimation = new Estimation();
        logWorkDto.setEstimationId(estimation.getId());
        LogWork logWork = new LogWork();
        logWork.setDescription(logWorkDto.getDescription());
        logWork.setLogged(logWorkDto.getLogged());
        logWork.setId(logWorkDto.getId());
        //logWork.setEstimation(logWorkDto.getEstimationId());
        //logWork.setAuthor(logWorkDto.getUserId());
    }

    @Test
    void toDTO() {
    }

    @Test
    void toEntityList() {
    }

    @Test
    void toDTOList() {
    }
}
