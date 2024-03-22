package com.bipro.food.service;

import com.bipro.food.dto.RestaurantDto;
import com.bipro.food.model.Address;
import com.bipro.food.model.Restaurant;
import com.bipro.food.model.User;
import com.bipro.food.repository.AddressRepository;
import com.bipro.food.repository.RestaurantRepository;
import com.bipro.food.repository.UserRepository;
import com.bipro.food.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantService implements IRestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address = addressRepository.save(req.getAddress());

        address.setCity(address.getCity());
        address.setCountry(address.getCountry());
        address.setStreet(address.getStreet());

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurant.getName());
        restaurant.setDescription(restaurant.getDescription());
        restaurant.setAddress(address);
        restaurant.setContactInformation(restaurant.getContactInformation());
        restaurant.setImages(restaurant.getImages());
        restaurant.setOpeningHours(restaurant.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setCuisineType(restaurant.getCuisineType());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedResturant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updatedResturant.getCuisineType());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updatedResturant.getDescription());
        }
        if(restaurant.getName()!=null){
            restaurant.setName(updatedResturant.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);

    }

    @Override
    public List<Restaurant> getAllRestaurant() {

        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {

        return restaurantRepository.findByRestaurant(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Resturant not founf by id"+id);
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {

       Restaurant restaurant = restaurantRepository.findByOwnerId(userId);

       if(restaurant==null){
           throw  new Exception(("restaurant not found with owner"+userId));
       }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorirtes(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        boolean isFavorite = false;

        List<RestaurantDto> favorites = user.getFavorites();
        for(RestaurantDto favorite:favorites){
            if(favorite.getId().equals(restaurantId)){
                isFavorite= true;
                break;
            }
        }
        if(isFavorite){
            favorites.removeIf(favorite->favorite.getId().equals(restaurantId));
        }
        else {
            favorites.add(dto);
        }

        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {

        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
