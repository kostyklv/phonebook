package lv.tele2.javaschool.phonebook;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;



public class Record implements Serializable {
    private static final long serialVersionUID=1L;
    private static int nextId;
    private int id;

    private String name;
 //   private String [] phone;
    private List<String> phoneList = new ArrayList<>();
    private String email;
    private int maxId;



    public void setMaxId (int maxId){
//        if (maxId ==0) {
//            nextId=maxId;
//        }
//        else {nextId=1;}
        this.maxId=maxId;
        if (maxId !=0) {
            nextId=maxId;
        } else {
            nextId=1;
        }

    }

    public Record (int maxId, String name, String email, String... phones) {
        this();  //mist be first row!
        this.maxId=maxId;
        System.out.println("Max="+maxId);
        if (maxId != 0 ) {
            nextId=maxId;
        }
       else {
            nextId=1;
        }
//        this.id=nextId;
        nextId++;
        System.out.println("Next="+nextId);
        //this.id
        id=nextId;
        this.name=name;
        this.email=email;
        Collections.addAll(this.phoneList, phones);

    }

    public Record (){
//        if (maxId != 0 ) {
//            nextId=maxId;
//        }
////       else {
////            nextId=maxId;
////
//       this.id=nextId;
//       nextId++;
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
