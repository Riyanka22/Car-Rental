import React from "react";

const NotFound = () => {
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
      }}
    >
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
        <h3>404 Page Not Found</h3>
      </div>
    </div>
  );
};

export default NotFound;
