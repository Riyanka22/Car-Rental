import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import {
  Card,
  CardImg,
  CardBody,
  CardTitle,
  CardText,
  Row,
  Col,
} from "reactstrap";

const ViewCar = ({ car }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const email = searchParams.get("email");

  useEffect(() => {
    const userLoggedIn = JSON.parse(localStorage.getItem("admin"));
    if (userLoggedIn === null) {
      toast.error("Please Login");
      navigate("/");
    } else if (userLoggedIn.email !== email) {
      toast.error("Alreary Logged in");
      navigate(`/admin?email=${userLoggedIn.email}`);
    }
  }, []);

  var path = "/Assets/" + car.carPhotoPath;
  return (
    <>
      <Row className="mt-4 ms-5">
        <Col sm={{ size: 6, offset: 3 }}>
          <Card
            style={{
              width: "25rem",
            }}
          >
            <CardImg top width="100%" src={path} alt="Car image" />
            <CardBody>
              <CardTitle tag="h5">{car.carBrand}</CardTitle>
              <CardText>
                <div>Segment: {car.carSegment}</div>
                <div>Seater: {car.carSeater}</div>
                <div>Fuel: {car.carFuel}</div>
                <div>City: {car.city}</div>
                <div>Daily Rate: {car.dailyRate}</div>
                <div>
                  Availability Status:{" "}
                  {car.availabilityStatus ? "Available" : "Not Available"}
                </div>
              </CardText>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </>
  );
};

export default ViewCar;
