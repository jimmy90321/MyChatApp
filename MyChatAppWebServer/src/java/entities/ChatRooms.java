
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "chat_rooms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChatRooms.findAll", query = "SELECT c FROM ChatRooms c")
    , @NamedQuery(name = "ChatRooms.findByChatRoomId", query = "SELECT c FROM ChatRooms c WHERE c.chatRoomId = :chatRoomId")
    , @NamedQuery(name = "ChatRooms.findByRoomName", query = "SELECT c FROM ChatRooms c WHERE c.roomName = :roomName")
    , @NamedQuery(name = "ChatRooms.findByCreatedAt", query = "SELECT c FROM ChatRooms c WHERE c.createdAt = :createdAt")})
public class ChatRooms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "chat_room_id")
    private Integer chatRoomId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "room_name")
    private String roomName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "chatRoomId")
    //private Collection<Messages> messagesCollection;

    public ChatRooms() {
    }

    public ChatRooms(Integer chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public ChatRooms(Integer chatRoomId, String roomName, Date createdAt) {
        this.chatRoomId = chatRoomId;
        this.roomName = roomName;
        this.createdAt = createdAt;
    }

    public Integer getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Integer chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

//    @XmlTransient
//    public Collection<Messages> getMessagesCollection() {
//        return messagesCollection;
//    }
//
//    public void setMessagesCollection(Collection<Messages> messagesCollection) {
//        this.messagesCollection = messagesCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chatRoomId != null ? chatRoomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChatRooms)) {
            return false;
        }
        ChatRooms other = (ChatRooms) object;
        if ((this.chatRoomId == null && other.chatRoomId != null) || (this.chatRoomId != null && !this.chatRoomId.equals(other.chatRoomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ChatRooms[ chatRoomId=" + chatRoomId + " ]";
    }

}
