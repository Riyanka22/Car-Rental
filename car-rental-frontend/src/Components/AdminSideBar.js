import React from "react";

import {
  Card,
  CardBody,
  CardTitle,
  ListGroup,
  ListGroupItem,
} from "reactstrap";
const AdminSideBar = ({ adminDetails }) => {
  return (
    <div>
      <Card style={{ backgroundColor: "rgba(255, 255, 255, 0.5)" }}>
        <CardBody>
          <CardTitle tag="h4" className="text-center">
            Your Profile
          </CardTitle>
        </CardBody>
        <ListGroup flush>
          <ListGroupItem
            style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
          >
            Name : {adminDetails.firstName} {adminDetails.lastName}
          </ListGroupItem>

          <ListGroupItem
            style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
          >
            Email : {adminDetails.email}
          </ListGroupItem>
          <ListGroupItem
            style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
          >
            Phone Number : {adminDetails.phoneNumber}
          </ListGroupItem>
        </ListGroup>
      </Card>
    </div>
  );
};

export default AdminSideBar;
