import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import {
  Container,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
  Row,
  Col,
} from "reactstrap";
import { editCar } from "../Services/admin-service";

const EditCar = ({ car, flag }) => {
  const [carItem, setCarItem] = useState("");

  const handelChange = (event, property) => {
    setCarItem({ ...carItem, [property]: event.target.value });
  };

  useEffect(() => {
    setCarItem(car);
    console.log("from edit Car out", car);
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();

    editCar(carItem)
      .then((res) => {
        console.log("from eidt Car");
        toast.success(`Car with ID ${carItem.carId} updated successfully`);
        flag(false);
      })
      .catch((err) => {
        toast.error("Failed");
      });
    console.log("Submitted from edit");
  };

  return (
    // <>{JSON.stringify(car)}</>
    <div>
      <Container>
        <Row className="mt-2">
          <Col
            style={{
              backgroundColor: "rgba(255, 255, 255, 0)",
              // padding: "1rem",
            }}
            sm={{ size: 6, offset: 3 }}
          >
            <div className="text-center">
              <h3>Update Car Details</h3>
            </div>
            <Form onSubmit={handleSubmit}>
              <FormGroup>
                <Label for="carId">
                  <b>Car ID</b>
                </Label>
                <Input
                  type="number"
                  name="carId"
                  id="carId"
                  placeholder="Enter car Id"
                  value={car.carId}
                  disabled
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="carBrand">
                  <b>Car Brand</b>
                </Label>
                <Input
                  type="text"
                  name="carBrand"
                  id="carBrand"
                  placeholder="Enter car brand"
                  value={carItem.carBrand}
                  onChange={(e) => {
                    handelChange(e, "carBrand");
                  }}
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="carSeater">
                  <b>Car Seater</b>
                </Label>
                <Input
                  type="number"
                  name="carSeater"
                  id="carSeater"
                  placeholder="Enter number of seats"
                  value={carItem.carSeater}
                  onChange={(e) => {
                    handelChange(e, "carSeater");
                  }}
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="carSegment">
                  <b>Car Segment</b>
                </Label>
                <Input
                  type="text"
                  name="carSegment"
                  id="carSegment"
                  placeholder="Enter car segment"
                  value={carItem.carSegment}
                  onChange={(e) => {
                    handelChange(e, "carSegment");
                  }}
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="carFuel">
                  <b>Car Fuel</b>
                </Label>
                <Input
                  type="text"
                  name="carFuel"
                  id="carFuel"
                  placeholder="Enter fuel type"
                  value={carItem.carFuel}
                  onChange={(e) => {
                    handelChange(e, "carFuel");
                  }}
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="city">
                  <b>City</b>
                </Label>
                <Input
                  type="text"
                  name="city"
                  id="city"
                  placeholder="Enter city"
                  value={carItem.city}
                  onChange={(e) => {
                    handelChange(e, "city");
                  }}
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="dailyRate">
                  <b>Daily Rate</b>
                </Label>
                <Input
                  type="number"
                  name="dailyRate"
                  id="dailyRate"
                  placeholder="Enter daily rate"
                  value={carItem.dailyRate}
                  onChange={(e) => {
                    handelChange(e, "dailyRate");
                  }}
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="availabilityStatus">
                  <b>Availability Status</b>
                </Label>
                <Input
                  type="select"
                  name="availabilityStatus"
                  id="availabilityStatus"
                  placeholder="Choose"
                  value={carItem.availabilityStatus}
                  onChange={(e) => {
                    handelChange(e, "availabilityStatus");
                  }}
                  required
                >
                  <option value={true}>Yes</option>
                  <option value={false}>No</option>
                </Input>
              </FormGroup>

              <div className="text-center">
                <Button type="submit" color="warning" className="mx-3 mb-3">
                  Update
                </Button>
              </div>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default EditCar;
