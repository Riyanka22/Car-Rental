import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import {
  getUser,
  showAllBookingsOfUser,
  showCars,
} from "../Services/user-service";
import { Bounce, toast } from "react-toastify";
import { Row, Col, Input, Label, Container, Button } from "reactstrap";

import UserSideBar from "../Components/UserSideBar";

import CarList from "../Components/CarList";

import UserBooking from "../Components/UserBooking";

const User = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const email = searchParams.get("email");
  const [userDetails, setUserDetails] = useState({
    dateOfBirth: "",
    drivingLicense: "",
    firstName: "",
    lastName: "",
    password: "",
    phoneNumber: "",
    registrationDate: "",
    userEmail: "",
  });

  const [city, setCity] = useState("Select");
  const [cars, setCars] = useState([]);
  useEffect(() => {
    const userLoggedIn = JSON.parse(localStorage.getItem("user"));
    if (userLoggedIn === null) {
      navigate("/");
    } else if (userLoggedIn.userEmail !== email) {
      // toast.error("You are already Logged in");
      navigate(`/user?email=${userLoggedIn.userEmail}`);
    }
    if (email !== "") {
      getUser(email)
        .then((res) => {
          console.log(res);
          setUserDetails(res);
        })
        .catch((error) => {
          toast.error(error.response.data);
          navigate("/login");
        });
    }
  }, [email, navigate]);

  const showAllCars = (event) => {
    event.preventDefault();
    if (city === "Select") {
      toast.error("Please select a city", {
        theme: "colored",
        transition: Bounce,
      });
    } else {
      showCars(city)
        .then((response) => {
          console.log("res is -------------->", JSON.stringify(response));
          setCars(response);
          console.log("details****************-->", cars);
        })
        .catch((error) => {
          toast.error("Failed");
        });
    }
  };
  const [isSearchedBefore, setIsSearchedBefore] = useState(false);

  const [showAllBooking, setShowAllBooking] = useState(false);
  const [bookingArray, setBookingArray] = useState([]);
  const handelShowAllBooking = () => {
    showAllBookingsOfUser(email)
      .then((res) => {
        setBookingArray(res);
        console.log(res);
      })
      .catch((error) => {
        toast.error("Failed");
      });
  };

  const handleLogout = () => {
    toast.success("Logout successfully");
    localStorage.removeItem("user");
    navigate("/");
  };

  const handleHome = () => {
    // toast.success("Logout successfully");
    setCity("Select");
    setShowAllBooking(false);
    navigate(`/user?email=${email}`);
  };

  return (
    <div>
      <Row>
        <Col md={3}>
          <Row>
            <UserSideBar userDetails={userDetails}></UserSideBar>
          </Row>
          <Row>
            <Button
              onClick={(e) => {
                handleHome();
              }}
            >
              Home
            </Button>
          </Row>
          <Row>
            <Button
              onClick={(e) => {
                setShowAllBooking(!showAllBooking);
                handelShowAllBooking();
              }}
            >
              Show Bookings
            </Button>
          </Row>
          <Row>
            <Button
              onClick={(e) => {
                handleLogout();
              }}
            >
              Logout
            </Button>
          </Row>
        </Col>

        <Col md={9} className="mt-3">
          {showAllBooking === false ? (
            <>
              <div>
                {/* 1st row */}
                <Row className="text-light">
                  <h2>Welcome {userDetails.firstName} ,</h2>
                </Row>
                {/* 2nd row */}
                <Row className="mt-4">
                  <Col md={6}>
                    <Container>
                      <Label for="city">
                        <h5 className="text-light">Choose City</h5>
                      </Label>
                      <Input
                        type="select"
                        id="city"
                        onChange={(e) => {
                          setCity(e.target.value);
                        }}
                      >
                        <option>Select</option>
                        <option>Kolkata</option>
                        <option>Chennai</option>
                        <option>Delhi</option>
                        <option>Pune</option>
                        <option>Mumbai</option>
                        <option>Jaipur</option>
                        <option>Hyderabad</option>
                        <option>Bengaluru</option>
                        <option>Coimbatore</option>
                      </Input>
                    </Container>
                    <Container className="text-center mt-3">
                      <Button
                        onClick={(e) => {
                          showAllCars(e);
                          setIsSearchedBefore(true);
                        }}
                      >
                        Search
                      </Button>
                    </Container>
                  </Col>
                </Row>
                {/* 3rd row */}
                <Row>
                  <div>
                    {cars.length > 0 ? (
                      <CarList cars={cars} userEmail={userDetails.userEmail} />
                    ) : (
                      <div
                        className="mt-5"
                        style={{
                          height: "5rem",

                          border: ".1rem solid #FFFF",
                          padding: "20px",
                          margin: "10%",
                          textAlign: "center",
                          borderRadius: "10px",
                          backgroundColor: "rgba(200, 245, 200, 0.7)",
                          // color: "SlateBlue",
                        }}
                      >
                        {city === "Select" ? (
                          <h3>Book your car NOW</h3>
                        ) : isSearchedBefore ? (
                          <h3> No car available</h3>
                        ) : (
                          <h3>Book your car NOW</h3>
                        )}
                      </div>
                    )}
                  </div>
                </Row>
              </div>
            </>
          ) : (
            <UserBooking allBookings={bookingArray}></UserBooking>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default User;
