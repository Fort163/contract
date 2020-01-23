package test.contract.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
/**
 * Created by Павел on 19.01.2020.
 */
@Entity(name = "man")
@Table(name = "MAN")
public class Man extends AuditEntity {
    private static final long serialVersionUID = -7844404675793257198L;
    private String lastName;
    private String firstName;
    private String secondName;
    private LocalDate birthDay;
    private Pasport pasport;
    private List<Address> addressList;

    @Column(name = "LASTNAME")
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "FIRSTNAME")
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "SECONDNAME")
    public String getSecondName() {
        return this.secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Column(name = "BIRTH_DAY", columnDefinition = "DATE")
    public LocalDate getBirthDay() {
        return this.birthDay;
    }
    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @OneToOne
    @JoinColumn(name = "FK_MAN_ID", foreignKey = @ForeignKey(name = "PASPORT_2_MAN"))
    public Pasport getPasport() {
        return pasport;
    }
    public void setPasport(Pasport pasport) {
        this.pasport = pasport;
    }


    @OneToMany(mappedBy = "man")
    public List<Address> getAddressList() {
        return this.addressList;
    }
    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Man man = (Man) o;
        if (lastName != null ? !lastName.equals(man.lastName) : man.lastName != null) return false;
        if (firstName != null ? !firstName.equals(man.firstName) : man.firstName != null) return false;
        if (secondName != null ? !secondName.equals(man.secondName) : man.secondName != null) return false;
        if (birthDay != null ? !birthDay.equals(man.birthDay) : man.birthDay != null) return false;
        return addressList != null ? addressList.equals(man.addressList) : man.addressList == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (addressList != null ? addressList.hashCode() : 0);
        return result;
    }

}
