import { myAxios } from "./Helper";

export const adminLogin = (userData) => {
  return myAxios
    .post("/api/admin/login", userData)
    .then((response) => response.data);
};
export const getAdmin = (email) => {
  return myAxios.get(`/api/admin/${email}`).then((response) => response.data);
};

export const showAllBookings = () => {
  return myAxios
    .get(`/api/admin/showAllBookings`)
    .then((response) => response.data);
};
export const showAllUsers = () => {
  return myAxios
    .get(`/api/admin/showAllUsers`)
    .then((response) => response.data);
};

export const showAllCars = () => {
  return myAxios
    .get(`/api/admin/showAllCars`)
    .then((response) => response.data);
};

export const addCar = (file, carData) => {
  // var data =
  //   '{   "carId": 888 ,  "carSegment": "Sedan",   "carBrand": "Suzuki",   "carSeater": 4,   "carFuel": "Petrol",   "city": "Kolkata",   "dailyRate": 1500,   "availabilityStatus": true}';

  const carId = Number(carData.carId);
  const carBrand = '"' + carData.carBrand + '"';
  const carSegment = '"' + carData.carSegment + '"';
  const carSeater = Number(carData.carSeater);
  const carFuel = '"' + carData.carFuel + '"';
  const city = '"' + carData.city + '"';
  const dailyRate = Number(carData.dailyRate);
  const availabilityStatus =
    carData.availabilityStatus === "1" ? "true" : "false";

  const data = `{   "carId": ${carId} ,  "carSegment": ${carSegment},  "carBrand": ${carBrand},   "carSeater": ${carSeater},   "carFuel": ${carFuel},   "city": ${city},   "dailyRate": ${dailyRate},   "availabilityStatus": ${availabilityStatus}}`;
  const formdata = new FormData();
  formdata.append("file", file);

  formdata.append("carData", data);

  console.log(JSON.stringify(formdata));

  return myAxios
    .post(`/api/admin/addCar`, formdata, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then((response) => response.data);
};

export const getCar = (carId) => {
  return myAxios
    .get(`/api/admin/getCar/${carId}`)
    .then((response) => response.data);
};

export const editCar = (car) => {
  return myAxios
    .put(`/api/admin/editCar`, car)
    .then((response) => response.data);
};
