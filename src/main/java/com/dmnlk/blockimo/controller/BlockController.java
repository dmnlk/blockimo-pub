package com.dmnlk.blockimo.controller;

import com.dmnlk.blockimo.domain.QueueStatus;
import com.dmnlk.blockimo.dto.TwitterAuthDto;
import com.dmnlk.blockimo.entity.TBlockQueue;
import com.dmnlk.blockimo.entity.TUserQueue;
import com.dmnlk.blockimo.response.IndexResponse;
import com.dmnlk.blockimo.service.BlockService;
import com.dmnlk.blockimo.util.doma.BlockimoUtils;
import com.dmnlk.blockimo.util.doma.DateUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Block
 */
@Controller
@Log4j
public class BlockController {
    @Autowired
    private TwitterAuthDto twitterAuthDto;
    @Autowired
    private BlockService blockService;


    @RequestMapping("/block/index")
    public String index() {
        return "block/index";
    }

    @ResponseBody
    @RequestMapping("/block/list")
    public IndexResponse list() {
        IndexResponse response = new IndexResponse();
        response.setMessage("aaa");
        return  response;
    }

    @ResponseBody
    @RequestMapping("/block/register")
    public IndexResponse register(@RequestParam String screenName) {
        IndexResponse response = new IndexResponse();

        AccessToken accessToken = twitterAuthDto.getAccessToken();
        Twitter twitter = new TwitterFactory().getInstance(accessToken);
        long cursor = -1L;
        User user = null;
        String rep = BlockimoUtils.replaceTwitterUserString(screenName);
        try {
            user = twitter.showUser(rep);
        } catch (TwitterException e) {
            e.printStackTrace();
            response.setMessage(e.getErrorMessage());
            response.setResultCode(1);
            return response;
        }
        if (user.isProtected()) {
            response.setMessage("非公開アカウントのため、登録できません");
            response.setResultCode(1);
            return response;
        }
        TBlockQueue data = blockService.findByTwitterIdAndBlockTwitterId(accessToken.getUserId(), user.getId());
        if (data == null) {
            LocalDateTime now = DateUtil.getNowTimestamp();
            TUserQueue tUserQueue = new TUserQueue();
            tUserQueue.setTwitterId(accessToken.getUserId());
            tUserQueue.setRegisterTwitterId(user.getId());
            tUserQueue.setRegisterScreenName(user.getScreenName());
            tUserQueue.setNextCursor(cursor);
            tUserQueue.setAddDate(now);
            tUserQueue.setUpdateDate(now);
            blockService.registerUser(tUserQueue);

            ArrayList<TBlockQueue> blockQueues = new ArrayList<>();
            TBlockQueue tb = new TBlockQueue();
            tb.setTwitterId(accessToken.getUserId());
            tb.setQueueStatus(QueueStatus.QUEUE);
            tb.setBlockUserTwitterId(user.getId());
            tb.setAddDate(now);
            tb.setUpdateDate(now);
            blockQueues.add(tb);

            blockService.registerBlockUser(blockQueues);
        }
        response.setResultCode(0);
        return  response;
    }
}
