package edu.iu.habahram.DinerPancakeHouseMerge.controllers;

import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItemRecord;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.MergerRepository;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/merger")
public class MergerController {

    private final MergerRepository mergerRepository;

    public MergerController(MergerRepository mergerRepository) {
        this.mergerRepository = mergerRepository;
    }

    @GetMapping
    public List<MenuItemRecord> get() {
        return mergerRepository.getTheMenuItems();
    }

    @GetMapping("/vegetarian")
    public List<MenuItemRecord> getVegetarianItems() {
        List<MenuItemRecord> vegetarianItems = new ArrayList<>();
        List<MenuItemRecord> allItems = mergerRepository.getTheMenuItems();

        for (MenuItemRecord item : allItems) {
            if (item.vegetarian()) {
                vegetarianItems.add(item);
            }
        }

        return vegetarianItems;
    }

    @GetMapping("/breakfast")
    public List<MenuItemRecord> getBreakfastItems() {
        List<MenuItemRecord> breakfastItems = new ArrayList<>();
        List<MenuItemRecord> allItems = mergerRepository.getTheMenuItems();

        for (MenuItemRecord item : allItems) {
            if (isBreakfast(item)) {
                breakfastItems.add(item);
            }
        }

        return breakfastItems;
    }

    // Helper method to check if an item is a breakfast item
    private boolean isBreakfast(MenuItemRecord item) {
        // Assuming the description contains the word "breakfast"
        return item.description().toLowerCase().contains("pancakes")
                || item.description().toLowerCase().contains("waffles")
                || item.description().toLowerCase().contains("eggs");

    }


    @GetMapping("/lunch")
    public List<MenuItemRecord> getLunchItems() {
        List<MenuItemRecord> lunchItems = new ArrayList<>();
        List<MenuItemRecord> allItems = mergerRepository.getTheMenuItems();

        for (MenuItemRecord item : allItems) {
            if (isLunch(item)) {
                lunchItems.add(item);
            }
        }

        return lunchItems;
    }

    // Helper method to check if an item is a lunch item
    private boolean isLunch(MenuItemRecord item) {
        // Assuming the description contains the word "lunch"
        return item.description().toLowerCase().contains("lunch")
                || item.description().toLowerCase().contains("bacon")
                || item.description().toLowerCase().contains("hotdog")
                || item.description().toLowerCase().contains("eggs");

    }


    @GetMapping("/supper")
    public List<MenuItemRecord> getSupperItems() {
        List<MenuItemRecord> supperItems = new ArrayList<>();
        List<MenuItemRecord> allItems = mergerRepository.getTheMenuItems();

        for (MenuItemRecord item : allItems) {
            if (isSupper(item)) {
                supperItems.add(item);
            }
        }

        return supperItems;
    }

    // Helper method to check if an item is a supper item
    private boolean isSupper(MenuItemRecord item) {
        List<String> supperItemNames = List.of(
                "Vegetarian BLT",
                "BLT",
                "Soup of the day",
                "Hotdog",
                "Steamed Veggies and Brown Rice",
                "Pasta"
        );

        return supperItemNames.contains(item.name());
    }




    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        String userData = String.format("Username: %s, Password: %s, Email: %s%n", username, password, email);
        try (FileWriter writer = new FileWriter("data/customers.txt", true)) {
            writer.write(userData);
            return "User signed up successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to sign up. Please try again later.";
        }
    }
}
