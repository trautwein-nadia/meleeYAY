
package com.meleeChat.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Participant {

    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("checked_in_at")
    @Expose
    private Object checkedInAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("final_rank")
    @Expose
    private Object finalRank;
    @SerializedName("group_id")
    @Expose
    private Object groupId;
    @SerializedName("icon")
    @Expose
    private Object icon;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("invitation_id")
    @Expose
    private Object invitationId;
    @SerializedName("invite_email")
    @Expose
    private Object inviteEmail;
    @SerializedName("misc")
    @Expose
    private Object misc;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("on_waiting_list")
    @Expose
    private Boolean onWaitingList;
    @SerializedName("seed")
    @Expose
    private Integer seed;
    @SerializedName("tournament_id")
    @Expose
    private Integer tournamentId;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("challonge_username")
    @Expose
    private Object challongeUsername;
    @SerializedName("challonge_email_address_verified")
    @Expose
    private Object challongeEmailAddressVerified;
    @SerializedName("removable")
    @Expose
    private Boolean removable;
    @SerializedName("participatable_or_invitation_attached")
    @Expose
    private Boolean participatableOrInvitationAttached;
    @SerializedName("confirm_remove")
    @Expose
    private Boolean confirmRemove;
    @SerializedName("invitation_pending")
    @Expose
    private Boolean invitationPending;
    @SerializedName("display_name_with_invitation_email_address")
    @Expose
    private String displayNameWithInvitationEmailAddress;
    @SerializedName("email_hash")
    @Expose
    private Object emailHash;
    @SerializedName("username")
    @Expose
    private Object username;
    @SerializedName("attached_participatable_portrait_url")
    @Expose
    private Object attachedParticipatablePortraitUrl;
    @SerializedName("can_check_in")
    @Expose
    private Boolean canCheckIn;
    @SerializedName("checked_in")
    @Expose
    private Boolean checkedIn;
    @SerializedName("reactivatable")
    @Expose
    private Boolean reactivatable;

    /**
     * 
     * @return
     *     The active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * 
     * @param active
     *     The active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * 
     * @return
     *     The checkedInAt
     */
    public Object getCheckedInAt() {
        return checkedInAt;
    }

    /**
     * 
     * @param checkedInAt
     *     The checked_in_at
     */
    public void setCheckedInAt(Object checkedInAt) {
        this.checkedInAt = checkedInAt;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The finalRank
     */
    public Object getFinalRank() {
        return finalRank;
    }

    /**
     * 
     * @param finalRank
     *     The final_rank
     */
    public void setFinalRank(Object finalRank) {
        this.finalRank = finalRank;
    }

    /**
     * 
     * @return
     *     The groupId
     */
    public Object getGroupId() {
        return groupId;
    }

    /**
     * 
     * @param groupId
     *     The group_id
     */
    public void setGroupId(Object groupId) {
        this.groupId = groupId;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public Object getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(Object icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The invitationId
     */
    public Object getInvitationId() {
        return invitationId;
    }

    /**
     * 
     * @param invitationId
     *     The invitation_id
     */
    public void setInvitationId(Object invitationId) {
        this.invitationId = invitationId;
    }

    /**
     * 
     * @return
     *     The inviteEmail
     */
    public Object getInviteEmail() {
        return inviteEmail;
    }

    /**
     * 
     * @param inviteEmail
     *     The invite_email
     */
    public void setInviteEmail(Object inviteEmail) {
        this.inviteEmail = inviteEmail;
    }

    /**
     * 
     * @return
     *     The misc
     */
    public Object getMisc() {
        return misc;
    }

    /**
     * 
     * @param misc
     *     The misc
     */
    public void setMisc(Object misc) {
        this.misc = misc;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The onWaitingList
     */
    public Boolean getOnWaitingList() {
        return onWaitingList;
    }

    /**
     * 
     * @param onWaitingList
     *     The on_waiting_list
     */
    public void setOnWaitingList(Boolean onWaitingList) {
        this.onWaitingList = onWaitingList;
    }

    /**
     * 
     * @return
     *     The seed
     */
    public Integer getSeed() {
        return seed;
    }

    /**
     * 
     * @param seed
     *     The seed
     */
    public void setSeed(Integer seed) {
        this.seed = seed;
    }

    /**
     * 
     * @return
     *     The tournamentId
     */
    public Integer getTournamentId() {
        return tournamentId;
    }

    /**
     * 
     * @param tournamentId
     *     The tournament_id
     */
    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The challongeUsername
     */
    public Object getChallongeUsername() {
        return challongeUsername;
    }

    /**
     * 
     * @param challongeUsername
     *     The challonge_username
     */
    public void setChallongeUsername(Object challongeUsername) {
        this.challongeUsername = challongeUsername;
    }

    /**
     * 
     * @return
     *     The challongeEmailAddressVerified
     */
    public Object getChallongeEmailAddressVerified() {
        return challongeEmailAddressVerified;
    }

    /**
     * 
     * @param challongeEmailAddressVerified
     *     The challonge_email_address_verified
     */
    public void setChallongeEmailAddressVerified(Object challongeEmailAddressVerified) {
        this.challongeEmailAddressVerified = challongeEmailAddressVerified;
    }

    /**
     * 
     * @return
     *     The removable
     */
    public Boolean getRemovable() {
        return removable;
    }

    /**
     * 
     * @param removable
     *     The removable
     */
    public void setRemovable(Boolean removable) {
        this.removable = removable;
    }

    /**
     * 
     * @return
     *     The participatableOrInvitationAttached
     */
    public Boolean getParticipatableOrInvitationAttached() {
        return participatableOrInvitationAttached;
    }

    /**
     * 
     * @param participatableOrInvitationAttached
     *     The participatable_or_invitation_attached
     */
    public void setParticipatableOrInvitationAttached(Boolean participatableOrInvitationAttached) {
        this.participatableOrInvitationAttached = participatableOrInvitationAttached;
    }

    /**
     * 
     * @return
     *     The confirmRemove
     */
    public Boolean getConfirmRemove() {
        return confirmRemove;
    }

    /**
     * 
     * @param confirmRemove
     *     The confirm_remove
     */
    public void setConfirmRemove(Boolean confirmRemove) {
        this.confirmRemove = confirmRemove;
    }

    /**
     * 
     * @return
     *     The invitationPending
     */
    public Boolean getInvitationPending() {
        return invitationPending;
    }

    /**
     * 
     * @param invitationPending
     *     The invitation_pending
     */
    public void setInvitationPending(Boolean invitationPending) {
        this.invitationPending = invitationPending;
    }

    /**
     * 
     * @return
     *     The displayNameWithInvitationEmailAddress
     */
    public String getDisplayNameWithInvitationEmailAddress() {
        return displayNameWithInvitationEmailAddress;
    }

    /**
     * 
     * @param displayNameWithInvitationEmailAddress
     *     The display_name_with_invitation_email_address
     */
    public void setDisplayNameWithInvitationEmailAddress(String displayNameWithInvitationEmailAddress) {
        this.displayNameWithInvitationEmailAddress = displayNameWithInvitationEmailAddress;
    }

    /**
     * 
     * @return
     *     The emailHash
     */
    public Object getEmailHash() {
        return emailHash;
    }

    /**
     * 
     * @param emailHash
     *     The email_hash
     */
    public void setEmailHash(Object emailHash) {
        this.emailHash = emailHash;
    }

    /**
     * 
     * @return
     *     The username
     */
    public Object getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *     The username
     */
    public void setUsername(Object username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     The attachedParticipatablePortraitUrl
     */
    public Object getAttachedParticipatablePortraitUrl() {
        return attachedParticipatablePortraitUrl;
    }

    /**
     * 
     * @param attachedParticipatablePortraitUrl
     *     The attached_participatable_portrait_url
     */
    public void setAttachedParticipatablePortraitUrl(Object attachedParticipatablePortraitUrl) {
        this.attachedParticipatablePortraitUrl = attachedParticipatablePortraitUrl;
    }

    /**
     * 
     * @return
     *     The canCheckIn
     */
    public Boolean getCanCheckIn() {
        return canCheckIn;
    }

    /**
     * 
     * @param canCheckIn
     *     The can_check_in
     */
    public void setCanCheckIn(Boolean canCheckIn) {
        this.canCheckIn = canCheckIn;
    }

    /**
     * 
     * @return
     *     The checkedIn
     */
    public Boolean getCheckedIn() {
        return checkedIn;
    }

    /**
     * 
     * @param checkedIn
     *     The checked_in
     */
    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    /**
     * 
     * @return
     *     The reactivatable
     */
    public Boolean getReactivatable() {
        return reactivatable;
    }

    /**
     * 
     * @param reactivatable
     *     The reactivatable
     */
    public void setReactivatable(Boolean reactivatable) {
        this.reactivatable = reactivatable;
    }

}
