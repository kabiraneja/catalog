package io.codzone.moviecatalogservice.controller;

import io.codzone.moviecatalogservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController{

    @Autowired
    RestTemplate restTemplate;

      @GetMapping ("/moviecatalog")
     public MCM_Wrapper getMovieCatalog() {
         List<MovieCatalogModels> moviecatalogList = new ArrayList<>();
         MCM_Wrapper wrapper = new MCM_Wrapper();

          moviecatalogList.add(settingValues("Transformers","Thriller with robots",4));
          moviecatalogList.add(settingValues("Intersteller","Thriller in space",3));
          moviecatalogList.add(settingValues("Dark Knight","Joker vs Batman",5));

          wrapper.setMcmList(moviecatalogList);
          return wrapper;
     }

     public MovieCatalogModels settingValues(String name,String desc,int rating) {
          return new MovieCatalogModels(name,desc,rating); //setting values using parameterized constructor
     }
    @GetMapping("getObjectList")
    public InfoServiceList getMovieCatalogAsObjectList() {

//Here we print the class of InfoServiceList (one object of InfoServiceList contains a List)
// having objects of InfoServiceModel

        return restTemplate.getForObject("http://movie-info-service/info/moviesList", InfoServiceList.class);
    }

     @GetMapping("/mId/{movieId}")
    public InfoServiceModel getMovieCatalog(@PathVariable("movieId") int movieId) {
//accepting object of InfoServiceList whose object contains List of InfoServiceModel objects
         InfoServiceList isl  =  restTemplate.getForObject("http://movie-info-service/info/moviesList", InfoServiceList.class);

         for(int i=0;isl!=null&&i<isl.getList().size();i++) {
             if(movieId==isl.getList().get(i).getMovieId()) //Here we are checking movieId to
                 // isl->List->InfoServiceModel_object_at_ith_location->movieId in that InfoServiceModel
                 return isl.getList().get(i);
         }
         return null;
     }
     
    @GetMapping("/communication/{movieId}")
    public InfoRating getMovieCatalog1(@PathVariable("movieId") int movieId) {

        String str = new String();

        str = restTemplate.getForObject("http://movie-info-service/info/" + movieId,String.class);

        return restTemplate.getForObject("http://movie-rating-service/rate/" + str,InfoRating.class);

    }

}
