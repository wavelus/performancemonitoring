package model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@NamedNativeQuery(name = "User.byName", query = "select * from users where name = ?", resultClass = User.class)
@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
public class User {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "Attendance",
        joinColumns = {@JoinColumn(name = "userid")},
        inverseJoinColumns = {@JoinColumn(name = "eventid")}
    )
    Set<Event> events = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    private Date joinDate;
    @OneToOne
    @JoinColumn(name = "addressid")
    private UsersAddress usersAddress;

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public UsersAddress getUsersAddress() {
        return usersAddress;
    }

    public void setUsersAddress(UsersAddress usersAddress) {
        this.usersAddress = usersAddress;
    }

    @Override
    public String toString() {
        return "Id= " + userId + ", Name= " + name + ", {Address= " + usersAddress + "}";
    }

}
