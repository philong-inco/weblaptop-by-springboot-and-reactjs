package com.dantn.weblaptop.util;

import com.dantn.weblaptop.entity.base.BaseEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Calendar;

/**
 * @since 06/3/2024
 * Github: https://github.com/philong-inco
 */

public class AutoSetTime {

    @PrePersist
    public void onCreate(BaseEntity entity) {
        entity.setNgayTao(getCurrentTimes());
        entity.setNgaySua(getCurrentTimes());
    }

    @PreUpdate
    public void onUpdate(BaseEntity entity) {
        entity.setNgaySua(getCurrentTimes());
    }

    public Long getCurrentTimes() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
