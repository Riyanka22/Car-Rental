import React, { useState } from "react";
import {
  Navbar,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  Collapse,
  NavbarToggler,
} from "reactstrap";
const NavbarHome = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => {
    setIsOpen(!isOpen);
  };
  const [showContact, setShowContact] = useState(false);

  const handleContactClick = () => {
    if (showAbout) {
      setShowAbout(false);
    }
    setShowContact(!showContact);
  };
  const [showAbout, setShowAbout] = useState(false);

  const handleAboutClick = () => {
    if (showContact) {
      setShowContact(false);
    }
    setShowAbout(!showAbout);
  };

  if (showContact || showAbout)
    setTimeout(() => {
      setShowContact(false);
      setShowAbout(false);
    }, 5000);
  return (
    <div>
      <Navbar color="dark" dark expand="md">
        <NavbarBrand href="/">Car Rental</NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="ml-auto" navbar>
            <NavItem>
              <NavLink onClick={handleAboutClick}>About</NavLink>
            </NavItem>
            <NavItem>
              <NavLink onClick={handleContactClick}>Contact</NavLink>
            </NavItem>
          </Nav>
        </Collapse>
      </Navbar>
      {showContact && (
        <div
          style={{
            padding: "2px",

            maxWidth: "300px",
            margin: "0 auto",

            backgroundColor: "DarkCyan",
            boxSizing: "border-box",
            borderRadius: "4px",
            textAlign: "center",
            color: "white",
          }}
        >
          <p style={{ marginBottom: "2px" }}>Contact us at: </p>
          <p style={{ marginBottom: "2px" }}>Email: riyanka@cognizant.com</p>
          <p>Contact : 144-4567-3582-00</p>
        </div>
      )}
      {showAbout && (
        <div
          style={{
            padding: "2px",

            maxWidth: "300px",
            margin: "0 auto",

            backgroundColor: "DarkCyan",
            boxSizing: "border-box",
            borderRadius: "4px",
            textAlign: "center",
            color: "white",
          }}
        >
          <p style={{ marginBottom: "2px" }}>
            Created by <em>Riyanka Dey</em>
          </p>
          <p>
            {" "}
            This application is made for the requirements of Cognizant training
            program.
          </p>
        </div>
      )}
    </div>
  );
};

export default NavbarHome;
