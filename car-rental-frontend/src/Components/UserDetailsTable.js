import React from "react";
import { Table } from "reactstrap";

const UserDetailsTable = ({ allUsers }) => {
  // var index = 0;
  return (
    <div style={{ padding: "20px" }}>
      <div>
        <Table hover responsive>
          <thead>
            <tr>
              <th>#</th>
              <th>User Name</th>
              <th>DOB</th>
              <th>User Email</th>
              <th>Contact Number</th>
              <th>Driving License</th>
              <th>Registration Date</th>
            </tr>
          </thead>
          <tbody>
            {allUsers.map((user, index) => (
              <tr key={user.userEmail}>
                <td>
                  <b>{index + 1}</b>
                </td>
                <td>
                  {user.firstName} {user.lastName}
                </td>
                <td>{user.dateOfBirth}</td>
                <td>{user.userEmail}</td>
                <td>{user.phoneNumber}</td>
                <td>{user.drivingLicense}</td>
                <td>{user.registrationDate}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    </div>
  );
};

export default UserDetailsTable;
