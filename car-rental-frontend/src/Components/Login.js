import React, { useState } from "react";
import { userLogin } from "../Services/user-service";
import { adminLogin } from "../Services/admin-service";
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

const Login = () => {
  const navigate = useNavigate();
  const [data, setData] = useState({
    email: "",
    password: "",
  });

  const [role, setRole] = useState("user");

  const handelChange = (event, property) => {
    setData({ ...data, [property]: event.target.value });
  };

  const resetData = () => {
    setData({
      email: "",
      password: "",
    });
  };

  const submitForm = (event) => {
    event.preventDefault();

    console.log(data);
    //api call
    if (role.toLowerCase() === "user") {
      const userLoggedIn = JSON.parse(localStorage.getItem("user"));
      if (userLoggedIn !== null) {
        alert("You are already Logged in");
        navigate(`/user?email=${userLoggedIn.userEmail}`);
      } else {
        userLogin(data)
          .then((response) => {
            localStorage.setItem("user", JSON.stringify(response));

            alert("Login Successful");
            console.log(response);
            navigate(`/user?email=${data.email}`);
          })
          .catch((error) => {
            alert(error.response.data);
            console.log("login error ->", error.response.data);
          });
      }
    } else {
      const userLoggedIn = JSON.parse(localStorage.getItem("admin"));
      if (userLoggedIn !== null) {
        alert("You are already Logged in");
        navigate(`/admin?email=${userLoggedIn.email}`);
      } else {
        adminLogin(data)
          .then((response) => {
            localStorage.setItem("admin", JSON.stringify(response));
            alert("Login Successful");
            console.log(response);
            navigate(`/admin?email=${data.email}`);
          })
          .catch((error) => {
            alert(error.response.data);
            console.log("error ->", error.response.data);
          });
      }
    }
  };
  return (
    <>
      <Container>
        <Row className="mt-4">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card className="shadow">
              <CardHeader className="text-center text-white bg-dark">
                <h2>Login</h2>
              </CardHeader>
              <CardBody>
                <Form onSubmit={submitForm}>
                  {/* Email */}
                  <FormGroup>
                    <Label for="email">Email Address</Label>
                    <Input
                      type="email"
                      placeholder="Enter email address"
                      id="email"
                      onChange={(e) => {
                        handelChange(e, "email");
                      }}
                      value={data.email}
                      required
                    />
                  </FormGroup>
                  {/* password */}
                  <FormGroup>
                    <Label for="password">Enter password</Label>
                    <Input
                      type="password"
                      placeholder="Enter password"
                      id="password"
                      onChange={(e) => {
                        handelChange(e, "password");
                      }}
                      value={data.password}
                      required
                    />
                  </FormGroup>

                  <FormGroup>
                    <Label for="role">Choose role</Label>
                    <Input
                      type="select"
                      id="role"
                      onChange={(e) => {
                        setRole(e.target.value);
                      }}
                      required
                    >
                      <option>User</option>
                      <option>Admin</option>
                    </Input>
                  </FormGroup>

                  <Container className="text-center">
                    <Button color="dark" className="me-3" type="submit">
                      Login
                    </Button>
                    <Button color="secondary" type="reset" onClick={resetData}>
                      Reset
                    </Button>
                    <div className="text-center">
                      Don't have an account?
                      <Link to="/register" action>
                        Register
                      </Link>
                    </div>
                  </Container>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Login;
