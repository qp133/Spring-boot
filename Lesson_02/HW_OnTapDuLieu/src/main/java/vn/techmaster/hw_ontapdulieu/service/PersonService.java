package vn.techmaster.hw_ontapdulieu.service;

import vn.techmaster.hw_ontapdulieu.model.Person;

import java.util.*;

public class PersonService {
    public void sortByName(ArrayList<Person> person, String direction) {
        person.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (direction.equals("asc")) {
                    return o1.getName().compareTo(o2.getName());
                } else if (direction.equals("desc")) {
                    return o2.getName().compareTo(o1.getName());
                }
                return 0;
            }
        });
    }

    public void sortByNationality(ArrayList<Person> allPerson,String direction){
        allPerson.sort(new Comparator<Person>() {
                           @Override
                           public int compare(Person o1, Person o2) {
                               if (direction.equals("asc")){
                                   return o1.getNationality().compareTo(o2.getNationality());
                               } else if (direction.equals("desc")) {
                                   return o2.getNationality().compareTo(o1.getNationality());
                               }
                               return 0;
                           }
                       }
        );
    }
    public void sortByAge(ArrayList<Person> allPerson,String direction){
        allPerson.sort(new Comparator<Person>() {
                           @Override
                           public int compare(Person o1, Person o2) {
                               if (direction.equals("asc")){
                                   return o1.getAge()- o2.getAge();
                               } else if (direction.equals("desc")) {
                                   return o2.getAge()- o1.getAge();
                               }
                               return 0;
                           }
                       }
        );
    }

    public List<Map.Entry<String, Long>> sortNationalityByList(HashMap<String, Long> hashMap, String direction) {
        List<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String, Long>>();
        list.addAll(hashMap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                if (direction.equals("asc")) {
                    return (int) (o1.getValue() - o2.getValue());
                } else if (direction.equals("desc")) {
                    return (int) (o2.getValue() - o1.getValue());
                }
                return 0;
            }
        });
        return list;
    }

    public HashMap<String, Long> sortNationalityByMap(HashMap<String, Long> hashMap, String direction) {
        List<Map.Entry<String, Long>> list = sortNationalityByList(hashMap, direction);
        LinkedHashMap<String, Long> mapSorted = new LinkedHashMap<>(list.size());
        for (Map.Entry<String, Long> entry : list) {
            mapSorted.put(entry.getKey(), entry.getValue());
        }
        return mapSorted;
    }
}
