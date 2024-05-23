import React, { useEffect, useState } from "react";
import { Table, Button } from "reactstrap";
import { showAllCars } from "../Services/admin-service";
import { toast } from "react-toastify";
import ViewCar from "./ViewCar";
import EditCar from "./EditCar";

const Cars = () => {
  const [cars, setCars] = useState([]);
  const [isViewClicked, setIsViewClicked] = useState(false);
  const [isEditClicked, setIsEditClicked] = useState(false);
  const [carItem, setCarItem] = useState("");

  useEffect(() => {
    loadCars();
  }, [isEditClicked, cars, isViewClicked]);

  const loadCars = () => {
    showAllCars()
      .then((res) => {
        console.log(res);
        setCars(res);
      })
      .catch((error) => {
        toast.error(error.response.data);
      });
  };

  const handleEdit = (car) => {
    setCarItem(car);
    console.log(car);
    setIsEditClicked(true);
    setIsViewClicked(false);
  };

  const handleView = (car) => {
    setCarItem(car);
    setIsViewClicked(true);
    setIsEditClicked(false);
  };

  return (
    <>
      {!isViewClicked && !isEditClicked && (
        <div>
          <div style={{ padding: "20px" }}>
            <Table hover responsive className="text-center">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Car ID</th>
                  <th>Car Brand</th>
                  <th>Daily Rate</th>
                  <th>City</th>
                  <th>Availability Status</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {cars.map((car, index) => (
                  <tr key={car.carId}>
                    <td>
                      <b>{index + 1}</b>
                    </td>
                    <td>{car.carId}</td>
                    <td>{car.carBrand}</td>
                    <td>{car.dailyRate}</td>
                    <td>{car.city}</td>
                    <td>
                      {car.availabilityStatus ? "Available" : "Not Available"}
                    </td>
                    <td>
                      <Button
                        className="mx-2"
                        color="info"
                        onClick={() => handleView(car)}
                      >
                        View
                      </Button>

                      <Button
                        className="mx-2"
                        color="warning"
                        onClick={() => handleEdit(car)}
                      >
                        Edit
                      </Button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </div>
        </div>
      )}
      {isViewClicked && (
        <div className="text-center">
          <ViewCar car={carItem} />
          <div className="mt-2">
            <Button color="danger" onClick={() => setIsViewClicked(false)}>
              Back
            </Button>
          </div>
        </div>
      )}
      {isEditClicked && (
        <div>
          <div
            className="text-center mb-3"
            style={{
              backgroundColor: "rgba(255, 255, 255, 0.5)",

              width: "95%",
            }}
          >
            <EditCar car={carItem} flag={setIsEditClicked}></EditCar>
            <div className="mt-2 mb-3">
              <Button
                className="mt-2 mb-3"
                color="danger"
                onClick={() => {
                  setIsEditClicked(false);
                }}
              >
                Back
              </Button>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default Cars;
