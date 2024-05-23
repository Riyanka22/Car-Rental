import React from "react";

import {
  Card,
  CardBody,
  CardTitle,
  ListGroup,
  ListGroupItem,
} from "reactstrap";
const UserSideBar = ({ userDetails }) => {
  return (
    <div>
      <Card style={{ backgroundColor: "rgba(255, 255, 255, 0.5)" }}>
        <CardBody>
          <CardTitle tag="h4" className="text-center">
            Your Profile
          </CardTitle>
          {/* <CardText>This is some text within a card body.</CardText> */}
        </CardBody>
        <ListGroup flush>
          <ListGroupItem
            style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
          >
            Name : {userDetails.firstName} {userDetails.lastName}
          </ListGroupItem>
          <ListGroupItem
            style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
          >
            DOB : {userDetails.dateOfBirth}
          </ListGroupItem>
          <ListGroupItem
            style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
          >
            Driving License : {userDetails.drivingLicense}
          </ListGroupItem>
          <ListGroupItem
            style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
          >
            Email : {userDetails.userEmail}
          </ListGroupItem>
          <ListGroupItem
            style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
          >
            Phone Number : {userDetails.phoneNumber}
          </ListGroupItem>
          <ListGroupItem
            style={{ backgroundColor: "rgba(255, 255, 255, 0.1)" }}
          >
            Registration Date : {userDetails.registrationDate}
          </ListGroupItem>
        </ListGroup>
      </Card>
    </div>
  );
};

export default UserSideBar;
