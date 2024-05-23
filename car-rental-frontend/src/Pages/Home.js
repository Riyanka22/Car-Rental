import React from "react";
import { Container, Row, Col, Button } from "reactstrap";

const Home = () => {
  return (
    <>
      <section style={{ background: "transparent" }}>
        <Container>
          <Row className="justify-content-center mt-5">
            <Col sm={6}>
              <div
                style={{
                  padding: "20px",
                  textAlign: "center",
                  borderRadius: "10px",
                  color: "Indigo",
                }}
              >
                <h1>
                  <strong>Car Rental</strong>
                </h1>
              </div>
              <div
                className="text-center "
                style={{ margin: "1rem", color: "yellow" }}
              >
                <h4> Keys to Freedom </h4>{" "}
                <h4> Rent a Ride, Redefine the Road</h4>
              </div>
              <div
                style={{
                  border: ".1rem solid #000000",
                  padding: "20px",
                  textAlign: "center",
                  borderRadius: "10px",
                  backgroundColor: "rgba(0, 0, 0, 0.6)",
                  color: "#FFFF",
                }}
              >
                <p>
                  Drive More, Save More
                  <h6 style={{ color: "pink" }}>
                    Enjoy Exclusive Discounts on Your First 5 Bookings !
                  </h6>
                </p>
                <Button color="warning" href="\login">
                  Get Start
                </Button>
              </div>
            </Col>
          </Row>
        </Container>
      </section>
    </>
  );
};

export default Home;
