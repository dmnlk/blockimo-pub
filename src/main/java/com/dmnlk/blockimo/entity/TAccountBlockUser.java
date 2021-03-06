package com.dmnlk.blockimo.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

/**
 * 
 */
@Entity
@Table(name = "T_ACCOUNT_BLOCK_USER")
public class TAccountBlockUser {

    /**  */
    @Id
    @Column(name = "twitter_id")
    Long twitterId;

    /**  */
    @Id
    @Column(name = "block_user_twitter_id")
    Long blockUserTwitterId;

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