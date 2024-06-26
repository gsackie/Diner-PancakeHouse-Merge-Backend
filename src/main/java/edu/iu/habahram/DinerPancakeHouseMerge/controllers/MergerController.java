package edu.iu.habahram.DinerPancakeHouseMerge.controllers;

import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItem;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.CafeRepository;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.DinerRepository;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.PancakeHouseRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/merger")
public class MergerController {

    private final DinerRepository dinerRepository;
    private final PancakeHouseRepository pancakeHouseRepository;
    private final CafeRepository cafeRepository;

    public MergerController(DinerRepository dinerRepository, PancakeHouseRepository pancakeHouseRepository, CafeRepository cafeRepository) {
        this.dinerRepository = dinerRepository;
        this.pancakeHouseRepository = pancakeHouseRepository;
        this.cafeRepository = cafeRepository;
    }

    @GetMapping
    public List<MenuItem> get() {
        List<MenuItem> menuItems = new ArrayList<>();

        // Add Diner menu items
        Iterator<MenuItem> lunchItems = dinerRepository.getTheMenuIterator();
        while (lunchItems.hasNext()) {
            menuItems.add(lunchItems.next());
        }

        // Add Pancake House menu items
        Iterator<MenuItem> breakfastItems = pancakeHouseRepository.getTheMenuIterator();
        while (breakfastItems.hasNext()) {
            menuItems.add(breakfastItems.next());
        }

        // Add Cafe menu items
        Iterator<MenuItem> cafeItems = cafeRepository.getTheMenuIterator();
        while (cafeItems.hasNext()) {
            menuItems.add(cafeItems.next());
        }

        return menuItems;
    }
}
