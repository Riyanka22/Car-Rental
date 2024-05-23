import React, { useState } from "react";
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
import { addCar } from "../Services/admin-service";
const AddCar = () => {
  const [carId, setCarId] = useState(null);
  const [carBrand, setCarBrand] = useState("");
  const [carSegment, setCarSegment] = useState("");
  const [carSeater, setCarSeater] = useState(null);
  const [carFuel, setCarFuel] = useState("");
  const [city, setCity] = useState("");
  const [dailyRate, setDailyRate] = useState(null);
  const [availabilityStatus, setAvailabilityStatus] = useState("1");
  const [file, setFile] = useState(null);

  // const handelChange = (event, property) => {
  //   setCarData({ ...carData, [property]: event.target.value });
  // };
  const handleSubmit = (event) => {
    event.preventDefault();

    addCar(file, {
      carId: carId,
      carBrand: carBrand,
      carSegment: carSegment,
      carSeater: carSeater,
      carFuel: carFuel,
      city: city,
      dailyRate: dailyRate,
      availabilityStatus: availabilityStatus,
    })
      .then((res) => {
        console.log(res);
        setCarId("");
        setCarBrand("");
        setCarSegment("");
        setCarSeater("");
        setCarFuel("");
        setCity("");
        setDailyRate("");
        setAvailabilityStatus("1");
        setFile("");
        toast.success("Car added successfully");
      })
      .catch((error) => {
        toast.error(error.response.data);
      });
  };

  return (
    <div>
      <Container>
        <Row className="mt-4">
          <Col
            style={{
              backgroundColor: "rgba(255, 255, 255, 0.5)",
              padding: "2rem",
            }}
            sm={{ size: 7, offset: 2 }}
          >
            <div className="text-center">
              <h3>Add Car Details</h3>
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
                  value={carId}
                  onChange={(e) => {
                    setCarId(e.target.value);
                  }}
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
                  value={carBrand}
                  onChange={(e) => {
                    setCarBrand(e.target.value);
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
                  value={carSeater}
                  onChange={(e) => {
                    setCarSeater(e.target.value);
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
                  value={carSegment}
                  onChange={(e) => {
                    setCarSegment(e.target.value);
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
                  value={carFuel}
                  onChange={(e) => {
                    setCarFuel(e.target.value);
                  }}
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="city">
                  <b>City</b>
                </Label>
                <Input
                  type="select"
                  name="city"
                  id="city"
                  placeholder="Enter city"
                  value={city}
                  onChange={(e) => {
                    setCity(e.target.value);
                  }}
                  required
                >
                <option>Select</option>
                        <option>Kolkata</option>
                        <option>Chennai</option>
                        <option>Delhi</option>
                        <option>Pune</option>
                        <option>Mumbai</option>
                        <option>Jaipur</option>
                        <option>Hyderabad</option>
                        <option>Bengaluru</option>
                        <option>Coimbatore</option>
                        </Input>
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
                  value={dailyRate}
                  onChange={(e) => {
                    setDailyRate(e.target.value);
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
                  value={availabilityStatus}
                  onChange={(e) => {
                    setAvailabilityStatus(e.target.value);
                  }}
                  required
                >
                  <option value="1">Yes</option>
                  <option value="0">No</option>
                </Input>
              </FormGroup>
              <FormGroup>
                <Label for="carPhotoPath">
                  <b>Upload Car Photo</b>
                </Label>
                <Input
                  type="file"
                  name="carPhotoPath"
                  id="carPhotoPath"
                  accept=".jpg,.jpeg,.png"
                  onChange={(e) => {
                    setFile(e.target.files[0]);
                  }}
                  required
                />
              </FormGroup>
              <div className="text-center">
                <Button type="submit" color="warning">
                  Submit
                </Button>
              </div>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default AddCar;
