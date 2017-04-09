package com.dmnlk.blockimo.task;

import com.dmnlk.blockimo.config.EnvironmentConfig;
import com.dmnlk.blockimo.entity.TAccount;
import com.dmnlk.blockimo.service.BlockTaskService;
import com.dmnlk.blockimo.util.doma.DateUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Log4j
@Component
public class BlockTask {
    @Autowired
    private BlockTaskService blockTaskService;

    @Autowired
    private EnvironmentConfig environmentConfig;

    @Scheduled(fixedDelay = 10000)
    public void blockTask() {
        LocalDateTime start = DateUtil.getNowTimestamp();
        if (environmentConfig.isEnableBlock()) {
            log.info(String.format("Block Task start %s", start.toString()));
        }
        List<TAccount> tAccounts = blockTaskService.firstStep();
        if (environmentConfig.isEnableBlock() && CollectionUtils.isNotEmpty(tAccounts)) {
            tAccounts.forEach(e -> blockTaskService.registerBlockUser(e));
        }
        if (environmentConfig.isEnableBlock() && CollectionUtils.isNotEmpty(tAccounts)) {
            tAccounts.forEach(e -> blockTaskService.secondStep(e));
        };
        if (environmentConfig.isEnableBlock() && CollectionUtils.isNotEmpty(tAccounts)) {
            //tAccounts.forEach(e -> blockTaskService.thirdStep(e));
            tAccounts.parallelStream().forEach(e -> blockTaskService.thirdStep(e));
        }
        if (environmentConfig.isEnableBlock()) {
            LocalDateTime end = DateUtil.getNowTimestamp();
            log.info(String.format("Block Task End %s", end.toString()));

            long elapsedmills = Duration.between(start, end).toMillis();
            log.info(String.format("elapsed time = %d", elapsedmills));
        }
    }

    @Scheduled(cron = "0 0 8 * * * ")
    public void notifyBlockCount() {
        log.info("Notify Task end");
        List<TAccount> tAccounts = blockTaskService.firstStep();
        if (environmentConfig.isEnableBlock() == false && CollectionUtils.isNotEmpty(tAccounts)) {
            tAccounts.forEach(e -> blockTaskService.notifyToAccount(e));
        }
        log.info("Notify Task end");
    }
 }
