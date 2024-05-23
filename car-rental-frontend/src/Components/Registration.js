import React, { useState } from "react";
import { Bounce, ToastContainer, toast } from "react-toastify";
import { register } from "../Services/user-service";
import {
  Button,
  Form,
  FormGroup,
  Label,
  Input,
  Card,
  Container,
  CardHeader,
  CardBody,
  Row,
  Col,
} from "reactstrap";
import { Link, useNavigate } from "react-router-dom";
const Registration = () => {
  const navigate = useNavigate();

  const [data, setData] = useState({
    firstName: "",
    lastName: "",
    userEmail: "",
    drivingLicense: "",
    dateOfBirth: "",
    phoneNumber: "",
    password: "",
  });

  const handelChange = (event, property) => {
    setData({ ...data, [property]: event.target.value });
  };

  const resetData = () => {
    setData({
      firstName: "",
      lastName: "",
      userEmail: "",
      drivingLicense: "",
      dateOfBirth: "",
      phoneNumber: "",
      password: "",
    });
  };

  const submitForm = (event) => {
    event.preventDefault();

    console.log(data);

    //validation

    //api call
    const userLoggedIn = JSON.parse(localStorage.getItem("user"));
    if (userLoggedIn !== null) {
      // toast.info("You are already Logged in", {
      //   theme: "colored",
      //   transition: Bounce,
      // });
      alert("You are already Logged in");
      setTimeout(() => {
        navigate(`/user?email=${userLoggedIn.userEmail}`);
      }, 1500);
    } else {
      register(data)
        .then((response) => {
          // toast.success("Registration Successful", {
          //   position: "top-right",
          //   autoClose: 3000,
          //   hideProgressBar: false,
          //   closeOnClick: true,
          //   pauseOnHover: true,
          //   draggable: true,
          //   progress: undefined,
          //   theme: "colored",
          //   transition: Bounce,
          // });
          alert("Registration Successful");
          console.log(response);
          localStorage.setItem("user", JSON.stringify(response));
          setTimeout(() => {
            navigate(`/user?email=${data.userEmail}`);
          }, 2000);
        })
        .catch((error) => {
          // toast.error(error.response.data);
          alert(error.response.data);
          console.log("error ->", error.response.data);
        });
    }
  };

  return (
    <div>
      <ToastContainer />
      <Container>
        <Row className="mt-4">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card className="shadow">
              <CardHeader className="text-center text-white bg-dark">
                <h2>Registration</h2>
              </CardHeader>
              <CardBody>
                <Form onSubmit={submitForm}>
                  {/* first name */}
                  <FormGroup>
                    <Label for="firstName">Enter first name</Label>
                    <Input
                      type="text"
                      placeholder="Enter your first name"
                      id="firstName"
                      onChange={(e) => handelChange(e, "firstName")}
                      value={data.firstName}
                    />
                  </FormGroup>
                  {/* last name */}
                  <FormGroup>
                    <Label for="lastName">Enter last name</Label>
                    <Input
                      type="text"
                      placeholder="Enter your last name"
                      id="lastName"
                      onChange={(e) => handelChange(e, "lastName")}
                      value={data.lastName}
                    />
                  </FormGroup>
                  {/* email */}
                  <FormGroup>
                    <Label for="userEmail">Email Address</Label>
                    <Input
                      type="email"
                      placeholder="Enter email address"
                      id="userEmail"
                      onChange={(e) => handelChange(e, "userEmail")}
                      value={data.email}
                      required
                    />
                  </FormGroup>
                  {/* phone no */}
                  <FormGroup>
                    <Label for="phoneNumber">Enter phone number</Label>
                    <Input
                      type="text"
                      placeholder="Enter phone number"
                      id="phoneNumber"
                      onChange={(e) => handelChange(e, "phoneNumber")}
                      value={data.phoneNumber}
                    />
                  </FormGroup>
                  {/* deiving license */}
                  <FormGroup>
                    <Label for="drivingLicense">Enter driving license</Label>
                    <Input
                      type="text"
                      placeholder="Enter driving license"
                      id="drivingLicense"
                      onChange={(e) => handelChange(e, "drivingLicense")}
                      value={data.drivingLicense}
                    />
                  </FormGroup>

                  {/* date of birth */}
                  <FormGroup>
                    <Label for="dateOfBirth">Enter date of birth</Label>
                    <Input
                      type="date"
                      placeholder="Enter date of birth"
                      id="dateOfBirth"
                      onChange={(e) => handelChange(e, "dateOfBirth")}
                      value={data.dateOfBirth}
                    />
                  </FormGroup>

                  {/* password */}
                  <FormGroup>
                    <Label for="password">Enter password</Label>
                    <Input
                      type="password"
                      placeholder="Enter password"
                      id="password"
                      onChange={(e) => handelChange(e, "password")}
                      value={data.password}
                      required
                    />
                  </FormGroup>

                  <Container className="text-center">
                    <Button className="me-3" color="dark" type="submit">
                      Register
                    </Button>
                    <Button color="secondary" type="reset" onClick={resetData}>
                      Reset
                    </Button>
                    <div className="text-center">
                      Already have an account?
                      <Link to="/login" action>
                        Login
                      </Link>
                    </div>
                  </Container>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Registration;
