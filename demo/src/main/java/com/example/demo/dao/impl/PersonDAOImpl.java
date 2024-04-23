package com.example.demo.dao.impl;

import com.example.demo.dao.PersonDAO;
import com.example.demo.database.PersonDB;
import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PersonDAOImpl implements PersonDAO {
    @Override
    public void printListPeople(List<Person> persons) {

    }

    @Override
    public List<Person> getAll() {
        return PersonDB.people;
    }

    @Override
    public List<Person> sortPeopleByFullName() {
        return getAll().stream()
                .sorted(Comparator.comparing(Person::getFullname))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> sortPeopleByFullNameReversed() {
        return getAll().stream()
                .sorted(Comparator.comparing(Person::getFullname).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getSortedJobs() {
        return getAll().stream()
                .map(Person::getJob)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
@Override
    public List<String> getSortedCities() {
        return getAll().stream()
                .map(Person::getCity)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
@Override
    public List<String> femaleNames() {
        return getAll().stream()
                .filter(person -> person.getGender().equals("Female"))
                .map(Person::getFullname)
                .collect(Collectors.toList());
    }
@Override
    public Person highestEarner() {
        return getAll().stream()
                .max(Comparator.comparingDouble(Person::getSalary))
                .orElse(null);
    }
@Override
    public List<Person> bornAfter1990() {
    return getAll().stream()
            .filter(person -> person.getBirthday().getYear() > 1990)
            .collect(Collectors.toList());
    }
@Override
    public double averageSalary() {
        return getAll().stream()
                .mapToDouble(Person::getSalary)
                .average()
                .orElse(0.0);
    }
@Override
    public double averageAge() {
        return getAll().stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
    }
@Override
    public List<Person> nameContains(String keyword) {
        return getAll().stream()
                .filter(person -> person.getFullname().contains(keyword))
                .collect(Collectors.toList());
    }
@Override
    public List<Person> sortedByAgeForMale() {
        return getAll().stream()
                .filter(person -> person.getGender().equals("Male"))
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .collect(Collectors.toList());
    }
@Override
    public Person longestName() {
        return getAll().stream()
                .max(Comparator.comparingInt(person -> person.getFullname().length()))
                .orElse(null);
    }
@Override
    public List<Person> aboveAverageSalary() {
        double averageSalary = averageSalary();
        return getAll().stream()
                .filter(person -> person.getSalary() > averageSalary)
                .collect(Collectors.toList());
    }
@Override
    public Map<String, List<Person>> groupPeopleByCity() {
        return getAll().stream()
                .collect(Collectors.groupingBy(Person::getCity));
    }
@Override
    public Map<String, Integer> groupJobByCount() {
        return getAll().stream()
                .collect(Collectors.groupingBy(Person::getJob, Collectors.summingInt(e -> 1)));
    }
}
