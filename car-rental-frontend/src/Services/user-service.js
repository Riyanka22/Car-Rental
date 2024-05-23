import { myAxios } from "./Helper";

export const userLogin = (user) => {
  return myAxios
    .post("/api/user/login", user)
    .then((response) => response.data);
};

export const register = (user) => {
  return myAxios
    .post("/api/user/register", user)
    .then((response) => response.data);
};

export const getUser = (email) => {
  return myAxios.get(`/api/user/${email}`).then((response) => response.data);
};

export const getCar = (carId) => {
  return myAxios
    .get(`/api/user/getCar/${carId}`)
    .then((response) => response.data);
};

export const showCars = (city) => {
  return myAxios
    .get(`/api/user/showAllCars/${city}`)
    .then((response) => response.data);
};

export const generateBill = (booking) => {
  return myAxios
    .post(`/api/user/generateBill`, booking)
    .then((response) => response.data);
};
export const bookCar = (booking) => {
  return myAxios
    .post(`/api/user/bookCar`, booking)
    .then((response) => response.data);
};

export const showAllBookingsOfUser = (email) => {
  return myAxios
    .get(`/api/user/viewBookingDetails/${email}`)
    .then((response) => response.data);
};
