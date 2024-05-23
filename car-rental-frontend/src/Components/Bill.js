import React from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { Button, Container, Row, Col } from "reactstrap";
import { bookCar } from "../Services/user-service";

const Bill = ({ booking }) => {
  const navigate = useNavigate();
  const handlePayment = () => {
    bookCar(booking)
      .then((res) => {
        toast.success("Payment Successful");
        console.log(res);
      })
      .catch((error) => {
        toast.error(error.response.data);
        navigate(`/user?email=${booking.user.userEmail}`);
      });
    setTimeout(() => {
      console.log("Payment Done");
      navigate(`/user?email=${booking.user.userEmail}`);
    }, 1500);
  };
  return (
    <div>
      <Container>
        <Row className="mt-4 ">
          <Col
            style={{ backgroundColor: "rgba(255, 255, 255, 0.8)" }}
            sm={{ size: 7, offset: 2 }}
          >
            <h1 className="text-center">Billing Details</h1>
            <p></p>
            <div className="text-center">
              <h5>--------- Customer Details ---------</h5>
              <p></p>
            </div>
            <div>
              <Row>
                <Col>
                  <p>
                    <strong>Name:</strong> {booking.user.firstName}{" "}
                    {booking.user.lastName}
                  </p>
                </Col>
                <Col>
                  <p>
                    <strong> Email:</strong> {booking.user.userEmail}
                  </p>
                </Col>
                <Col>
                  <p>
                    <strong>Contact Number:</strong> {booking.user.phoneNumber}
                  </p>
                </Col>
              </Row>
            </div>

            <div>
              <div className="text-center">
                <p></p>
                <h5>--------- Car Details ---------</h5>
                <p></p>
              </div>
              <div>
                <Row>
                  <Col>
                    <p>
                      <strong>Brand:</strong>
                      {booking.car.carBrand}
                      {/* {booking.totalAmount.toFixed(2)} */}
                    </p>
                  </Col>
                  <Col>
                    <p>
                      <strong>Segment:</strong>
                      {booking.car.carSegment}
                    </p>
                  </Col>
                  <Col>
                    <p>
                      <strong>Seater:</strong>
                      {booking.car.carSeater}
                    </p>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <p>
                      <strong>Fuel type:</strong>
                      {booking.car.carFuel}
                    </p>
                  </Col>
                  <Col>
                    <p>
                      <strong>Rate:</strong>
                      {booking.car.dailyRate}/day
                    </p>
                  </Col>
                  <Col></Col>
                </Row>
              </div>

              <div>
                <div className="text-center">
                  <p></p>
                  <h5>--------- Booking Details ---------</h5>
                  <p></p>
                </div>
                <Row>
                  <Col>
                    <p>
                      <strong>Booking Date:</strong> {booking.bookingDate}
                    </p>
                  </Col>
                  <Col>
                    <p>
                      <strong> Booked from:</strong> {booking.bookingStartDate}{" "}
                      <b>to</b> {booking.bookingEndDate}
                    </p>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <p>
                      <strong> Pickup Location:</strong>{" "}
                      {booking.pickupLocation}
                    </p>
                  </Col>
                  <Col>
                    <p>
                      <strong> Return Location:</strong>{" "}
                      {booking.returnLocation}
                    </p>
                  </Col>
                </Row>
              </div>

              <div>
                <div className="text-center">
                  <p></p>
                  <h5>--------- Payment Details ---------</h5>
                  <p></p>
                </div>
                <Row>
                  <Col>
                    <p>
                      <strong>Base Fare:</strong> {booking.baseFare.toFixed(2)}
                    </p>
                  </Col>
                  <Col>
                    <p>
                      <strong> Security Deposit:</strong>
                      {booking.securityDeposit.toFixed(2)}
                    </p>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <p>
                      <strong> Delivery and pickup charge:</strong>
                      {booking.deliveryAndPickupCharge.toFixed(2)}
                    </p>
                  </Col>

                  <Col>
                    <p>
                      <strong> Discount:</strong>
                      {booking.discount.toFixed(2)}
                    </p>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <div className="text-center mt-4 mb-3">
                      <h4> Total amount: {booking.totalAmount.toFixed(2)}</h4>
                    </div>
                  </Col>
                </Row>
              </div>
            </div>
            <div className="text-center mb-3">
              <Button color="primary" onClick={handlePayment}>
                Pay
              </Button>
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Bill;
