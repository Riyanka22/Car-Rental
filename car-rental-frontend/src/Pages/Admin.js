import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { Row, Col, Button } from "reactstrap";
import AdminSideBar from "../Components/AdminSideBar";
import {
  getAdmin,
  showAllBookings,
  showAllUsers,
} from "../Services/admin-service";
import UserBooking from "../Components/UserBooking";
import UserDetailsTable from "../Components/UserDetailsTable";
import Cars from "../Components/Cars";
import AddCar from "../Components/AddCar";

const Admin = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const email = searchParams.get("email");

  const [adminDetails, setAdminDetails] = useState({
    firstName: "",
    lastName: "",
    password: "",
    phoneNumber: "",
    email: "",
  });

  const [showAllBooking, setShowAllBooking] = useState(false);
  const [bookingArray, setBookingArray] = useState([]);
  const [showAllUser, setShowAllUser] = useState(false);
  const [userArray, setUserArray] = useState([]);
  const [showCars, setShowCars] = useState(false);
  const [addCar, setAddCar] = useState(false);

  useEffect(() => {
    const userLoggedIn = JSON.parse(localStorage.getItem("admin"));
    if (userLoggedIn === null) {
      navigate("/");
    } else if (userLoggedIn.email !== email) {
      toast.error("You are already Logged in");
      navigate(`/admin?email=${userLoggedIn.email}`);
    }

    if (email !== "") {
      getAdmin(email)
        .then((res) => {
          console.log(res);
          setAdminDetails(res);
        })
        .catch((error) => {
          toast.error(error.response.data);
          navigate("/login");
        });
    }
  }, [email, navigate]);

  const handelShowAllBooking = () => {
    showAllBookings()
      .then((res) => {
        setBookingArray(res);
        console.log(res);
      })
      .catch((error) => {
        toast.error("Failed");
      });
  };
  const handelShowAllUser = () => {
    showAllUsers()
      .then((res) => {
        setUserArray(res);
        console.log(res);
      })
      .catch((error) => {
        toast.error("Failed");
      });
  };
  const handleHome = () => {
    setShowAllBooking(false);
    setShowAllUser(false);
    navigate(`/admin?email=${email}`);
  };

  // here--------------------------------------------------------->
  const handelCars = () => {};

  const handelAddCar = () => {};
  // here--------------------------------------------------------->
  const handleLogout = () => {
    toast.success("Logout successfully");
    localStorage.removeItem("admin");
    navigate("/");
  };
  return (
    <div>
      <Row>
        <Col md={3}>
          <Row>
            <AdminSideBar adminDetails={adminDetails}></AdminSideBar>
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
                setShowAllUser(false);
                setShowCars(false);
                setAddCar(false);
                handelShowAllBooking();
              }}
            >
              Show Bookings
            </Button>
          </Row>
          <Row>
            <Button
              onClick={(e) => {
                setShowAllBooking(false);
                setShowAllUser(!showAllUser);
                setShowCars(false);
                setAddCar(false);
                handelShowAllUser();
              }}
            >
              Show Users
            </Button>
          </Row>
          <Row>
            <Button
              onClick={(e) => {
                setShowAllBooking(false);
                setShowAllUser(false);
                setShowCars(false);
                setAddCar(!addCar);
                handelAddCar();
              }}
            >
              Add Car
            </Button>
          </Row>
          <Row>
            <Button
              onClick={(e) => {
                setShowAllBooking(false);
                setShowAllUser(false);
                setAddCar(false);
                setShowCars(!showCars);
                handelCars();
              }}
            >
              Show Cars
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
          {showAllBooking === false &&
            showAllUser === false &&
            showCars === false &&
            addCar === false && (
              <>
                <div>
                  {/* 1st row */}
                  <Row className="text-light">
                    <h2>Welcome {adminDetails.firstName} ,</h2>
                  </Row>
                  {/* 2nd row */}

                  <Row>
                    <div>
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
                        <h3>You are in Admin Page</h3>
                      </div>
                    </div>
                  </Row>
                </div>
              </>
            )}

          {showAllBooking && (
            <UserBooking allBookings={bookingArray}></UserBooking>
          )}
          {showAllUser && (
            <UserDetailsTable allUsers={userArray}></UserDetailsTable>
          )}
          {addCar && <AddCar></AddCar>}
          {showCars && <Cars></Cars>}
        </Col>
      </Row>
    </div>
  );
};

export default Admin;
