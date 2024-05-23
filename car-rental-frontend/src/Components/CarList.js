import React from "react";
import CarItem from "./CarItem";

const CarList = ({ cars, userEmail }) => {
  return (
    <div
      style={{
        display: "flex",
        flexWrap: "wrap",
      }}
    >
      {cars.map((car) => (
        // <p>{car.carId}</p>
        <CarItem car={car} userEmail={userEmail}></CarItem>
      ))}
    </div>
  );
};

export default CarList;
