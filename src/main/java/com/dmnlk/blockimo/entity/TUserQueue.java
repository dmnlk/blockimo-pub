package com.dmnlk.blockimo.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

/**
 * 
 */
@Entity
@Table(name = "T_USER_QUEUE")
public class TUserQueue {

    /**  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_user_queue_serial_id")
    Long blockUserQueueSerialId;

    /**  */
    @Column(name = "twitter_id")
    Long twitterId;

    /**  */
    @Column(name = "register_twitter_id")
    Long registerTwitterId;

    /**  */
    @Column(name = "register_screen_name")
    String registerScreenName;

    /**  */
    @Column(name = "next_cursor")
    Long nextCursor;

    /**  */
    @Column(name = "add_date")
    LocalDateTime addDate;

    /**  */
    @Column(name = "update_date")
    LocalDateTime updateDate;

    /** 
     * Returns the blockUserQueueSerialId.
     * 
     * @return the blockUserQueueSerialId
     */
    public Long getBlockUserQueueSerialId() {
        return blockUserQueueSerialId;
    }

    /** 
     * Sets the blockUserQueueSerialId.
     * 
     * @param blockUserQueueSerialId the blockUserQueueSerialId
     */
    public void setBlockUserQueueSerialId(Long blockUserQueueSerialId) {
        this.blockUserQueueSerialId = blockUserQueueSerialId;
    }

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
     * Returns the registerTwitterId.
     * 
     * @return the registerTwitterId
     */
    public Long getRegisterTwitterId() {
        return registerTwitterId;
    }

    /** 
     * Sets the registerTwitterId.
     * 
     * @param registerTwitterId the registerTwitterId
     */
    public void setRegisterTwitterId(Long registerTwitterId) {
        this.registerTwitterId = registerTwitterId;
    }

    /** 
     * Returns the registerScreenName.
     * 
     * @return the registerScreenName
     */
    public String getRegisterScreenName() {
        return registerScreenName;
    }

    /** 
     * Sets the registerScreenName.
     * 
     * @param registerScreenName the registerScreenName
     */
    public void setRegisterScreenName(String registerScreenName) {
        this.registerScreenName = registerScreenName;
    }

    /** 
     * Returns the nextCursor.
     * 
     * @return the nextCursor
     */
    public Long getNextCursor() {
        return nextCursor;
    }

    /** 
     * Sets the nextCursor.
     * 
     * @param nextCursor the nextCursor
     */
    public void setNextCursor(Long nextCursor) {
        this.nextCursor = nextCursor;
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