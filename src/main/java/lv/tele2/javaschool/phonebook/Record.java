package lv.tele2.javaschool.phonebook;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;



public class Record implements Serializable {
    public static final long serialVersionUID=1L;
    private static int nextId=1;
    private int id;

    private String name;
 //   private String [] phone;
    private List<String> phoneList = new ArrayList<>();
    private String email;

    public Record (){
        if (this.id == 1 ) {
            this.id=nextId;
            nextId++;
        }
        else {
            this.id=getId();
        }

    }

    public Record (String name, String email, String... phones) {
        this();  //mist be first row!
        this.name=name;
        this.email=email;
        Collections.addAll(this.phoneList, phones);

    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

   // public void setPhone(String... phone) {
   //     this.phone = phone;
   // }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", name='" + name + '\'' +
 //               ", phone=" + Arrays.toString(phoneList) +
                ", phone=" + phoneList +
                ", email='" + email + '\'' +
                '}';
    }


}
