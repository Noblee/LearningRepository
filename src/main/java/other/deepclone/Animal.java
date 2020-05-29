package other.deepclone;

import java.io.Serializable;

class Animal implements Serializable {
    Person host;//主人
    int age;//年纪
    Animal(Person person,int age){
        this.host=person;
        this.age=age;
    }
}