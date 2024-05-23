package com.myProject.car_rent.service;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myProject.car_rent.entity.Car;
import com.myProject.car_rent.exception.CarAreadyPresentException;
import com.myProject.car_rent.exception.CarNotFoundException;
import com.myProject.car_rent.exception.InvalidDataFormatException;
import com.myProject.car_rent.exception.NullValueEnteredException;
import com.myProject.car_rent.repository.CarRepository;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

	@InjectMocks
	private CarServiceImpl carService;

	@Mock
	private CarRepository carRepository;

	private Car car;
	private List<Car> cars = new ArrayList<>();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);// initialize @Mock

		car = new Car(1, null, "SUV", "Ford", 4, "petrol", "Kolkata", 200.0, true);
	}

	@Test
	public void showAllCarsForAdmin() {

		cars.add(car);
		when(carRepository.findAll()).thenReturn(cars);

		List<Car> result = carService.showAllCarsForAdmin();

		assertEquals(cars, result);
	}

	@Test
	public void addCar_AlreadyPresent() {

		when(carRepository.findById(1)).thenReturn(Optional.of(car));

		assertThrows(CarAreadyPresentException.class, () -> carService.addCar(car));

		verify(carRepository, never()).save(car);// verify save() method with argument car is never called
	}

	@Test
	public void testAddCar_NullValueEnteredException() {
		car.setCarSegment(null);

		assertThrows(NullValueEnteredException.class, () -> carService.addCar(car));

		verify(carRepository, never()).save(car);
	}

	@Test
	public void testAddCar_InvalidDataFormatException() {
		car.setCarSeater(3);

		assertThrows(InvalidDataFormatException.class, () -> carService.addCar(car));

		verify(carRepository, never()).save(car);
	}

	@Test
	public void testAddCar_Success() throws Exception {
		when(carRepository.findById(1)).thenReturn(Optional.empty());

		Car result = carService.addCar(car);

		assertEquals(car, result);
		verify(carRepository, times(1)).save(car);
	}

	@Test
	public void testDeleteCar_CarNotFoundException() {
		when(carRepository.findById(2)).thenReturn(Optional.empty());

		assertThrows(CarNotFoundException.class, () -> carService.deleteCar(2));

		verify(carRepository, never()).deleteById(2);
	}

	@Test
	public void testDeleteCar_Success() throws Exception {
		when(carRepository.findById(1)).thenReturn(Optional.of(car));

		String result = carService.deleteCar(1);

		assertEquals("Deleted successfully !!!", result);
		verify(carRepository, times(1)).deleteById(1);
	}

	@Test
	public void testEditCar_CarNotFoundException() {
		when(carRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(CarNotFoundException.class, () -> carService.editCar(car));

		verify(carRepository, never()).save(car);
	}

	@Test
	public void testEditCar_Success() throws CarNotFoundException {
		when(carRepository.findById(car.getCarId())).thenReturn(Optional.of(car));

		Car result = carService.editCar(car);

		assertThat(result).isEqualTo(car);
		verify(carRepository, times(1)).save(car);
	}

	@Test
	public void testShowAllCars() {
		cars.add(car);
		when(carRepository.findByCityAndAvailabilityStatusTrue("Kolkata")).thenReturn(cars);

		List<Car> result = carService.showAllCars("Kolkata");

		assertThat(result).isEqualTo(cars);
		verify(carRepository, times(1)).findByCityAndAvailabilityStatusTrue("Kolkata");
	}

	@Test
	public void testSearchByCity() {
		when(carRepository.findByCityAndAvailabilityStatusTrue("city")).thenReturn(cars);

		List<Car> result = carService.searchByCity("city");

		assertThat(result).isEqualTo(cars);
		verify(carRepository, times(1)).findByCityAndAvailabilityStatusTrue("city");
	}

	@Test
	public void testSearchBySeater() {
		when(carRepository.findByCityAndAvailabilityStatusTrue("city")).thenReturn(cars);

		List<Car> result = carService.searchBySeater("city", 4);

		assertEquals(0, result.size());

	}

	@Test
	public void testSearchByBrand() {

		when(carRepository.findByCityAndAvailabilityStatusTrue("city")).thenReturn(cars);

		List<Car> result = carService.searchByBrand("city", "brand");

		assertThat(result.size()).isEqualTo(0);
		verify(carRepository, times(1)).findByCityAndAvailabilityStatusTrue("city");
	}

	@Test
	public void testSearchBySegment() {
		when(carRepository.findByCityAndAvailabilityStatusTrue("city")).thenReturn(cars);

		List<Car> result = carService.searchBySegment("city", "segment");

		assertThat(result.size()).isEqualTo(0);
		verify(carRepository, times(1)).findByCityAndAvailabilityStatusTrue("city");
	}

	@Test
	public void testSearchByFuel() {
		when(carRepository.findByCityAndAvailabilityStatusTrue("city")).thenReturn(cars);

		List<Car> result = carService.searchByFuel("city", "fuel");

		assertThat(result.size()).isEqualTo(0);
		verify(carRepository, times(1)).findByCityAndAvailabilityStatusTrue("city");
	}
}
