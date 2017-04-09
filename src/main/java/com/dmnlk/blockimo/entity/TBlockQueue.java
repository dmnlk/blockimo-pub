package com.dmnlk.blockimo.entity;

import com.dmnlk.blockimo.domain.QueueStatus;
import org.seasar.doma.*;

import java.time.LocalDateTime;

/**
 * 
 */
@Entity
@Table(name = "T_BLOCK_QUEUE")
public class TBlockQueue {

    /**  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_queue_serial_id")
    Long blockQueueSerialId;

    /**  */
    @Column(name = "twitter_id")
    Long twitterId;

    /**  */
    @Column(name = "block_user_twitter_id")
    Long blockUserTwitterId;

    /**  */
    @Column(name = "queue_status")
    QueueStatus queueStatus;

    /**  */
    @Column(name = "block_user_screen_name")
    String blockUserScreenName;

    /**  */
    @Column(name = "add_date")
    LocalDateTime addDate;

    /**  */
    @Column(name = "update_date")
    LocalDateTime updateDate;

    /** 
     * Returns the blockQueueSerialId.
     * 
     * @return the blockQueueSerialId
     */
    public Long getBlockQueueSerialId() {
        return blockQueueSerialId;
    }

    /** 
     * Sets the blockQueueSerialId.
     * 
     * @param blockQueueSerialId the blockQueueSerialId
     */
    public void setBlockQueueSerialId(Long blockQueueSerialId) {
        this.blockQueueSerialId = blockQueueSerialId;
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
     * Returns the blockUserTwitterId.
     * 
     * @return the blockUserTwitterId
     */
    public Long getBlockUserTwitterId() {
        return blockUserTwitterId;
    }

    /** 
     * Sets the blockUserTwitterId.
     * 
     * @param blockUserTwitterId the blockUserTwitterId
     */
    public void setBlockUserTwitterId(Long blockUserTwitterId) {
        this.blockUserTwitterId = blockUserTwitterId;
    }

    /** 
     * Returns the queueStatus.
     * 
     * @return the queueStatus
     */
    public QueueStatus getQueueStatus() {
        return queueStatus;
    }

    /** 
     * Sets the queueStatus.
     * 
     * @param queueStatus the queueStatus
     */
    public void setQueueStatus(QueueStatus queueStatus) {
        this.queueStatus = queueStatus;
    }

    /** 
     * Returns the blockUserScreenName.
     * 
     * @return the blockUserScreenName
     */
    public String getBlockUserScreenName() {
        return blockUserScreenName;
    }

    /** 
     * Sets the blockUserScreenName.
     * 
     * @param blockUserScreenName the blockUserScreenName
     */
    public void setBlockUserScreenName(String blockUserScreenName) {
        this.blockUserScreenName = blockUserScreenName;
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