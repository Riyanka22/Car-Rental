import React, { useState } from "react";
import {
  Card,
  CardBody,
  CardTitle,
  CardSubtitle,
  CardText,
  Button,
  Row,
} from "reactstrap";
import Booking from "./Booking";
import { useNavigate } from "react-router-dom";

const CarItem = ({ car, userEmail }) => {
  const navigate = useNavigate();
  var path = "/Assets/" + car.carPhotoPath;
  const [isBooked, setIsBooked] = useState(false);
  const handleBooking = () => {
    navigate(`/booking?email=${userEmail}&carId=${car.carId}`);
  };

  return (
    <div>
      <Row className="mt-4 ms-5">
        <Card
          style={{
            width: "15rem",
          }}
        >
          <img alt="Loading..." src={path} />
          <CardBody>
            <CardTitle tag="h5">{car.carBrand}</CardTitle>
            <CardSubtitle className="mb-2 text-muted" tag="h6">
              {car.carSegment}
            </CardSubtitle>
            <CardText>
              {car.carSeater} Seater
              <p> {car.carFuel}</p>
              <h5>{car.dailyRate} / day</h5>
            </CardText>
            <div className="text-center">
              <Button color="warning" onClick={handleBooking}>
                Book
              </Button>
            </div>

            {isBooked && (
              <div>
                <Booking />
              </div>
            )}
          </CardBody>
        </Card>
      </Row>
    </div>
  );
};

export default CarItem;
