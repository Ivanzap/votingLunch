package ru.javaOps.votingLunch.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaOps.votingLunch.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId order by m.dateTime")
    List<Meal> getAllMenuOfRestaurant(@Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId AND m.dateTime>=:toDay order by m.dateTime")
    List<Meal> getMenuTodayOfRestaurant(@Param("restaurantId") int restaurantId, @Param("toDay") LocalDateTime toDay);
}
