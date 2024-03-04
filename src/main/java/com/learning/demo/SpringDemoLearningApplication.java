package com.learning.demo;

import com.learning.demo.entity.Guest;
import com.learning.demo.entity.Manager;
import com.learning.demo.entity.manytomany.Amenities;
import com.learning.demo.entity.manytomany.Package;
import com.learning.demo.repository.AmenitiesRepository;
import com.learning.demo.repository.PackageRepository;
import com.learning.demo.service.GuestService;
import com.learning.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Use CommandLineRunner
 * to run specific codes
 * on application run
 *
 */
@SpringBootApplication
public class SpringDemoLearningApplication implements CommandLineRunner {

    @Autowired
    GuestService guestService;

    @Autowired
    ManagerService managerService;

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    AmenitiesRepository amenitiesRepository;


    public static void main(String[] args) {

        SpringApplication.run(SpringDemoLearningApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initGuests();
        initManagers();
        initAmenities();
    }

    private void initGuests()
    {
        guestService.createGuest(new Guest("John", "Doe", "897-987-1234", 12));
        guestService.createGuest(new Guest("Jack", "Black", "897-987-4567", 1));
        guestService.createGuest(new Guest("James", "Smith", "897-987-0987", 7));
        guestService.createGuest(new Guest("Dana", "White", "897-987-3445", 2));
        guestService.createGuest(new Guest("Ana", "Smith", "897-987-4675", 8));

    }

    private void initManagers()
    {
        managerService.createManager(new Manager("Sam", "Adams", "sam@hotel.com"));
        managerService.createManager(new Manager("Michael", "Doe", "mike@hotel.com"));
        managerService.createManager(new Manager("George", "Williams", "george@hotel.com"));
    }

    private void initAmenities() {
        Amenities amenities = new Amenities();
        amenities.setAmenitiesCode("MYAMENITY001");
        amenities.setAmenitiesType("Golf");

        Set<Package> packageSet = new HashSet<>();

        Package amenitiesPackage = new Package();
        amenitiesPackage.setPackageName("Free meal on the course");
        amenitiesPackage.setPackageCode("PKG001");

        packageRepository.save(amenitiesPackage);

        packageSet.add(amenitiesPackage);
        amenities.setPackageSet(packageSet);

        Amenities amenitiesSaved = amenitiesRepository.save(amenities);

        //=========================================

        Amenities amenities2 = new Amenities();
        amenities2.setAmenitiesCode("MYAMENITY002");
        amenities2.setAmenitiesType("AXE Throwing");

        Set<Package> packageSet2 = new HashSet<>();

        Package amenitiesPackage2 = new Package();
        amenitiesPackage2.setPackageName("Free AXE");
        amenitiesPackage2.setPackageCode("PKG002");

        packageRepository.save(amenitiesPackage2);

        packageSet2.add(amenitiesPackage2);
        amenities2.setPackageSet(packageSet2);

        Amenities amenitiesSaved2 = amenitiesRepository.save(amenities2);
    }
}
