import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import {
  Container,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
  Row,
  Col,
} from "reactstrap";
import { generateBill, getCar, getUser } from "../Services/user-service";
import { toast } from "react-toastify";
import Bill from "./Bill";

const Booking = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const email = searchParams.get("email");
  const carId = searchParams.get("carId");

  const [booking, setBooking] = useState(null);

  const [bookingStartDate, setBookingStartDate] = useState("");
  const [bookingEndDate, setBookingEndDate] = useState("");
  const [pickupLocation, setPickupLocation] = useState("");
  const [returnLocation, setReturnLocation] = useState("");

  const [user, setUser] = useState(null);
  const [car, setCar] = useState(null);

  useEffect(() => {
    const userLoggedIn = JSON.parse(localStorage.getItem("user"));
    if (userLoggedIn === null) {
      toast.error("Please Login");
      navigate("/");
    } else if (userLoggedIn.userEmail !== email) {
      toast.error("Alreary Logged in");
      navigate(`/user?email=${userLoggedIn.userEmail}`);
    }
    myUser();
    myCar();
  }, []);

  const myUser = () => {
    console.log("Hi i am user");
    getUser(email)
      .then((res) => {
        console.log(res);
        setUser(res);
      })
      .catch((error) => {
        toast.error(error.response.data);
        console.log(error.response.data);
        // navigate("/login");
      });
    console.log("After Booking", booking);
  };
  const myCar = () => {
    console.log("CAR");
    getCar(carId)
      .then((res) => {
        console.log("car is here", res);
        setCar(res);
      })
      .catch((error) => {
        toast.error(error.response.data);
        // navigate("/login");
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // console.log("My Booking------------->", booking);

    generateBill({
      bookingStartDate: bookingStartDate,
      bookingEndDate: bookingEndDate,
      pickupLocation: pickupLocation,
      returnLocation: returnLocation,
      user: user,
      car: car,
    })
      .then((res) => {
        setBooking(res);
        console.log("resssss", res);
        setIsSubmitted(true);
      })
      .catch((error) => {
        toast.error(error.response.data);
        // navigate("/login");
      });
  };
  const [isSubmited, setIsSubmitted] = useState(false);

  return (
    <div>
      {isSubmited ? (
        <Bill booking={booking}></Bill>
      ) : (
        <Container>
          <Row className="mt-4">
            <Col
              style={{
                backgroundColor: "rgba(255, 255, 255, 0.5)",
                padding: "2rem",
              }}
              sm={{ size: 7, offset: 2 }}
            >
              <Form onSubmit={handleSubmit}>
                <FormGroup>
                  <Label for="bookingStartDate">
                    <b>Start Date</b>
                  </Label>
                  <Input
                    type="date"
                    name="bookingStartDate"
                    id="bookingStartDate"
                    value={bookingStartDate}
                    onChange={(e) => {
                      setBookingStartDate(e.target.value);
                    }}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="bookingEndDate">
                    <b>End Date</b>
                  </Label>
                  <Input
                    type="date"
                    name="bookingEndDate"
                    id="bookingEndDate"
                    value={bookingEndDate}
                    onChange={(e) => {
                      setBookingEndDate(e.target.value);
                    }}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="pickupLocation">
                    <b>Pickup Location</b>
                  </Label>
                  <Input
                    type="text"
                    name="pickupLocation"
                    id="pickupLocation"
                    placeholder="Enter pickup location"
                    value={pickupLocation}
                    onChange={(e) => {
                      setPickupLocation(e.target.value);
                    }}
                  />
                </FormGroup>
                <FormGroup>
                  <Label for="returnLocation">
                    <b>Return Location</b>
                  </Label>
                  <Input
                    type="text"
                    name="returnLocation"
                    id="returnLocation"
                    placeholder="Enter return location"
                    value={returnLocation}
                    onChange={(e) => {
                      setReturnLocation(e.target.value);
                    }}
                  />
                </FormGroup>
                <div className="text-center">
                  <Button type="submit" color="warning">
                    Submit
                  </Button>
                </div>
              </Form>
            </Col>
          </Row>
        </Container>
      )}
    </div>
  );
};

export default Booking;
