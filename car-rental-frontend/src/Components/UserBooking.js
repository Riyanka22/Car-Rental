import React from "react";
import { Table } from "reactstrap";

const UserBookings = ({ allBookings }) => {
  // var index = 0;
  return (
    <div style={{ padding: "20px" }}>
      <div>
        <Table hover responsive>
          <thead>
            <tr>
              <th>#</th>
              <th>User Email</th>
              <th>Booking ID</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Car Brand</th>
              <th>Car Segment</th>
              <th>Pickup Location</th>
              <th>Return Location</th>
              <th>Discount</th>
              <th>Total Amount</th>
            </tr>
          </thead>
          <tbody>
            {allBookings.map((booking, index) => (
              <tr key={booking.bookingId}>
                <td>
                  <b>{index + 1}</b>
                </td>
                <td>{booking.user.userEmail}</td>
                <td>{booking.bookingId}</td>
                <td>{booking.bookingStartDate}</td>
                <td>{booking.bookingEndDate}</td>
                <td>{booking.car.carBrand}</td>
                <td>{booking.car.carSegment}</td>
                <td>{booking.pickupLocation}</td>
                <td>{booking.returnLocation}</td>
                <td>{booking.discount}</td>
                <td>₹{booking.totalAmount}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    </div>
  );
};

export default UserBookings;
