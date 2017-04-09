package com.dmnlk.blockimo.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.time.LocalDateTime;

/**
 * 
 */
@Entity
@Table(name = "T_ACCOUNT")
public class TAccount {

    /**  */
    @Id
    @Column(name = "twitter_id")
    Long twitterId;

    /**  */
    @Column(name = "screen_id")
    String screenId;

    /**  */
    @Column(name = "access_token")
    String accessToken;

    /**  */
    @Column(name = "access_token_secret")
    String accessTokenSecret;

    /**  */
    @Column(name = "next_batch_execute_date")
    LocalDateTime nextBatchExecuteDate;

    /**  */
    @Column(name = "block_user_cursor")
    Long blockUserCursor;

    /**  */
    @Column(name = "friend_block_flg")
    Boolean friendBlockFlg;

    /**  */
    @Column(name = "follower_block_flg")
    Boolean followerBlockFlg;

    /**  */
    @Column(name = "verified_block_flg")
    Boolean verifiedBlockFlg;

    /**  */
    @Column(name = "auto_block_enable_flg")
    Boolean autoBlockEnableFlg;

    /**  */
    @Column(name = "add_date")
    LocalDateTime addDate;

    /**  */
    @Column(name = "update_date")
    LocalDateTime updateDate;

    /** 
     * Returns the twitterId.
     * 
     * @return the twitterId
     */
    public Long getTwitterId() {
        return twitterId;
    }

    /** 
     * Sets the twitterId.
     * 
     * @param twitterId the twitterId
     */
    public void setTwitterId(Long twitterId) {
        this.twitterId = twitterId;
    }

    /** 
     * Returns the screenId.
     * 
     * @return the screenId
     */
    public String getScreenId() {
        return screenId;
    }

    /** 
     * Sets the screenId.
     * 
     * @param screenId the screenId
     */
    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    /** 
     * Returns the accessToken.
     * 
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /** 
     * Sets the accessToken.
     * 
     * @param accessToken the accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /** 
     * Returns the accessTokenSecret.
     * 
     * @return the accessTokenSecret
     */
    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    /** 
     * Sets the accessTokenSecret.
     * 
     * @param accessTokenSecret the accessTokenSecret
     */
    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    /** 
     * Returns the nextBatchExecuteDate.
     * 
     * @return the nextBatchExecuteDate
     */
    public LocalDateTime getNextBatchExecuteDate() {
        return nextBatchExecuteDate;
    }

    /** 
     * Sets the nextBatchExecuteDate.
     * 
     * @param nextBatchExecuteDate the nextBatchExecuteDate
     */
    public void setNextBatchExecuteDate(LocalDateTime nextBatchExecuteDate) {
        this.nextBatchExecuteDate = nextBatchExecuteDate;
    }

    /** 
     * Returns the blockUserCursor.
     * 
     * @return the blockUserCursor
     */
    public Long getBlockUserCursor() {
        return blockUserCursor;
    }

    /** 
     * Sets the blockUserCursor.
     * 
     * @param blockUserCursor the blockUserCursor
     */
    public void setBlockUserCursor(Long blockUserCursor) {
        this.blockUserCursor = blockUserCursor;
    }

    /** 
     * Returns the friendBlockFlg.
     * 
     * @return the friendBlockFlg
     */
    public Boolean getFriendBlockFlg() {
        return friendBlockFlg;
    }

    /** 
     * Sets the friendBlockFlg.
     * 
     * @param friendBlockFlg the friendBlockFlg
     */
    public void setFriendBlockFlg(Boolean friendBlockFlg) {
        this.friendBlockFlg = friendBlockFlg;
    }

    /** 
     * Returns the followerBlockFlg.
     * 
     * @return the followerBlockFlg
     */
    public Boolean getFollowerBlockFlg() {
        return followerBlockFlg;
    }

    /** 
     * Sets the followerBlockFlg.
     * 
     * @param followerBlockFlg the followerBlockFlg
     */
    public void setFollowerBlockFlg(Boolean followerBlockFlg) {
        this.followerBlockFlg = followerBlockFlg;
    }

    /** 
     * Returns the verifiedBlockFlg.
     * 
     * @return the verifiedBlockFlg
     */
    public Boolean getVerifiedBlockFlg() {
        return verifiedBlockFlg;
    }

    /** 
     * Sets the verifiedBlockFlg.
     * 
     * @param verifiedBlockFlg the verifiedBlockFlg
     */
    public void setVerifiedBlockFlg(Boolean verifiedBlockFlg) {
        this.verifiedBlockFlg = verifiedBlockFlg;
    }

    /** 
     * Returns the autoBlockEnableFlg.
     * 
     * @return the autoBlockEnableFlg
     */
    public Boolean getAutoBlockEnableFlg() {
        return autoBlockEnableFlg;
    }

    /** 
     * Sets the autoBlockEnableFlg.
     * 
     * @param autoBlockEnableFlg the autoBlockEnableFlg
     */
    public void setAutoBlockEnableFlg(Boolean autoBlockEnableFlg) {
        this.autoBlockEnableFlg = autoBlockEnableFlg;
    }

    /** 
     * Returns the addDate.
     * 
     * @return the addDate
     */
    public LocalDateTime getAddDate() {
        return addDate;
    }

    /** 
     * Sets the addDate.
     * 
     * @param addDate the addDate
     */
    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    /** 
     * Returns the updateDate.
     * 
     * @return the updateDate
     */
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    /** 
     * Sets the updateDate.
     * 
     * @param updateDate the updateDate
     */
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}